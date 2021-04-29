package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class SaveDocumentModel {
	
	private int id;

	private String empId;

	private String docType;

	private String fileName;

	private String filePath;

	private String financialYear;

	private String kinName;

	private String logUser;

	private Date logDate;

	private String logTime;

	private String logIp;

}
