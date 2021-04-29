package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import admin.payroll.entity.SeventhMatrixEntity;

@Repository
public interface SeventhMatrixRepo extends JpaRepository<SeventhMatrixEntity, String> {

	@Query("From SeventhMatrixEntity where payBand=:payBand OR levelPay=:levelPay OR scaleNo=:scaleNo")
	List<SeventhMatrixEntity> findAllByPaybandAndLevel(@Param("payBand") String payBand, @Param("levelPay") String levelPay,@Param("scaleNo") Integer scaleNo);

	@Query("From SeventhMatrixEntity where payBand=:payBand AND gp=:gp AND payStep=:payStep")
	List<SeventhMatrixEntity> editByPaybandAndLevel(@Param("payBand") String payBand,@Param("gp") Integer gp,@Param("payStep") Integer payStep);

	@Modifying
	@Transactional
	@Query(value = "delete from SeventhMatrixEntity e where e.payBand=:payBand AND e.gp=:gp AND e.payStep=:payStep")
	int deleteSeventhMatrix(@Param("payBand")String payBand,@Param("gp") Integer gp,@Param("payStep") Integer payStep);

}
