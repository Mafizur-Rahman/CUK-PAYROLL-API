package admin.payroll.models;

import admin.payroll.enums.APISTATUS;
import lombok.Data;

@Data
public class ResponseDTO {

	private String message;
	private String status;
	private Object statusCode;
	private Object data;

	/**
	 * 
	 * @param message
	 * @param status
	 * @param statusCode
	 * @param data
	 */
	public ResponseDTO(String message, APISTATUS status, Object statusCode, Object data) {
		super();
		this.message = message;
		this.status = status.getStatus();
		this.statusCode = statusCode;
		this.data = data;

	}

}
