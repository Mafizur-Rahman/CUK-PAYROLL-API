package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import admin.payroll.entity.PmBankEntity;

@Repository
public interface PmBankRepo extends JpaRepository<PmBankEntity, String> {

	@Query("From PmBankEntity where bankName=:bankName OR ifscCode=:ifscCode")
	List<PmBankEntity> findByBankNameAndIfscCode(@Param("bankName") String bankName, @Param("ifscCode") String ifscCode);

	@Query("From PmBankEntity where bankCode=:bankCode")
	List<PmBankEntity> getBankDetailsBasedOnBankcode(@Param("bankCode") String bankCode);

}
