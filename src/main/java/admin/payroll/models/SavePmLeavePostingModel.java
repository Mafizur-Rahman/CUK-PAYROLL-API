package admin.payroll.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SavePmLeavePostingModel {

	private String empNo;

	private String empName;

	private String leaveCode;

	private String leaveDescr;

	private String leaveYear;

	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate fromDate;

	private String fromWhichHalf;

	private String applicationType;
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate toDate;

	private String toWhichHalf;

	private String daysNo;

	private String commute;

	private String reason;

	private String postDataAttendance;

	private String officeOrderNo;
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate officeOrderDate;

	private String supplementary;

	private String logUser;

	private LocalDate logDate;

	private String logTime;

	private String logIp;

}
