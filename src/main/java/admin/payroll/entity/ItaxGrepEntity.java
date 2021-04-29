package admin.payroll.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ITAXGREP")
public class ItaxGrepEntity {
	
	@Id
	@Column(name="EMPID")
	private String empId;
	
	@Column(name="MONTHID")
	private Double mounthId;
	
	@Column(name="YEARID")
	private Double yearId;
	
	@Column(name="DA")
	private Double da;
	
	@Column(name="HRA")
	private Double hra;
	
	@Column(name="CCA")
	private Double cca;
	
	@Column(name="TOTAL")
	private Double total;
	
	@Column(name="GIS")
	private Double gis;
	
	@Column(name="EIS")
	private Double eis;
	
	@Column(name="TALLOW")
	private Double tallow;
	
	@Column(name="BASIC")
	private Double basic;
	
	@Column(name="PROF_TAX")
	private Double profTax;
	
	@Column(name="DP")
	private Double dp;
	
	@Column(name="BASIC_NEW")
	private Double basicNew;
	
	@Column(name="CGHS")
	private Double cghs;
	
	@Column(name="TA_EXEM")
	private Double taExem;
	
	@Column(name="TUTFEE_EXEM")
	private Double tutfeeExem;
		

}
