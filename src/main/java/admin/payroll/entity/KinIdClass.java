package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class KinIdClass implements Serializable {

	@Id
	@Column(name = "EMPID")
	private String empId;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SERLNO")
	private Integer serlNo;

}
