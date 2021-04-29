package admin.payroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import admin.payroll.entity.SumTaxEntity;

public interface SumTaxRepo extends JpaRepository<SumTaxEntity, String> {

}
