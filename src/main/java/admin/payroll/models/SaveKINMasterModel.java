package admin.payroll.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SaveKINMasterModel {
	
//	private int id;
	 
	private String empId;

	private int serlNo;

	private int fpaDesinCode;

	private int pensionNomineeNo;

	private int cgeisNomineeNo;

	private int gpfNomineeNo;
	private int admissionNo;

	private int cghsBenId;

	private int pensionShare;
	private Date fpaDate;
	private String name;

	private String sex;

	private String reln;

	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate bDate;

	private String depdnt;

	private String isEmployed;

	private String isHandicapped;

	private String handicappedCategory;

	private int handicappedPercentage;

	private String occpn;

	private String nominee;

	private String school;

	private String classs;

	private String isKvDrdo;

	private String govtEmployee;

	private String gpfNominee;

	private String pensionNominee;

	private String maritialStatus;

	private String cgeisNominee;

	private int cgeisShare;

	private String adopted;

	private String logUser;

	private Date logDate;

	private String LogTime;

	private String logIp;

}
