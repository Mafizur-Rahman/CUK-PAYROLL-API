package admin.payroll.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "REGIMENTAL_PAYBILL")

public class RegimentalPaybillEntity {

	@Id
	@Column(name = "EMPNO")
	private String empNo;
	
	@Column(name = "NAME")
	private String name;
	
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
	
	@Column(name = "PAN_NUMBER")
	private String panNumber;
	
	@Column(name = "BASIC")
	private Double basic;
	
	@Column(name = "EDNLOAN")
	private Double ednLoan;
	
	@Column(name = "FRFUND")
	private Double frFund;
	
	@Column(name = "MKMANCH")
	private Double mkManch;
	
	@Column(name = "UNIONN")
	private Double union;
	
	@Column(name = "DROMI")
	private Double dromi;
	
	@Column(name = "NONCGHS")
	private Double nonCghs;
	
	@Column(name = "MISCSCH")
	private Double misCsch;
	
	@Column(name = "IBLOAN")
	private Double ibLoan;

	@Column(name = "CGOCLUB")
	private Double cgoClub;
	
	@Column(name = "PLI")
	private Double pli;
	
	@Column(name = "NPTA")
	private Double npta;
	
	@Column(name = "NGO")
	private Double ngo;
	
	@Column(name = "RTF")
	private Double rtf;
}
