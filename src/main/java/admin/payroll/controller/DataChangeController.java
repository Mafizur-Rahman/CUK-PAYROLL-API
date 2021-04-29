package admin.payroll.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.exceptions.InvalidJsonException;
import admin.payroll.models.EditCurrentMonthEdModel;
import admin.payroll.models.EditInstalRecovModel;
import admin.payroll.models.EditPmPraModel;
import admin.payroll.models.EditRegRecovModel;
import admin.payroll.models.EmpAndEdCodeModel;
import admin.payroll.models.GetCurrentMonthEdModel;
import admin.payroll.models.GetPayRatesModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePmPraModel;
import admin.payroll.models.SaveRegRecovModel;
import admin.payroll.models.SaveCurrentMonthEdModel;
import admin.payroll.models.SaveInstalRecovModel;
import admin.payroll.service.DataChangeService;
import admin.payroll.utils.StringConstants;

@RestController
@RequestMapping(value = "/dataChange")
public class DataChangeController {

	private static final Logger log = LoggerFactory.getLogger(DataChangeController.class);

	@Autowired
	DataChangeService dataChangeServive;
	
	
	@GetMapping("/checkSalaryProcessedOrNot")
	public ResponseDTO checkSalaryProcessedOrNot() {
			log.debug("saving getPayRates");
			return dataChangeServive.checkSalaryProcessedOrNot();
	}


	@PostMapping("/getPayRates")
	public ResponseDTO getPayRates(@RequestBody @Valid GetPayRatesModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving getPayRates");
			return dataChangeServive.getPayRates(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getCurrentMonthEdEmployeeWise")
	public ResponseDTO getCurrentMonthEdEmployeeWise(@RequestBody @Valid GetCurrentMonthEdModel payload,
			BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getCurrentMonthEdEmployeeWise");
			return dataChangeServive.getCurrentMonthEdEmployeeWise(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getCurrentMonthEdEDCodeWise")
	public ResponseDTO getCurrentMonthEdEDCodeWise(@RequestBody @Valid GetCurrentMonthEdModel payload,
			BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getCurrentMonthEdEDCodeWise");
			return dataChangeServive.getCurrentMonthEdEDCodeWise(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	// Table used is PM_PRA
	@PostMapping("/savePayRate")
	public ResponseDTO savePayRate(@RequestBody @Valid SavePmPraModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving savePayRate");
			return dataChangeServive.savePayRate(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/editPayRate")
	public ResponseDTO editPayRate(@RequestBody @Valid EditPmPraModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving editPayRate");
			return dataChangeServive.editPayRate(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/deletePayRate/{id}")
	public ResponseDTO deleteDocument(@PathVariable("id") Integer id) {
		log.debug("saving deleteDocument");
		return dataChangeServive.deletePayRate(id);
	}

	
	@PostMapping("/checkPayRateExist")
	public ResponseDTO checkPayRateExist(@RequestBody EmpAndEdCodeModel model) {
		return this.dataChangeServive.checkPayRateExist(model);
	}
	
//Table used is PM_MED	
	@PostMapping("/saveCurrentMonthED")
	public ResponseDTO saveCurrentMonthED(@RequestBody @Valid SaveCurrentMonthEdModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveCurrentMonthED");
			return dataChangeServive.saveCurrentMonthED(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	
	
	
	public ResponseDTO editCurrentMonthED(@RequestBody @Valid EditCurrentMonthEdModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving editCurrentMonthED");
			return dataChangeServive.editCurrentMonthED(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

//Table used is PM_RED	
	@PostMapping("/saveRegularRecoveries")
	public ResponseDTO saveRegularRecoveries(@RequestBody @Valid SaveRegRecovModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveRegularRecoveries");
			return dataChangeServive.saveRegularRecoveries(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/editRegularRecoveries")
	public ResponseDTO editRegularRecoveries(@RequestBody @Valid EditRegRecovModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving editRegularRecoveries");
			return dataChangeServive.editRegularRecoveries(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

//Table used is PM_LOAN	
	@PostMapping("/saveInstallRecov")
	public ResponseDTO saveInstallRecov(@RequestBody @Valid SaveInstalRecovModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveInstallRecov");
			return dataChangeServive.saveInstallRecov(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/editInstallRecov")
	public ResponseDTO editInstallRecov(@RequestBody @Valid EditInstalRecovModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving editInstallRecov");
			return dataChangeServive.editInstallRecov(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getAllDescAndCode")
	public ResponseDTO getAllDescAndCode() {
		log.debug("getAllDescAndCode");
		return dataChangeServive.dataChangeServive();
	}

	@PostMapping("/getRegularRecoveries")
	public ResponseDTO getRegularRecoveries(@RequestBody @Valid EmpAndEdCodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving getRegularRecoveries");
			return dataChangeServive.getRegularRecoveries(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getInstallmentRecoveries")
	public ResponseDTO getInstallmentRecoveries(@RequestBody @Valid EmpAndEdCodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving getInstallmentRecoveries");
			return dataChangeServive.getInstallmentRecoveries(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

}
