package admin.payroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import admin.payroll.entity.PmDaArrearHdrEntity;

public interface PmDaArrearHdrRepo extends JpaRepository<PmDaArrearHdrEntity, String> {

}
