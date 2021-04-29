package admin.payroll.models;

import java.util.Date;



import lombok.Data;

@Data
public class SaveDepartmentModel {

	private String code;

	private String description;
	
	private Date logDate;

	private String logUser;

	private String logTime;

	private String logIp;
}
