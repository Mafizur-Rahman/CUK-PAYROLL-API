package admin.payroll.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
