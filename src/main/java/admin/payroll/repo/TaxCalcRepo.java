package admin.payroll.repo;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.TaxCalcEntity;
import admin.payroll.models.TaxCalcModel;

@Repository
public interface TaxCalcRepo extends JpaRepository<TaxCalcEntity, String > {
	
	@Procedure(name="taxcalempidsixth")
	void taxcalempidsixth(@Param("mempid") String mempid);

	@Query("SELECT NEW admin.payroll.models.TaxCalcModel(tce.employeeId, tce.salary, iae.addinCarr, iae.toTarrs, iae.addinCarrPua, "
			+ "tce.hra,  tce.ccc80,  tce.d80,  tce.dd80,  tce.ddb80,  tce.e80,  tce.g80,  tce.gpf, "
			+ "tce.ppf,  tce.pli,  tce.ctd,  tce.nsc,  tce.ulip,  tce.lic,  tce.nscInt,  tce.hba, "
			+ "tce.misc,  tce.infra,  tce.taxdue,  tce.taxPaid,  tce.taxbal,  tce.taxdec,  tce.taxjan,"
			+ "tce.taxFeB, tce.profTax,  tce.s192,  tce.a88,  tce.gis,  tce.women,  tce.surch, "
			+ "tce.otherIncome,  tce.taxNov,  tce.surchPaid,  tce.surchBal,  tce.form16,  tce.dda80, "
			+ "tce.hdfc,  tce.intDividend,  tce.taxpaidArrear,  tce.arrear,  tce.ednExp,  tce.ccd80, "
			+ "tce.sslicPen,  tce.sslicExt,  tce.surchNov,  tce.surchDec,  tce.surchJan,  tce.surchFeb, "
			+ "tce.fd,  tce.taxpaidRetire,  tce.surchpaidRetire,  tce.surch10,  tce.surchBal10, "
			+ "tce.surchNov10,  tce.surchDec10,  tce.surchJan10,  tce.surchFeb10,  tce.surchPaid10, "
			+ "tce.cessPaidRetire, tce.cess10,  tce.cessBal10,  tce.cessNov10,  tce.cessDec10, "
			+ "tce.cessJan10,  tce.cessFeb10,  tce.cessPaid10,  tce.sec89Relief,  tce.arrearTax, "
			+ "tce.surchArr,  tce.surchArr10,  tce.cghs,  tce.ccf80,  tce.d80Parents,  tce.ccd80Nps, "
			+ "tce.ccf80G, tce.dtfrom,  tce.dtto,  tce.ee80,  tce.tta80,  tce.a87,  tce.hraRent, "
			+ "tce.totSbiInt,  tce.hbaOther,  tce.hbaRemarks,  tce.otherRemarks,  tce.pliOther, "
			+ "tce.ccd80NpsOther,  tce.ccd801b,  tce.trfIn,  tce.trfOut,  tce.retr,  tce.firstAppnt,"
			+ "tce.ownerPan,  tce.ownerName, tce.hbaRebateYear,  tce.hbaBankId,  tce.itIndCon, "
			+ "tce.otherEmployer,  tce.misc80d,  tce.miscRem80c,  tce.miscRem80d,  ste.scghs) "
			+ " FROM admin.payroll.entity.TaxCalcEntity tce INNER JOIN admin.payroll.entity.ItaxArrearEntity  iae "
			+ " ON  tce.employeeId = iae.empId INNER JOIN admin.payroll.entity.SumTaxEntity ste ON tce.employeeId = ste.employeeId "
			+ " WHERE tce.employeeId = :employeeId ")
	TaxCalcModel getTaxCalcByEmployeeId(@Param("employeeId") String employeeId);
	
//	@Query("SELECT t FROM TaxCalcEntity t WHERE t.employeeId = :employeeId")
//	TaxCalcEntity getTaxCalcByEmployeeId(@Param("employeeId") String employeeId);
	
	

}
