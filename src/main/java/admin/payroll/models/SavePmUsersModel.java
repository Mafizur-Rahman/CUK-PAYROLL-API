package admin.payroll.models;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class SavePmUsersModel {
	
    private Integer id;
	
	private String userType;
	
	private String section;
	
	private String classess;
	
	private String userName;
	
	private String desig;
	
	private String mobileNo;
	
	private String emailId;
	
	private String loginId;
	
	private String password;
	
	private String logUser;
	
	private Date logDate;
	
	private String logTime;
	
	private String logIp;
	
}
