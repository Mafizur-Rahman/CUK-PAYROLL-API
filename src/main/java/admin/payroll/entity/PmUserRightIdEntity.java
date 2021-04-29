package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class PmUserRightIdEntity  implements Serializable{
	
	@Id
	@Column(name = "USERNAME")
	private String userId;

	@Id
	@Column(name = "ROLEID")
	private String roleId;

}
