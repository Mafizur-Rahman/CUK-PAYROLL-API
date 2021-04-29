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

@Entity
@Table(name = "GPF_CLOSGBAL")
@Data
public class GpfCloseBalEntity {
	
	@Id
	@Column(name = "EMPID")
	private String employeeId;
	
	@Column(name = "FYR")
	private String financialYear;
	
	@Column(name = "CLOS_BAL")
	private Double closeBal;
	
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
