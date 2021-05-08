package admin.payroll.entity;

import static admin.payroll.utils.DateTimeUtility.getCurrentDate;
import static admin.payroll.utils.DateTimeUtility.getCurrentTimeString;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;
@IdClass(PmPayMasteEntityId.class)
@Data
@Entity
@Table(name="pm_paymaster")
public class PmPayMasteEntity {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="EMPNO")
	private String empNo;
	@Id
	@Column(name="EARNINGDEDUCTION")
	private String earningdeduction;
	
	@Id
	@Column(name="PAYPERIOD")
	private String payperiod;
	
	@Column(name="RATE")
	private Integer rate;
	
	@Column(name="NO_OF_DAYS")
	private Integer noOfDays;
	
	@Column(name="AMT")
	private Integer amt;
	
	@Column(name="LOGUSER")
	private String logUser;
	
	@Column(name="LOGDATE")
	private LocalDate logDate;
	
	@Column(name="LOGIP")
	private String logIp;
	
	/*
	 * 
	 * */
	
	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = getCurrentDate();
		//this.logTime = getCurrentTimeString();
	}
}
