package admin.payroll.models;

import java.util.List;
import lombok.Data;

@Data
public class SaveInstalRecovModel {

	private String empNo;

	private String type;

	private List empNos;

	private String earningDeduction;

	private List refNo;

	private List sancDate;

	private List principalAmt;

	private List sancAmt;

	private List payCalPeriod;

	private List rateRecovery;

	private List totInstalment;

	private List amtRecovered;

	private List instalmentRecovered;

	private List startYearMm;

	private List endYearMm;

	private String logUser;

	private String logIp;
}
