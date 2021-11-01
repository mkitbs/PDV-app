package rs.mkgroup.pdvapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.mkgroup.pdvapp.model.PDV1;

@Repository
public interface PDV1Repository extends JpaRepository<PDV1, Long> {
	
	List<PDV1> findByBukrsAndYear(String bukrs, String year);

}
