package admin.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmSalHdrEntity;
import admin.payroll.entity.PmSysMasterEntity;

@Repository
public interface PmSalHdrRepo extends JpaRepository<PmSalHdrEntity, String> {

	@Query("select distinct paycalPeriod from PmSalHdrEntity where groupUnit=:groupUnit and paycalPeriod=:paycalPeriod")
	Optional<PmSalHdrEntity> findByGroupUnitAndPayperiod(@Param("groupUnit") String groupUnit, @Param("paycalPeriod") String payPeriod);

	



}
