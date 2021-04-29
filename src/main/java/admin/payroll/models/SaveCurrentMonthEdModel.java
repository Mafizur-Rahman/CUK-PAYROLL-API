package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
public class SaveCurrentMonthEdModel {

//Data will be saved in PM_MED Table

	private String empNo;

	private String type;

	private List empNos;

	private String earningDeduction;

	private List payPeriod;

	private List refNo;

	private List refDate;

	private List amt;

	private List payCalPeriod;

	private String logUser;

	private String logIp;

}
