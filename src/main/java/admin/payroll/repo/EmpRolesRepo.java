package admin.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.EmpRolesEntity;

@Repository
public interface EmpRolesRepo extends JpaRepository<EmpRolesEntity, String>{


}
