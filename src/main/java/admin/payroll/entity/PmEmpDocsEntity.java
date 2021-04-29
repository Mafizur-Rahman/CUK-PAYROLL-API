package admin.payroll.entity;

import java.io.Serializable;
import java.time.LocalDate;

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

@Data
@Entity
@Table(name = "PM_EMP_DOCS")
public class PmEmpDocsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "EMPID")
	private String empId;

	@Column(name = "DOCTYPE")
	private String docType;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name = "FILEPATH")
	private String filePath;

	@Column(name = "FINANCIALYEAR")
	private String financialYear;

	@Column(name = "KINID")
	private String kinName;

	@Column(name = "DOCUMENTOF")
	private String documentOf;

	@Column(name = "DOCUMENTNAME")
	private String documentName;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGDATE")
	private  LocalDate logDate;

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
