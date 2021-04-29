package admin.payroll.models;

import lombok.Data;

@Data
public class GetCurrentMonthEdModel {

	private String code;

	private String edCode;

	private String payPeriod;
}
