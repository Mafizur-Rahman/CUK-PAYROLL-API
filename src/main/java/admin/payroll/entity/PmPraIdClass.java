package admin.payroll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PmPraIdClass implements Serializable {

	@Id
	@Column(name = "EMPNO")
	private String empNo;

	@Id
	@Column(name = "EARNINGDEDUCTION")
	private String earningDeduction;

	@Id
	@Column(name = "FROMDATE")
	private Date fromDate;

}
