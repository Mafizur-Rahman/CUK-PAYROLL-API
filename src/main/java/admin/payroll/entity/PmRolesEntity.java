package admin.payroll.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PM_ROLES")
public class PmRolesEntity {
	
	@Id
	@Column(name="ROLEID")
	private String roleId;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ACTIVE")
	private String active;
	
	@Column(name="LOGUSER")
	private String logUser;
	
	@Column(name="LOGDATE")	
	private Date logDate;
	
	@Column(name="LOGTIME")
	private String logTime;
	
	@Column(name="LOGIP")
	private String logIp;
	
}
