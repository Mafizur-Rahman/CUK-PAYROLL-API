package admin.payroll.entity;

import static admin.payroll.utils.DateTimeUtility.getCurrentDate;
import static admin.payroll.utils.DateTimeUtility.getCurrentTimeString;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PM_USERS")
public class PmUsersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="USERTYPE")
	private String userType;
	
	@Column(name="SECTION")
	private String section;
	
	@Column(name="CLASS")
	private String classess;
	

	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="DESIG")
	private String desig;
	
	@Column(name="MOBILENO")
	private String mobileNo;
	
	@Column(name="EMAILID")
	private String emailId;
	
	@Column(name="LOGINID")
	private String loginId;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="LOGUSER")
	private String logUser;
	
	@Column(name="LOGDATE")
	private LocalDate logDate;
	
	@Column(name="LOGTIME")
	private String logTime;
	
	@Column(name="LOGIP")
	private String logIp;
	
	/*
	 * 
	 * */
	
	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = getCurrentDate();
		this.logTime = getCurrentTimeString();
	}
}
