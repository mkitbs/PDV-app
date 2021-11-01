package rs.mkgroup.pdvapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TestModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String test;
	
}
