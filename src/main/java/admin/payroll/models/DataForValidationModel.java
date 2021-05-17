package admin.payroll.models;

import lombok.Data;

@Data
public class DataForValidationModel {
	private String empNo;
	private String name;
	private String gender;
	private String caddrss1;
	private String caddrss2;
	private String caddrss3;
	private String cdistrict;
	private String state2;
	private String nationality;
	private String bankName;
	private String ifscCode;
	private String banknoNew;
	private Long adharNo;
	private Integer cpinCode;
	private Double netpay;

	public DataForValidationModel(String empNo, String name, String gender, String caddrss1, String caddrss2,
			String caddrss3, String cdistrict, String state2, String nationality, String bankName, String ifscCode,
			String banknoNew, Long adharNo, Integer cpinCode, Double netpay) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.gender = gender;
		this.caddrss1 = caddrss1;
		this.caddrss2 = caddrss2;
		this.caddrss3 = caddrss3;
		this.cdistrict = cdistrict;
		this.state2 = state2;
		this.nationality = nationality;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.banknoNew = banknoNew;
		this.adharNo = adharNo;
		this.cpinCode = cpinCode;
		this.netpay = netpay;
	}

	public DataForValidationModel() {
		super();
	}

}
