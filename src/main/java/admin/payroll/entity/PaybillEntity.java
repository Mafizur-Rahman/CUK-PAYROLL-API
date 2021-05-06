package admin.payroll.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PAYBILL")

public class PaybillEntity implements Serializable {

	@Id
	@Column(name = "EMPNO")
	private String empNo;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "IFSC")
	private String ifscCode;
	
	@Column(name = "CLASS")
	private String classs;
	
	@Column(name = "DESIG")
	private String desig;
	
	@Column(name = "GPFNO")
	private String gpfNo;
	
	@Column(name = "PMLEVEL")
	private String level;
	
	@Column(name = "PMCELL")
	private String cell;
	
	@Column(name = "ADHAR_NO")
	private String adharNo;
	
	@Column(name = "DTBIRTH")
	private Date dtBirth;	

	@Column(name = "DTAPPT")
	private Date dtAppt;

	@Column(name = "SOS_DATE")
	private Date sosDate;
	
	@Column(name = "DNI")
	private String dni;
	
	@Column(name = "VINC_VALID_DT")
	private String vincValidDt;
	
	//NPS,GIS,BANK,ITAX
	@Column(name = "PRAN")
	private String pran;
	
	@Column(name = "BANKNO")
	private String bankNo;
	
	@Column(name = "BANKNO_NEW")
	private String bankNoNew;
	
	@Column(name = "PAN_NUMBER")
	private String panNumber;
	
	@Column(name = "BASIC")
	private Double basic;
	
	@Column(name = "DA")
	private Double da;
	
	@Column(name = "TA")
	private Double ta;
	
	@Column(name = "DA_ON_TA")
	private Double daOnTa;
	
	@Column(name = "V_INCR")
	private Double vIncr;
	
	@Column(name = "HRA")
	private Double hra;
	
	@Column(name = "SPAY")
	private Double spay;

	@Column(name = "GMC_CPF")
	private Double gmcCpf;
	
	@Column(name = "GMC_ARR")
	private Double gmcArr;
	
	@Column(name = "GMC_REC")
	private Double gmcRec;
	
	@Column(name = "NPSTOTAl")
	private Double npsTotal;
	
	@Column(name = "GROSS_PAY")
	private Double grossPay;
	
	@Column(name = "GPF_SUB")
	private Double gpfSub;
	
	@Column(name = "GPF_REF")
	private Double gpfRef;
	
	@Column(name = "CPF_IND")
	private Double cpfInd;
	
	@Column(name = "CPF_ARRS")
	private Double cpfArrs;
	//added on 16-03-2021
	@Column(name = "PLI")
	private Double pli;
	
	@Column(name = "PLISERVTAX")
	private Double pliServTax;
	
	@Column(name = "PLI_SBSERVTAX")
	private Double pliSbServTax;
	
	@Column(name = "PLI_CESS ")
	private Double pliCess;
	
	@Column(name = "CGEIS")
	private Double cgeis;	
	
	@Column(name = "CGHS")
	private Double cghs;
	
	@Column(name = "ITAX")
	private Double iTax;
	
	@Column(name = "CESS")
	private Double cess;
	
	@Column(name = "HBA_INT")
	private Double hba;
	
	@Column(name = "CAR_ADV_INT")
	private Double carAdvInt;
	
	@Column(name = "VEH_ADV_INT")
	private Double vehAdvInt;
	
	@Column(name = "COMP_ADV_INT")
	private Double compAdvInt;
	
	@Column(name = "SSLIC")
	private Double ssLic;	
	
	@Column(name = "LPWF")
	private Double lpwf;
	
	@Column(name = "LTC_REC")
	private Double ltcRec;
	
	@Column(name = "MED_REC")
	private Double medRec;
	
	@Column(name = "TADA_REC")
	private Double tdaRec;
	
	@Column(name = "PENAL_IN")
	private Double penalIn;
	
	@Column(name = "MIS_REC")
	private Double misRec;
	
	@Column(name = "NETPAY")
	private Double netPay;
	
	@Column(name = "GIS")
	private Double gis;	
	
	@Column(name = "CESS2")
	private Double cess2;

	
//	@Column(name = "GRADEPAY")
//	private Double gradePay;
//
//	@Column(name = "PSO_A_FPA")
//	private Double psoAfpa;
//
//	@Column(name = "ADD_INC")
//	private Double addInc;
//	
//	@Column(name = "CEA")
//	private Double cea;
//	
//	@Column(name = "MISC_CR")
//	private Double miscCr;
//	
//	@Column(name = "REF_CPF_ARR")
//	private Double refCpfArr;	
}
