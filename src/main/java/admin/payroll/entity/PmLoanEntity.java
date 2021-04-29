package admin.payroll.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@IdClass(PmLoanEntityID.class)
@Entity
@Table(name = "PM_LOAN")
public class PmLoanEntity implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Id
//	@Column(name = "ID")
//	private String id;
//	
	@Id
	@Column(name = "EMPNO", length = 50)
	private String empNo;

	@Id
	@Column(name = "EARNINGDEDUCTION", length = 50)
	private String earningDeduction;

	@Id
	@Column(name = "REFNO", length = 50)
	private String refNo;

	@Id
	@Column(name = "SANCDATE")
	private Date sancDate;

	@Column(name = "PRINCIPALAMT")
	private double principalAmt;

	@Column(name = "SANCAMT")
	private double sancAmt;

	@Column(name = "PAYCALPERIOD")
	private String paycalPeriod;

	@Column(name = "RATERECOVERY")
	private double rateRecovery;

	@Column(name = "TOTINSTALMENT")
	private double totInstalment;

	@Column(name = "AMTRECOVERED")
	private double amtRecovered;

	@Column(name = "INSTALMENTRECOVERED")
	private double instalmentRecovered;

	@Id
	@Column(name = "STARTYEARMM", length = 50)
	private String startYearMm;

	@Column(name = "ENDYEARMM")
	private String endYearMm;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGTIME")
	private String logTime;

	@Column(name = "LOGIP")
	private String logIp;

	public PmLoanEntity(String empNo, String earningDeduction, String refNo, Date sancDate, double principalAmt,
			double sancAmt, String paycalPeriod, double rateRecovery, double totInstalment, double amtRecovered,
			double instalmentRecovered, String startYearMm, String endYearMm, LocalDate logDate, String logUser,
			String logTime, String logIp) {
		super();
		this.empNo = empNo;
		this.earningDeduction = earningDeduction;
		this.refNo = refNo;
		this.sancDate = sancDate;
		this.principalAmt = principalAmt;
		this.sancAmt = sancAmt;
		this.paycalPeriod = paycalPeriod;
		this.rateRecovery = rateRecovery;
		this.totInstalment = totInstalment;
		this.amtRecovered = amtRecovered;
		this.instalmentRecovered = instalmentRecovered;
		this.startYearMm = startYearMm;
		this.endYearMm = endYearMm;
		this.logDate = logDate;
		this.logUser = logUser;
		this.logTime = logTime;
		this.logIp = logIp;
	}

	public PmLoanEntity() {
		super();
	}
	
	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = DateTimeUtility.getCurrentDate();
		this.logTime = DateTimeUtility.getCurrentTimeString();
	}

}
