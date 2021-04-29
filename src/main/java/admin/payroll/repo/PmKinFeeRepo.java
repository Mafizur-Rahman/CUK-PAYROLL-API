package admin.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmKinFeeEntity;

@Repository
public interface PmKinFeeRepo extends JpaRepository<PmKinFeeEntity, Integer> {

	@Query("from PmKinFeeEntity where empId=:empId")
	List<PmKinFeeEntity> findByEmpId(@Param("empId") String empId);

	@Query("from PmKinFeeEntity where id=:id")
	List<PmKinFeeEntity> findByID(@Param("id") Integer id);

	// sPmKinFeeEntity getKinMaster(String employeeid, String employeeName);

}
