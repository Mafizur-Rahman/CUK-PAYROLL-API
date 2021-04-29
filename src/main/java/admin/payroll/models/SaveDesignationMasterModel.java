package admin.payroll.models;

import java.util.Date;


import lombok.Data;

@Data
public class SaveDesignationMasterModel {

	private String desigCode;

	private String desigShortDesc;

	private String desigLongDesc;

	private String payCell;

	private String payLevel;

	private String gradePay;

	private String isActive;

	private String logUser;

	private Date logDate;

	private String logTime;

	private String logIp;

}
