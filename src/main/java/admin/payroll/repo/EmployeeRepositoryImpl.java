package admin.payroll.repo;

import admin.payroll.entity.EmpMastEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EmpMastEntity> getAllEmployees() {
		StoredProcedureQuery findByYearProcedure = em.createNamedStoredProcedureQuery("getAllEmployees");
		return findByYearProcedure.getResultList();
	}
}
