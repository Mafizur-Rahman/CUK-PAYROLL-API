package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class SaveKINFeeMasterModel {
	
	private int id;

	private String empId;

	private int kinId;

	private String feeType;

	private Double feeAmt;

	private String claim;

	private String receiptNo;

	private String financialYear;

	private String logUser;

	private Date logDate;

	private String logTime;

	private String logIp;

}
