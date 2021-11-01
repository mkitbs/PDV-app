package rs.mkgroup.pdvapp.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ExcelDTO {
	String bukrs;
	String companyName;
	Map<String, Long> pdv1;
	Map<String, Long> pdv2;
	Map<String, Long> listSumValues = new HashMap<String, Long>();

}
