package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PmBonusMstIdClass implements Serializable {

	@Id
	@Column(name = "EMPID")
	private String employeeId;

	@Id
	@Column(name = "FINANCIAL_YEAR")			         
	private String financialYear;
}
