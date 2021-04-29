package admin.payroll.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import admin.payroll.exceptions.InvalidJsonException;
import admin.payroll.models.CodeModel;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.EmployeeProfileModel;
import admin.payroll.models.GetKinModel;
import admin.payroll.models.IDModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SaveDataMasterModel;
import admin.payroll.models.SaveEmployeeAddressModel;
import admin.payroll.models.SaveEmployeeDatasModel;
import admin.payroll.models.SaveEmployeeQualificationModel;
import admin.payroll.models.SaveKINFeeMasterModel;
import admin.payroll.models.SaveKINMasterModel;
import admin.payroll.service.EmployeeManagementService;
import admin.payroll.utils.StringConstants;

@RestController
@RequestMapping(value = "/employeeManagement")
public class EmployeeManagementController {

	@Autowired
	EmployeeManagementService employeeManagementService;

	private static final Logger log = LoggerFactory.getLogger(EmployeeManagementController.class);

	@GetMapping("/getAllEmployeeId")
	public ResponseDTO geAllEmployeeId() {
		log.debug("getting AllEmployeeId");
		return employeeManagementService.getAllEmployeeIds();
	}

	@GetMapping("/getAllEmployee")
	public ResponseDTO getAllEmployee() {
		log.debug("getting getAllEmployee");
		return employeeManagementService.getAllEmployee();
	}

	@GetMapping("/getAllEmployeeNameAndId")
	public ResponseDTO getAllEmployeeNameAndId() {
		log.debug("getting AllEmployeeNameAndId");
		return employeeManagementService.getAllEmployeeNameAndId();
	}

	@GetMapping("/getAllDivisionDepartment")
	public ResponseDTO getAllDivisionDepartment() {
		log.debug("getting allDivisionDepartment");
		return employeeManagementService.getAllDivisionDepartment();
	}

	@PostMapping("/getClassAndDesigntion")
	public ResponseDTO getClassAndDesigntion(@RequestBody @Valid EmployeeID empId, BindingResult data) {
		log.debug("getting getClassAndDesigntion");
		return employeeManagementService.getClassAndDesigntion(empId.getEmployeeId());

	}

	@PostMapping("/getEmployeeName")
	public ResponseDTO getEmployeeName(@RequestBody @Valid EmployeeID empId, BindingResult data) {
		log.debug("getting employeeName");
		return employeeManagementService.getEmployeeName(empId.getEmployeeId());

	}

	@PostMapping("/getEmployeeDatas")
	public ResponseDTO getEmployeeDatas(@RequestBody @Valid EmployeeProfileModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting employeeDatas");
			return employeeManagementService.getEmployeeDatas(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/deleteEmployee")
	public ResponseDTO deleteEmployee(@RequestBody @Valid EmployeeID empId, BindingResult data) {
		log.debug("deleting Employee");
		return employeeManagementService.deleteEmployee(empId.getEmployeeId());

	}

	@PostMapping("/checkDuplicateEmployeeName")
	public ResponseDTO checkDuplicateEmployeeName(@RequestBody @Valid EmployeeID empId, BindingResult data) {
		log.debug("checkDuplicateEmployeeName");
		return employeeManagementService.checkDuplicateEmployeeName(empId.getEmployeeId());

	}

	// getDivisionOrDepartment has to be added(PM_DEPT)

	@GetMapping("getPayMatrixLevel")
	public ResponseDTO getPayMatrixLevel() {
		log.debug("getPayMatrixLevel");
		return employeeManagementService.getPayMatrixLevel();

	}

	@GetMapping("getPayMatrixCell")
	public ResponseDTO getPayMatrixCell() {
		log.debug("getPayMatrixCell");
		return employeeManagementService.getPayMatrixCell();

	}

	@GetMapping("getGradePay")
	public ResponseDTO getGradePay() {
		log.debug("getGradePay");
		return employeeManagementService.getGradePay();

	}

	@PostMapping("/saveEmployeeData")
	public ResponseDTO saveEmployeeData(@RequestBody @Valid SaveEmployeeDatasModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveEmployeeData");
			return employeeManagementService.saveEmployeeData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getAddress")
	public ResponseDTO getAddress(@RequestBody @Valid EmployeeProfileModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getAddress");
			return employeeManagementService.getAddress(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getAllAddressData")
	public ResponseDTO getAllAddressData(@RequestBody @Valid EmployeeID payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getAllAddressData");
			return employeeManagementService.getAllAddressData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@GetMapping("/getAllAddress")
	public ResponseDTO getAllAddress() {
		log.debug("getting getAllAddress");
		return employeeManagementService.getAllAddress();

	}

	@PostMapping("/checkAddress")
	public ResponseDTO checkAddress(@RequestBody @Valid EmployeeID payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("checkAddress");
			return employeeManagementService.checkAddress(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/saveAddress")
	public ResponseDTO saveAddress(@RequestBody @Valid SaveEmployeeAddressModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveAddress");
			return employeeManagementService.saveAddress(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/deleteAddress/{id}")
	public ResponseDTO deleteAddress(@PathVariable("id") String id) {
		log.debug("saving employeeQualificationModel");
		return employeeManagementService.deleteAddress(id);

	}

	@PostMapping("/saveQualification")
	public ResponseDTO saveQualification(@RequestBody @Valid SaveEmployeeQualificationModel payload,
			BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveQualification");
			return employeeManagementService.saveQualification(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getQualification")
	public ResponseDTO getQualification(@RequestBody @Valid EmployeeProfileModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting EmployeeDatas");
			return employeeManagementService.getQualification(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getAllQualification")
	public ResponseDTO getAllQualification(@RequestBody @Valid EmployeeID payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getAllQualification");
			return employeeManagementService.getAllQualification(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getAllQualificationById")
	public ResponseDTO getAllQualificationById(@RequestBody @Valid IDModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getAllQualificationById");
			return employeeManagementService.getAllQualificationById(payload.getId());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@GetMapping("/deleteQualification/{id}")
	public ResponseDTO deleteQualification(@PathVariable("id") String id) {
		log.debug("saving employeeQualificationModel");
		// return employeeManagementService.deleteQualification(id);
		return employeeManagementService.deleteQualification(id);
	}

	@PostMapping("/saveKinMaster")
	public ResponseDTO saveKinMaster(@RequestBody @Valid SaveKINMasterModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving inMaster");
			return employeeManagementService.saveKinMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getAllKinById")
	public ResponseDTO getAllKinById(@RequestBody @Valid GetKinModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getAllKinById");
			return employeeManagementService.getAllKinById(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getKin")
	public ResponseDTO getKin(@RequestBody @Valid EmployeeProfileModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getKin");
			return employeeManagementService.getKin(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@GetMapping("getAllKinData")
	public ResponseDTO getAllKinData() {
		log.debug("getting AllKinData");
		return employeeManagementService.getAllKinData();

	}

	@PostMapping("/saveKinFeeMaster")
	public ResponseDTO saveKinFeeMaster(@RequestBody @Valid SaveKINFeeMasterModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveKinFeeMaster");
			return employeeManagementService.saveKinFeeMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getKinMaster")
	public ResponseDTO getKinMaster(@RequestBody @Valid EmployeeProfileModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting get	ter");
			return employeeManagementService.getKinMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getAllKinFeeById")
	public ResponseDTO getAllKinFeeById(@RequestBody @Valid IDModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getAllKinFeeById");
			return employeeManagementService.getAllKinFeeById(payload.getId());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// Modified on 28-10-2020
	@PostMapping("/deleteKinMaster")
	public ResponseDTO deleteKinMaster(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		log.debug("saving deleteKinMaster");
		return employeeManagementService.deleteKinMaster(payload);
	}

	@GetMapping("/deleteKinFeeMaster/{id}")
	public ResponseDTO deleteKinFeeMaster(@PathVariable("id") Integer id) {
		log.debug("saving deleteKinFeeMaster");
		return employeeManagementService.deleteKinFeeMaster(id);

	}

	@PostMapping("/saveDataMaster")
	public ResponseDTO saveDataMaster(@RequestBody @Valid SaveDataMasterModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveDataMaster");
			return employeeManagementService.saveDataMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getAllDocumentById")
	public ResponseDTO getAllDocumentById(@RequestBody @Valid IDModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getAllDocumentById");
			return employeeManagementService.getAllDocumentById(payload.getId());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getDateById")
	public ResponseDTO getDateById(@RequestBody @Valid IDModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getAllDocumentById");
			return employeeManagementService.getDateById(payload.getId());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getDataMaster")
	public ResponseDTO getDataMaster(@RequestBody @Valid EmployeeProfileModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getDataMaster");
			return employeeManagementService.getDataMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

//	
//	@PostMapping("/getDocumentByPath")
//	public ResponseEntity<?> getDocumentByPath(@RequestBody DocumentPathModel pathModel, HttpServletRequest request) {
//		return this.employeeManagementService.getDocumentByPath(pathModel, request);
//		
//	}

	@PostMapping("/getDocumentByPath")
	public ResponseEntity<?> getDocumentByPath(@RequestParam String filepath, HttpServletRequest request) {
		return this.employeeManagementService.getDocumentByPath(filepath, request);
	}

	@GetMapping("/deleteDataMaster/{id}")
	public ResponseDTO deleteDataMaster(@PathVariable("id") Integer id) {
		log.debug("saving deleteDataMaster");
		return employeeManagementService.deleteDataMaster(id);

	}

//have to give the server path
	// @ExceptionHandler(MultipartException.class)
	@RequestMapping(value = "/saveDocument", method = RequestMethod.POST, consumes = {
			"multipart/form-data;boundary=----WebKitFormBoundaryeBXI2LxBOwRAmzfE;charset=UTF-8" })
	public ResponseDTO saveDocument(@RequestPart(value = "file", required = true) MultipartFile file,
			@RequestPart(value = "types", required = true) String types,
			@RequestPart(value = "documentName", required = true) String documentName,
			@RequestPart(value = "documentsof", required = false) String documentsof,
			@RequestPart(value = "kinName", required = false) String kinName,
			@RequestPart(value = "financialYear", required = false) String financialYear,
			@RequestPart(value = "empId", required = true) String empId,
			@RequestPart(value = "logUser", required = true) String logUser,
			@RequestPart(value = "logIp", required = false) String logIp) throws Exception {

		log.debug("saving saveDocument");
		return employeeManagementService.saveDocument(file, types, documentName, documentsof, kinName, financialYear,
				empId, logUser, logIp);

	}

//	//@ExceptionHandler(MultipartException.class)
//	@RequestMapping(value = "/editDocumentById1", method = RequestMethod.POST, consumes = {
//			"multipart/form-data;charset=UTF-8" })
//	// "multipart/form-data; boundary=----WebKitFormBoundaryicDj8CnfCORbUTAs" })
//	public ResponseDTO editDocumentById(@RequestPart(value = "file", required = false) MultipartFile file,
//			@RequestPart(value = "types", required = true) String types,
//			@RequestPart(value = "documentName", required = true) String documentName,
//			@RequestPart(value = "documentsof", required = false) String documentsof,
//			@RequestPart(value = "kinName", required = false) String kinName,
//			@RequestPart(value = "financialYear", required = false) String financialYear,
//			@RequestPart(value = "id", required = true) int id) throws Exception {
//
//		log.debug("saving editDocumentById");

	@ExceptionHandler(MultipartException.class)
	@RequestMapping(value = "/editDocumentById", method = RequestMethod.POST, consumes = {
			"multipart/form-data;charset=UTF-8" })
	public ResponseDTO saveDocument1(@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart(value = "types", required = true) String types,
			@RequestPart(value = "documentName", required = true) String documentName,
			@RequestPart(value = "documentsof", required = false) String documentsof,
			@RequestPart(value = "kinName", required = false) String kinName,
			@RequestPart(value = "financialYear", required = false) String financialYear,
			@RequestPart(value = "empId", required = true) String empId,
			@RequestPart(value = "logUser", required = true) String logUser,
			@RequestPart(value = "logIp", required = false) String logIp,
			@RequestPart(value = "id", required = false) String id) throws Exception {

		return employeeManagementService.editDocumentById(file, types, documentName, documentsof, kinName,
				financialYear, empId, logUser, logIp, Integer.parseInt(id));

	}

	@PostMapping("/getDocumentById")
	public ResponseDTO getDocumentById(@RequestBody @Valid IDModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getDocumentById");
			return employeeManagementService.getDocumentById(payload.getId());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@GetMapping("/deleteDocument/{id}")
	public ResponseDTO deleteDocument(@PathVariable("id") Integer id) {
		log.debug("saving deleteDocument");
		return employeeManagementService.deleteDocument(id);
	}

	@PostMapping("/getDocumentMaster")
	public ResponseDTO getDocumentMaster(@RequestBody @Valid EmployeeProfileModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getDocumentMaster");
			return employeeManagementService.getDocumentMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@GetMapping("/getAllDocumentMaster")
	public ResponseDTO getAllDocumentMaster() {
		log.debug("getting getDocumentMaster");
		return employeeManagementService.getAllDocumentMaster();

	}

	@PostMapping("/getDesigBasedOnEmpId")
	public ResponseDTO getDesigBasedOnEmpId(@RequestBody @Valid EmployeeID payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getDesigBasedOnEmpId");
			return employeeManagementService.getDesigBasedOnEmpId(payload.getEmployeeId());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@GetMapping("/test")
	public ResponseDTO test() {
		log.debug("getting test");
		return employeeManagementService.test();
	}

}
