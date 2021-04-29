package admin.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmQualificationEntity;

@Repository
public interface PmQualificationRepo extends JpaRepository<PmQualificationEntity, Integer> {

//	@Query("from PmQualificationEntity where empId=:empId")
//	PmQualificationEntity getQualification(@Param("empId") String empId, String employeeName);

	@Query("from PmQualificationEntity where empId=:empId")
	List<PmQualificationEntity> findByEmployeeId(@Param("empId") String empId);

}
