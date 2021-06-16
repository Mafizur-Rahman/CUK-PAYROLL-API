package admin.payroll.entity;

import static admin.payroll.utils.DateTimeUtility.getCurrentDate;
import static admin.payroll.utils.DateTimeUtility.getCurrentTimeString;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PM_PFMS")
public class PmPfmsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "NAME_REG")
	private String nameReg;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE2")
	private String addressLine2;

	@Column(name = "ADDRESS_LINE3")
	private String addressLine3;

	@Column(name = "DISTRICT")
	private String district;

	@Column(name = "STATE")
	private String state;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "BANK_NAME")
	private String bankName;

	@Column(name = "IFSC_CODE")
	private String ifscCode;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "AADHAR_NUMBER")
	private String aadharNumber;

	@Column(name = "PIN_CODE")
	private String pinCode;

	@Column(name = "SCHEME_CODE")
	private String schemeCode;

	@Column(name = "SUBVENTION_AMOUNT")
	private Double subventionAmount;

	@Column(name = "CASTE_CATEGORY")
	private String casteCategory;

	@Column(name = "IS_VALIDATED_BY_PFMS")
	private String isValidatedByPfms;

	@Column(name = "VALIDATION_DATE")
	private LocalDate validationDate;

	@Column(name = "VUID")
	private String vuId;

	@Column(name = "BENEFICIARY_CODE")
	private String cpsmsCode;

//	@Column(name = "FINANCIAL_YEAR")
//	private String financialYear;
//
//	@Column(name = "QUARTER")
//	private LocalDate quarter;

	@Column(name = "UPLOADED_BY")
	private String uploadedBy;

	@Column(name = "UPLOADED_DATE")
	private Date uploadedDate;

	@Column(name = "ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name = "VPUID")
	private String vpuId;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGTIME")
	private String logTime;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGIP")
	private String logIp;

	/*
	 * 
	 * */

	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = getCurrentDate();
		this.logTime = getCurrentTimeString();
	}
}
