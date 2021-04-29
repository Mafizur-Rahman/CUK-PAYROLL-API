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
@Table(name = "PM_DESIG")

public class PmDesigEntity {

	@Id
	@Column(name = "DESIGCODE")
	private String desigCode;

	@Column(name = "DESIGSHORTDESC")
	private String desigShortDesc;

	@Column(name = "DESIGLONGDESC")
	private String desigLongDesc;

	@Column(name = "PMCELL")
	private String payCell;

	@Column(name = "PMLEVEL")
	private String payLevel;

	@Column(name = "GRADEPAY")
	private String gradePay;

	@Column(name = "ISACTIVE")
	private String isActive;

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
