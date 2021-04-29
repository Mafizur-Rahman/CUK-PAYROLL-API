package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class SaveBankMasterModel {

	private String bankCode;
	
	private String bankName;
	
	private String ifscCode;

	private String branchCode;

	private String micrCode;

	private String bankPanNumber;

	private String isHba;

	private String branchName;

	private String addrress1;

	private String addrress2;

	private String city;

	private String pincode;

	private String state;

	private String logUser;

	private Date logDate;

	private String logTime;

	private String logIp;
}
