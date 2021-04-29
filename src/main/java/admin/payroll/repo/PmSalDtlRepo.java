package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmSalDtlEntity;

@Repository
public interface PmSalDtlRepo extends JpaRepository<PmSalDtlEntity, String>{
	
	@Query("select payPeriod from PmSalDtlEntity where payPeriod=: payperiod")
	String checkMonthandYearDataPresentInSalaryDetail(@Param("payPeriod") String payperiod);

}
