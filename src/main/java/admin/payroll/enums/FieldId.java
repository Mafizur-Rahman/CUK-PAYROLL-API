package admin.payroll.enums;

public enum FieldId {
	userId("userId"), profileName("profileName"), userName("userName"), emailId("emailId"),
	mobileNumber("mobileNumber"), zipCode("zipCode"), consultantType("consultantType"),
	userSpeciality("userSpeciality"), hospital("hospital"), userFacility("userFacility");

	private String id;

	private FieldId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
