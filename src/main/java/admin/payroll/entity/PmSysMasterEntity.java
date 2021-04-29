package admin.payroll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

@Data
@IdClass(PmSysMasterEntityId.class)
@Table(name = "pm_sys_master")
@Entity

@NamedStoredProcedureQueries({

		@NamedStoredProcedureQuery(name = "Salary.sp_salaryprocess1", procedureName = "SP_SALARYPROCESS1", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "GU", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "PP", type = String.class) }),

		@NamedStoredProcedureQuery(name = "Salary.sp_salaryprocess2", procedureName = "SP_SALARYPROCESS2", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "GU", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "PP", type = String.class) }),

		@NamedStoredProcedureQuery(name = "Salary.sp_salaryprocess3", procedureName = "SP_SALARYPROCESS3", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "GU", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "PP", type = String.class) }),

		@NamedStoredProcedureQuery(name = "Salary.pm_mnth_closing", procedureName = "PM_MNTH_CLOSING", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "GU", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "PP", type = String.class) }),

		@NamedStoredProcedureQuery(name = "Salary.pm_sal_reverse", procedureName = "PM_SAL_REVERSE", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "GU", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "PP", type = String.class) }),

})
public class PmSysMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GROUPUNIT")
	private String groupUnit;

	@Id
	@Column(name = "BILLUNIT")
	private String billUnit;

	@Column(name = "FROMDATE")
	private Date fromDate=new Date();

	@Column(name = "TODATE")
	private Date toDate=new Date();

	@Column(name = "PAYCALPERIOD")
	private String payCalPeriod;

	@Column(name = "WAGEPERIOD")
	private String wagePeriod;

	@Column(name = "PAYCOMMISSION")
	private String paycommission;

	@Column(name = "MSTATUS")
	private String mStatus;

	@Column(name = "MBACKUP")
	private String mBackup;

	@Column(name = "LOGDATE")
	private Date LOGDATE;

	@Column(name = "LOGUSER")
	private String LOGUSER;

	@Column(name = "LOGTIME")
	private String LOGTIME;

	@Column(name = "LOGIP")
	private String LOGIP;

}
