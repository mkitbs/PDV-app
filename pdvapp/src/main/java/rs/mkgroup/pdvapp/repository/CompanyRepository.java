package rs.mkgroup.pdvapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.mkgroup.pdvapp.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
	
	Optional<Company> findCompanyByCompanyCode(String companyCode);

}
