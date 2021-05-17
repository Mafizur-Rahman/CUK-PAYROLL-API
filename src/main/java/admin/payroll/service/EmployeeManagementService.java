package admin.payroll.service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import admin.payroll.models.CodeModel;
import admin.payroll.models.DocumentPathModel;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.EmployeeProfileModel;
import admin.payroll.models.GetKinModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SaveDataMasterModel;
import admin.payroll.models.SaveEmployeeAddressModel;
import admin.payroll.models.SaveEmployeeDatasModel;
import admin.payroll.models.SaveEmployeeQualificationModel;
import admin.payroll.models.SaveKINFeeMasterModel;
import admin.payroll.models.SaveKINMasterModel;

@Service
public interface EmployeeManagementService {

	@Transactional
	ResponseDTO saveEmployeeData(@Valid SaveEmployeeDatasModel payload);

	@Transactional
	ResponseDTO saveAddress(@Valid SaveEmployeeAddressModel payload);

//	@Transactional
//	ResponseDTO getQualifications(@Valid SaveEmployeeQualificationModel payload);

	@Transactional
	ResponseDTO saveKinMaster(@Valid SaveKINMasterModel payload);

	@Transactional
	ResponseDTO saveKinFeeMaster(@Valid SaveKINFeeMasterModel payload);

	@Transactional
	ResponseDTO saveDataMaster(@Valid SaveDataMasterModel payload);

	@Transactional
	ResponseDTO getAllEmployeeIds();

	@Transactional
	ResponseDTO getEmployeeDatas(@Valid EmployeeProfileModel payload);

	@Transactional
	ResponseDTO getPayMatrixLevel();

	@Transactional
	ResponseDTO getPayMatrixCell();

	@Transactional
	ResponseDTO getEmployeeName(String empId);

	@Transactional
	ResponseDTO deleteEmployee(String employeeId);

	@Transactional
	ResponseDTO checkDuplicateEmployeeName(String enployeeId);

	@Transactional
	ResponseDTO deleteQualification(String id);

	@Transactional
	ResponseDTO getAllEmployee();

	@Transactional
	ResponseDTO deleteKinMaster(@Valid CodeModel payload);

	@Transactional
	ResponseDTO deleteKinFeeMaster(int id);

	@Transactional
	ResponseDTO deleteDataMaster(int id);

	@Transactional
	ResponseDTO deleteDocument(int id);

	@Transactional
	ResponseDTO getAllDivisionDepartment();

	@Transactional
	ResponseDTO getAllKinData();

	@Transactional
	ResponseDTO getAddress(@Valid EmployeeProfileModel payload);

	@Transactional
	ResponseDTO getQualification(@Valid EmployeeProfileModel payload);

	@Transactional
	ResponseDTO getKin(@Valid EmployeeProfileModel payload);

	@Transactional
	ResponseDTO getKinMaster(@Valid EmployeeProfileModel payload);

	@Transactional
	ResponseDTO getDataMaster(@Valid EmployeeProfileModel payload);

	@Transactional
	ResponseDTO getDocumentMaster(@Valid EmployeeProfileModel payload);

	@Transactional
	ResponseDTO getDesigBasedOnEmpId(String employeeId);

	@Transactional
	ResponseDTO getAllEmployeeNameAndId();

	@Transactional
	ResponseDTO getClassAndDesigntion(String employeeId);

	@Transactional
	ResponseDTO getAllAddress();

	@Transactional
	ResponseDTO checkAddress(@Valid EmployeeID payload);

	@Transactional
	ResponseDTO getGradePay();

	@Transactional
	ResponseDTO deleteAddress(String id);

	@Transactional
	ResponseDTO getAllAddressData(@Valid EmployeeID payload);

	@Transactional
	ResponseDTO getAllQualification(@Valid EmployeeID payload);

	@Transactional
	ResponseDTO saveQualification(@Valid SaveEmployeeQualificationModel payload);

	@Transactional
	ResponseDTO getAllQualificationById(Integer id);

	@Transactional
	ResponseDTO getAllKinById(@Valid GetKinModel payload);

	@Transactional
	ResponseDTO getAllKinFeeById(Integer id);

	@Transactional
	ResponseDTO getDateById(Integer id);

	@Transactional
	ResponseDTO getAllDocumentById(Integer id);

	@Transactional
	ResponseDTO saveDocument(MultipartFile file, String types, String documentName, String documentsof, String kinName,
			String financialYear, String empId, String logUser, String logIp);

	@Transactional
	ResponseDTO getDocumentById(Integer id);

	@Transactional
	ResponseDTO editDocumentById(MultipartFile file, String types, String documentName, String documentsof,
			String kinName, String financialYear, String empId, String logUser, String logIp, int id);

	@Transactional
	ResponseDTO getAllDocumentMaster();

	@Transactional
	ResponseDTO test();

	@Transactional
	ResponseEntity<?> getDocumentByPath(String pathModel, HttpServletRequest request);

}
