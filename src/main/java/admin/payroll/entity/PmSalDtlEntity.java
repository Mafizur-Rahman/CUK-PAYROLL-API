package admin.payroll.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PM_SAL_DTL")
public class PmSalDtlEntity {

	@Id
	@Column(name = "EMPNO")
	private String empNo;
	
//	@Id
	@Column(name = "EARNINGDEDUCTION")
	private String earningDeduction;

//	@Id
	@Column(name = "REFNO")
	private String refNo;

	@Column(name = "EFFECTDATE")
	private Date effectDate;

//	@Id
	@Column(name = "PAYPERIOD")
	private String payPeriod;

	@Column(name = "PAYCALPERIOD")
	private String paycalPeriod;

	@Column(name = "RATE")
	private double rate;

	@Column(name = "AMT")
	private double amt;

	@Column(name = "NOTEFFECTED")
	private String notEffected;

	@Column(name = "INSTNO")
	private double instNo;

	@Column(name = "BALINST  ")
	private double balInst;

	@Column(name = "BALLOAN")
	private double balLoan;

	@Column(name = "FILEKEY")
	private String fileKey;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name = "GROUPUNIT")
	private String groupUnit;

	@Column(name = "BILLUNIT")
	private String billUnit;

	@Column(name = "LOGDATE")
	private Date logDate;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGTIME")
	private String logTime;

	@Column(name = "LOGIP")
	private String logIp;

}
