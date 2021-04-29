package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PaybillEntity;
import admin.payroll.models.SSLICModel;



@Repository
public interface PaybillRepo extends JpaRepository<PaybillEntity, String> {

//	@Query("Select desigCode From PmDesigEntity")
//	List<String> getDesignatedCodeFromPmDesig();

	@Query("From PaybillEntity where classs=:classs")
	List<PaybillEntity> findAllByClass(@Param("classs") String classs);
	
	@Query("from PaybillEntity where iTax != null and iTax != 0.0 and iTax != 0")
	List<PaybillEntity> getByItax();

	@Query("from PaybillEntity where npsTotal != null and iTax != 0.0 and iTax != 0")
    List<PaybillEntity> getNps();
	
	@Query("from PaybillEntity where gis != null and gis != 0.0 and gis != 0")
	List<PaybillEntity> getGroupInsurance();
	
	@Query("from PaybillEntity where ssLic != null and ssLic != 0.0 and ssLic != 0 ")
	// multiple sslic are there for a particular employee
//	@Query("select new admin.payroll.models.SSLICModel(a.empId,a.name,c.desigShortDesc,b.refNo,b.amt) FROM EmpMastEntity a,PmSalDtlEntity b,PmDesigEntity c where b.earningDeduction='RC000' and a.empId=b.empNo and a.desig=c.desigCode")	
	List<PaybillEntity> getSSLic();
	
//	@Query("select new admin.payroll.models.SSLICModel(a.empId,a.name,c.desigShortDesc,b.refNo,b.amt) FROM EmpMastEntity a,PmSalDtlEntity b,PmDesigEntity c where b.earningDeduction='RC000' and a.empId=b.empNo and a.desig=c.desigCode")	
//	List<SSLICModel> getPli();
//	
	
	@Query("from PaybillEntity where cghs != null and cghs != 0.0 and cghs != 0 ")
	List<PaybillEntity> getCghsRecovery();
	
	@Query("from PaybillEntity where misRec != null and misRec != 0.0 and misRec != 0 ")
	List<PaybillEntity> getMiscRecovery();

	@Query("from PaybillEntity where pliCess != null and pliCess != 0.0 and pliCess != 0")
//	@Query("from PliEntity where pliCess != null and pliCess != 0.0 and pliCess != 0")
	List<PaybillEntity> getPli();

	@Query("from PaybillEntity where cghs = null OR cghs = 0.0 OR cghs = 0 ")
	List<PaybillEntity> getNonCghsRecovery();

}
