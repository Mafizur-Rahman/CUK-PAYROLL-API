package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmPayMatrixEntity;

@Repository
public interface PmPayMatrixRepo extends JpaRepository<PmPayMatrixEntity, String> {

	@Query("from PmPayMatrixEntity where cellNo=:cellNo and levelNo=:levelNo")
	PmPayMatrixEntity getMatrixData(@Param("levelNo") String levelNo, @Param("cellNo") String cellNo);

//	@Query("select levelNo from PmPayMatrixEntity")
	@Query("select distinct levelPay from SeventhMatrixEntity ORDER BY levelPay")
	List<String> getPayMatrixLevel();

//	@Query("select cellNo from PmPayMatrixEntity")
	@Query("select distinct col from SeventhMatrixEntity ORDER BY col asc")
	List<String> getPayMatrixCell();

	@Query("select payComission from PmPayMatrixEntity")
	List<String> getGradePay();

}
