package admin.payroll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

@IdClass(PmDaArrearDtlEntityId.class)
@Data
@Entity
@Table(name = "pm_da_arrear_dtl")

@NamedStoredProcedureQuery(name = "Arrear.da_arr_proc", procedureName = "DA_ARR_PROC", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "PGROUPUNIT", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "PBILLUNIT", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "FRDT", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "TODT", type = String.class) })

public class PmDaArrearDtlEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GROUPUNIT", length = 50)
	private String groupUnit;

	@Id
	@Column(name = "BILLUNIT", length = 50)
	private String billUnit;

	@Id
	@Column(name = "EMPNO", length = 50)
	private String empNo;

	@Id
	@Column(name = "PAYPERIOD", length = 50)
	private String PayPeriod;

	@Column(name = "NETBASIC")
	private String netBasic;

	@Column(name = "AMT_DRAWN")
	private String amtDrawn;

	@Column(name = "AMT_DUE")
	private String amtDue;

	@Column(name = "AMT_ARREARS")
	private String amtArrear;

	@Column(name = "POSTED")
	private String posted;

	@Column(name = "PROCESS_TYPE")
	private String processType;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGDATE")
	private Date logDate = new Date();

//	@Column(name = "LOGTIME")
//	private int LogTime;

	@Column(name = "LOGIP")
	private String logIp;

}
