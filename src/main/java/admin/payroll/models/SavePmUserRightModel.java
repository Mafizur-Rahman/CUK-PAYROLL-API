package admin.payroll.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;

import admin.payroll.utils.BooleanToStringConvertor;
import lombok.Data;

@Data
public class SavePmUserRightModel {

	// private Integer id;

	private String userId;
	private String logUser;
	private List<SavePmUserRightInnerModel> rolesData;
	
//
//	private List roleId;
//
//	private List isInsert;
//
//	private List isDelete;
//
//	private List isUpdate;
//
//	private List isView;
	
	@Data
	public static class SavePmUserRightInnerModel  {
		private String roleId;
		private Boolean insert;
		private Boolean delete;
		private Boolean update;
		private Boolean view;
	}

}
