package admin.payroll.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PmRateEntityId implements Serializable {

	@Id
	@Column(name = "EARNINGDEDUCTION", length = 50)
	private String earningDeduction;

	@Id
	@Column(name = "FROMDATE")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate fromDate;
}
