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

@IdClass(EmpPmMedIdClass.class)
@Data
@Entity
@Table(name = "PM_MED")
public class EmpPmMedEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "empno", length = 50)
	private String empNo;

	@Id
	@Column(name = "earningdeduction", length = 50)
	private String earningDeduction;

	@Id
	@Column(name = "payperiod", length = 50)
	private String payPeriod;

	@Id
	@Column(name = "refno", length = 50)
	private String refNo;

	@Column(name = "refdate")
	private Date refDate;

	@Column(name = "amt")
	private double amt;

	@Column(name = "paycalperiod")
	private String payCalPeriod;

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
