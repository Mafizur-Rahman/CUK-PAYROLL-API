package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmRolesEntity;
import admin.payroll.entity.PmUsersEntity;

@Repository
public interface PmRoleRepo extends JpaRepository<PmRolesEntity, Integer>{


	
	@Query("From PmRolesEntity where roleId=:roleId")
	List<PmRolesEntity> getPmRolesById(@Param("roleId") int roleId);
	
	@Query("FROM PmRolesEntity  WHERE roleId=:roleId")
	List<PmRolesEntity> searchPmRoleById(@Param("roleId") int roleId);
	
	 @Query("FROM PmRolesEntity WHERE description LIKE %:description%")
	  public List<PmRolesEntity> searchPmRoleByDescription(@Param("description") String userName);

}
