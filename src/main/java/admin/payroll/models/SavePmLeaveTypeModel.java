package admin.payroll.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SavePmLeaveTypeModel {

	private String leaveCode;

	private String leaveDesc;

	private String leaveLongDesc;

	private String groupAEligible;

	private String groupBEligible;

	private String groupCEligible;

	private String groupDEligible;

	private Integer maximumCreditDays;

	private String sexEligible;

	private String halfDayPermissible;

	private String paycut;

	private String logUser;

	private LocalDate logDate;

	private String logTime;

	private String logIp;

}
