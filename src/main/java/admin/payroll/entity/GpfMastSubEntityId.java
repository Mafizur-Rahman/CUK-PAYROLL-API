package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class GpfMastSubEntityId implements Serializable {
	
	@Id
	@Column(name="EMPID")
	private String empId;
	
	@Id
	@Column(name="PAYCALPERIOD")
	private String payCalPeriod;

}
