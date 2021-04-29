package admin.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import admin.payroll.entity.KinEntity;

@Repository
public interface KinRepo extends JpaRepository<KinEntity, Integer> {

	@Query("From KinEntity where empId=:empId")
	List<KinEntity> findByEmpId(@Param("empId") String empId);

	@Query("From KinEntity where empId=:empId AND serlNo=:serlNo")
	List<KinEntity> findByEmpIdAndSerlNo(@Param("empId") String empId, @Param("serlNo") int serlNo);

	@Query("From KinEntity where empId=:empId AND serlNo=:serlNo")
	List<KinEntity> findByEmpIdAndSerl(@Param("empId") String empId,@Param("serlNo") int kinId);

	@Modifying
	@Transactional
	@Query(value = "delete from KinEntity e where e.empId=:empId AND e.serlNo=:serlNo")
	int deleteKinData(@Param("empId") String code,@Param("serlNo") int id);

}
