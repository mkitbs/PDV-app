package rs.mkgroup.pdvapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.mkgroup.pdvapp.model.PoreskiIzvestajPK;
import rs.mkgroup.pdvapp.model.PoreskiPodaci;

@Repository
public interface PoreskiPodaciRepository extends JpaRepository<PoreskiPodaci, PoreskiIzvestajPK> {

	Optional<PoreskiPodaci> findById(PoreskiIzvestajPK id);

	@Query(value = "select * from poreski_podaci where type = ?1 and company_name = ?2", nativeQuery = true)
	List<PoreskiPodaci> findByTypeAndCompanyName(Integer type, String companyName);
	
	@Query(value = "select * from poreski_podaci where type = ?1", nativeQuery = true)
	List<PoreskiPodaci> findByType(Integer type);

	@Query(value = "select sum(value) from poreski_podaci where type = ?1 and company_name = ?2 and years_period NOT LIKE '%2025' and years_period NOT LIKE '%2019'", nativeQuery = true)
	Long getSumValueForCompany2019(Integer type, String companyName);
	
	@Query(value = "select sum(value) from poreski_podaci where type = ?1 and company_name = ?2 and years_period NOT LIKE '%2019'", nativeQuery = true)
	Long getSumValueForCompany2020(Integer type, String companyName);

	@Query(value = "select sum(value) from poreski_podaci where type=:type and years_period LIKE %:yearsPeriod", nativeQuery = true)
	Optional<Long> getSumValueForType(@Param("type") Integer type, String yearsPeriod);

	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2025' and years_period not like '%2019' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValuePerCompanyForLastDayOn2020PG(int ordinal, String company);

	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2020' and years_period not like '%2019' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValueToUsePerCompany(int ordinal, String company);

	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2019' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValuePerCompanyForLastDayOn2020PK(int ordinal, String company);
}
