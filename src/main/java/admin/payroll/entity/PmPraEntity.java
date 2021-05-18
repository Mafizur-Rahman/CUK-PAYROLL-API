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
@IdClass(PmPraIdClass.class)
@Entity
@Table(name = "PM_PRA")
public class PmPraEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPNO")
	private String empNo;

	@Id
	@Column(name = "EARNINGDEDUCTION")
	private String earningDeduction;

	@Id
	@Column(name = "FROMDATE")
	private Date fromDate;

	@Column(name = "TODATE")
	private Date toDate;

	@Column(name = "DESIGCODE")
	private String desigCode;

//	@Column(name = "SCALECODE")
//	private String scaleCode;

	@Column(name = "PMLEVEL")
	private String pmLevel;

	@Column(name = "PMCELL")
	private String pmCell;

	@Column(name = "RATE")
	private double rate;

//	@Column(name = "LOCALITYCLASS")
//	private String localityClass;

	@Column(name = "OFFICEORDERNO")
	private String officeOrderNo;

	@Column(name = "OFFICEORDERDATE")
	private Date officeOrderDate;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGTIME")
	private String logTime;

	@Column(name = "LOGIP")
	private String logIp;

	@Column(name = "REG_REMARK")
	private String regRemarks;

	@Column(name = "PAYCALPERIOD")
	private String paycallPeriod;

	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = DateTimeUtility.getCurrentDate();
		this.logTime = DateTimeUtility.getCurrentTimeString();
	}

}
