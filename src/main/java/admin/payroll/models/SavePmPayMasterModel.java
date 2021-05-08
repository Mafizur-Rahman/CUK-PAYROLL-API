package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
public class SavePmPayMasterModel {
	


	private String empNo;
	private String payperiod;
    private String logUser;
    private String logIp;
	private List<PayMasterRows> payMasterRows;
    
    
	@Data
	public static class PayMasterRows {
		private String earningdeduction;
		private Integer rate;
		private Integer noOfDays;
		private Integer amt;
	}	
	
}
