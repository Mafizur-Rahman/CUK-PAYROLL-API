package admin.payroll.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SavePfmsTempModel {

	private List<PfmsTempList> dbt01Data;

	@Data
	public static class PfmsTempList {
		private Integer id;

		private String name;

		private String nameReg;

		private String gender;

		private String addressLine1;

		private String addressLine2;

		private String addressLine3;

		private String district;

		private String state;

		private String country;

		private String bankName;

		private String ifscCode;

		private String accountNumber;

		private String aadharNumber;

		private String pinCode;

		private String schemeCode;

		private Double subventionAmount;

		private String cpsmsCode;

		private String financialYear;

		private LocalDate quarter;

		private String uploadedBy;

		private String uploadedDate;

		private String accountStatus;

		private String casteCategory;

		private LocalDate logDate;

		private String logTime;

		private String logUser;

		private String logIp;
	}

}
