package admin.payroll.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import admin.payroll.enums.APISTATUS;
import admin.payroll.exceptions.InvalidJsonException;
import admin.payroll.models.BonusChangeDataModel;
import admin.payroll.models.PreProcessModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.service.BonusService;
import admin.payroll.utils.StringConstants;

@RestController
@RequestMapping(value = "/bonus")
public class BonusController {

	private static final Logger log = LoggerFactory.getLogger(DaArrearController.class);

	@Autowired
	BonusService bonusService;

	
	//create the APi to call SP_BONUS_PREPROCESS procedure by kishan pandey
	@PostMapping("/preBonus")
	public ResponseDTO preBonus(@RequestBody @Valid PreProcessModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("bonusService");
			return bonusService.preBonus(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/changeData")
	public ResponseDTO changeData(@RequestBody @Valid BonusChangeDataModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("changeData");
			return bonusService.changeData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}
	
	//create the API to get the data from PmBonusMstEntity by kishan pandey
	@GetMapping("/getPmBonusMstData")
	public ResponseDTO getPmBonusMstData() {
		log.debug("getPmBonusMstData");
		return bonusService.getPmBonusMstData();
	}
	
	//create the API to get the PmBonusMstEntity by EmpId
	@PostMapping("/getPmBonusMstDataByEmpId")
	public ResponseDTO getPmBonusMstDataByEmpId(@RequestBody @Valid BonusChangeDataModel payload,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("BonusChangeDataModel");
			return bonusService.getPmBonusMstDataByEmpId(payload);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	
	@GetMapping("/bonusReports")
	public ResponseDTO bonusReports() {
		log.debug("genereating report");
		return bonusService.bonusReports();
	}
	
	@PostMapping("/bonusReportsByClassAndFyr")
	public ResponseDTO bonusReportsByClassAndFyr(@RequestBody @Valid BonusChangeDataModel payload,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("getting databonusReportsByClassAndFyr");
			return bonusService.bonusReportsByClassAndFyr(payload);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);

	}
	
	@PostMapping("/bonusPaymentByClassAndFyr")
	public ResponseDTO bonusPaymentByClassAndFyr(@RequestBody @Valid BonusChangeDataModel payload,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("getting dataEmployeeByClassnAndFyr");
			return bonusService.bonusPaymentByClassAndFyr(payload);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);

	}
	
	@GetMapping("/bonusPayment")
	public ResponseDTO bonusPayment() {
		log.debug("getting bonusPayment");
		return bonusService.bonusPayment();
	}
	
	
	@PostMapping("/checkIfBonusProcessed")
	public ResponseDTO checkIfBonusProcessed(@RequestBody BonusChangeDataModel payload) {
		log.debug("CHECKING BONUS PROCESS");
		return this.bonusService.checkIfBonusProcessed(payload);
	}
	

	
	@PostMapping("/reverseBonusProcess")
	public ResponseDTO reverseBonusProcess(@RequestBody BonusChangeDataModel payload) {
		log.debug("reverse Bonus process");
		return this.bonusService.reverseBonusProcess(payload);
	}
	//
	
}
