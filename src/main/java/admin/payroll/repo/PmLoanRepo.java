package admin.payroll.repo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmLoanEntity;

@Repository
public interface PmLoanRepo extends JpaRepository<PmLoanEntity, Integer> {

	@Query("From PmLoanEntity where earningDeduction=:earningDeduction and empNo=:empNo")
	List<PmLoanEntity> getInstallmenteRecoveries(@Param("earningDeduction") String edCode,
			@Param("empNo") String empId);

	List<PmLoanEntity> findByEmpNoAndEarningDeduction(String empNo, String earningDeduction);

	PmLoanEntity findByEmpNoAndEarningDeductionAndRefNoAndSancDateAndStartYearMm(String empNo, String earningDeduction,
			String refNo, Date sancDate, String startYearMm);

	@Modifying
	@Transactional
	@Query(value = "delete from PmLoanEntity e where e.empNo=:empNo  AND e.earningDeduction=:earningDeduction AND e.refNo=:refNo AND e.sancDate=:sancDate")
	int deletePmLoan(String empNo, String earningDeduction, String refNo, Date sancDate);

//	@Query("From PmLoanEntity e where e.empNo=:empNo  AND e.earningDeduction=:earningDeduction AND e.refNo=:refNo AND e.sancDate=:sancDate ")
//	PmLoanEntity deletePmLoan(@Param("empNo") String empNo, @Param("earningDeduction") String earningDeduction,
//			@Param("refNo") String refNo, @Param("sancDate") LocalDate sancDate);

}
