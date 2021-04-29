package admin.payroll.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PunchingMediaModel {
	private Double grossPay;
	private Double totDeduction;
	private Double netPay;
}
