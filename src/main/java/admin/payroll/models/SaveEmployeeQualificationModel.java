package admin.payroll.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SaveEmployeeQualificationModel {

	private Integer id;

	private String empId;

	private String qualCode;

	private String subjectCode;

	private String universityName;

	private String place;

	private String isDrdoSponsored;

	private String qualificationAcquired;

	private int passingYear;

	private String gradeMarks;

	private String logUser;

	private LocalDate logDate;

	private String logTime;

	private String logIp;

}
