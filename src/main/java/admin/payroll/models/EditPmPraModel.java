package admin.payroll.models;

import lombok.Data;

@Data
public class EditPmPraModel {

	private int id;

	private String fromDate;

	private String toDate;

	private String desigCode;

	// private List scaleCode;

	private String pmLevel;

	private String pmCell;

	private String rate;

	// private List localityClass;

	private String officeOrderNo;

	private String officeOrderDate;

	private String remarks;

	private String regimentalRemarks;

	private String logUser;

	private String logIp;

}
