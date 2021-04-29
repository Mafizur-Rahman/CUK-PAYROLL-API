package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PmSysMasterEntityId implements Serializable {

	@Id
	@Column(name = "GROUPUNIT")
	private String groupUnit;

	@Id
	@Column(name = "BILLUNIT")
	private String billUnit;

}
