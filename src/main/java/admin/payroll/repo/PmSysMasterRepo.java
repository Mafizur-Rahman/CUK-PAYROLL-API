package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmSysMasterEntity;

@Repository
public interface PmSysMasterRepo extends JpaRepository<PmSysMasterEntity, String> {

	List<PmSysMasterEntity> findByBillUnit(String billUnit);

	List<PmSysMasterEntity> findByGroupUnitAndPayCalPeriod(String groupUnit, String payCalPeriod);

	@Procedure
	String find_empmast(String empName);

	@Procedure(name = "Salary.SP_SALARYPROCESS1")
	void sp_salaryprocess1(@Param("GU") String GU, @Param("PP") String pp);

	@Procedure(name = "Salary.SP_SALARYPROCESS2")
	void sp_salaryprocess2(@Param("GU") String GU, @Param("PP") String pp);

	@Procedure(name = "Salary.SP_SALARYPROCESS3")
	void sp_salaryprocess3(@Param("GU") String GU, @Param("PP") String pp);

	@Procedure(name = "Salary.pm_mnth_closing")
	void pm_mnth_closing(@Param("GU") String GU, @Param("PP") String pp);

	@Procedure(name = "Salary.pm_sal_reverse")
	void pm_sal_reverse(@Param("GU") String GU, @Param("PP") String pp);

}
