package admin.payroll.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.IncrementMainEntity;

@Repository
public interface IncrementMainRepo extends JpaRepository<IncrementMainEntity, String> {
	
	
	@Procedure(name ="ANNUAL_INCREMENT_PROCEDURE")
	void incrementProcess1(@Param("PDNI") String strDate);
	
	@Query("from IncrementMainEntity where classes=:clas")
	List<IncrementMainEntity> getIncrementMainByClass(@Param("clas") String clas);

	
}
