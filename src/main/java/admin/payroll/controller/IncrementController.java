package admin.payroll.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.entity.IncrementMainEntity;
import admin.payroll.models.DniModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.service.IncrementService;

@RestController
@RequestMapping(value ="/increment")
public class IncrementController {
	
	private static final Logger log = LoggerFactory.getLogger(DaArrearController.class);

	@Autowired
	private IncrementService incrementService;
	
	@PostMapping("/processAnnualIncrement")
	public ResponseDTO annualIncrementProducer(@RequestBody @Valid DniModel payload) {
		log.debug("run annualIncrement Producer");
		return incrementService.annualIncrementProducer(payload);
	}
	
	@GetMapping("/getAllIncrementMain")
	public ResponseDTO getAllIncrementMain() {
		log.debug("getAllIncrementMain");
		return incrementService.getAllIncrementMain();
		
	}

	@PostMapping("/getIncrementMainByClass")
	public ResponseDTO getIncrementMainByClass(@RequestBody @Valid IncrementMainEntity payload) {
		
		log.debug("getIncrementMainByClass");
		return incrementService.getIncrementMainByClass(payload);
		
	}
}
