package rs.mkgroup.pdvapp.dto;

import java.util.List;

import lombok.Data;
import rs.mkgroup.pdvapp.model.Field;

@Data
public class TroskoviDTO {
	List<Field> listaFieldova;
	String bukrs;
	String companyName;
}
