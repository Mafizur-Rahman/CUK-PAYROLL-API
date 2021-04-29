package admin.payroll.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpfEmpDisplayModel {
	
	private String name;
	private String gpfAccountNumber;
	private String designation;
}
