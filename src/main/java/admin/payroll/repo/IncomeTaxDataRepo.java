package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.AddressEntity;
import admin.payroll.entity.ITaxDataEntity;

@Repository
public interface IncomeTaxDataRepo extends JpaRepository<ITaxDataEntity, String> {
	
	@Procedure(name = "Income_itaxMonthlyProcess")
	void itaxMonthlyProcess(@Param("MM") Double MM,@Param("YYYY") Double YYYY);
	
	@Query("from ITaxDataEntity where empId=:empid")
	//@Query(value="select * from ITAXDATA where empid='756465684'", nativeQuery=true)
	List<ITaxDataEntity> getItaxDataByEmpId(@Param("empid") String empid);

	@Query("from ITaxDataEntity where empId=:empid")
	List<ITaxDataEntity> getItaxData(@Param("empid") String empId);
	


}
