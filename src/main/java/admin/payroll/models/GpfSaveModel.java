package admin.payroll.models;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class GpfSaveModel {
	private String employeeId;
	private String financialYear;
	private String lockstatus;
	private String loguser;
	private String logDate;
	private String logTime;
	private String logIp;
	private List<GpfSaveModelMonthWiseModel> gpfMonthWiseModels;
	
	@Data
	public static class GpfSaveModelMonthWiseModel {
		private  String month;
		private Integer subscriptionAmount;
		private Date fromDate;
		private Date toDate;
		
	}
}
