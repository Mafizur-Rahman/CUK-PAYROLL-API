package admin.payroll.repo;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import admin.payroll.entity.PmPayMasteEntity;

@Repository
public interface PmPayMasterRepo extends JpaRepository<PmPayMasteEntity, String> {
	
	@Query("From PmPayMasteEntity where empNo=:empNo AND payperiod=:payperiod")
	List<PmPayMasteEntity> getByEmpNoAndPayperiod(@Param("empNo") String empNo, @Param("payperiod") String payperiod);
	@Modifying
	@Transactional
	@Query(value = "delete from PmPayMasteEntity e where e.empNo=:empNo AND e.payperiod=:payperiod AND e.earningdeduction=:earningdeduction")
	int deleteByEmpNoAndPayperiodAndEarnindeduction(String empNo, String payperiod, String earningdeduction);


}
