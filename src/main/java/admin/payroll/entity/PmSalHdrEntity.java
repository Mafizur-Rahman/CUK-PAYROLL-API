package admin.payroll.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PM_SAL_HDR")
public class PmSalHdrEntity {

	@Id
	@Column(name = "EMPNO")
	private String empNo;

	
	@Column(name = "PAYCALPERIOD")
	private String paycalPeriod;

	@Column(name = "PAYCATEGORY")
	private String payCategory;

	@Column(name = "BILLUNIT")
	private String billUnit;

	@Column(name = "CATID")
	private Integer catId;

	@Column(name = "CLASS")
	private String classs;

	@Column(name = "SCALECODE")
	private String scaleCode;

	@Column(name = "PLEVEL")
	private String pLevel;

	@Column(name = "PCELL")
	private String pCell;

	@Column(name = "PAYMODE")
	private String payMode;

	@Column(name = "BANKCODE  ")
	private String bankCode;

	@Column(name = "ACCOUNTNO")
	private String accountNo;

	@Column(name = "GROUPUNIT")
	private String groupUnit;

	@Column(name = "DESIGCODE")
	private String desigCode;

	@Column(name = "GAHOURS")
	private Double gaHours;

	@Column(name = "LOSTHOURS")
	private Double lostHours;

	@Column(name = "NOTHOURS")
	private Double notHours;

	@Column(name = "FOTHOURS")
	private Double fotHours;

	@Column(name = "NDAHOURS")
	private Double ndaHours;

	@Column(name = "NHAHOURS")
	private Double nhaHours;

	@Column(name = "MONTHDAYS")
	private Double monthDays;

	@Column(name = "LHAP")
	private Double lhap;

	@Column(name = "LWP")
	private Double lwp;

	@Column(name = "ABSENCE")
	private Double absence;

	@Column(name = "SLIPNO  ")
	private Double slipNo;

	@Column(name = "PAYDAYS")
	private Double payDays;

	@Column(name = "LEAVEDAYS")
	private Double leaveDays;

	@Column(name = "GROSSPAY")
	private Double grossPay;

	@Column(name = "TOTDEDUCTION")
	private Double totDeduction;

	@Column(name = "NETPAY  ")
	private Double netPay;

	@Column(name = "LOGDATE")
	private Date logDate;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGTIME")
	private String logTime;

	@Column(name = "LOGIP")
	private String logIp;

}
