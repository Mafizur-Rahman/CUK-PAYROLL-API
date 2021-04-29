package admin.payroll.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;


@Table(name ="INCR_MAIN")
@Data
@Entity


@NamedStoredProcedureQueries({

		@NamedStoredProcedureQuery(name = "ANNUAL_INCREMENT_PROCEDURE", procedureName = "ANNUAL_INCR_PROC1", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "PDNI", type = String.class)})
})

public class IncrementMainEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EMPID")
	private String empId;
	
	@Column(name="INC")
	private Integer inc;
	
	@Column(name="FIX")
	private Integer fix;
	
	@Column(name="NEWBASIC")
	private Integer newBasic;
	
	@Column(name="PROMOTED")
	private String promoted;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="GIVE")
	private String give;
	
	@Column(name="BENIFITS")
	private Date benifits;
	
	@Column(name="DNI")
	private Date dni;
	
	@Column(name="CURBASIC")
	private Integer curBasic;
	
	@Column(name="PRESENT")
	private String present;
	
	@Column(name="ABSENT")
	private Date absent;
	
	@Column(name="DNI_OLD")
	private Date dniold;
	
	@Column(name="OTHER")
	private Integer other;
	
	@Column(name="PMT_STATUS")
	private String pmTStatus;
	
	@Column(name="QUAL_NOTFROM")
	private Date qualNotFrom;
	
	@Column(name="QUAL_NOTTO")
	private Date qualNotTo;
	
	@Column(name="CLASS")
	private String classes;
	
	@Column(name="CATID")
	private Integer catId;
	
	@Column(name="DIVISION")
	private String division;
	
	@Column(name="SCALE_ID")
	private String scaleId;
	
	@Column(name="PART_II_ORDER")
	private String partOrder;
	
	@Column(name="GRADE_PAY")
	private Integer gradePay;
	
	@Column(name="COL")
	private Integer col;
	
	@Column(name="LEVEL_PAY")
	private String levelPay;
	
	@Column(name="SCALE_NO")
	private Integer scaleNo;
	
	@Column(name="COL_NEW")
	private Integer colNew;
	
	@Column(name="LEVEL_PAY_NEW")
	private String levelPayNew;
	
	
	
	

}
