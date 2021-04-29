package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
public class RateMasterModel {

	private String earningDeduction;

	private List fromDate;

	private List toDate;

	private List rate;

	private List payCommission;

	private List isActive;

	private String logUser;

	private String logIp;
}
