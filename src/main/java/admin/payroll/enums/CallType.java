package admin.payroll.enums;

public enum CallType {
	
	VIDEO_CALL("Video Call"), VOICE_CALL("Voice Call");

	public String getDisplayname() {
		return displayname;
	}



	private String displayname;
 
	private CallType(String displayname) {
		this.displayname = displayname;
	}
	
	
	

}
