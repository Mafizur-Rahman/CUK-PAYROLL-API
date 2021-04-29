package admin.payroll.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ItaxDataListModel {
	
	public String employeeId;
	private String employeeName;
	private String designation;
	private List<ItaxDataListModelRowData> rowsData;
	
	@Data
	public static class ItaxDataListModelRowData {
		private Double basic; // done
		private Double monthId; // done
		private Double yearId; // done
		private Double pfsub; // done
		private Double tacode; // done
		private Double itax; // done
		private Double pp; // done
		private Double misCredit; // done
		private Double hba; // done
		private Double hdfc; // done
		private Double pli; // done
		private Double sslic; // done
		private Double insCode; // done
		private Double qrsCode; // done
		private Double ota; // done
		private Double addinc; // done
		private Double surch;
		private Double taxCode; // done
		private Double regpli; //done
		private Double spay; // done
		private Double cea; // done
		private Double sslicpen; // done
		private Double hbaAdvid; // done
		private Double pfCode;
		private Double cpfArrs;
		private String opt;
		private Double basicNew;
		private Double gradePay; // done
		private Double surchNew;
		private Double kvFee;
		private Double waMed; // done
		private Double varinc; // done
		private Double cghsCode; // done
		private String levelPay;
		private Double pfsubGovt;
	}
	
//	public String empId;
//	public List basic;
//	public List mounthId;
//	public List yearId;
//	public List ota;
//	public List gradePay;
//	public List misCredit;
//	public List pfsub;
//	public List itax;
//	public List cess;
//	public List pp;
//	public List varinc;
//	public List tacode;
//	public List hba;
//	public List hbaAdvid;
//	public List hdfc;
//	public List pli;
//	public List sslic;
//	public List sslicpen;
//	public List waMed;
//	public List spay;
//	public List cea;
//	public List insCode;
//	public List qrsCode;
//	public List addinc;
//	public List ptaxCode;
//	public List regpli;
//	public List cghsCode;
//	public List cghs;
//	public List pc;
}
