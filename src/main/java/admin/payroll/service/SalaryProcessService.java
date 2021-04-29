package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import admin.payroll.models.GetMonthOpeningModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SalaryProcessModel;
import admin.payroll.models.SaveCloseMonthModel;
import admin.payroll.models.SaveMonthOpeningModel;

@Service
public interface SalaryProcessService {

	@Transactional
	ResponseDTO salaryProcess1(@Valid SalaryProcessModel payload);

	@Transactional
	ResponseDTO salaryProcess2(@Valid SalaryProcessModel payload);

	@Transactional
	ResponseDTO salaryProcess3(@Valid SalaryProcessModel payload);

	@Transactional
	ResponseDTO saveMonthOpening(@Valid SaveMonthOpeningModel payload);

	@Transactional
	ResponseDTO getMonthOpening(@Valid GetMonthOpeningModel payload);

	@Transactional
	ResponseDTO saveMonthclosing(@Valid SalaryProcessModel payload);

	@Transactional
	ResponseDTO reverseSalary(@Valid SalaryProcessModel payload);

	@Transactional
	ResponseDTO checkSalaryProcess(@Valid SalaryProcessModel payload);
	
	@Transactional
	ResponseDTO getMonthOpeningData();

}
