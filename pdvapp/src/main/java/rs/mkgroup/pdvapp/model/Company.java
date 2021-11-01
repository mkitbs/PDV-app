package rs.mkgroup.pdvapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Company {
	
	@Id
	String companyCode;
	String companyName;

}
