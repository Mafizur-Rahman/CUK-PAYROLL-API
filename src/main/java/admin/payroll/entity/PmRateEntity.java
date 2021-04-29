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

import org.springframework.format.annotation.DateTimeFormat;

import admin.payroll.models.PmRateEntityId;
import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@IdClass(PmRateEntityId.class)
@Data
@Entity
@Table(name = "pm_rate")

public class PmRateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EARNINGDEDUCTION", length = 50)
	private String earningDeduction;

	@Id
	@Column(name = "FROMDATE")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate fromDate;

	@Column(name = "TODATE")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate toDate;

	@Column(name = "RATE")
	private Double rate;

	@Column(name = "ISACTIVE")
	private String isActive;

	@Column(name = "PAYCOMISSION")
	private int payComission;

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
