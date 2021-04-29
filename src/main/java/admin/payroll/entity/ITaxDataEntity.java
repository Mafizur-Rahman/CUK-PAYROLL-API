package admin.payroll.entity;

import java.io.Serializable;

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

@IdClass(ItaxDataEntityId.class)
@Data
@Entity
@Table(name="ITAXDATA")

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Income_itaxMonthlyProcess", procedureName = "ITAX_MONTHLY_PROCESS", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MM", type = Double.class),
	       	@StoredProcedureParameter(mode=ParameterMode.IN, name ="YYYY", type = Double.class)	
	}),
})
public class ITaxDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EMPID")
	private String employeeId;
	
	@Column(name="BASIC")
	private Double basic;
	
	@Id
	@Column(name="MONTHID")
	private Double monthId;
	
	@Id
	@Column(name="YEARID")
	private Double yearId;
	
	@Column(name="PFSUB")
	private Double pfsub;
	
	@Column(name="TACODE")
	private Double tacode;
	
	@Column(name="ITAX")
	private Double itax;
	
	@Column(name="PP")
	private Double pp;
	
	@Column(name="MISCREDIT")
	private Double misCredit;
	
	@Column(name="HBA")
	private Double hba;
	
	@Column(name="HDFC")
	private Double hdfc;
	
	@Column(name="PLI")
	private Double pli;
	
	@Column(name="SSLIC")
	private Double sslic;
	
	@Column(name="INSCODE")
	private Double insCode;
	
	@Column(name="QRSCODE")
	private Double qrsCode;
	
	@Column(name="OTA")
	private Double ota;
	
	@Column(name="ADDINC")
	private Double addinc;
	
	@Column(name="SURCH")
	private Double surch;
	
	@Column(name="PTAXCODE")
	private Double taxCode;
	
	@Column(name="REG_PLI")
	private Double regpli;
	
	@Column(name="SPAY")
	private Double spay;
	
	@Column(name="CEA")
	private Double cea;
	
	@Column(name="SSLIC_PEN")
	private Double sslicpen;
	
	@Column(name="HBA_ADVID")
	private Double hbaAdvid;
	
	@Column(name="PFCODE")
	private Double pfCode;
	
	@Column(name="CPF_ARRS")
	private Double cpfArrs;
	
	@Column(name="OPT")
	private String opt;
	
	@Column(name="BASIC_NEW")
	private Double basicNew;
	
	@Column(name="GRADE_PAY")
	private Double gradePay;
	
	@Column(name="SURCH_NEW")
	private Double surchNew;
	
	@Column(name="KV_FEE")
	private Double kvFee;
	
	@Column(name="WA_MED")
	private Double waMed;
	
	@Column(name="VARINC")
	private Double varinc;
	
	@Column(name="CGHSCODE")
	private Double cghsCode;
	
	@Column(name="LEVEL_PAY")
	private String levelPay;
	
	@Column(name="PFSUB_GOVT")
	private Double pfsubGovt;

}
