package rs.mkgroup.pdvapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long inid;
	
	private Long value;
	
	private String year;
	
	private String bukrs;
	
	private String lastChangedBy;
	
	private String lastChangedOn;


}
