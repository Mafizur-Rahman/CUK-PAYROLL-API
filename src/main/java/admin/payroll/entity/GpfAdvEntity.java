package admin.payroll.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "GPF_ADVDATA")
public class GpfAdvEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACKNUMBER")
	private Integer acknowledgementNumber;
	
	@Column(name = "EMPID")
	private String employeeId;
	
	@Column(name = "ADVPERIOD")
	private String advancePeriod;
	
	@Column(name = "ADV_DATE")
	private Date date;
	
	@Column(name = "ADV_TYPE")
	private String advanceType;
	
	@Column(name = "ADV_AMT")
	private Double advanceAmount;
	
	@Column(name = "NOINS")
	private Double noOfInstallment;

	@Column(name = "SECTION")
	private String section;
	
	@Column(name = "INSTAMT")
	private Double installmentAmount;

	@Column(name = "BAL_AMT")
	private Double balanceAmount;

	@Column(name = "DVNO")
	private String dvno;

	@Column(name = "OTCR")
	private String otcr;

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
