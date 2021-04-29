package admin.payroll.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import admin.payroll.entity.PmBonusMstEntity;

@Repository
public interface PmBonusMstReRepo extends JpaRepository<PmBonusMstEntity, String> {

//	@Query("From PmBankEntity where bankName=:bankName OR ifscCode=:ifscCode")
//	List<PmBankEntity> findByBankNameAndIfscCode(@Param("bankName") String bankName, @Param("ifscCode") String ifscCode);
//
	@Query("From PmBonusMstEntity where EMPID=:empId")
	List<PmBonusMstEntity> getPmBonusMstDataByEmpId(@Param("empId") String empId);
	
	
	@Procedure(name="SPBONUSPREPROCESS")
	void preBonus(@Param("PCLASS") String PCLASS,@Param("FYR") String FYR,@Param("AMT") Double AMT);
	
//	@Query("SELECT E.EMPID,E.NAME,E.DESIG,E.PAYRATE,E.BONUS FROM PmBonusMstEntity E where CLASS=:classes and FINANCIAL_YEAR=:financialYear")
//	List<PmBonusMstEntity> bonusReportsByClassAndFyr(@Param("classes") String classes,@Param("financialYear") String financialYear);
//@Query(" EMPID, NAME, DESIG, PAYRATE, BONUS FROM PmBonusMstEntity ")
	
	
	//@Query("from PmBonusMstEntity e where e.CLASS=:classes AND e.FINANCIAL_YEAR=:financialYear")
	//List<PmBonusMstEntity> bonusReportsByClassAndFyr(@Param("classes") String classes,@Param("financialYear") String financialYear);
	
	//@Query("From PmBonusMstEntity where CLASS=:classes")
	
	
	//create the query to select PmBonusMstEntity by class and financialYear
	@Query(value="select * from PM_BONUS_MST a where a.CLASS= :classes and a.FINANCIAL_YEAR= :financialYear" , nativeQuery=true)
	List<PmBonusMstEntity> bonusReportsByClassAndFyr(@Param("classes") String classes,@Param("financialYear") String financialYear);

	@Query("FROM PmBonusMstEntity WHERE classes = :classes AND financialYear = :financialYear AND lockStatus = 'Y'")
	List<PmBonusMstEntity> checkLockStatus(@Param("classes") String classes, @Param("financialYear") String financialYear);
	
	@Procedure(name = "SPBONUSREVESRSE")
	void reverseBonusProcess(@Param("PCLASS") String PCLASS,@Param("FYR") String FYR);
}
