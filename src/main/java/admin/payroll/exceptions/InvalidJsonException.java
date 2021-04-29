package admin.payroll.exceptions;

public class InvalidJsonException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "Invalid json";
	private Object data;

	/**
	 * 
	 * @param message
	 * @param data
	 */
	public InvalidJsonException(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	public InvalidJsonException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

}
