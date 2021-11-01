package rs.mkgroup.pdvapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.mkgroup.pdvapp.model.Field;

@Repository
public interface FieldRepository  extends JpaRepository<Field, Long>{
	
	Field findByInidAndBukrsAndYear(Long inid, String bukrs, String year);
	
	List<Field> findByYearAndBukrs(String year, String bukrs);
	
	@Query(value = "SELECT sum(value) FROM field where year=?1 and inid=?2", nativeQuery = true)
	Optional<Long> getSumValue(String year, Long inid);
}
