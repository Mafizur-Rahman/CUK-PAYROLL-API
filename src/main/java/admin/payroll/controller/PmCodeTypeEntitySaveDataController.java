package admin.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.entity.PmCodeTypeEntity;
import admin.payroll.serviceImpl.pmCodeServiceImpl;

@RestController
public class PmCodeTypeEntitySaveDataController {
	
	@Autowired
	private pmCodeServiceImpl PmCodeServiceImpl;
	
	@PostMapping("/saveData")
	public String saveData(@RequestBody PmCodeTypeEntity PmCodeTypeEntity ) {
		PmCodeServiceImpl.SavePmCodeTypeEntity(PmCodeTypeEntity);
		
		
		return "Done";
	}

}
