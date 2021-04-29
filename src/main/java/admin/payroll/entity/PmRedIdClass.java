package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PmRedIdClass implements Serializable {

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
	@Column(name = "STARTYEARMM", length = 50)
	private String startYearMm;

}
