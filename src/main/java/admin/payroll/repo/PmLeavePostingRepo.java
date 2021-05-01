package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmLeavePostingEntity;

@Repository
public interface PmLeavePostingRepo extends JpaRepository<PmLeavePostingEntity, String> {

	@Query("From PmLeavePostingEntity where empNo=:empNo")
	List<PmLeavePostingEntity> findPmLeavePostingById(@Param("empNo") String empNo);
//	
//	@Query("From PmUsersEntity where userName=:userName")
//	List<PmUsersEntity> findPmUserByName(@Param("userName") String userName);
//	
//	Optional<PmUsersEntity> findByLoginId(String loginId);
//	
//	Optional<PmUsersEntity> findByUserName(String username);

}
