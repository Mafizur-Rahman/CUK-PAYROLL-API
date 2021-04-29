package admin.payroll.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmDesigEntity;

@Repository
public interface PmDesigRepo extends JpaRepository<PmDesigEntity, String> {

	@Query("Select desigCode From PmDesigEntity")
	List<String> getDesignatedCodeFromPmDesig();

	@Query("From PmDesigEntity where desigCode=:desigCode OR desigShortDesc=:desigShortDesc OR desigLongDesc=:desigShortDesc AND isActive='Y'")
	List<PmDesigEntity> findByDesigCodeAndDesigShortDesc(@Param("desigCode") String desigCode,
			@Param("desigShortDesc") String desigShortDesc);

	@Query("Select desigCode From PmDesigEntity where desigShortDesc=:desigShortDesc")
	String getDesigBasedOnDesig(@Param("desigShortDesc") String desigShortDesc);

	@Query("Select desigShortDesc from PmDesigEntity where desigCode=:desigCode")
	String findDescBasedOnCode(@Param("desigCode") String desigCode);
	
	@Query("Select desigLongDesc from PmDesigEntity where desigCode=:desigCode")
	String findDesc(@Param("desigCode") String desigCode);

	@Query("From PmDesigEntity where isActive='Y'")
	List<PmDesigEntity> findAllActive(Sort sort);
	
	

}
