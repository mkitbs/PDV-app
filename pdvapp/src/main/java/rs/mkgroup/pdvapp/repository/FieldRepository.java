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
	
	@Query(value = "SELECT sum(value) FROM field where year=?1 and inid=?2 and (bukrs=\"1000\" or bukrs=\"AGUN\" or bukrs=\"5100\" or bukrs=\"4037\" or bukrs=\"CARV\" or bukrs=\"CARH\" or bukrs=\"GRAN\" or bukrs=\"MKC\" \r\n"
			+ "or bukrs=\"2000\" or bukrs=\"2300\" or bukrs=\"SUN\" or bukrs=\"MKAG\" or bukrs=\"5200\" or bukrs=\"PIKB\" or bukrs=\"1100\" or bukrs=\"4026\" or bukrs=\"4024\" or bukrs=\"4029\" or bukrs=\"VPD1\" or bukrs=\"VPD3\" \r\n"
			+ "or bukrs=\"VPRA\" or bukrs=\"VPTO\" or bukrs=\"VPKO\" or bukrs=\"MKH\" or bukrs=\"AIKB\" or bukrs=\"4046\" or bukrs=\"4044\" or bukrs=\"4043\")", nativeQuery = true)
	Optional<Long> getSumValue2019(String year, Long inid);
	
	@Query(value = "SELECT sum(value) FROM field where year=?1 and inid=?2 and (bukrs=\"1000\" or bukrs=\"AGUN\" or bukrs=\"5100\" or bukrs=\"4037\" or bukrs=\"CARV\" or bukrs=\"CARH\" or bukrs=\"CARS\" or bukrs=\"CARV2\" \r\n"
			+ "or bukrs=\"GRAN\" or bukrs=\"MKC\" or bukrs=\"2000\" or bukrs=\"2300\" or bukrs=\"SUN\" or bukrs=\"MKAG\" or bukrs=\"5200\" or bukrs=\"PIKB\" or bukrs=\"1100\" or bukrs=\"4026\" or bukrs=\"4024\" or bukrs=\"4029\" \r\n"
			+ "or bukrs=\"VPD1\" or bukrs=\"VPD3\" or bukrs=\"VPRA\" or bukrs=\"4043\" or bukrs=\"VPTO\" or bukrs=\"VPKO\" or bukrs=\"MKH\" or bukrs=\"AIKB\" or bukrs=\"4046\" or bukrs=\"4044\")", nativeQuery = true)
	Optional<Long> getSumValue2020(String year, Long inid);

	@Query(value = "SELECT sum(value) FROM field where year=?1 and inid=?2 and (bukrs=\"4037\" or bukrs=\"CARS\" or bukrs=\"MKC\" or bukrs=\"2000\" or bukrs=\"2300\" or bukrs=\"SUN\" or bukrs=\"AGUN\" or bukrs=\"MKAG\" \r\n"
			+ "or bukrs=\"5200\" or bukrs=\"PIKB\" or bukrs=\"4026\" or bukrs=\"4024\" or bukrs=\"4029\" or bukrs=\"VPD1\" or bukrs=\"VPD3\" or bukrs=\"VPRA\" or bukrs=\"VPTO\" or bukrs=\"VPKO\" or bukrs=\"ADE\" or bukrs=\"IVI\" \r\n"
			+ "or bukrs=\"MKH\" or bukrs=\"4046\" or bukrs=\"4044\")", nativeQuery = true)
	Optional<Long> getSumValue2021(String year, Long inid);
}
