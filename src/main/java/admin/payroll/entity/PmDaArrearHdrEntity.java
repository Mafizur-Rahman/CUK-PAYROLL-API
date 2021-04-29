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

@IdClass(PmDaArrearHdrEntityId.class)
@Data
@Entity
@Table(name = "pm_da_arrear_hdr")

public class PmDaArrearHdrEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GROUPUNIT",length = 50)
	private String groupUnit;

	@Id
	@Column(name = "BILLUNIT",length = 50)
	private String billUnit;

	@Id
	@Column(name = "EMPNO",length = 50)
	private String empNo;

	@Id
	@Column(name = "PAYCALPERIOD",length = 50)
	private String payCalPeriod;

	@Column(name = "NETBASIC")
	private String netBasic;

	@Column(name = "TOT_AMT_DRAWN")
	private String totAmtDrawn;

	@Column(name = "TOT_AMT_DUE")
	private String totAmtDue;

	@Column(name = "TOT_AMT_ARREARS")
	private String totAmtArrears;

	@Column(name = "POSTED")
	private String posted;

	@Column(name = "PROCESS_TYPE")
	private String processType;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGDATE")
	private Date logDate = new Date();

	@Column(name = "LOGTIME")
	private long LogTime;

	@Column(name = "LOGIP")
	private String logIp;

}
