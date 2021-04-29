package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class SavePayMatrixModel {

	private String levelNo;

	private String cellNo;

	private int amount;

	private int payComission;

	private String active;

	private String logUser;

	private Date logDate;

	private String logTime;

	private String logIp;

}
