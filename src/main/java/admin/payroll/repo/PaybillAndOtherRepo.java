package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PaybillAndOtherEntity;
import admin.payroll.entity.PaybillEntity;
import admin.payroll.models.ClassModel;
import admin.payroll.models.PunchingMediaModel;
import admin.payroll.models.SSLICModel;

@Repository
public interface PaybillAndOtherRepo extends JpaRepository<PaybillAndOtherEntity, String> {

//	@Query("Select desigCode From PmDesigEntity")
//	List<String> getDesignatedCodeFromPmDesig();

	@Query("From PaybillEntity where classs=:classs")
	List<PaybillEntity> findAllByClass(@Param("classs") String classs);

	@Query("from PaybillAndOtherEntity where iTax != null and iTax != 0.0 and iTax != 0")
	List<PaybillAndOtherEntity> getByItax();

	@Query("from PaybillAndOtherEntity where iTax != null and iTax != 0.0 and iTax != 0 and classs=:classs")
	List<PaybillAndOtherEntity> getItaxByClass(@Param("classs") String classs);

	@Query("from PaybillEntity where npsTotal != null and iTax != 0.0 and iTax != 0")
	List<PaybillAndOtherEntity> getNps();

	@Query("from PaybillEntity where gis != null and gis != 0.0 and gis != 0")
	List<PaybillAndOtherEntity> getGroupInsurance();

	@Query("from PaybillAndOtherEntity where ssLic != null and ssLic != 0.0 and ssLic != 0 ")
	List<PaybillAndOtherEntity> getSSLic();

	@Query("from PaybillAndOtherEntity where ssLic != null and ssLic != 0.0 and ssLic != 0  and classs = :classs")
	List<PaybillAndOtherEntity> getSSLicByClass(@Param("classs") String classs);

//	@Query("select new admin.payroll.models.SSLICModel(a.empId,a.name,c.desigShortDesc,b.refNo,b.amt) FROM EmpMastEntity a,PmSalDtlEntity b,PmDesigEntity c where b.earningDeduction='RC000' and a.empId=b.empNo and a.desig=c.desigCode")	
//	List<SSLICModel> getPli();
//	

	@Query("from PaybillAndOtherEntity where cghs != null and cghs != 0.0 and cghs != 0 ")
	List<PaybillAndOtherEntity> getCghsRecovery();

	@Query("from PaybillAndOtherEntity where misRec != null and misRec != 0.0 and misRec != 0 ")
	List<PaybillAndOtherEntity> getMiscRecovery();

	@Query("from PaybillAndOtherEntity where pliCess != null and pliCess != 0.0 and pliCess != 0")
	List<PaybillAndOtherEntity> getPli();

	@Query("from PaybillAndOtherEntity where pliCess != null and pliCess != 0.0 and pliCess != 0 and classs=:classs")
	List<PaybillAndOtherEntity> getPliByClass(@Param("classs") String classs);

	@Query("from PaybillAndOtherEntity where cghs = null OR cghs = 0.0 OR cghs = 0 ")
	List<PaybillAndOtherEntity> getNonCghsRecovery();

	@Query("from PaybillAndOtherEntity where cghs != null and cghs != 0.0 and cghs != 0 and classs=:classs")
	List<PaybillAndOtherEntity> getCghsRecoveryByClass(@Param("classs") String classs);

	@Query("from PaybillAndOtherEntity where gis != null and gis != 0.0 and gis != 0 ")
	List<PaybillAndOtherEntity> getGis();

	@Query("from PaybillAndOtherEntity where gis != null and gis != 0.0 and gis != 0 and classs=:classs")
	List<PaybillAndOtherEntity> getGisByClass(@Param("classs") String classs);

	@Query("from PaybillAndOtherEntity where misRec != null and misRec != 0.0 and misRec != 0 ")
	List<PaybillAndOtherEntity> getMiscRecoverySchedule();

	@Query("from PaybillAndOtherEntity where misRec != null and misRec != 0.0 and misRec != 0 and classs=:classs")
	List<PaybillAndOtherEntity> getMiscRecoveryScheduleByClass(@Param("classs") String classs);

	@Query(value = "SELECT NEW admin.payroll.models.PunchingMediaModel(SUM(grossPay), SUM(totDeduction), SUM(netPay)) FROM PmSalHdrEntity")
	PunchingMediaModel getPunchingMedia();

}
