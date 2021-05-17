package admin.payroll.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmPraEntity;

@Repository
public interface PmPraRepo extends JpaRepository<PmPraEntity, String> {

	@Query("FROM PmPraEntity where earningDeduction=:earningDeduction and empNo=:empNo")
	List<PmPraEntity> getPayRatesDatas(@Param("earningDeduction") String earningDeduction, @Param("empNo") String empNo,
			Sort sort);

	@Query("FROM PmPraEntity where earningDeduction=:earningDeduction and empNo=:empNo and fromDate=:fromDate")
	PmPraEntity getDataBasedOnEmpIdEdCodeFromDate(@Param("earningDeduction") String earningDeduction,
			@Param("empNo") String empNo, @Param("fromDate") Date fromDate);

	PmPraEntity findByEmpNoAndEarningDeductionAndFromDate(String empId, String earningDeduction, Date fromDate);

	List<PmPraEntity> findByFromDate(Date fromDate);

	@Query("From PmPraEntity where empNo=:empNo")
	List<PmPraEntity> findByEmpNo(@Param("empNo") String empNo);

	@Query("From PmPraEntity where earningDeduction=:earningDeduction")
	List<PmPraEntity> findByEarningDeduction(@Param("earningDeduction") String earningDeduction);

	// PmPraEntity getDataBasedOnEmpIdEdCodeFromDate1(String empId, String
	// earningDeduction, LocalDateTime parse);

	@Query("FROM PmPraEntity Where empNo = :empId AND earningDeduction= :edCode AND toDate is null")
	Optional<PmPraEntity> checkPayRateExist(@Param("empId") String empId, @Param("edCode") String edCode);

	@Modifying
	@Transactional
	@Query(value = "delete from PmPraEntity e where e.empNo=:empNo  AND e.earningDeduction=:earningDeduction AND e.fromDate=:fromDate")
	int deletePmPara(String empNo, String earningDeduction, Date fromDate);

}
