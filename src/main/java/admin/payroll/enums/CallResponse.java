package admin.payroll.enums;

public enum CallResponse {
	Y("Accept"), N("Decline");

	public String getDisplayname() {
		return displayname;
	}

	private String displayname;

	private CallResponse(String displayname) {
		this.displayname = displayname;
	}

}
