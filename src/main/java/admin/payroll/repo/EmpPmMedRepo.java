package admin.payroll.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.EmpPmMedEntity;

@Repository
public interface EmpPmMedRepo extends JpaRepository<EmpPmMedEntity, Integer> {

	@Query("From EmpPmMedEntity where earningDeduction=:earningDeduction and empNo=:empNo and payPeriod=:payPeriod")
	List<EmpPmMedEntity> getEmployeeWiseData(@Param("empNo") String code, @Param("earningDeduction") String edCode,
			@Param("payPeriod") String payPeriod);

	EmpPmMedEntity findByEmpNoAndEarningDeductionAndPayPeriodAndRefNo(String empNo, String earningDeduction,
			String payPeriod, String refNo);

	@Modifying
	@Transactional
	@Query(value = "delete from EmpPmMedEntity e where e.empNo=:empNo  AND e.earningDeduction=:earningDeduction AND e.payPeriod=:payPeriod")
	int deletePmMad(String empNo, String earningDeduction, String payPeriod);

}
