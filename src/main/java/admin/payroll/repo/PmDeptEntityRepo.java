package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import admin.payroll.entity.PmDeptEntity;

public interface PmDeptEntityRepo extends JpaRepository<PmDeptEntity, String> {

	@Query("From PmDeptEntity where code=:code OR description=:description")
	List<PmDeptEntity> getCode(@Param("code") String code, @Param("description") String desc);

}
