package admin.payroll.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@Entity
@Table(name = "PM_BANK")

public class PmBankEntity {

	@Id
	@Column(name = "BANKCODE")
	private String bankCode;

	@Column(name = "BANKNAME")
	private String bankName;

	@Column(name = "IFSCCODE")
	private String ifscCode;

	@Column(name = "BRANCHCODE")
	private String branchCode;

	@Column(name = "MICRCODE")
	private String micrCode;

	@Column(name = " BANKPANNUMBER")
	private String bankPanNumber;

	@Column(name = "HBABANK")
	private String isHba;

//	@Column(name = "CHECKBANKCODE")
//	private String checkbankcode;

	@Column(name = "BRANCHNAME")
	private String branchName;

	@Column(name = "ADDRESS1")
	private String addrress1;

	@Column(name = "ADDRESS2")
	private String addrress2;

	@Column(name = "CITY")
	private String city;

	@Column(name = "PINCODE")
	private String pincode;

	@Column(name = "STATE")
	private String state;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGTIME")
	private String logTime;

	@Column(name = "LOGIP")
	private String logIp;

	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = DateTimeUtility.getCurrentDate();
		this.logTime = DateTimeUtility.getCurrentTimeString();
	}
}
