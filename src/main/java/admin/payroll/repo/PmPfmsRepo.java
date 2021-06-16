package admin.payroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import admin.payroll.entity.PmPfmsEntity;

public interface PmPfmsRepo extends JpaRepository<PmPfmsEntity, Integer> {

}
