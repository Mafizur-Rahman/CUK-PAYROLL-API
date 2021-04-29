package admin.payroll.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SUMTAX")
@Data
public class SumTaxEntity {
	
	@Id
	@Column(name = "EMPID")
	private String employeeId;
	
	@Column(name = "SBASIC")
	private Double sBasic;
	
	@Column(name = "SPP")
	private Double spp;
	
	@Column(name = "SDA")
	private Double sda;
	
	@Column(name = "SCCA")
	private Double scca;
	
	@Column(name = "STA")
	private Double sta;
	
	@Column(name = "SHRA")
	private Double shra;
	
	@Column(name = "SMISC")
	private Double smisc;
	
	@Column(name = "STOT")
	private Double stot;
	
	@Column(name = "SGPF")
	private Double sgpf;
	
	@Column(name = "SGIS")
	private Double sgis;
	
	@Column(name = "SEIS")
	private Double seis;
	
	@Column(name = "SHBA")
	private Double shba;
	
	@Column(name = "SPLI")
	private Double spli;
	
	@Column(name = "SU_SSLIC")
	private Double susslic;
	
	@Column(name = "SITAX")
	private Double sitax;
	
	@Column(name = "SOTA")
	private Double sota;
	
	@Column(name = "SHDFC")
	private Double shdfc;
	
	@Column(name = "SARRS")
	private Double sarrs;
	
	@Column(name = "SADDINC")
	private Double saddinc;
	
	@Column(name = "SSURCH")
	private Double ssurch;
	
	@Column(name = "SREGPLI")
	private Double sregpli;
	
	@Column(name = "SPROFTAX")
	private Double sproftax;
	
	@Column(name = "SDP")
	private Double sdp;
	
	@Column(name = "SU_PEN")
	private Double supen;
	
	@Column(name = "SCPF_ARRS")
	private Double scpfarrs;
	
	@Column(name = "SBASIC_NEW")
	private Double sbasicnew;
	
	@Column(name = "SGRADE_PAY")
	private Double sgradepay;
	
	@Column(name = "SSURCH_10")
	private Double ssurch10;
	
	@Column(name = "SSPAY")
	private Double sspay;
	
	@Column(name = "SVARINC")
	private Double svarinc;
	
	@Column(name = "SCGHS")
	private Double scghs;
	
	@Column(name = "SWA")
	private Double swa;
	
	@Column(name = "STA_EXEM")
	private Double staexam;
	
	@Column(name = "SKV_FEE")
	private Double skvfee;
	
	@Column(name = "STUT_EXEM")
	private Double stutexem;
	
	@Column(name = "SGPF_GOVT")
	private Double sgpfgovt;
	
}
