package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class EmpPmMedIdClass implements Serializable {

	@Id
	@Column(name = "empno", length = 50)
	private String empNo;

	@Id
	@Column(name = "earningdeduction", length = 50)
	private String earningDeduction;

	@Id
	@Column(name = "payperiod", length = 50)
	private String payPeriod;

	@Id
	@Column(name = "refno", length = 50)
	private String refNo;
}
