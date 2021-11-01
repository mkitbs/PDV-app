package rs.mkgroup.pdvapp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PoreskiIzvestajPK implements Serializable {

	private static final long serialVersionUID = 5515221623687294299L;

	protected String companyName;
	protected String yearsPeriod;
	protected PoreskiIzvestajType type;

}
