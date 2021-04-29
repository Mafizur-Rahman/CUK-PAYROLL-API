package admin.payroll.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@IdClass(KinIdClass.class)
@Entity
@Table(name = "KIN")
public class KinEntity implements Serializable {

//	@Column(name = "ID")
//	private int id;
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPID")
	private String empId;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SERLNO")
	private Integer serlNo;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SEX")
	private String sex;

	@Column(name = "RELT") // RELN
	private String reln;

	@Column(name = "BDATE")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private LocalDate bDate;

	@Column(name = "DEPDNT")
	private String depdnt;

	@Column(name = "ISEMPLOYED")
	private String isEmployed;

	@Column(name = "OCCPN")
	private String occpn;

	@Column(name = "NOMINEE")
	private String nominee;

	@Column(name = "SCHOOL")
	private String school;

	@Column(name = "CLASS")
	private String classs;

	@Column(name = "PHCODE")
	private String handicappedCategory;

	@Column(name = "KVDRDO") // ISKVDRDO
	private String isKvDrdo;

	@Column(name = "ADMISSIONNO")
	private Integer admissionNo;

	@Column(name = "CGHS_BEN_ID")
	private Integer cghsBenId;

	@Column(name = "GOVT_EMPLOYEE")
	private String govtEmployee;

	@Column(name = "GPF_NOMINEE")
	private String gpfNominee;

	@Column(name = "PENSION_NOMINEE")
	private String pensionNominee;

	@Column(name = "PENSION_SHARE")
	private Integer pensionShare;

	@Column(name = "MARRIED") // MARITASTATUS
	private String maritialStatus;

	@Column(name = "CGEIS_NOMINEE")
	private String cgeisNominee;

	@Column(name = "CGEIS_SHARE")
	private Integer cgeisShare;

	@Column(name = "ADOPTED")
	private String adopted;

	@Column(name = "FPA_DESGN_CODE")
	private Integer FpaDesinCode;

	@Column(name = "FPA_DATE")
	private Date FpaDate;

	@Column(name = "PENSION_NOMINEE_NO")
	private Integer pensionNomineeNo;

	@Column(name = "CGEIS_NOMINEE_NO")
	private Integer cgeisNomineeNo;

	@Column(name = "GPF_NOMINEE_NO")
	private Integer gpfNomineeNo;

	@Column(name = "ISHANDICAPPED")
	private String isHandicapped;

	@Column(name = "HANDICAPPEDPERCENTAGE")
	private Integer handicappedPercentage;

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
