package admin.payroll.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.PmUserRightsEntity;

@Repository
public interface PmUserRightRepo extends JpaRepository<PmUserRightsEntity, Integer> {

	@Query("from PmUserRightsEntity where userId=:userid")
	List<PmUserRightsEntity> getPmUserRightById(@Param("userid") String s);

	@Query("from PmUserRightsEntity where userid LIKE %:id% or roleid LIKE %:id%")
	List<PmUserRightsEntity> searchPmUserRightByUserIdOrRoleId(@Param("id") Integer id);

	@Query("from PmUserRightsEntity where userId=:userid")
	List<List<PmUserRightsEntity>> getPmUserRightByuserId(@Param("userid") Integer userid);

	@Transactional
	@Modifying
	@Query("delete from PmUserRightsEntity  where userId=:userid")
	void deletePmUserRightByuserId(@Param("userid") String userid);

	@Query("SELECT roleId FROM PmUserRightsEntity WHERE userId=:userId")
	List<String>getRolesByUserId(@Param("userId") String userId);
	
	// @Query("delete from CLimit l where l.trader.id =:#{#trader.id}")
	// void deleteLimitsByTrader(@Param("trader") CTrader trader);

}
