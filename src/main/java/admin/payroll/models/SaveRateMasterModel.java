package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class SaveRateMasterModel {

	private String earningDeduction;

	private Date fromDate;

	private Date toDate;

	private String rate;

	private String isActive;

	private String payComission;

	private String logUser;

	private Date logDate;

	private String logTime;

	private String logIp;

}
