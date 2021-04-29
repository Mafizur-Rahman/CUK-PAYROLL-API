package admin.payroll.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;
import lombok.NoArgsConstructor;

@IdClass(GpfMastSubEntityId.class)
@Data
@Entity
@NoArgsConstructor
@Table(name="GPFMAST_SUB")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "gpfMastSubProcess", procedureName = "SP_MONTH_SUB", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "monthId", type = Integer.class),
	       	@StoredProcedureParameter(mode=ParameterMode.IN, name ="pClass", type = String.class),
	       	@StoredProcedureParameter(mode=ParameterMode.IN, name = "financialYear", type=String.class)
	}),
})
public class GpfMastSubEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPID")
	private String empId;
	
	@Id
	@Column(name="PAYCALPERIOD")
	private String payCalPeriod;
	
	@Column(name="GPFSUB")
	private Integer gpfSub;
	
	@Column(name="DTFROM")
	private Date dtfRom;
	
	@Column(name="DTTO")
	private Date dtto;
	
	@Column(name="INSTDT")
	private Date instdt;
	
	@Column(name="STARTYEARMM")
	private String startyEarmm;
	
	@Column(name="ENDYEARMM")
	private String endyEarmm;
	
	@Column(name="GPSUB_NO")
	private String gpSubNo;
	
	@Column(name="FYR")
	private String fyr;
	
	@Column(name="LOCKSTATUS")
	private String lockstatus;
	
	@Column(name="LOGUSER")
	private String loguser;
	
	@Column(name="LOGDATE")
	private LocalDate logDate;
	
	@Column(name="LOGTIME")
	private String logTime;
	
	@Column(name="LOGIP")
	private String logIp;
	
	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = DateTimeUtility.getCurrentDate();
		this.logTime = DateTimeUtility.getCurrentTimeString();
	}

}
