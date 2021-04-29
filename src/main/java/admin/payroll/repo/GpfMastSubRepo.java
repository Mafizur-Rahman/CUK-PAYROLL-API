package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.GpfMastSubEntity;
import admin.payroll.entity.GpfMastSubEntityId;
import admin.payroll.models.GpfEmpDisplayModel;
//import admin.payroll.models.GpfMastSubProcessModel;
import admin.payroll.entity.ITaxDataEntity;

@Repository
public interface GpfMastSubRepo extends JpaRepository<GpfMastSubEntity, GpfMastSubEntityId> {

	@Procedure(name = "gpfMastSubProcess")
	void gpfMastSubProcess(@Param("monthId") int monthId ,@Param("pClass") String pClass,@Param("financialYear") String financialYear);
	
	@Query("FROM GpfMastSubEntity gpf WHERE gpf.empId = :empId AND gpf.fyr = :fyr")
	List<GpfMastSubEntity> getAllByEmpIdAndFyr(@Param("empId") String empId, @Param("fyr") String fyr);
	
	
//	@Query("from GpfMastSubEntity where empid=:empId and fyr=:fyr")
//	List<GpfMastSubEntity> getGpfMustSubByEmpIdAndFyr(@Param("empId") String empid,@Param("financialYear") String fyr);
	
	@Query("from GpfMastSubEntity where empId=:empid and fyr=:fyr")
	List<GpfMastSubEntity> getGpfMustSubByEmpIdAndFyr(@Param("empid") String empId,@Param("fyr") String fyr);

	@Query("SELECT NEW admin.payroll.models.GpfEmpDisplayModel(emp.name, emp.gpfNo, d.desigLongDesc) FROM admin.payroll.entity.EmpMastEntity emp INNER JOIN admin.payroll.entity.PmDesigEntity d "
			+ " ON emp.desig = d.desigCode WHERE emp.empId = :empId") 
	GpfEmpDisplayModel getGpfEmpDisplayModel(@Param("empId") String empId);

}
