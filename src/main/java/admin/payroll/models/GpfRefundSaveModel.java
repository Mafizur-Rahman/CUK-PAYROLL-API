package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class GpfRefundSaveModel {
	private String employeeId;
	private Double refundAmount;
	private Date refundDate;
	private Date dateFrom;
	private Date dateTo;
	private Date installmentDate;
	private int gpfReferenceNo;
	private int acknowledgementNumber;
}