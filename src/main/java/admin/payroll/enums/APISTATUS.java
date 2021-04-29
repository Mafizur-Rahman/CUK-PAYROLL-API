package admin.payroll.enums;

public enum APISTATUS {
	
	SUCCESS("success"),FAIL("fail");
	private String status;
	
	private APISTATUS(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
}

	
