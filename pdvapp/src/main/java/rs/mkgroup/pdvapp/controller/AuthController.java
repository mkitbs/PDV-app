package rs.mkgroup.pdvapp.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.mkgroup.pdvapp.config.InvalidJWTokenException;
import rs.mkgroup.pdvapp.dto.LoginRequestDTO;
import rs.mkgroup.pdvapp.dto.LoginResponseDTO;
import rs.mkgroup.pdvapp.dto.ProfileDTO;
import rs.mkgroup.pdvapp.dto.RoleDTO;
import rs.mkgroup.pdvapp.dto.SignupRequestDTO;
import rs.mkgroup.pdvapp.dto.UserDTO;
import rs.mkgroup.pdvapp.model.Role;
import rs.mkgroup.pdvapp.model.RoleName;
import rs.mkgroup.pdvapp.model.User;
import rs.mkgroup.pdvapp.repository.RoleRepository;
import rs.mkgroup.pdvapp.repository.UserRepository;
import rs.mkgroup.pdvapp.security.JwtTokenProvider;
import rs.mkgroup.pdvapp.service.CheckTokenAndPermissions;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenProvider jwtProvider;

    
    @Autowired
    CheckTokenAndPermissions permissions;
    
    
    public static RoleName roleName;
   
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    int attemps = 0;
    String myEmail = "";
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
    	InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip = (localhost.getHostAddress()).trim();
    	try {
    		
    		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	                loginRequest.getEmail(),
	                loginRequest.getPassword()
	         );
    		
	         
    		Authentication authentication = authenticationManager.authenticate(
	            token
	        );
    		
    		String email = authentication.getName();
    		List<String> authorities = authentication.getAuthorities().stream()
    				.map(GrantedAuthority::getAuthority)
    				.collect(Collectors.toList());
    		
    		String jwt = jwtProvider.generateToken(authentication);
    		ProfileDTO profile = new ProfileDTO(email, authorities, true);
    		User us = userRepository.findByEmail(loginRequest.getEmail()).get();
    		logger.info("ID: {} | PRN4SI | success", us.getId() );
	        return new ResponseEntity<LoginResponseDTO>(new LoginResponseDTO(profile, jwt), HttpStatus.OK);
		} catch (AuthenticationException e) {

			logger.error("userIP: {} | PRN4SI | failed", ip);
			System.out.println(e.toString());

			logger.error("PRN4SI | fail");

			return new ResponseEntity<String>("Not logged! " + e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
    }
    
    @GetMapping("/getLogged")
    public ResponseEntity<?> getLogged(Principal principal) {
    	
		User logged = userRepository.findCurrentUser()
									.orElseThrow(() -> new InvalidJWTokenException("Invalid JWT Token"));
		
		return new ResponseEntity<UserDTO>(new UserDTO(logged), HttpStatus.OK);

    }
    
    @GetMapping("/check/{token}") 
    public ResponseEntity<?> checkToken(@PathVariable String token) throws InvalidJWTokenException{
    	if(permissions.validateJwtToken(token)) {
    		String s = "";
    		for(GrantedAuthority str:jwtProvider.getUserPrincipal(token).getAuthorities()) {
    			s += str.getAuthority() + '|';
    		}
    		return new ResponseEntity<String>(s,HttpStatus.OK);
    	} return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
    @GetMapping("/check/{token}/username")
    public ResponseEntity<?> getUsername(@PathVariable String token) throws InvalidJWTokenException{
    	if(permissions.validateJwtToken(token)) {
    		return new ResponseEntity<String>(jwtProvider.getUserPrincipal(token).getUsername(), HttpStatus.OK);
    	}
    	return null;
    }
    
    @GetMapping("/signout") 
    public ResponseEntity<?> signout() {
    	System.out.println("LOGOUT");
    	SecurityContextHolder.getContext().setAuthentication(null);
    	SecurityContextHolder.clearContext();
    	//JwtBlackList.lista.add(request.getHeader("Authorization").substring(7, request.getHeader("Authorization").length()));
		return null;
    }

    
    @RequestMapping(value="/signup", method= RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDTO signUpRequest) throws MessagingException {
       
       if(userRepository.existsByEmail(signUpRequest.getEmail())) {
          return new ResponseEntity<>("Fail -> Email is already in use!",
                   HttpStatus.BAD_REQUEST);
        }

       if(signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
				try {
					   Set<Role> roles = new HashSet<Role>();
					   
				       User user = new User(signUpRequest, encoder.encode(signUpRequest.getPassword()), roles);
				        
				        user.setEnabled(true);
				        
				        user.setBukrs2access(signUpRequest.getBukrs2access());
				       
				        user = userRepository.save(user);
				        UserDTO us = new UserDTO(user);
				        
				        
						logger.info("user: {} | R3USER | success", user.getId());
				        return new ResponseEntity<UserDTO>(us, HttpStatus.CREATED);
						   
					   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       }else {
	    	   return new ResponseEntity<>("Fail -> Passwords don't match!",
	                   HttpStatus.BAD_REQUEST);
	       }
	return null;
    }    
    
    @GetMapping("/getAllRoles")
    public ResponseEntity<?> getAllRoles(){
    	List<RoleDTO> retVal = new ArrayList<RoleDTO>();
    	List<Role> roles = roleRepository.findAll();
    	for(Role r : roles) {
    		retVal.add(new RoleDTO(r.getId(), r.getName()));
    	}
    	return new ResponseEntity<List<RoleDTO>>(retVal, HttpStatus.OK);
    }
    
    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
    	User u = userRepository.findByEmail(email).get();
    	return new ResponseEntity<String>(u.getName()+" "+u.getSurname(), HttpStatus.OK);
    	
    }
    
    
}