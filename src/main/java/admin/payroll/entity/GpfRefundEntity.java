package admin.payroll.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GPFMAST_REF")
@NoArgsConstructor
@Data
public class GpfRefundEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACKNUMBER")
	private Integer acknowledgementNumber;
	
	@Column(name = "EMPID")
	private String employeeId;
	
	@Column(name = "GPFREF")
	private Double refundAmount;
	
	@Column(name = "REFDATE")
	private Date refundDate;
	
	@Column(name = "DTFROM")
	private Date dateFrom;
	
	@Column(name = "DTTO")
	private Date dateTo;
	
	@Column(name = "INSTDT")
	private Date installmentDate;
	
	@Column(name = "GPREF_NO")
	private int gpfReferenceNo;
	
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
