package admin.payroll.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmEedEntity;

@Repository
public interface PmEedRepo extends JpaRepository<PmEedEntity, String> {

	@Query("From PmEedEntity where shortDesc=:shortDesc OR earningDeduction=:earningDeduction OR longDesc=:shortDesc and active='Y'")
	List<PmEedEntity> findAllByDesigShortDescAndcode(@Param("shortDesc") String shortDesc,
			@Param("earningDeduction") String earningDeduction);

	@Query("From PmEedEntity where earningDeduction=:earningDeduction and shortDesc=:shortDesc")
	PmEedEntity findAllByEarningDeductionAndShortDesc(@Param("earningDeduction") String earningDeduction,
			@Param("shortDesc") String shortDesc);

	@Query("select longDesc from PmEedEntity where earningDeduction=:earningDeduction")
	String getDescription(@Param("earningDeduction") String employeeId);

	@Query("From PmEedEntity where rateToBeEntered='Y' and active='Y'")
	List<PmEedEntity> getEedForPayRates(Sort sort);

	@Query("From PmEedEntity where miscellaneous='Y' and active='Y'")
	List<PmEedEntity> getEedForcurrentMonth(Sort sort);

	@Query("From PmEedEntity where fixed='Y' and active='Y'")
	List<PmEedEntity> getEedForregularRecoveries(Sort sort);

	@Query("From PmEedEntity where installment='Y' and active='Y'")
	List<PmEedEntity> getEedForinstallmentRecoveries(Sort sort);

	@Query("From PmEedEntity where active='Y'")
	List<PmEedEntity> findAllActive(Sort sort);

}
