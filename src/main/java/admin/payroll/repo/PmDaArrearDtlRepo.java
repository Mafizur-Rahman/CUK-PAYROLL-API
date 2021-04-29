package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import admin.payroll.entity.PmDaArrearDtlEntity;

@Repository
public interface PmDaArrearDtlRepo extends JpaRepository<PmDaArrearDtlEntity, String> {

	@Procedure(name = "Arrear.da_arr_proc")
	void da_arr_proc(@Param("PGROUPUNIT") String PGROUPUNIT, @Param("PBILLUNIT") String PBILLUNIT,
			@Param("FRDT") String FRDT, @Param("TODT") String TODT);

	@Query("From PmDaArrearDtlEntity where groupUnit=:groupUnit and PayPeriod BETWEEN :startDate AND :endDate")
	List<PmDaArrearDtlEntity> findPayPeriodBetweenAndGroupUnit(@Param("startDate") String startDate,
			@Param("endDate") String endDate, String groupUnit);

	@Query("From PmDaArrearDtlEntity where empNo=:empNo and groupUnit=:groupUnit and PayPeriod BETWEEN :startDate AND :endDate")
	List<PmDaArrearDtlEntity> findPayPeriodBetweenAndGroupUnitAndEmpNo(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("groupUnit") String groupUnit, @Param("empNo") String empNo);

}
