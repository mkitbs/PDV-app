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

	@Query(value = "select sum(value) from poreski_podaci where type=:type and years_period LIKE %:yearsPeriod and company_name != \"Adeyemy\" and company_name != \"Carnex Stočarstvo doo Bečej\" \r\n"
			+ "and company_name != \"Carnex Veterina Veterinarska služba doo Bečej\" and company_name != \"Ivicom\" and company_name != \"MK Aviation Services\" and company_name != \"MK Fintel Wind Develop.\" \r\n"
			+ "and company_name != \"Vetropark Kula - pridruženo\"", nativeQuery = true)
	Optional<Long> getSumValueForType2019(@Param("type") Integer type, String yearsPeriod);
	
	@Query(value = "select sum(value) from poreski_podaci where type=:type and years_period LIKE %:yearsPeriod and company_name != \"Adeyemy\" and company_name != \"Ivicom\" \r\n"
			+ "and company_name != \"MK Aviation Services\" and company_name != \"MK Fintel Wind Develop.\" and company_name != \"Vetropark Kula - pridruženo\"", nativeQuery = true)
	Optional<Long> getSumValueForType2020(@Param("type") Integer type, String yearsPeriod);
	
	@Query(value = "select sum(value) from poreski_podaci where type=:type and years_period LIKE %:yearsPeriod and company_name != \"Agroglobe d.o.o., Novi Sad\" and company_name != \"AIK banka a.d.\" \r\n"
			+ "and company_name != \"Banat Seme d.o.o., Zrenjanin\" and company_name != \"Carnex Holding\" and company_name != \"Carnex Veterina Veterinarska služba doo Bečej\" \r\n"
			+ "and company_name != \"Granexport ad, Pančevo\" and company_name != \"MK Aviation Services\" and company_name != \"MK Fintel Wind Develop.\"\r\n"
			+ " and company_name != \"Sojaprotein\" and company_name != \"Veterinarska stanica Carnex Farm\" and company_name != \"Vetropark Kula - pridruženo\" and company_name != \"Žito Bačka doo, Kula\"", nativeQuery = true)
	Optional<Long> getSumValueForType2021(@Param("type") Integer type, String yearsPeriod);

	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2025' and years_period not like '%2019' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValuePerCompanyForLastDayOn2020PG(int ordinal, String company);
	
	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2026' and years_period not like '%2020' and years_period not like '%2019' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValuePerCompanyForLastDayOn2021PG(int ordinal, String company);

	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2020' and years_period not like '%2019' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValueToUsePerCompany(int ordinal, String company);
	
	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2021' and years_period not like '%2020' and years_period not like '%2019' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValueToUsePerCompany2021(int ordinal, String company);

	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2019' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValuePerCompanyForLastDayOn2020PK(int ordinal, String company);
	
	@Query(value = "SELECT sum(value) FROM poreski_podaci where type=?1 and years_period not like '%2019' and years_period not like '%2020' and company_name=?2", nativeQuery = true)
	Optional<Long> getSumValuePerCompanyForLastDayOn2021PK(int ordinal, String company);
}
