package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
public class EditCurrentMonthEdModel {

	private int id;

	private String payPeriod;

	private String refNo;

	private String refDate;

	private String amt;

	private String payCalPeriod;

	private String logUser;

	private String logIp;

}
