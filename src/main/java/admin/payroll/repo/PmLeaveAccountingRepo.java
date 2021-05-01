package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmLeaveAccountingEntity;

@Repository
public interface PmLeaveAccountingRepo extends JpaRepository<PmLeaveAccountingEntity, String> {

	@Query("From PmLeaveAccountingEntity where empNo=:empNo")
	List<PmLeaveAccountingEntity> findPmLeaveAccountingById(@Param("empNo") String empNo);

	@Query("From PmLeaveAccountingEntity where empNo=:empNo AND leaveCode=:leaveCode ")
	List<PmLeaveAccountingEntity> findPmLeaveAccountingByEmpAndLeave(@Param("empNo") String empNo,
			@Param("leaveCode") String leaveCode);

}
