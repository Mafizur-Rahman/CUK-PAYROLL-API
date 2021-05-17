package admin.payroll.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePfmsTempModel;
import admin.payroll.service.PfmsService;

@RestController
@RequestMapping(value = "/pfms")
public class PfmsController {

	private static final Logger log = LoggerFactory.getLogger(DaArrearController.class);

	@Autowired
	private PfmsService pfmsService;

	@PostMapping("/uploadDbt01ToTemp")
	public ResponseDTO annualIncrementProducer(@RequestBody @Valid SavePfmsTempModel payload) {
		log.debug("upoad pfms temp ");
		return pfmsService.uploadDbt01ToTemp(payload);
	}
//
//	@GetMapping("/getAllIncrementMain")
//	public ResponseDTO getAllIncrementMain() {
//		log.debug("getAllIncrementMain");
//		return incrementService.getAllIncrementMain();
//
//	}
//
//	@PostMapping("/getIncrementMainByClass")
//	public ResponseDTO getIncrementMainByClass(@RequestBody @Valid IncrementMainEntity payload) {
//
//		log.debug("getIncrementMainByClass");
//		return incrementService.getIncrementMainByClass(payload);
//
//	}
}
