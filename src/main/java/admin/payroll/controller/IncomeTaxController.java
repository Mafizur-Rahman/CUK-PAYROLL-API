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
import admin.payroll.models.EmployeeID;
import admin.payroll.models.ItaxArrearModel;
import admin.payroll.models.ItaxDataListModel;
import admin.payroll.models.ItaxDataModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.TaxCalcModel;
import admin.payroll.service.IncomeTaxService;
import admin.payroll.utils.StringConstants;

@RestController
@RequestMapping("/incomeTax")
public class IncomeTaxController {

   public static final Logger log = LoggerFactory.getLogger(IncomeTaxController.class);	
   
   @Autowired
   private IncomeTaxService incomeTaxService;
	
	@PostMapping("/saveItaxData")
	public ResponseDTO saveItaxData(@RequestBody @Valid ItaxDataListModel payload,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("saveItaxData");
			return incomeTaxService.saveItaxData(payload);	
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
		
	// create APi to call procedure ItaxMonthlyProcess by kishan pandey
	
	@PostMapping("/ItaxMonthlyProcess")
	public ResponseDTO itaxMonthlyProcess(@RequestBody @Valid ItaxDataModel payload,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("checkMonthandYearDataPresentInSalaryDetail");
			 String payperiod =incomeTaxService.checkMonthandYearDataPresentInSalaryDetail(payload);
			 if(!(payperiod == null)) {
				 return incomeTaxService.itaxMonthlyProcess(payload);
		  }
			 return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
		
	}
	
	//create the Api to call procedure tax_calempid_sixth 
	@PostMapping("/TaxCalEmpIdSixthProcedure")
	public ResponseDTO taxcalempidsixth(@RequestBody @Valid TaxCalcModel payload,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("calling TaxCalEmpIdSixth procedure");
			return incomeTaxService.taxcalempidsixth(payload);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	/*
	 Kishan pandey (pandeykishan626@gmail.com)
	 */
	//create the api to save TaxCalcEntity data 
	
	@PostMapping("/saveTaxCalc")
	public ResponseDTO saveTaxCalc(@RequestBody @Valid TaxCalcModel payload,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("saveTaxCalc");
			return incomeTaxService.saveTaxCalc(payload);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg,APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	

	
	@PostMapping("/getTaxCalcByEmployeeId")
	public ResponseDTO getTaxCalcByEmployeeId(@RequestBody EmployeeID employeeId) {
		return this.incomeTaxService.getTaxCalcByEmployeeId(employeeId);
	}

	
	//create the api to save ItaxArrearEntity
	@PostMapping("/saveItaxArrear")
	public ResponseDTO saveItaxArrear(@RequestBody @Valid ItaxArrearModel payload, BindingResult bindings) {
		if(!bindings.hasErrors()) {
			log.debug("saveitaxArrear");
			return incomeTaxService.saveItaxArrear(payload);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg,APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);

	}
	
	
	//create the api to get data from ItaxDataEntity and ITaxArriearEntity
	@PostMapping("/getItaxDataByEmpId")
	public ResponseDTO getItaxDataByEmpId(@RequestBody @Valid ItaxArrearModel payload, BindingResult bindings) {
		if(!bindings.hasErrors()) {
		log.debug("getItaxDataByEmpId");
		return incomeTaxService.getItaxDataByEmpId(payload);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null); 
	}
	
	
	
}
