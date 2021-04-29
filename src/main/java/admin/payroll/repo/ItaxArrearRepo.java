package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.ItaxArrearEntity;

@Repository
public interface ItaxArrearRepo extends JpaRepository<ItaxArrearEntity, String>{
	
	@Query("from ItaxArrearEntity where empId=:empid")
	List<ItaxArrearEntity> getItaxArrearByEmpId(@Param("empid") String empid);

}
