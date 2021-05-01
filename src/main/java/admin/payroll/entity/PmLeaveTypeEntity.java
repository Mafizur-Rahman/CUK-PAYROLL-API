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
@Table(name = "PM_LVTYPE")

public class PmLeaveTypeEntity {

	@Id
	@Column(name = "LEAVECODE", length = 2)
	private String leaveCode;

	@Column(name = "LEAVEDESC", length = 15)
	private String leaveDesc;

	@Column(name = "LEAVELONGDESC", length = 40)
	private String leaveLongDesc;

	@Column(name = "GROUPAELIGIBLE", length = 1)
	private String groupAEligible;

	@Column(name = "GROUPBELIGIBLE", length = 1)
	private String groupBEligible;

	@Column(name = "GROUPCELIGIBLE", length = 1)
	private String groupCEligible;

	@Column(name = "GROUPDELIGIBLE", length = 1)
	private String groupDEligible;

	@Column(name = "MAXIMUMCREDITDAYS", length = 3)
	private Integer maximumCreditDays;

	@Column(name = "SEXELIGIBLE", length = 1)
	private String sexEligible;

	@Column(name = "HALFDAYPERMISSIBLE", length = 1)
	private String halfDayPermissible;

	@Column(name = "PAYCUT", length = 1)
	private String paycut;

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
