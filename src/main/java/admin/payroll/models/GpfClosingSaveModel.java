package admin.payroll.models;

import java.util.List;

import lombok.Data;

@Data
public class GpfClosingSaveModel {
	private String financialYear;
	private List<GpfCloseBalArray> employeeData;
	@Data
	public static class GpfCloseBalArray {
		private Double amount;
		private String employeeId;
	}
}
