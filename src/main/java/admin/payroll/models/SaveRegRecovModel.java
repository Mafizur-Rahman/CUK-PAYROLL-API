package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
// Data is saved in PM_RED
public class SaveRegRecovModel {

	private String empNo;

	private String type;

	private List empNos;

	private String earningDeduction;

	private List refNo;

	private List refDate;

	private List startYearMm;

	private List endYearMm;

	private List amt;

	private List payCalPeriod;

	private String logUser;

	private String logIp;

}
