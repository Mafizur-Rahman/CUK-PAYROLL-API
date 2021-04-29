package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import admin.payroll.entity.GpfAdvEntity;

public interface GpfAdvRepo extends JpaRepository<GpfAdvEntity, Integer> {
	List<GpfAdvEntity> findByEmployeeId(String employeeId);
	//void deleteByAcknowledgementNumber(Long ackNumber);
	
	@Query("From GpfAdvEntity where acknowledgementNumber=:acknowledgementNumber ")
	GpfAdvEntity findByAckNo(@Param("acknowledgementNumber") int ackNumber);
}
