package admin.payroll.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import admin.payroll.entity.EmpUsersEntity;

@Repository
public interface EmpUsersRepo extends JpaRepository<EmpUsersEntity, String> {

}