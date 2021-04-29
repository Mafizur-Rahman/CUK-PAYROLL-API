package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
public class EditInstalRecovModel {

	private int id;

	private String refNo;

	private String sancDate;

	private String principalAmt;

	private String sancAmt;

	private String payCalPeriod;

	private String rateRecovery;

	private String totInstalment;

	private String amtRecovered;

	private String instalmentRecovered;

	private String startYearMm;

	private String endYearMm;

	private String logUser;

	private String logIp;

}
