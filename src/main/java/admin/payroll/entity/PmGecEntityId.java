package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PmGecEntityId implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
//	private Integer id = 1000 + new Random().nextInt(9999);

	@Id
	@Column(name = "CODETYPE")
	private String codeType;

	@Id
	@Column(name = "CODE")
	private String code;

}
