package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import admin.payroll.models.AckNumber;
import admin.payroll.models.EmpIdFyrModel;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.GpfAdvSaveModel;
import admin.payroll.models.GpfClosingSaveModel;
import admin.payroll.models.GpfMastSubProcessModel;
import admin.payroll.models.GpfRefundSaveModel;
import admin.payroll.models.GpfSaveModel;
import admin.payroll.models.ResponseDTO;

public interface GpfService {

	@Transactional
	ResponseDTO gpfMastSubProcess(@Valid GpfMastSubProcessModel payload);

	@Transactional
	ResponseDTO saveGpfMast(@Valid GpfSaveModel gpfSaveModel);

	@Transactional
	ResponseDTO getGpfMastByEmpIdAndFy(EmpIdFyrModel empIdFyrModel);

	@Transactional
	ResponseDTO getGpfEmployeeDisplayModel(EmployeeID empId);

	@Transactional
	ResponseDTO saveGpfAdv(@Valid GpfAdvSaveModel gpfAdvSaveModel);

	@Transactional
	ResponseDTO getGpfAdvanceByEmpId(EmployeeID id);
	
	@Transactional
	ResponseDTO getGpfAdvByAckNo(AckNumber id);

	@Transactional
	ResponseDTO deleteGpfAdvByAckNo(AckNumber ackNumber);
	
	@Transactional
	ResponseDTO getGpfMustSubByEmpIdAndFyr(@Valid GpfMastSubProcessModel payload);

	@Transactional
	ResponseDTO saveGpfRefund(@Valid GpfRefundSaveModel gpfRefundSaveModel);

	@Transactional
	ResponseDTO getGpfRefundByEmployeeId(EmployeeID employeeId);

	@Transactional
	ResponseDTO getGpfRefundByAckNo(AckNumber ackNumber);

	@Transactional
	ResponseDTO deleteGpfRefundByAckNo(AckNumber ackNumber);

	@Transactional
	ResponseDTO readExcelFile(MultipartFile excelFile);

	@Transactional
	ResponseDTO saveGpfCloseBal(@Valid GpfClosingSaveModel gpfClosingSaveModel);
}
