package admin.payroll.models;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Data
public class SaveEmployeeAddressModel {

	private String empId;

	private String pdhNo;

	private String pstreetNo;

	private String paddrss;

	private String paddrss1;

	private String paddrss2;

	private String paddrss3;

	private String pdistrict;

	private String state1;

	private int ppinCode;

	private String cdhNo;

	private String cstreetNo;

	private String caddrss;

	private String caddrss1;

	private String caddrss2;

	private String caddrss3;

	private String cdistrict;

	private String state2;

	private int cpinCode;

	private String hdhNo;

	private String hstreetNo;

	private String htAddress;

	private String htAddress1;

	private String htAddress2;

	private String htAddress3;

	private String hdistrict;

	private String state3;

	private int hpinCode;

	private String ophone;

	private String rphone;

	private String oiPhone;

	private String mobPhone;

	private String email;

	private String logUser;

//	private LocalDate logDate = LocalDate.now();
//
//	private long LogTime = new Date().getTime();

	private String logIp;

}
