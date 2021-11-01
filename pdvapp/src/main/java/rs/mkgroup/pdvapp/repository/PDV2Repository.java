package rs.mkgroup.pdvapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.mkgroup.pdvapp.model.PDV1;
import rs.mkgroup.pdvapp.model.PDV2;

@Repository
public interface PDV2Repository extends JpaRepository<PDV2, Long> {
	
	List<PDV2> findByBukrsAndYear(String bukrs, String years);

}