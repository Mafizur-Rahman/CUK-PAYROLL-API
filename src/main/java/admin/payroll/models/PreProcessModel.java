package admin.payroll.models;

import lombok.Data;

@Data
public class PreProcessModel {
	
	private String selectGroupUnit;
	private String financialYear;
	private Double amount;

}
