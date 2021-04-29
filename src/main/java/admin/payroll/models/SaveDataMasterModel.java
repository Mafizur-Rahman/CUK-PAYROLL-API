package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class SaveDataMasterModel {
	
	private int id;
	
	private String empId;

	private String dateType;

	private Date empDate;

	private String refNumber;

	private String logUser;

	private Date logDate;

	private String logTime;

	private String logIp;

}
