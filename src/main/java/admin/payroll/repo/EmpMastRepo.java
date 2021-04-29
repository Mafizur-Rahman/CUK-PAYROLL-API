package admin.payroll.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import admin.payroll.entity.EmpMastEntity;

@Repository
public interface EmpMastRepo extends JpaRepository<EmpMastEntity, String>, EmployeeRepositoryCustom {

//	@Query("select empName from EmpMastEntity")
//	List<String> getAllEmployee();
//		

//	@Query("From EmpMastEntity order by name")
//	List<EmpMastEntity> getAllEmployee();

	@Query("From EmpMastEntity where empId=:empId OR name=:name")
	List<EmpMastEntity> getEmployeeDatas(@Param("empId") String empId, @Param("name") String name);

//	@Query("select name from EmpMastEntity where empId=:empId")
//	String findNameBasedOnEmpId(@Param("empId") String empId);

	@Query("From EmpMastEntity where name=:name")
	List<EmpMastEntity> FindAllEmpIds(@Param("name") String name);

	@Query("select name From EmpMastEntity where empId=:empId")
	String FindEmpName(@Param("empId") String empId);

	@Query("From EmpMastEntity where empId=:empId")
	List<EmpMastEntity> getEMpDesig(@Param("empId") String empId);

	@Query("From EmpMastEntity where empId=:empId")
	EmpMastEntity findEmployeeClassANdDesig(@Param("empId") String employeeId);

	@Procedure(procedureName = "find_empmast")
	String getData(String name);

	@Procedure(name = "getAllEmployees")
	List<EmpMastEntity> getAllEmployees();

	EmpMastEntity findByEmpId(String empId);

	@Query("From EmpMastEntity where sosDate=:sosDate")
	List<EmpMastEntity> findBySosDAte(Sort sort, @Param("sosDate")LocalDate localDate);

	// Dashboard Statistics Methods
	
	@Query(value =  "select count(*) from empmast where job_category='DRTC'", nativeQuery = true)
	int getDRTCCount();
	
	@Query(value = "select count(*) from empmast where job_category='DRDS'", nativeQuery = true)
	int getDRDSCount();
	
	@Query(value = "select count(*) from empmast where desig in ('SC B','SC B(AD)')", nativeQuery= true)
	int getScBCount();
	
	@Query(value = "select count(*) from empmast where desig in ('SC C','SC C(AD)')", nativeQuery = true)
	int getScCCount();
	
	@Query(value = "select count(*) from empmast where desig = 'SC D'", nativeQuery = true)
	int getScDCount();
	
	@Query(value = "select count(*) from empmast where desig = 'SC E'", nativeQuery = true)
	int getScECount();
	
	
	@Query(value = "select count(*) from empmast where desig in ('SC F','SC F(AD)')", nativeQuery = true)
	int getScFCount();
	
	@Query(value = "select count(*) from empmast where desig = 'SC G'", nativeQuery = true)
	int getScGCount();
	
	@Query(value = "select count(*) from empmast where desig = 'SC H'", nativeQuery = true)
	int getScHCount();
	
	@Query(value = "select count(*) from empmast", nativeQuery = true)
	int getAllCount();

	@Query(value = "select count(*) from empmast where job_category='OTHER'", nativeQuery = true)
	int getOthersCount();
	
	@Query(value = "select count(*) from EMPMAST t WHERE DESIG LIKE 'T.O%'", nativeQuery = true)
	int getTOCount();
    
	@Query("from EmpMastEntity where empId=:empId")
    EmpMastEntity getNameByempId(@Param("empId") String empNo, Sort sort);
}
