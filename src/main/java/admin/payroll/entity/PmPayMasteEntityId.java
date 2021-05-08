package admin.payroll.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data

public class PmPayMasteEntityId implements Serializable{
	
	@Id
	
	@Column(name="EMPNO")
	private String empNo;
	@Id
	@Column(name="EARNINGDEDUCTION")
	private String earningdeduction;
	
	@Id
	@Column(name="PAYPERIOD")
	private String payperiod;
	

}
