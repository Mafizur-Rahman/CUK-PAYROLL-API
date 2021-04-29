package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmCodeTypeEntity;

@Repository
public interface PmCodeTypeRepo extends JpaRepository<PmCodeTypeEntity, String> {

	@Query("From PmCodeTypeEntity where code=:code OR shortDesc=:shortDesc OR longDesc=:shortDesc")
	List<PmCodeTypeEntity> getCode(@Param("code") String code, @Param("shortDesc") String desc);

}
