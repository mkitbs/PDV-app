package rs.mkgroup.pdvapp.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import rs.mkgroup.pdvapp.model.PDV1;
import rs.mkgroup.pdvapp.model.PDV2;

@Data
public class PDVDTO {

	Long id;
	String name;
	Map<String, Long> listOfValues1 = new HashMap<String, Long>();
	Map<String, Long> listOfValues2 = new HashMap<String, Long>();

	String bukrs;
	String lastChangedBy;
	String year;
	String lastChangedOn;

	public PDVDTO(PDV1 pdv1) {
		this.id = pdv1.getId();
		this.name = pdv1.getName();
		this.lastChangedOn = pdv1.getLastChangedOn();
		this.bukrs = pdv1.getBukrs();
		this.listOfValues1 = pdv1.getListOfValues();
		this.lastChangedBy = pdv1.getLast_changed_by();
		this.year = pdv1.getYear();
	}

	public PDVDTO(PDV2 pdv2) {
		this.id = pdv2.getId();
		this.name = pdv2.getName();
		this.lastChangedOn = pdv2.getLastChangedOn();
		this.bukrs = pdv2.getBukrs();
		this.listOfValues2 = pdv2.getListOfValues();
		this.lastChangedBy = pdv2.getLast_changed_by();
		this.year = pdv2.getYear();
	}
	
	public PDVDTO() {

	}

}
