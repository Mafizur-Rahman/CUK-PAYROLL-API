package admin.payroll.models;

import lombok.Data;

@Data
public class GetBankDetails {

	private String bankName;

	private String ifscCode;
	
	private String bankCode;

}
