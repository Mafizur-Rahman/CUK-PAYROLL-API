package admin.payroll.models;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ItaxGrepModel {
	
    private String empId;
	
	private Double mounthId;
	
	private Double yearId;
	
	private Double da;
	
	private Double hra;
	
	private Double cca;
	
	private Double total;
	
	private Double gis;
	
	private Double eis;
	
	private Double tallow;
	
	private Double basic;
	
	private Double profTax;
	
	private Double dp;
	
	private Double basicNew;
	
	private Double cghs;
	
	private Double taExem;
	
	private Double tutfeeExem;

}
