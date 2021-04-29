package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
public class EditRegRecovModel {

	private int id;

	private String refNo;

	private String refDate;

	private String startYearMm;

	private String endYearMm;

	private String amt;

	private String payCalPeriod;

	private String logUser;

	private String logIp;
}
