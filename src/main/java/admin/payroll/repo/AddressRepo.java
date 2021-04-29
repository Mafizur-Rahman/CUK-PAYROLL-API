package admin.payroll.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.AddressEntity;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, String> {

	@Query("FROM AddressEntity where empId=:empId")
	AddressEntity findByEmpId(@Param("empId") String empId);

//	@Query("FROM AddressEntity where empId=:empId")
//	AddressEntity getAddress(@Param("empId") String empId);
	@Query("FROM AddressEntity where empId=:empId")
	Optional<AddressEntity> findByEmployeeId(@Param("empId") String empId);
	

}
