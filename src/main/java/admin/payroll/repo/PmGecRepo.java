package admin.payroll.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import admin.payroll.entity.PmGecEntity;
import admin.payroll.entity.PmGecEntityId;

@Repository
public interface PmGecRepo extends JpaRepository<PmGecEntity, PmGecEntityId> {

	@Query("From PmGecEntity where (shortDesc=:shortDesc OR code=:code OR longDesc=:shortDesc OR codeType=:codeType) AND isActive='Y'")
	List<PmGecEntity> getDataBasedOnCodeAndDescription(@Param("code") String code,
			@Param("shortDesc") String shortDesc,@Param("codeType") String codeType);

	@Query("Select DISTINCT(codeType) From PmGecEntity")
	// select DISTINCT(c.name) from Customer c
	List<String> getAllCodeData();

	@Query("From PmGecEntity where codeType=:codeType")
	List<PmGecEntity> getSelectBoxData(@Param("codeType") String codeType);

	@Query("From PmGecEntity where codeType=:codeType")
	PmGecEntity getDataBasedOnSelecBox(@Param("codeType") String codeType);

	@Query("From PmGecEntity where code=:code AND codeType=:codeType")
	List<PmGecEntity> findByCodeTypeandCode(@Param("code") String code, @Param("codeType") String codeType);

	@Query("select shortDesc from PmGecEntity where code=:code AND codeType='ECL'")
	String findDescBasedOnCode(@Param("code") String classs);

	@Query("select shortDesc from PmGecEntity where code=:code AND codeType='ECG'")
	String findCategoryBasedOnCode(@Param("code") String category);

	@Query("From PmGecEntity where code=:code AND codeType=:codeType")
	List<PmGecEntity> getDataForBasedOnCodeAndCodeType(@Param("code") String code, @Param("codeType") String codeType);

	@Query("From PmGecEntity where id=:id")
	List<PmGecEntity> findAllById(int id);

	List<PmGecEntity> findByCodeType(String codeType);

	@Query("From PmGecEntity where isActive='Y'")
	List<PmGecEntity> findAllActive(Sort sort);

	@Modifying
	@Transactional
	@Query(value = "delete from PmGecEntity e where e.code=:code AND e.codeType=:codeType AND e.id=:id")
	int deleteGeneralCode(@Param("id") int id, @Param("code") String code, @Param("codeType") String codeType);

	@Query("select shortDesc from PmGecEntity where code=:code AND codeType=:codeType")
	String findByCodeTypeandCode1(@Param("code")String feeType, @Param("codeType") String codeType);

//	void deleteByIdAndCodeTypeAndCode(int id, String codeType, String code);

}
