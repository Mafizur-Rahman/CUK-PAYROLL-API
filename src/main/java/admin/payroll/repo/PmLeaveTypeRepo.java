package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmLeaveTypeEntity;

@Repository
public interface PmLeaveTypeRepo extends JpaRepository<PmLeaveTypeEntity, String> {

	@Query("From PmLeaveTypeEntity where leaveCode=:leaveCode")
	List<PmLeaveTypeEntity> findPmLeaveTypeById(@Param("leaveCode") String leaveCode);

	@Query("From PmLeaveTypeEntity where leaveCode=:leaveCode OR leaveDesc=:leaveDesc")
	List<PmLeaveTypeEntity> getLeaveTypeDatas(@Param("leaveCode") String leaveCode,
			@Param("leaveDesc") String leaveDesc);

}
