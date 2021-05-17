package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class PmRedModel {

	private String empNo;

	private String earningDeduction;

	private String refNo;
	private Date refDate;
}
