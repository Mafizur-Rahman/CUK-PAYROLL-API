package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PaybillEntity;
import admin.payroll.entity.PmDesigEntity;
import admin.payroll.entity.RegimentalPaybillEntity;
import admin.payroll.models.ClassModel;

@Repository
public interface RegimentalPaybillRepo extends JpaRepository<RegimentalPaybillEntity, String> {

//	@Query("Select desigCode From PmDesigEntity")
//	List<String> getDesignatedCodeFromPmDesig();

//	@Query("From PaybillEntity where classs=:classs")
//	List<PaybillEntity> findAllByClass(@Param("classs") String classs);
	
	@Query("from RegimentalPaybillEntity where misCsch != null and misCsch != 0.0 and misCsch != 0")
       List<RegimentalPaybillEntity> getMiscRecoverySchedule();

	@Query("from RegimentalPaybillEntity where ednLoan != null and ednLoan != 0.0 and ednLoan != 0")
    List<RegimentalPaybillEntity> getEducationLoan();
	
	@Query("from RegimentalPaybillEntity where ibLoan != null and ibLoan != 0.0 and ibLoan != 0 ")
	List<RegimentalPaybillEntity> getIbLoan();
	
	@Query("from RegimentalPaybillEntity where rtf != null and rtf != 0.0 and rtf != 0 ")
	List<RegimentalPaybillEntity> grtReimbursmentTfee();
	
	@Query("from RegimentalPaybillEntity where cgoClub != null and cgoClub != 0.0 and cgoClub != 0 ")
	List<RegimentalPaybillEntity> getCgoClubRecovery();
	
	@Query("from RegimentalPaybillEntity where cgoClub != null and cgoClub != 0.0 and cgoClub != 0 and classs=:classs")
	List<RegimentalPaybillEntity> getCgoClubRecoveryByClass(@Param("classs") String classs);
	
	@Query("from RegimentalPaybillEntity where mkManch != null and mkManch != 0.0 and mkManch != 0 ")
	List<RegimentalPaybillEntity> getMahilaKalyanManch();
	
	@Query("from RegimentalPaybillEntity where frFund != null and frFund != 0.0 and frFund != 0 ")
	List<RegimentalPaybillEntity> getFamilyReliefFund();
	
	@Query("from RegimentalPaybillEntity where union != null and union != 0.0 and union != 0 ")
	List<RegimentalPaybillEntity> getUnionn();

	@Query("from RegimentalPaybillEntity where dromi != null and dromi != 0.0 and dromi != 0 ")
	List<RegimentalPaybillEntity> getDromi();

	@Query("from RegimentalPaybillEntity where ednLoan != null and ednLoan != 0.0 and ednLoan != 0 and classs=:classs")
	List<RegimentalPaybillEntity> getEducationLoanByClass(@Param("classs") String classs);

	@Query("from RegimentalPaybillEntity where dromi != null and dromi != 0.0 and dromi != 0 and classs=:classs")
	List<RegimentalPaybillEntity> getDromiByClass(@Param("classs") String classs);

	@Query("from RegimentalPaybillEntity where union != null and union != 0.0 and union != 0 and classs=:classs ")
	List<RegimentalPaybillEntity> getUnionnByClass(@Param("classs") String classs);

}
