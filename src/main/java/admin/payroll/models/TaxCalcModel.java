package admin.payroll.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TaxCalcModel {

	
	
	
	public TaxCalcModel(String employeeId, Double salary, Double addinCarr, Double toTarrs, Double addinCarrPua,
			Double hra, Double ccc80, Double d80, Double dd80, Double ddb80, Double e80, Double g80, Double gpf,
			Double ppf, Double pli, Double ctd, Double nsc, Double ulip, Double lic, Double nscInt, Double hba,
			Double misc, Double infra, Double taxdue, Double taxPaid, Double taxbal, Double taxdec, Double taxjan,
			Double taxFeB, String profTax, Double s192, Double a88, Double gis, String women, Double surch,
			Double otherIncome, Double taxNov, String surchPaid, Double surchBal, String form16, Double dda80,
			Double hdfc, Double intDividend, Double taxpaidArrear, Double arrear, Double ednExp, Double ccd80,
			Double sslicPen, Double sslicExt, Double surchNov, Double surchDec, Double surchJan, Double surchFeb,
			Double fd, Double taxpaidRetire, Double surchpaidRetire, Double surch10, Double surchBal10,
			Double surchNov10, Double surchDec10, Double surchJan10, Double surchFeb10, Double surchPaid10,
			Double cessPaidRetire, String cess10, Double cessBal10, Double cessNov10, Double cessDec10,
			Double cessJan10, Double cessFeb10, Double cessPaid10, Double sec89Relief, String arrearTax,
			Double surchArr, Double surchArr10, Double cghs, Double ccf80, Double d80Parents, Double ccd80Nps,
			Double ccf80G, LocalDate dtfrom, LocalDate dtto, Double ee80, Double tta80, Double a87, Double hraRent,
			Double totSbiInt, Double hbaOther, String hbaRemarks, String otherRemarks, Double pliOther,
			Double ccd80NpsOther, Double ccd801b, String trfIn, String trfOut, String retr, String firstAppnt,
			String ownerPan, String ownerName, String hbaRebateYear, Double hbaBankId, Double itIndCon,
			Double otherEmployer, Double misc80d, String miscRem80c, String miscRem80d, Double scghs) {
		
		super();
		this.employeeId = employeeId;
		this.salary = salary;
		this.addinCarr = addinCarr;
		this.toTarrs = toTarrs;
		this.addinCarrPua = addinCarrPua;
		this.hra = hra;
		this.ccc80 = ccc80;
		this.d80 = d80;
		this.dd80 = dd80;
		this.ddb80 = ddb80;
		this.e80 = e80;
		this.g80 = g80;
		this.gpf = gpf;
		this.ppf = ppf;
		this.pli = pli;
		this.ctd = ctd;
		this.nsc = nsc;
		this.ulip = ulip;
		this.lic = lic;
		this.nscInt = nscInt;
		this.hba = hba;
		this.misc = misc;
		this.infra = infra;
		this.taxdue = taxdue;
		this.taxPaid = taxPaid;
		this.taxbal = taxbal;
		this.taxdec = taxdec;
		this.taxjan = taxjan;
		this.taxFeB = taxFeB;
		this.profTax = profTax;
		this.s192 = s192;
		this.a88 = a88;
		this.gis = gis;
		this.women = women;
		this.surch = surch;
		this.otherIncome = otherIncome;
		this.taxNov = taxNov;
		this.surchPaid = surchPaid;
		this.surchBal = surchBal;
		this.form16 = form16;
		this.dda80 = dda80;
		this.hdfc = hdfc;
		this.intDividend = intDividend;
		this.taxpaidArrear = taxpaidArrear;
		this.arrear = arrear;
		this.ednExp = ednExp;
		this.ccd80 = ccd80;
		this.sslicPen = sslicPen;
		this.sslicExt = sslicExt;
		this.surchNov = surchNov;
		this.surchDec = surchDec;
		this.surchJan = surchJan;
		this.surchFeb = surchFeb;
		this.fd = fd;
		this.taxpaidRetire = taxpaidRetire;
		this.surchpaidRetire = surchpaidRetire;
		this.surch10 = surch10;
		this.surchBal10 = surchBal10;
		this.surchNov10 = surchNov10;
		this.surchDec10 = surchDec10;
		this.surchJan10 = surchJan10;
		this.surchFeb10 = surchFeb10;
		this.surchPaid10 = surchPaid10;
		this.cessPaidRetire = cessPaidRetire;
		this.cess10 = cess10;
		this.cessBal10 = cessBal10;
		this.cessNov10 = cessNov10;
		this.cessDec10 = cessDec10;
		this.cessJan10 = cessJan10;
		this.cessFeb10 = cessFeb10;
		this.cessPaid10 = cessPaid10;
		this.sec89Relief = sec89Relief;
		this.arrearTax = arrearTax;
		this.surchArr = surchArr;
		this.surchArr10 = surchArr10;
		this.cghs = cghs;
		this.ccf80 = ccf80;
		this.d80Parents = d80Parents;
		this.ccd80Nps = ccd80Nps;
		this.ccf80G = ccf80G;
		this.dtfrom = dtfrom;
		this.dtto = dtto;
		this.ee80 = ee80;
		this.tta80 = tta80;
		this.a87 = a87;
		this.hraRent = hraRent;
		this.totSbiInt = totSbiInt;
		this.hbaOther = hbaOther;
		this.hbaRemarks = hbaRemarks;
		this.otherRemarks = otherRemarks;
		this.pliOther = pliOther;
		this.ccd80NpsOther = ccd80NpsOther;
		this.ccd801b = ccd801b;
		this.trfIn = trfIn;
		this.trfOut = trfOut;
		this.retr = retr;
		this.firstAppnt = firstAppnt;
		this.ownerPan = ownerPan;
		this.ownerName = ownerName;
		this.hbaRebateYear = hbaRebateYear;
		this.hbaBankId = hbaBankId;
		this.itIndCon = itIndCon;
		this.otherEmployer = otherEmployer;
		this.misc80d = misc80d;
		this.miscRem80c = miscRem80c;
		this.miscRem80d = miscRem80d;
		this.scghs = scghs;
	}

	private String employeeId;
	
	private Double salary;
	
	private Double addinCarr;
	
	private Double toTarrs;
	
	private Double addinCarrPua;
	
	private Double hra;
	
	private Double ccc80;
	
	private Double d80;
	
	private Double dd80;
	
	private Double ddb80;
	
	private Double e80;
	
	private Double g80;
	
	private Double gpf;
	
	private Double ppf;
	
	private Double pli;
	
	private Double ctd;
	
	private Double nsc;
	
	private Double ulip;
	
	private Double lic;
	
	private Double nscInt;
	
	private Double hba;
	
	private Double misc;
	
	private Double infra;
	
	private Double taxdue;
	
	private Double taxPaid;
	
	private Double taxbal;
	
	private Double taxdec;
	
	private Double taxjan;
	
	private Double taxFeB;
	
	private String profTax;
	
	private Double s192;
	
	private Double a88;
	
	private Double gis;
	
	private String women;
	
	private Double surch;
	
	private Double otherIncome;
	
	private Double taxNov;
	

	private String surchPaid;
	
	private Double surchBal;

//..................................................................................................
	
	private String form16;
	
	private Double dda80;
	
	private Double hdfc;
	
	private Double intDividend;
	
	private Double taxpaidArrear;
	
	private Double arrear;
	
	private Double ednExp;
	
	private Double ccd80;
	
	private Double sslicPen;
	
	private Double sslicExt;
	
	private Double surchNov;
	
	private Double surchDec;
	
	private Double surchJan;
	
	private Double surchFeb;
	
	private Double fd;
	
	private Double taxpaidRetire;
	
	private Double surchpaidRetire;
	
	private Double surch10;
	
	private Double surchBal10;
	
	private Double surchNov10;
	
	private Double surchDec10;
	
	private Double surchJan10;
	
	private Double surchFeb10;
	
	private Double surchPaid10;
	
	private Double cessPaidRetire;
	
	private String cess10;
	
	private Double cessBal10;
	
	private Double cessNov10;
	
	private Double cessDec10;
	
	private Double cessJan10;
	
	private Double cessFeb10;
	
	private Double cessPaid10;
	
	private Double sec89Relief;
	
	private String arrearTax;
	
	private Double surchArr;
	
	//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	
	private Double surchArr10;
	
	private Double cghs;
	
	private Double ccf80;
	
	private Double d80Parents;
	
	private Double ccd80Nps;
	
	private Double ccf80G;
	
	private LocalDate dtfrom;
	
	private LocalDate dtto;
	
	private Double ee80;
	
	private Double tta80;
	
	private Double a87;
	
	private Double hraRent;
	
	private Double totSbiInt;
	
	private Double hbaOther;
	
	private String hbaRemarks;
	
	private String otherRemarks;
	
	private Double pliOther;
	
	private Double ccd80NpsOther;
	
	private Double ccd801b;
	
	private String trfIn;
	
	private String trfOut;
	
	private String retr;
	
	private String firstAppnt;
	
	private String ownerPan;
	
	private String ownerName;
	
	private String hbaRebateYear;
	
	private Double hbaBankId;
	
	private Double itIndCon;
	
	private Double otherEmployer;
	
	private Double misc80d;
	
	private String miscRem80c;
	
	private String miscRem80d;

	private Double scghs;
}
