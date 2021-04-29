package admin.payroll.models;

import lombok.Data;

@Data
public class BonusPreProcessModel {

	private String selectGroupUnit;
	

	private String financialYear;
//YYYY-YYYY

	private String amount;
}
