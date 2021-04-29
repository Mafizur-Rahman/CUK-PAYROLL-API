package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import admin.payroll.entity.GpfRefundEntity;

public interface GpfRefundRepo extends JpaRepository<GpfRefundEntity, Integer> {
	List<GpfRefundEntity> findByEmployeeId(String employeeId);
	GpfRefundEntity findByAcknowledgementNumber(int ackNumber);
}
