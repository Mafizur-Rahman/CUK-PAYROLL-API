package admin.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmSalHdrEntity;
import admin.payroll.models.DataForValidationModel;

@Repository
public interface PmSalHdrRepo extends JpaRepository<PmSalHdrEntity, String> {

	@Query("select distinct paycalPeriod from PmSalHdrEntity where groupUnit=:groupUnit and paycalPeriod=:paycalPeriod")
	Optional<PmSalHdrEntity> findByGroupUnitAndPayperiod(@Param("groupUnit") String groupUnit,
			@Param("paycalPeriod") String payPeriod);

	@Query("select new admin.payroll.models.DataForValidationModel(a.empId,a.name,a.gender,b.caddrss1,b.caddrss2,b.caddrss3,b.cdistrict,b.state2,a.nationality,a.bankName,a.ifscCode,a.banknoNew,a.adharNo,b.cpinCode,c.netPay) FROM EmpMastEntity a,AddressEntity b, PmSalHdrEntity c where a.empId=b.empId and a.empId=c.empNo")
	List<DataForValidationModel> DataForValidation();

}
