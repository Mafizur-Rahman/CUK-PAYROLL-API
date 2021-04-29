package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class SeventhMatrixEntityId implements Serializable {

	@Id
	@Column(name = "PAYBAND")
	private String payBand;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GP")
	private Integer gp;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAY_STEP")
	private Integer payStep;
}
