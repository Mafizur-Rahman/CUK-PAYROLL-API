package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class PmLoanModel {

	private String empNo;

	private String earningDeduction;

	private String refNo;
	private Date sancDate;
}
