package admin.payroll.enums;

public enum Type {
	number("number"),text("text"),select("select");
	private String type;

	private Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
