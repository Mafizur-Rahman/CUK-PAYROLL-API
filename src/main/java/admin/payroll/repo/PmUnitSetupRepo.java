package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmUnitSetupEntity;

@Repository
public interface PmUnitSetupRepo extends JpaRepository<PmUnitSetupEntity, String> {

	@Query("From PmUnitSetupEntity where uniId=:uniId")
	List<PmUnitSetupEntity> findAllById(@Param("uniId")  String unitId);

}
