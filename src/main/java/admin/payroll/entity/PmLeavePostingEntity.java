package admin.payroll.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@Entity
@Table(name = "PM_LVPOST")

public class PmLeavePostingEntity {

	@Id
	@Column(name = "EMPNO", length = 9)
	private String empNo;

	@Column(name = "LEAVECODE", length = 2)
	private String leaveCode;

	@Column(name = "LEAVEYEAR", length = 4)
	private String leaveYear;

	@Column(name = "FROMDATE", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate fromDate;

	@Column(name = "FROMWHICHHALF", length = 1)
	private String fromWhichHalf;

	@Column(name = "APPLICATIONTYPE", length = 1)
	private String applicationType;
	@Column(name = "TODATE", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate toDate;

	@Column(name = "TOWHICHHALF", length = 1)
	private String toWhichHalf;

	@Column(name = "DAYSNO", length = 4)
	private String daysNo;

	@Column(name = "COMMUTE", length = 1)
	private String commute;

	@Column(name = "REASON", length = 2)
	private String reason;

	@Column(name = "POSTDATEATTENDANCE", length = 1)
	private String postDataAttendance;

	@Column(name = "OFFICEORDERNO", length = 10)
	private String officeOrderNo;

	@Column(name = "OFFICEORDERDATE", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate officeOrderDate;

	@Column(name = "SUPPLEMENTARY", length = 1)
	private String supplementary;

	@Column(name = "LOGUSER", length = 50)
	private String logUser;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGTIME", length = 50)
	private String logTime;

	@Column(name = "LOGIP", length = 20)
	private String logIp;

	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = DateTimeUtility.getCurrentDate();
		this.logTime = DateTimeUtility.getCurrentTimeString();
	}
}
