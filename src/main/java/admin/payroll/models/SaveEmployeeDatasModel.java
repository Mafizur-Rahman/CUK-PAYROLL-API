package admin.payroll.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SaveEmployeeDatasModel {

	private String empId;

	private String name;

	private String gender;

	private String maritalStatus;

	private String employedAs;

	private long tokNo;

	private String religion;

	private String communityOriginal;

	private String communitySelected;

	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate dtBirth;

	private long adharNo;

	private String projCode;

	private long slNo;

	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate sosDate;

	private String gpfSuffix;

	private String pisNo;

	private String cdaCatid;

	private String jobCategory;

	private String localityHra;

	private long catId;

	private String classs;

	private long catEst;

	private String classEst;

	private String desig;

	private String division;

	private String grp;

	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate dtAppt;

	private String billUnit;

	private String pmLevel;

	private String pmCell;

	private float payRate;

	private float gradePay;

	private String isHandicap;

	private int handicapCode;

	private String paymentMode;

	private String bankCode;

	private long bankNo;

	private String banknoNew;

	private String bankName;

	private String branch;

	private String ifscCode;

	private String micrCode;

	private String panNumber;

	private String nationality;

	private String empStatus;

	private String payCommision;

	private String gpfNo;

	private String pran;

	private String pPanNo;

//	private Date retirementDate;

	private String logUser;

	private String logIp;

//	private String qualificationAcquired;

	;

}
