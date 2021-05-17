package admin.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmUsersEntity;

@Repository
public interface PmUserRepo extends JpaRepository<PmUsersEntity, Integer>{
	
	@Query("From PmUsersEntity where id=:id")
	List<PmUsersEntity> findPmUserById(@Param("id") int id);
	
	@Query("From PmUsersEntity where userName=:userName")
	List<PmUsersEntity> findPmUserByName(@Param("userName") String userName);
	
	Optional<PmUsersEntity> findByLoginId(String loginId);
	Optional<PmUsersEntity> findByUserName(String username);

	
}
