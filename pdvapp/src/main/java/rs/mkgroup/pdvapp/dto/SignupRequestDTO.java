package rs.mkgroup.pdvapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {

	private String name;

	private String surname;
	
	private List<RoleDTO> roles;

	private String telephone;

	private String email;
	
	private String password;
	
	private String bukrs2access;

	private String confirmPassword;
	
	
}
