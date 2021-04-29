package admin.payroll.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@IdClass(PmBonusMstIdClass.class)
@Data
@Entity
@Table(name = "PM_BONUS_MST")

@NamedStoredProcedureQueries({

	@NamedStoredProcedureQuery(
			name = "SPBONUSPREPROCESS",
			procedureName = "SP_BONUS_PREPROCESS", 
	        parameters = { 
	        		@StoredProcedureParameter(mode = ParameterMode.IN, name = "PCLASS", type = String.class), 
	        		@StoredProcedureParameter(mode = ParameterMode.IN, name = "FYR", type = String.class), 
	        		@StoredProcedureParameter(mode = ParameterMode.IN, name = "AMT", type = Double.class), 	        		
			}),
	
	@NamedStoredProcedureQuery(
			name = "SPBONUSREVESRSE",
			procedureName = "SP_BONUS_REVERSE", 
	        parameters = { 
	        		@StoredProcedureParameter(mode = ParameterMode.IN, name = "PCLASS", type = String.class), 
	        		@StoredProcedureParameter(mode = ParameterMode.IN, name = "FYR", type = String.class), 
	        		
			}),
	
})


public class PmBonusMstEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EMPID")
	private String employeeId;

	@Column(name="NAME")
	private String name;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="DOB")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate dob;
	
	@Column(name="CLASS")
	private String classes;
	
	@Column(name="DESIG")
	private String desig;
	
	
	@Id
	@Column(name = "FINANCIAL_YEAR")			         
	private String financialYear;

	@Column(name = "GROUPUNIT")
	private String groupUnit;

	@Column(name = "CDA_CATID")
	private String cdaCategory;

	@Column(name = "PAYRATE")
	private Double payRate;

	@Column(name = "GRADEPAY")
	private Double gradePay;

	@Column(name = "RECY")
	private Double recovery;

	@Column(name = "ABSENCE")
	private Double absenceDays;

	@Column(name = "PAYDAYS")
	private Double payDays;

	@Column(name = "LEAVEDAYS")
	private Double leaveDays;

	@Column(name = "LEAVEAMOUNT")
	private Double leaveAmount;
	
	@Column(name = "BONUS")
	private Double bonusAmount;
	
	@Column(name = "TOTDEDUCTION")
	private Double totalDeduction;
	
	@Column(name = "NETPAY")
	private Double newPay;
	
	@Column(name = "REV")
	private Double rev;
	
	@Column(name="EOLAMT")
	private Double eolamt;
	
	@Column(name = "FINALAMT")
	private Double finalAmount;
	
	@Column(name = "LOCKSTATUS")
	private String lockStatus;
	
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
