package admin.payroll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PmLoanEntityID implements Serializable {

	@Id
	@Column(name = "EMPNO", length = 50)
	private String empNo;

	@Id
	@Column(name = "EARNINGDEDUCTION", length = 50)
	private String earningDeduction;

	@Id
	@Column(name = "REFNO", length = 50)
	private String refNo;

	@Id
	@Column(name = "SANCDATE")
	private Date sancDate;

	@Id
	@Column(name = "STARTYEARMM", length = 50)
	private String startYearMm;

}
