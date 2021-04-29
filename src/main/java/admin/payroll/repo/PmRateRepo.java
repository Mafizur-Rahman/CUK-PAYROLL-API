package admin.payroll.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmRateEntity;

@Repository
public interface PmRateRepo extends JpaRepository<PmRateEntity, String> {

	@Query("From PmRateEntity where earningDeduction=:code")
	List<PmRateEntity> findAllById(@Param("code") String code);

	@Query("From PmRateEntity where earningDeduction=:earningDeduction and toDate=null")
	PmRateEntity getByEarningDeduction(@Param("earningDeduction") String earningDeduction);
	
	@Query("From PmRateEntity where isActive='Y'")
	List<PmRateEntity> findAllaActive(Sort sort);

}
