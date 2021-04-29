package admin.payroll.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import admin.payroll.enums.APISTATUS;
import admin.payroll.models.AckNumber;
import admin.payroll.models.EmpIdFyrModel;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.GpfAdvSaveModel;
import admin.payroll.models.GpfClosingSaveModel;
import admin.payroll.models.GpfMastSubProcessModel;
import admin.payroll.models.GpfRefundSaveModel;
import admin.payroll.models.GpfSaveModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.service.GpfService;
import admin.payroll.utils.StringConstants;
import lombok.val;

@RestController
@RequestMapping("/gpf")
public class GpfController {
	
	@Autowired
	private GpfService gpfService;
	
	private static final Logger log = LoggerFactory.getLogger(GpfController.class);
	
	//created by kishan pandey
	
	@PostMapping("/gpfMastSubProcess")
	public ResponseDTO gpfMastSubProcess(@RequestBody @Valid GpfMastSubProcessModel payload ,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("gpfMastSubProcess start in controller");
			return gpfService.gpfMastSubProcess(payload);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	@PostMapping("/saveGpfMast")
	public ResponseDTO saveGpfMast(@RequestBody @Valid GpfSaveModel gpfSaveModel, BindingResult binding) {
		if (!binding.hasErrors()) {
			log.debug("saving gpf mast");
			return gpfService.saveGpfMast(gpfSaveModel);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	@PostMapping("/getGpfMastByEmpIdAndFy")
	public ResponseDTO getGpfMastByEmpIdAndFy(@RequestBody EmpIdFyrModel empIdFyrModel) {
		return this.gpfService.getGpfMastByEmpIdAndFy(empIdFyrModel);
	}
	
	@PostMapping("/getGpfEmployeeDisplayModel") 
	public ResponseDTO getGpfEmployeeDisplayModel(@RequestBody EmployeeID empId) {
		return this.gpfService.getGpfEmployeeDisplayModel(empId);
	}
	
	@PostMapping("/saveGpfAdv")
	public ResponseDTO saveGpfAdv(@RequestBody @Valid GpfAdvSaveModel gpfAdvSaveModel, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			return this.gpfService.saveGpfAdv(gpfAdvSaveModel);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	@PostMapping("/getGpfAdvByEmpId")
	public ResponseDTO getGpfAdvByEmpId(@RequestBody EmployeeID id) {
		return this.gpfService.getGpfAdvanceByEmpId(id);
	}
	
	@PostMapping("/getGpfAdvByAckNo")
	public ResponseDTO getGpfAdvByAckNo(@RequestBody AckNumber id) {
		return this.gpfService.getGpfAdvByAckNo(id);
	}
	
	@PostMapping("/deleteGpfAdvByAckNo")
	public ResponseDTO deleteGpfAdvByEmpId(@RequestBody AckNumber ackNumber) {
		return this.gpfService.deleteGpfAdvByAckNo(ackNumber);
	}
	
	@PostMapping("/getGpfMustSubByEmpIdAndFyr")
	public ResponseDTO getGpfMustSubByEmpIdAndFyr(@RequestBody @Valid GpfMastSubProcessModel payload,BindingResult binding) {
		if(!binding.hasErrors()) {
			log.debug("GpfMastSubProcessModel process");
			return gpfService.getGpfMustSubByEmpIdAndFyr(payload);
		}
		return new ResponseDTO(StringConstants.INVALID_INPUT, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	@PostMapping("/saveGpfRefund")
	public ResponseDTO saveGpfRefund(@RequestBody @Valid GpfRefundSaveModel gpfRefundSaveModel,BindingResult bindings) {
		if (!bindings.hasErrors()) {
			return this.gpfService.saveGpfRefund(gpfRefundSaveModel);
		}
		return new ResponseDTO(StringConstants.INVALID_INPUT, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	@PostMapping("/getGpfRefundByEmployeeId")
	public ResponseDTO getGpfRefundByEmployeeId(@RequestBody EmployeeID employeeId) {
		return this.gpfService.getGpfRefundByEmployeeId(employeeId);
	}
	
	@PostMapping("/getGpfRefundByAckNo")
	public ResponseDTO getGpfRefundByAckNo(@RequestBody AckNumber ackNumber) {
		return this.gpfService.getGpfRefundByAckNo(ackNumber);
	}
	
	@PostMapping("/deleteGpfRefundByAckNo")
	public ResponseDTO deleteGpfRefundByAckNo(@RequestBody AckNumber ackNumber) {
		return this.gpfService.deleteGpfRefundByAckNo(ackNumber);
	}
	
	@PostMapping(path = "/readExcelFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseDTO readExcelFile(@RequestPart MultipartFile excelFile) {
		return this.gpfService.readExcelFile(excelFile);
	}
	
	@PostMapping("/saveGpfClosing")
	public ResponseDTO saveGpfClosing(@RequestBody @Valid GpfClosingSaveModel gpfClosingSaveModel, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			return this.gpfService.saveGpfCloseBal(gpfClosingSaveModel);
		}
		return new ResponseDTO(StringConstants.INVALID_INPUT, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
}
