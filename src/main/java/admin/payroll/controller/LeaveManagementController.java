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
import admin.payroll.models.PmId;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePmLeaveAccountingModel;
import admin.payroll.models.SavePmLeavePostingModel;
import admin.payroll.models.SavePmLeaveTypeModel;
import admin.payroll.service.LeaveManagementService;
import admin.payroll.utils.StringConstants;

@RestController
@RequestMapping(value = "/leaveManage")
public class LeaveManagementController {

	private static final Logger log = LoggerFactory.getLogger(LeaveManagementController.class);

	@Autowired
	LeaveManagementService leaveManagementService;

	@PostMapping("/saveAccounting")
	public ResponseDTO savePmLeaveAccounting(@RequestBody SavePmLeaveAccountingModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveAccounting");
			return leaveManagementService.savePmLeaveAccounting(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getALlPmLeaveAccounting")
	public ResponseDTO getAllPmLeaveManagement() {
		log.debug("getting all Accounting");
		return leaveManagementService.getAllPmLeaveManagement();
	}

	@GetMapping("/getALlLeaveAccEmpAndLeave")
	public ResponseDTO getALlLeaveAccEmpAndLeave() {
		log.debug("getting all Accounting");
		return leaveManagementService.getALlLeaveAccEmpAndLeave();
	}

	@PostMapping("/getLeaveAccountingByEmpAndLeave")
	public ResponseDTO getLeaveAccountingByEmpandleave(@RequestBody @Valid PmId payload) {
		log.debug("getting  Accounting by id");
		return leaveManagementService.getLeaveAccountingByEmpandleave(payload);

	}

	@PostMapping("/getPmLeaveAccountingById")
	public ResponseDTO getLeaveAccountingById(@RequestBody @Valid PmId payload) {
		log.debug("getting  Accounting by id");
		return leaveManagementService.getLeaveAccountingById(payload);

	}

	@PostMapping("/deleteLeaveAccountingById")
	public ResponseDTO deletingLeaveAccountingById(@RequestBody @Valid PmId payload) {
		log.debug("deletting Accounting by id");
		return leaveManagementService.deletingLeaveAccountingById(payload);

	}

	@PostMapping("/savePosting")
	public ResponseDTO savePmLeavePosting(@RequestBody @Valid SavePmLeavePostingModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("savePosting");
			return leaveManagementService.savePmLeavePosting(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getALlPmLeavePosting")
	public ResponseDTO getAllPmLeavePosting() {
		log.debug("getting all Posting");
		return leaveManagementService.getAllPmLeavePosting();
	}

	@GetMapping("/getALlLeavePostEmpAndLeave")
	public ResponseDTO getALlLeavePostEmpAndLeave() {
		log.debug("getting all Posting");
		return leaveManagementService.getALlLeavePostEmpAndLeave();
	}

	@PostMapping("/getPmLeavePostingById")
	public ResponseDTO getLeavePostingById(@RequestBody @Valid PmId payload) {
		log.debug("getting Posting by id");
		return leaveManagementService.getLeavePostingById(payload);

	}

	@PostMapping("/deleteLeavePostingById")
	public ResponseDTO deletingLeavePostingById(@RequestBody @Valid PmId payload) {
		log.debug("deletting Posting by id");
		return leaveManagementService.deletingLeavePostingById(payload);

	}

	@PostMapping("/saveType")
	public ResponseDTO savePmLeaveType(@RequestBody @Valid SavePmLeaveTypeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveType");
			return leaveManagementService.savePmLeaveType(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getALlPmLeaveType")
	public ResponseDTO getAllPmLeaveType() {
		log.debug("getting all Type");
		return leaveManagementService.getAllPmLeaveType();
	}

	@PostMapping("/getPmLeaveTypeById")
	public ResponseDTO getLeaveTypeById(@RequestBody @Valid PmId payload) {
		log.debug("getting Type by id");
		return leaveManagementService.getLeaveTypeById(payload);

	}

	@PostMapping("/deleteLeaveTypeById")
	public ResponseDTO deletingLeaveTypeById(@RequestBody @Valid PmId payload) {
		log.debug("deletting Type by id");
		return leaveManagementService.deletingLeaveTypeById(payload);

	}

	@PostMapping("/getLeaveTypeDatas")
	public ResponseDTO getLeaveTypeDatas(@RequestBody @Valid SavePmLeaveTypeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting employeeDatas");
			return leaveManagementService.getLeaveTypeDatas(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

}
