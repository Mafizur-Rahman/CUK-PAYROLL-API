package admin.payroll.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import admin.payroll.exceptions.InvalidJsonException;
import admin.payroll.models.GetMonthOpeningModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SalaryProcessModel;
import admin.payroll.models.SaveMonthOpeningModel;
import admin.payroll.service.SalaryProcessService;
import admin.payroll.utils.StringConstants;

@RestController
@RequestMapping(value = "/salaryProcess")
public class SalaryProcessController {

	private static final Logger log = LoggerFactory.getLogger(SalaryProcessController.class);

	@Autowired
	SalaryProcessService salaryProcessService;
	
	
	@PostMapping("/checkSalaryProcess")
	public ResponseDTO checkSalaryProcess(@RequestBody @Valid SalaryProcessModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving salaryProcess1");
			return salaryProcessService.checkSalaryProcess(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	// Emp_mast , Pm_sal_hdr

	@PostMapping("/salaryProcess1")
	public ResponseDTO salaryProcess1(@RequestBody @Valid SalaryProcessModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving salaryProcess1");
			return salaryProcessService.salaryProcess1(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/salaryProcess2")
	public ResponseDTO salaryProcess2(@RequestBody @Valid SalaryProcessModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving salaryProcess2");
			return salaryProcessService.salaryProcess2(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/salaryProcess3")
	public ResponseDTO salaryProcess3(@RequestBody @Valid SalaryProcessModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving salaryProcess3");
			return salaryProcessService.salaryProcess3(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/saveMonthOpening")
	public ResponseDTO saveMonthOpening(@RequestBody @Valid SaveMonthOpeningModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving getMonthOpening");
			return salaryProcessService.saveMonthOpening(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getMonthOpeningData")
	public ResponseDTO getMonthOpeningData() {
		log.debug("getting getMonthOpeningData");
		return salaryProcessService.getMonthOpeningData();
		} 
	
	@PostMapping("/getMonthOpening")
	public ResponseDTO getMonthOpening(@RequestBody @Valid GetMonthOpeningModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving GetMonthOpeningModel");
			return salaryProcessService.getMonthOpening(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/saveMonthclosing")
	public ResponseDTO saveMonthclosing(@RequestBody @Valid SalaryProcessModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveMonthclosing");
			return salaryProcessService.saveMonthclosing(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/reverseSalary")
	public ResponseDTO reverseSalary(@RequestBody @Valid SalaryProcessModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving reverseSalary");
			return salaryProcessService.reverseSalary(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

}
