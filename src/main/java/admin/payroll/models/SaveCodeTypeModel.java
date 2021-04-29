package admin.payroll.models;

import java.util.Date;



import lombok.Data;

@Data
public class SaveCodeTypeModel {

	private String code;

	private String shortDesc;

	private String longDesc;

	private Date logDate;

	private String logTime;

	private String logUser;

	private String logIp;
}
