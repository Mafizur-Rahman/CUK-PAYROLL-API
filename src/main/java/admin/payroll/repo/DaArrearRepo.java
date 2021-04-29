package admin.payroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.AddressEntity;

@Repository
public interface DaArrearRepo extends JpaRepository<AddressEntity, String> {

}
