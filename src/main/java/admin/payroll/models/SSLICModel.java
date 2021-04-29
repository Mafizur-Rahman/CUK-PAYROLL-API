package admin.payroll.models;

import lombok.Data;

@Data
public class SSLICModel {
	private String empNo;
	private String name;
	private String desig;
	private String refNo;
	private Double ssLic;
	public SSLICModel(String empNo, String name, String desig, String refNo, Double ssLic) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.desig = desig;
		this.refNo = refNo;
		this.ssLic = ssLic;
	}
	public SSLICModel() {
		super();
	}

	
}
