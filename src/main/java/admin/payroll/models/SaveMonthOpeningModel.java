package admin.payroll.models;

import lombok.Data;

@Data
public class SaveMonthOpeningModel {

	private String groupUnit;

	private String billUnit;

	private String fromDate;

	private String toDate;

	private String wagePeriod;

	private String payPeriod;

	private String payComission;

	private String status;

	private String monthlyBackup;

	private String logIp;

	private String logUser;

}
