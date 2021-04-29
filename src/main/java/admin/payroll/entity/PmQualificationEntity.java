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
@Table(name = "PM_QUALIFICATION")
public class PmQualificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "EMPID")
	private String empId;

	@Column(name = "QUALCODE")
	private String qualCode;

	@Column(name = "SUBJECTCODE")
	private String subjectCode;

	@Column(name = "UNIVERSITYNAME")
	private String universityName;

	@Column(name = "PLACE")
	private String place;

	@Column(name = "ISDRDOSPONSORED")
	private String isDrdoSponsored;

	@Column(name = "QUAL_ACCUIRED")
	private String qualificationAcquired;

	@Column(name = "PASSINGYEAR")
	private int passingYear;

	@Column(name = "GRADE_MARKS")
	private String gradeMarks;

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
		this.logDate = getCurrentDate();
		this.logTime = getCurrentTimeString();
	}
}
