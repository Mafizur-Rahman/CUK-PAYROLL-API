package admin.payroll.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import admin.payroll.utils.DateTimeUtility;
import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

	@Id
	@Column(name = "EMPID")
	private String empId;

	@Column(name = "P_DH_NO")
	private String pdhNo;

	@Column(name = "P_STREETNO")
	private String pstreetNo;

	@Column(name = "PADDRSS")
	private String paddrss;

	@Column(name = "PADDRSS1")
	private String paddrss1;

	@Column(name = "PADDRSS2")
	private String paddrss2;

	@Column(name = "PADDRSS3")
	private String paddrss3;

	@Column(name = "P_DISTRICT")
	private String pdistrict;

	@Column(name = "STATE1")
	private String state1;

	@Column(name = "P_PINCODE")
	private Integer ppinCode;

	@Column(name = "C_DH_NO")
	private String cdhNo;

	@Column(name = "C_STREETNO")
	private String cstreetNo;

	@Column(name = "CADDRSS")
	private String caddrss;

	@Column(name = "CADDRSS1")
	private String caddrss1;

	@Column(name = "CADDRSS2")
	private String caddrss2;

	@Column(name = "CADDRSS3")
	private String caddrss3;

	@Column(name = "C_DISTRICT")
	private String cdistrict;

	@Column(name = "STATE2")
	private String state2;

	@Column(name = "C_PINCODE")
	private Integer cpinCode;

	@Column(name = "H_DH_NO")
	private String hdhNo;

	@Column(name = "H_STREETNO")
	private String hstreetNo;

	@Column(name = "htown1")  //HTADDRESS
	private String htAddress;

	@Column(name = "htown2")  //HTADDRESS1
	private String htAddress1;

	@Column(name = "htown3")  //HTADDRESS2
	private String htAddress2;

	@Column(name = "HTADDRESS3")
	private String htAddress3;

	@Column(name = "H_DISTRICT")
	private String hdistrict;

	@Column(name = "STATE3")
	private String state3;

	@Column(name = "H_PINCODE")
	private Integer hpinCode;

	@Column(name = "O_PHONE")
	private String ophone;

	@Column(name = "R_PHONE")
	private String rphone;

	@Column(name = "OI_PHONE")
	private String oiPhone;

	@Column(name = "MOB_PHONE")
	private String mobPhone;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "LOGUSER")
	private String logUser;

	@Column(name = "LOGDATE")
	private LocalDate logDate;

	@Column(name = "LOGTIME")
	private String logTime;

	@Column(name = "LOGIP")
	private String logIp;
	
	
	@PreUpdate
	@PrePersist
	public void onUpdate() {
		this.logDate = DateTimeUtility.getCurrentDate();
		this.logTime = DateTimeUtility.getCurrentTimeString();
	}

}
