package admin.payroll.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@Entity
@Table(name = "EMPMAST")

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "getAllEmployees", procedureName = "get_all_employees", resultClasses = EmpMastEntity.class) })

public class EmpMastEntity implements Serializable {

	@Id
	@Column(name = "EMPID")
	private String empId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "MARITALSTATUS")
	private String maritalStatus;

	@Column(name = "EMPLOYEDAS")
	private String employedAs;

	@Column(name = "TOKNO")
	private Integer tokNo;

	@Column(name = "RELIGION")
	private String religion;

	@Column(name = "COMMUNITY_ORIGINAL")
	private String communityOriginal;

	@Column(name = "COMMUNITY_SELECTED")
	private String communitySelected;

	@Column(name = "DTBIRTH")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate dtBirth;

	// @Column(name = "IDPTYPE")
	// private String IDPTYPE;

	// @Column(name = "IDNUMBER")
	// private String IDNUMBER;

	// @Column(name = "EXPDATE")
	// private Date EXPDATE;

	@Column(name = "ADHAR_NO")
	private Long adharNo;

	@Column(name = "PROJCODE")
	private String projCode;

	@Column(name = "SLNO")
	private long slNo;

	@Column(name = "SOS_DATE")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate sosDate;

	@Column(name = "GPF_SUFFIX")
	private String gpfSuffix;

	@Column(name = "PIS_NO")
	private String pisNo;

	@Column(name = "CDA_CATID")
	private String cdaCatid;

	@Column(name = "JOB_CATEGORY")
	private String jobCategory;

	@Column(name = "LOCALITYHRA")
	private String localityHra;

	@Column(name = "CATID")
	private Long catId;

	@Column(name = "CLASS")
	private String classs;

	@Column(name = "CATEST")
	private Long catEst;

	@Column(name = "CLASS_EST")
	private String classEst;

	@Column(name = "DESIG")
	private String desig;

	@Column(name = "DIVISION")
	private String division;

	@Column(name = "GRP")
	private String grp;

	@Column(name = "DTAPPT")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate dtAppt;

	@Column(name = "BILLUNIT")
	private String billUnit;

	@Column(name = "PMLEVEL")
	private String pmLevel;

	@Column(name = "PMCELL")
	private String pmCell;

	@Column(name = "PAYRATE")
	private Double payRate;

	@Column(name = "GRADEPAY")
	private Double gradePay;

	@Column(name = "ISHANDICAP")
	private String isHandicap;

	@Column(name = "HANDICAPCODE")
	private Integer handicapCode;

	@Column(name = "PAYMENTMODE")
	private String paymentMode;

	@Column(name = "BANKCODE")
	private String bankCode;

	@Column(name = "BANKNO")
	private Long bankNo;

	@Column(name = "BANKNO_NEW")
	private String banknoNew;

	@Column(name = "BANKNAME")
	private String bankName;

	@Column(name = "BRANCH")
	private String branch;

	@Column(name = "IFSCCODE")
	private String ifscCode;

	@Column(name = "MICRCODE")
	private String micrCode;

	@Column(name = "PAN_NUMBER")
	private String panNumber;

	@Column(name = "NATIONALITY")
	private String nationality;

	@Column(name = "EMP_STATUS")
	private String empStatus;

	@Column(name = "PAYCOMMISION")
	private String payCommision;

	@Column(name = "GPFNO")
	private String gpfNo;

	@Column(name = "PRAN")
	private String pran;

	@Column(name = "PPAN_NO")
	private String pPanNo;

//	@Column(name = "RETIREMENTDATE")
//	private Date retirementDate;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGTIME")
	private String logTime;

	@Column(name = "LOGIP")
	private String logIp;

//	@Column(name = "QUALIFICATIONACQUIRED")
//	private String qualificationAcquired;
	
	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = DateTimeUtility.getCurrentDate();
		this.logTime = DateTimeUtility.getCurrentTimeString();
	}
}
