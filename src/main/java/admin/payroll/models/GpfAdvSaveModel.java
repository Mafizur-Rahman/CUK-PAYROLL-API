package admin.payroll.models;

import java.sql.Date;

import lombok.Data;

@Data
public class GpfAdvSaveModel {
	
	private int acknowledgementNumber;
	private String employeeId;
	private Double advanceAmount;
	private Date date;
	private String advanceType;
	private String section;
	private Integer noOfInstallment;
	private Double installmentAmount;
	private String advancePeriod;
}
