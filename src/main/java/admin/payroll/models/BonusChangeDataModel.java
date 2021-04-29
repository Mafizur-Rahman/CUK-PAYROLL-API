package admin.payroll.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;



@Data
public class BonusChangeDataModel {


	private String employeeId;
	private String name;
	private String gender;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate dob;
	private String classes;
	private String desig;			         
	private String financialYear;
	private String groupUnit;
	private String cdaCategory;
	private Double payRate;
	private Double gradePay;
	private Double recovery;
	private Double absenceDays;
	private Double payDays;
	private Double leaveDays;
	private Double leaveAmount;
	private Double bonusAmount;
	private Double totalDeduction;
	private Double newPay;
	private Double rev;
	private Double eolamt;
	private Double finalAmount;
	private Date logDate;
	private String logUser;
	private String logTime;
	private String logIp;
	
}
