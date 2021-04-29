package admin.payroll.models;

import java.util.Date;



import lombok.Data;

@Data
public class SavePmRolesModel {
	
	
	private String roleId;
	
	private String description;
	
	private String active;
	
	private String logUser;
	
	private Date logDate;
	
	private String logTime;
	
	private String logIp;
	
	
	

}
