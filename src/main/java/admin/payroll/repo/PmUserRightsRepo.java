package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmUserRightsEntity;

@Repository
public interface PmUserRightsRepo extends JpaRepository<PmUserRightsEntity, String> {

	List<PmUserRightsEntity> findAll();

}
