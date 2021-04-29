package admin.payroll.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@Entity
@Table(name = "PM_EED")

public class PmEedEntity {

	@Id
	@Column(name = "EARNINGDEDUCTION")
	private String earningDeduction;

	@Column(name = "SHORTDESC")
	private String shortDesc;

	@Column(name = "LONGDESC")
	private String longDesc;

	@Column(name = "ALLOCATION")
	private String allocation;

	@Column(name = "PAYCOMISSION")
	private String payCommission;

	@Column(name = "ISITPROJ")
	private String isItProj;

	@Column(name = "ISITHEAD")
	private String isItHead;

	@Column(name = "ITHEAD")
	private String itHead;

	@Column(name = "DACALCULATION")
	private String daCalculation;

	@Column(name = "GROSSPT")
	private String grossPt;

	@Column(name = "GROSSBONUS")
	private String grossBonus;

	@Column(name = "GROSSPF")
	private String grossPf;

	@Column(name = "GROSS")
	private String gross;

	@Column(name = "CARRY")
	private String carry;

	@Column(name = "LOCALITYCLASS")
	private String localityClass;

	@Column(name = "BASICDEPENDENT")
	private String basicDependent;

	@Column(name = "PRARATEEDIT")
	private String praRateType;

	@Column(name = "PRAEDIT")
	private String rateToBeEntered;

	@Column(name = "MEDEDIT")
	private String miscellaneous;

	@Column(name = "LOANEDIT")
	private String installment;

	@Column(name = "REDEDIT")
	private String fixed;

	@Column(name = "ACTIVE")
	private String active;

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
