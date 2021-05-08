package admin.payroll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
public class PmGecEntityId implements Serializable {

	@Id
	@GeneratedValue(generator = "gec_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName = "gec_seq", name = "gec_seq", allocationSize = 1, initialValue = 1)
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
