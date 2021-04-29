package admin.payroll.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class PmUserRightsDto {
	
	private String userId;
	private String roleId;
	private String isInsert;
	private String isDelete;
	private String isUpdate;
	private String isView;
	private String description;
	
	public PmUserRightsDto(String userId, String roleId, String isInsert, String isDelete, String isUpdate,
			String isView, String description) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.isInsert = isInsert;
		this.isDelete = isDelete;
		this.isUpdate = isUpdate;
		this.isView = isView;
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getIsInsert() {
		return isInsert;
	}

	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
