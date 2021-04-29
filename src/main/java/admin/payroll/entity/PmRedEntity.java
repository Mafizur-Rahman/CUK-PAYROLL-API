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

@IdClass(PmRedIdClass.class)
@Data
@Entity
@Table(name = "PM_RED")
public class PmRedEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPNO", length = 50)
	private String empNo;

	@Id
	@Column(name = "EARNINGDEDUCTION", length = 50)
	private String earningDeduction;

	@Id
	@Column(name = "REFNO", length = 50)
	private String refNo;

	@Column(name = "REFDATE")
	private Date refDate;

	@Id
	@Column(name = "STARTYEARMM", length = 50)
	private String startYearMm;

	@Column(name = "ENDYEARMM")
	private String endYearMm;

	@Column(name = "AMOUNT")
	private double amt;

	@Column(name = "PAYCALPERIOD")
	private String paycalPeriod;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGUSER")
	private String logUser;

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
