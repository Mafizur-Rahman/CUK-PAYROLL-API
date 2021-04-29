package admin.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmEmpDatesEntity;

@Repository
public interface PmEmpDatesRepo extends JpaRepository<PmEmpDatesEntity, Integer> {

	@Query("From PmEmpDatesEntity where empId=:empId")
	List<PmEmpDatesEntity> findByEmpId(@Param("empId") String empId);

}
