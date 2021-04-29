package admin.payroll.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ITAXARREAR")
public class ItaxArrearEntity {
	
	@Id
	@Column(name="EMPID")
	public String empId;
	
	@Column(name="TUTFEE")
	public Double tutFee;
	
	@Column(name="MISCARRS")
	public Double misCarrs;
	
	@Column(name="DAA1")
	public Double daa1;
	
	@Column(name="DAA2")
	public Double daa2;
	
	@Column(name="TOTARRS")
	public Double toTarrs;
	
	@Column(name="BONUS")
	public Double bonus;
	
	@Column(name="HRAARRS")
	public Double hrAarrs;
	
	@Column(name="NEWPENARR1")
	public Double newPenArr1;
	
	@Column(name="NEWPENARR2")
	public Double newPenArr2;
	
	@Column(name="VARINCARR")
	public Double varinCarr;
	
	@Column(name="ADDINCARR")
	public Double addinCarr;
	
	@Column(name="LTCENCASH")
	public Double ltcenCash;
	
	@Column(name="CPCARR")
	public Double cpCarr;
	
	@Column(name="TUTFEE_DED")
	public Double tutFeeDed;
	
	@Column(name="STD_DEDUCT")
	public Double stdDeduct;
	
	@Column(name="PROF_UPD_ALLOW")
	public Double profUpdAllow;
	
	@Column(name="DAA1_DATE")
	public Date daa1Date;
	
	@Column(name="DAA1_REMARKS")
	public String daa1Remarks;
	
	@Column(name="DAA2_DATE")
	public Date daa2Date;
	
	@Column(name="DAA2_REMARKS")
	public String daa2Remarks;
	
	@Column(name="PUA_DATE")
	public Date puaDate;
	
	@Column(name="PUA_REMARKS")
	public String puaRemarks;
	
	@Column(name="LTCENCASH_DATE")
	public Date ltcenCashDate;
	
	@Column(name="LTCENCASH_REMARKS")
	public String ltcenCashRemarks;
	
	@Column(name="TUTFEE_DATE")
	public Date tutFeeDate;
	
	@Column(name="TUTFEE_REMARKS")
	public String tutFeeRemarks;
	
	@Column(name="BONUS_DATE")
	public Date bonusDate;
	
	@Column(name="BONUS_REMARKS")
	public String bonusRemarks;
	
	@Column(name="MISCARRS_DATE")
	public Date misCarrsDate;
	
	@Column(name="MISCARRS_REMARKS")
	public String misCarrsRemarks;
	
	@Column(name="CPCARR_DATE")
	public Date cpCarrDate;
	
	@Column(name="CPCARR_REMARKS")
	public String cpCarrRemarks;
	
	@Column(name="VARINCARR_DATE")
	public Date varinCarrDate;
	
	@Column(name="VARINCARR_REMARKS")
	public String varinCarrRemarks;
	
	@Column(name="HRAARRS_DATE")
	public Date hraarrsDate;
	
	@Column(name="HRAARRS_REMARKS")
	public String hraarrsRemarks;
	
	@Column(name="NEWPENARR1_DATE")
	public Date newPenarr1Date;
	
	@Column(name="NEWPENARR1_REMARKS")
	public String newPenarr1Remarks;
	
	@Column(name="NEWPENARR2_DATE")
	public Date newPenarr2Date;
	
	@Column(name="NEWPENARR2_REMARKS")
	public String newPenarr2Remarks;
	
	@Column(name="LTCREIMB")
	public Double ltcreimb;
	
	@Column(name="LTCREIMB_DATE")
	public Date ltcreimbDate;
	
	@Column(name="LTCREIMB_REMARKS")
	public String ltcreimbRemarks;
	
	@Column(name="RETBENEFITS")
	public Double retBenefits;
	
	@Column(name="RETBENEFITS_DATE")
	public Date retBenefitsDate;
	
	@Column(name="RETBENEFITS_REMARKS")
	public String retBenefitsRemarks;
	
	@Column(name="GRATUITY")
	public Double gratuity;
	
	@Column(name="GRATUITY_DATE")
	public Date gratuityDate;
	
	@Column(name="GRATUITY_REMARKS")
	public String gratuityRemarks;
	
	@Column(name="COMMUTATION")
	public Double commutation;
	
	@Column(name="COMMUTATION_DATE")
	public Date commutationDate;
	
	@Column(name="COMMUTATION_REMARKS")
	public String commutationRemarks;
	
	@Column(name="ELENCASH")
	public Double elenCash;
	
	@Column(name="ELENCASH_DATE")
	public Date elenCashDate;
	
	@Column(name="ELENCASH_REMARKS")
	public String elenCashRemarks;
	
	@Column(name="UPDATED_BY")
	public String updateBy;
	
	@Column(name="ADDINCARR_PUA")
	public Double addinCarrPua;

}
