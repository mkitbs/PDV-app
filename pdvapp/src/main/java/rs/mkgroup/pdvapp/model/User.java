package rs.mkgroup.pdvapp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import rs.mkgroup.pdvapp.dto.SignupRequestDTO;

@Entity
@Table(name = "users")
@NamedEntityGraph(name = "User.Roles.Permissions", 
	attributeNodes = @NamedAttributeNode(value = "roles", subgraph = "permissions"), 
	subgraphs = @NamedSubgraph(name = "permissions", attributeNodes = @NamedAttributeNode("permissions")))
@Inheritance(
	    strategy = InheritanceType.JOINED
	)
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String surname;	
	
	private String email;
	
	private String password;
	
	private boolean enabled;
	
	private boolean nonLocked;
	
	private String bukrs2access;
	
	protected Date dateOfBirth;
	
	protected String telephone;
	
   
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User() {
		this.enabled = false;
	}

	public User(String name, String surname, Long address, String email, 
				String password, Set<Role> roles) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		//this.enabled = true;
		//this.nonLocked = true;
		this.roles = roles;
	}

	public User(Long id, String name, String surname, Long addressId, String email, String password,
			boolean enabled, boolean nonLocked, Date dateOfBirth, String telephone) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.nonLocked = nonLocked;
		this.dateOfBirth = dateOfBirth;
		this.telephone = telephone;
	}

	public User(String email, String password, Set<Role> roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.enabled = true;
		this.nonLocked = true;
	}
	
	public User(SignupRequestDTO sr, String pass, Set<Role> roles) {
		this.email = sr.getEmail();
		this.password = pass;
		this.roles = roles;
		this.name = sr.getName();
		this.surname = sr.getSurname();
		this.telephone = sr.getTelephone();
		this.enabled = true;
		this.nonLocked = true;
	}
}
