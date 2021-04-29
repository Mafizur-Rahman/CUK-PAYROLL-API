package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
public class SaveGeneralCodeModel {

	private Integer id;

	private String codeType;

	private List code;

	private String isActive;

	private List shortDesc;

	private List longDesc;

	private String logUser;

	private String logIp;
}
