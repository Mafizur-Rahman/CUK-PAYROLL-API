package admin.payroll.entity;

import java.time.LocalDate;
import java.util.Date;
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
@Table(name = "PM_CODETYPE")

public class PmCodeTypeEntity {

	@Id
	@Column(name = "CODE")
	private String code;

//	@Column(name = "DESCRIPTION")
//	private String description;

	@Column(name = "SHORTDESC")
	private String shortDesc;

	@Column(name = "LONGDESC")
	private String longDesc;

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
