package admin.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmEmpDocsEntity;

@Repository
public interface PmEmpDocsRepo extends JpaRepository<PmEmpDocsEntity, Integer> {

	@Query("From PmEmpDocsEntity where empID=:empID")
	Optional<PmEmpDocsEntity> findByEmpId(@Param("empID") String empID);

	@Query("From PmEmpDocsEntity where empID=:empID")
	List<PmEmpDocsEntity> findDataByEmpId(@Param("empID") String empID);

}
