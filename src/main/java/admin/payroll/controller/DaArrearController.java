package admin.payroll.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import admin.payroll.exceptions.InvalidJsonException;
import admin.payroll.models.DAProcessModel;
import admin.payroll.models.FromToModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.service.DaArrearService;
import admin.payroll.utils.StringConstants;

@RestController
@RequestMapping(value = "/daArrear")
public class DaArrearController {

	private static final Logger log = LoggerFactory.getLogger(DaArrearController.class);

	@Autowired
	DaArrearService daArrearService;

	@PostMapping("/daProcess")
	public ResponseDTO daProcess(@RequestBody @Valid DAProcessModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("daProcess");
			return daArrearService.daProcess(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/reportView")
	public ResponseDTO reportView(@RequestBody @Valid FromToModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("reportView");
			return daArrearService.reportView(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/paymentStatement")
	public ResponseDTO paymentStatement(@RequestBody @Valid FromToModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("paymentStatement");
			return daArrearService.paymentStatement(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

}
