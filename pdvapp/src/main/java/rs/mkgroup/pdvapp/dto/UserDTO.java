package rs.mkgroup.pdvapp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.mkgroup.pdvapp.model.Role;
import rs.mkgroup.pdvapp.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	 Long id;
	 String name;
	 String surname;
	 Long addressId;
	 String email;
	 String password;
	 boolean enabled;
	 boolean nonLocked;
	 List<RoleDTO> roles;
	 String telephone;
	 String bukrs2access;
	

	public UserDTO(Long id, String name, String surname, Long address, String email, String password,
			boolean enabled, boolean nonLocked, String bukrs2access) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.addressId = address;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.nonLocked = nonLocked;
		this.bukrs2access = bukrs2access;
	}
	
	public UserDTO(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.surname = u.getSurname();
		this.email = u.getEmail();
		this.password = u.getPassword();
		this.enabled = u.isEnabled();
		this.nonLocked = u.isNonLocked();
		this.telephone = u.getTelephone();
		this.bukrs2access = u.getBukrs2access();
		
		roles = new ArrayList<>();
		if(u.getRoles() != null) {
			for(Role r : u.getRoles()) {
				roles.add(new RoleDTO(r.getName()));
			}
		}
	}

	
	
}