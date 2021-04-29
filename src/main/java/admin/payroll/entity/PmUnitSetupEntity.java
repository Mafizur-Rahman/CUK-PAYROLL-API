package admin.payroll.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PM_UNIT_SETUP")
public class PmUnitSetupEntity {
	
	@Id
	@Column(name = "UNIID")
	private String uniId;

	@Column(name = "SHORT_NAME")			         
	private String shortName;

	@Column(name = "LONG_NAME")
	private String longName;

	@Column(name = "ADDRESS1")
	private String address1;

	@Column(name = "ADDRESS2")
	private String address2;

	@Column(name = "ADDRESS3")
	private String address3;

	@Column(name = "CITY_LOCATION")
	private String cityLocation;

	@Column(name = "STATE")
	private String sate;

	@Column(name = "PHONENO")
	private String phoneNo;

	@Column(name = "EMAILID")
	private String emailId;

	@Column(name = "GSTIN")
	private String gstin;
	
	@Column(name = "PANNO")
	private String panNo;
	
}
