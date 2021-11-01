package rs.mkgroup.pdvapp.dto;

import lombok.Data;
import rs.mkgroup.pdvapp.model.Company;

@Data
public class CompanyDTO {
	String companyName;
	String companyCode;
	
	public CompanyDTO(Company c) {
		this.companyCode = c.getCompanyCode();
		this.companyName = c.getCompanyName();
	}

}
