package rs.mkgroup.pdvapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.mkgroup.pdvapp.dto.RoleDTO;
import rs.mkgroup.pdvapp.dto.UserDTO;
import rs.mkgroup.pdvapp.model.Role;
import rs.mkgroup.pdvapp.model.User;
import rs.mkgroup.pdvapp.repository.RoleRepository;
import rs.mkgroup.pdvapp.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepo;
	
	@GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAll(){
    	List<UserDTO> retVal = new ArrayList<UserDTO>();
    	List<User> users = userRepository.findAll();
    	for(User u : users) {
    		UserDTO usr = new UserDTO(u);
    		retVal.add(usr);
    	}
    	return new ResponseEntity<List<UserDTO>>(retVal, HttpStatus.OK);
    }
	
	@GetMapping("/userActivation/{id}/{str}")
	public ResponseEntity<?> userActivation(@PathVariable Long id, @PathVariable String str){
		User u = userRepository.getOne(id);
		if(u == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			if(str.equals("true")) {
				u.setEnabled(true);
				userRepository.save(u);
				return new ResponseEntity<>(HttpStatus.OK);
			}else if(str.equals("false")) {
				u.setEnabled(false);
				userRepository.save(u);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@GetMapping("/getNumberOfUsers")
    public ResponseEntity<Integer> getNumberOfUsers(){
		List<User> retVal = userRepository.findAll();
    	return new ResponseEntity<Integer>(retVal.size(), HttpStatus.OK);
    }
	
	@GetMapping("/getRoles")
	public ResponseEntity<?> getRoles(){
		List<RoleDTO> retVal = new ArrayList<RoleDTO>();
		List<Role> roles = roleRepo.findAll();
		for(Role r : roles) {
			RoleDTO rdto = new RoleDTO(r.getName());
			retVal.add(rdto);
		}
		return new ResponseEntity<List<RoleDTO>>(retVal,HttpStatus.OK);
	}
	

}
