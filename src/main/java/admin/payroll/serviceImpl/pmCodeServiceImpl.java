package admin.payroll.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.payroll.entity.PmCodeTypeEntity;
import admin.payroll.repo.PmCodeTypeRepo;
import admin.payroll.service.pmCodeService;

@Service
public class pmCodeServiceImpl implements pmCodeService {

	@Autowired
	 private PmCodeTypeRepo pmCodeTypeRepo;
	
	@Override
	public void SavePmCodeTypeEntity(PmCodeTypeEntity PmCodeTypeEntity) {
		 pmCodeTypeRepo.save(PmCodeTypeEntity);
	}

}
