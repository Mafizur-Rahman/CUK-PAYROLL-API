package admin.payroll.entity;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CAR")

@NamedStoredProcedureQuery(name = "Car1.getTotalCardsbyModelEntity", procedureName = "GET_TOTAL_CARS_BY_MODEL", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "model_in", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class) })

public class CarEntity implements Serializable {

	@Id
	@Column(name = "ID")
	private Integer id = 1000 + new Random().nextInt(9999);

	@Column(name = "MODEL")
	private String model;

	@Column(name = "YEAR")
	private Integer year;

}