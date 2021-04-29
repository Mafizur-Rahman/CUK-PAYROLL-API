package admin.payroll.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.EmpPmMedEntity;
import admin.payroll.entity.PmRedEntity;

@Repository
public interface PmRedRepo extends JpaRepository<PmRedEntity, Integer> {

	@Query("From PmRedEntity where earningDeduction=:earningDeduction and empNo=:empNo")
	List<PmRedEntity> getRegularRecoveries(@Param("empNo") String empId, @Param("earningDeduction") String edCode, Sort sort);

	PmRedEntity findByEmpNoAndEarningDeductionAndRefNoAndStartYearMm(String empNo, String earningDeduction,
			String refNos, String startYearMm);

}
