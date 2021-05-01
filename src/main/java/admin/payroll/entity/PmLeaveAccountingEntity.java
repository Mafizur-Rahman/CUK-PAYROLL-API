package admin.payroll.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import admin.payroll.utils.BooleanToStringConvertor;
import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@Entity
@Table(name = "PM_LVAC")

public class PmLeaveAccountingEntity {

	@Id
	@Column(name = "EMPNO", length = 8)
	private String empNo;

	@Column(name = "LEAVECODE", length = 2)
	private String leaveCode;

	@Column(name = "LEAVEYEAR", length = 4)
	private String leaveYear;

	@Column(name = "TOTALLND", length = 4)
	private Integer totalLnd;

	@Column(name = "REMAINLND", length = 4)
	private Integer remainLnd;

	@Column(name = " IHALFLEAVEACCUMULATED", length = 4)
	private Integer iHalfLeaveAccumulated;

	@Column(name = "IHALFLEAVEELIGIBLE", length = 3)
	private Integer iHalfLeaveEligible;

	@Column(name = "IHALFLEAVEAVAILED", length = 4)
	private Integer iHalfLeaveAvailed;

	@Column(name = "IHALFHALFDAYS", length = 3)
	private Integer iHalfHalfDays;

	@Column(name = "IIHALFLEAVEACCUMULATED", length = 4)
	private Integer iiHalfLeaveAccumulated;

	@Column(name = "IIHALFLEAVEELIGIBLE", length = 3)
	private Integer iiHalfLeaveEligible;

	@Column(name = "IIHALFLEAVEAVAILED", length = 4)
	private Integer iiHalfLeaveAvailed;

	@Column(name = "IIHALFHALFDAYS", length = 3)
	private Integer iiHalfHalfDays;

	@Convert(converter = BooleanToStringConvertor.class)
	@Column(name = "IIHALFCREDIT", length = 1)
	private Boolean iiHalfCreadit;

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
