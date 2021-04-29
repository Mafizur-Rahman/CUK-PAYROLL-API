package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PmDaArrearHdrEntityId implements Serializable {

	@Id
	@Column(name = "GROUPUNIT",length = 50)
	private String groupUnit;

	@Id
	@Column(name = "BILLUNIT",length = 50)
	private String billUnit;

	@Id
	@Column(name = "EMPNO",length = 50)
	private String empNo;

	@Id
	@Column(name = "PAYCALPERIOD",length = 50)
	private String payCalPeriod;

}
