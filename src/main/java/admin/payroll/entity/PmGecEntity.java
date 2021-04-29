package admin.payroll.entity;

import java.io.Serializable;
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

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@IdClass(PmGecEntityId.class)
@Data
@Entity
@Table(name = "PM_GEC")

public class PmGecEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
//	private Integer id = 1000 + new Random().nextInt(9999);

	@Id
	@Column(name = "CODETYPE")
	private String codeType;

	@Id
	@Column(name = "CODE")
	private String code;

	@Column(name = "SHORTDESC")
	private String shortDesc;

	@Column(name = "LONGDESC")
	private String longDesc;

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
