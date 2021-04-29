package admin.payroll.models;

import java.util.Date;
import javax.persistence.Id;

import lombok.Data;

@Data
public class SaveSeventhMatrixModel {

    @Id
	private String payBand;

	private int gp;

	private int payStep;

	private int col;

	private String levelPay;
	
	private int scaleNo;

	private String logUser;

	private Date logDate;

	private String logTime;

	private String logIp;

}
