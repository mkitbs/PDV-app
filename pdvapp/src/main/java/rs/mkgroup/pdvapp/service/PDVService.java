package rs.mkgroup.pdvapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.mkgroup.pdvapp.exception.types.CompanyDoesNotExistsException;
import rs.mkgroup.pdvapp.model.Company;
import rs.mkgroup.pdvapp.model.PoreskiIzvestajType;
import rs.mkgroup.pdvapp.model.PoreskiPodaci;
import rs.mkgroup.pdvapp.model.User;
import rs.mkgroup.pdvapp.repository.CompanyRepository;
import rs.mkgroup.pdvapp.repository.PoreskiPodaciRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PDVService {
	
	private final PoreskiPodaciRepository poreskiPodaciRepository;
	private final CompanyRepository companyRepository;
	
	/**
	 * If entry to add already exists in database, it will get updated, otherwise
	 * new entry will be persisted. Since primary key is composite and consists
	 * of yearsPeriod and companyName, this check is required.
	 * @param operationUser logged user, fetched from JWT token
	 * @param poreskiGubiciToAdd new entry to add
	 */
	public void createNewPoreskiPodaci(User operationUser, List<PoreskiPodaci> poreskiGubiciToAdd) {
		
		poreskiGubiciToAdd.forEach(poreskiToAdd -> {
			
			Optional<PoreskiPodaci> found = poreskiPodaciRepository
					.findById(poreskiToAdd.getId());

			if(!found.isPresent()) {
							
				addNewPoreskiGubici(operationUser, poreskiToAdd);
			
			} else {
			
				updateExistingPoreskiGubici(found.get(), operationUser, poreskiToAdd);
			
			}
			
		});
		
		
		
	}
	
	@Transactional
	private PoreskiPodaci updateExistingPoreskiGubici(PoreskiPodaci found, User operationUser, PoreskiPodaci poreskiGubiciToAdd) {
		log.info("Updating existing PoreskiGubici;User: {}, Date: {}", operationUser.getId(), new Date().toString());
		
		found.setValue(poreskiGubiciToAdd.getValue());
		found.setLastChangedBy(operationUser.getName() + " " + operationUser.getSurname());
		
		return poreskiPodaciRepository.save(found);
		
	}
	
	@Transactional
	private PoreskiPodaci addNewPoreskiGubici(User operationUser, PoreskiPodaci poreskiGubiciToAdd) {
		log.info("Adding new PoreskiGubici entry;User: {}, Date: {}", operationUser.getId(), new Date().toString());

		poreskiGubiciToAdd.setLastChangedBy(operationUser.getName() + " " + operationUser.getSurname());
		
		Company companyToAdd = companyRepository.findCompanyByCompanyCode(poreskiGubiciToAdd.getId().getCompanyName())
                                                .orElseThrow(() -> new CompanyDoesNotExistsException(poreskiGubiciToAdd.getId().getCompanyName()));
		
		poreskiGubiciToAdd.getId().setCompanyName(companyToAdd.getCompanyName()); //receiving bukrs and setting full name

		return poreskiPodaciRepository.save(poreskiGubiciToAdd);
	}
	
	public List<PoreskiPodaci> getPoreskiPodaci(PoreskiIzvestajType type, String bukrs) {
		
		Company company = companyRepository.findCompanyByCompanyCode(bukrs)
										   .orElseThrow(() -> new CompanyDoesNotExistsException(bukrs));
		
		log.info("Fetching PoreskiPodaci of type: {} and company {}", type.toString(), company.getCompanyName());
		
		List<PoreskiPodaci> returningList = poreskiPodaciRepository
						.findByTypeAndCompanyName(type.ordinal(), company.getCompanyName());
		
		return returningList;
		
	}

}









