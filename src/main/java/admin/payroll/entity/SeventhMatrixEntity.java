package admin.payroll.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@IdClass(SeventhMatrixEntityId.class)
@Table(name = "SEVENTH_MATRIX")
@Entity
public class SeventhMatrixEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PAYBAND")
	private String payBand;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GP")
	private Integer gp;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAY_STEP")
	private Integer payStep;

	@Column(name = "COL")
	private Integer col;

	@Column(name = "LEVEL_PAY")
	private String levelPay;

	@Column(name = "SCALE_NO")
	private Integer scaleNo;

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
