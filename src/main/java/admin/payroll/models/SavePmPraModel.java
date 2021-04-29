package admin.payroll.models;

import java.util.List;
import lombok.Data;

@Data
public class SavePmPraModel {

	private String empNo;

	private String type;

	private List empNos;

	private String earningDeduction;

	private List fromDate;

	private List toDate;

	private List desigCode;

	// private List scaleCode;

	private List pmLevel;

	private List pmCell;

	private List rate;

	// private List localityClass;

	private List officeOrderNo;

	private List officeOrderDate;

	private List remarks;

	private List regimentalRemarks;

	private String logUser;

	private String logIp;
}
