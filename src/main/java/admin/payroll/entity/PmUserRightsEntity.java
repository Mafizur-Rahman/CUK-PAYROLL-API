package admin.payroll.entity;

import static admin.payroll.utils.DateTimeUtility.getCurrentDate;
import static admin.payroll.utils.DateTimeUtility.getCurrentTimeString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import admin.payroll.utils.BooleanToStringConvertor;
import lombok.Data;
import static admin.payroll.utils.DateTimeUtility.*;
@Data
@Entity
@IdClass(PmUserRightIdEntity.class)
@Table(name = "PM_USERRIGHTS")
public class PmUserRightsEntity implements Serializable{

	private static final long serialVersionUID = 1L;
//	@Id
//	@Column(name = "ID")
//	private Integer id;

	@Id
	@Column(name = "USERNAME")
	private String userId;

	@Id
	@Column(name = "ROLEID")
	private String roleId;

	@Convert(converter = BooleanToStringConvertor.class)
	@Column(name = "ISINSERT")
	private Boolean insert;

	@Convert(converter = BooleanToStringConvertor.class)
	@Column(name = "ISDELETE")
	private Boolean delete;

	@Convert(converter = BooleanToStringConvertor.class)
	@Column(name = "ISUPDATE")
	private Boolean update;

	@Convert(converter = BooleanToStringConvertor.class)
	@Column(name = "ISVIEW")
	private Boolean view;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGTIME")
	private String logTime;
	
	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = getCurrentDate();
		this.logTime = getCurrentTimeString();
	}
	
	
}
