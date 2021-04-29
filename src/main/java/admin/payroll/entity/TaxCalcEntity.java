package admin.payroll.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TAX_CALC")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "taxcalempidsixth", procedureName = "tax_calempid_sixth", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mempid", type = String.class) }), })
public class TaxCalcEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EMPID")
	private String employeeId;

	@Column(name = "SALARY")
	private Double salary;

	@Column(name = "HRA")
	private Double hra;

	@Column(name = "CCC80")
	private Double ccc80;

	@Column(name = "D80")
	private Double d80;

	@Column(name = "DD80")
	private Double dd80;

	@Column(name = "DDB80")
	private Double ddb80;

	@Column(name = "E80")
	private Double e80;

	@Column(name = "G80")
	private Double g80;

	@Column(name = "GPF")
	private Double gpf;

	@Column(name = "PPF")
	private Double ppf;

	@Column(name = "PLI")
	private Double pli;

	@Column(name = "CTD")
	private Double ctd;

	@Column(name = "NSC")
	private Double nsc;

	@Column(name = "ULIP")
	private Double ulip;

	@Column(name = "LIC")
	private Double lic;

	@Column(name = "NSCINT")
	private Double nscInt;

	@Column(name = "HBA")
	private Double hba;

	@Column(name = "MISC")
	private Double misc;

	@Column(name = "INFRA")
	private Double infra;

	@Column(name = "TAXDUE")
	private Double taxdue;

	@Column(name = "TAXPAID")
	private Double taxPaid;

	@Column(name = "TAXBAL")
	private Double taxbal;

	@Column(name = "TAXDEC")
	private Double taxdec;

	@Column(name = "TAXJAN")
	private Double taxjan;

	@Column(name = "TAXFEB")
	private Double taxFeB;

	@Column(name = "PROFTAX")
	private String profTax;

	@Column(name = "S192")
	private Double s192;

	@Column(name = "A88")
	private Double a88;

	@Column(name = "GIS")
	private Double gis;

	@Column(name = "WOMEN")
	private String women;

	@Column(name = "SURCH")
	private Double surch;

	@Column(name = "OTHER_INCOME")
	private Double otherIncome;

	@Column(name = "TAXNOV")
	private Double taxNov;

	@Column(name = "SURCH_PAID")
	private String surchPaid;

	@Column(name = "SURCH_BAL")
	private Double surchBal;

//..................................................................................................

	@Column(name = "FORM16")
	private String form16;

	@Column(name = "DDA80")
	private Double dda80;

	@Column(name = "HDFC")
	private Double hdfc;

	@Column(name = "INT_DIVIDEND")
	private Double intDividend;

	@Column(name = "TAXPAID_ARREAR")
	private Double taxpaidArrear;

	@Column(name = "ARREAR")
	private Double arrear;

	@Column(name = "EDN_EXP")
	private Double ednExp;

	@Column(name = "CCD80")
	private Double ccd80;

	@Column(name = "SSLIC_PEN")
	private Double sslicPen;

	@Column(name = "SSLIC_EXT")
	private Double sslicExt;

	@Column(name = "SURCH_NOV")
	private Double surchNov;

	@Column(name = "SURCH_DEC")
	private Double surchDec;

	@Column(name = "SURCH_JAN")
	private Double surchJan;

	@Column(name = "SURCH_FEB")
	private Double surchFeb;

	@Column(name = "FD")
	private Double fd;

	@Column(name = "TAXPAID_RETIRE")
	private Double taxpaidRetire;

	@Column(name = "SURCHPAID_RETIRE")
	private Double surchpaidRetire;

	@Column(name = "SURCH_10")
	private Double surch10;

	@Column(name = "SURCH_BAL10")
	private Double surchBal10;

	@Column(name = "SURCH_NOV10")
	private Double surchNov10;

	@Column(name = "SURCH_DEC10")
	private Double surchDec10;

	@Column(name = "SURCH_JAN10")
	private Double surchJan10;

	@Column(name = "SURCH_FEB10")
	private Double surchFeb10;

	@Column(name = "SURCH_PAID10")
	private Double surchPaid10;

	@Column(name = "CESSPAID_RETIRE")
	private Double cessPaidRetire;

	@Column(name = "CESS_10")
	private String cess10;

	@Column(name = "CESS_BAL10")
	private Double cessBal10;

	@Column(name = "CESS_NOV10")
	private Double cessNov10;

	@Column(name = "CESS_DEC10")
	private Double cessDec10;

	@Column(name = "CESS_JAN10")
	private Double cessJan10;

	@Column(name = "CESS_FEB10")
	private Double cessFeb10;

	@Column(name = "CESS_PAID10")
	private Double cessPaid10;

	@Column(name = "SEC89_RELIEF")
	private Double sec89Relief;

	@Column(name = "ARREAR_TAX")
	private String arrearTax;

	@Column(name = "SURCH_ARR")
	private Double surchArr;
	// ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

	@Column(name = "SURCH_ARR10")
	private Double surchArr10;

	@Column(name = "CGHS")
	private Double cghs;

	@Column(name = "CCF80")
	private Double ccf80;

	@Column(name = "D80_PARENTS")
	private Double d80Parents;

	@Column(name = "CCD80_NPS")
	private Double ccd80Nps;

	@Column(name = "CCF80_G")
	private Double ccf80G;

	@Column(name = "DTFROM")
	private LocalDate dtfrom;

	@Column(name = "DTTO")
	private LocalDate dtto;

	@Column(name = "EE80")
	private Double ee80;

	@Column(name = "TTA80")
	private Double tta80;

	@Column(name = "A87")
	private Double a87;

	@Column(name = "HRA_RENT")
	private Double hraRent;

	@Column(name = "TOT_SBI_INT")
	private Double totSbiInt;

	@Column(name = "HBA_OTHER")
	private Double hbaOther;

	@Column(name = "HBA_REMARKS")
	private String hbaRemarks;

	@Column(name = "OTHER_REMARKS")
	private String otherRemarks;

	@Column(name = "PLI_OTHER")
	private Double pliOther;

	@Column(name = "CCD80_NPS_OTHER")
	private Double ccd80NpsOther;

	@Column(name = "CCD80_1B")
	private Double ccd801b;

	@Column(name = "TRF_IN")
	private String trfIn;

	@Column(name = "TRF_OUT")
	private String trfOut;

	@Column(name = "RETR")
	private String retr;

	@Column(name = "FIRST_APPNT")
	private String firstAppnt;

	@Column(name = "OWNER_PAN")
	private String ownerPan;

	@Column(name = "OWNER_NAME")
	private String ownerName;

	@Column(name = "HBA_REBATE_YEAR")
	private String hbaRebateYear;

	@Column(name = "HBA_BANK_ID")
	private Double hbaBankId;

	@Column(name = "IT_IND_CON")
	private Double itIndCon;

	@Column(name = "OTHER_EMPLOYER")
	private Double otherEmployer;

	@Column(name = "MISC_80D")
	private Double misc80d;

	@Column(name = "MISC_REM_80C")
	private String miscRem80c;

	@Column(name = "MISC_REM_80D")
	private String miscRem80d;

}
