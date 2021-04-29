package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ItaxDataEntityId implements Serializable {

	@Id
	@Column(name="EMPID")
	private String employeeId;
	
	@Id
	@Column(name="MONTHID")
	private Double monthId;
	
	@Id
	@Column(name="YEARID")
	private Double yearId;
}
