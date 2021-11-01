package rs.mkgroup.pdvapp.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class PoreskiPodaci extends Timestamp implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PoreskiIzvestajPK id;
	
	private Long value;
	
	private String lastChangedBy;
	
}