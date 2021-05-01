package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import admin.payroll.models.PmId;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePmLeaveAccountingModel;
import admin.payroll.models.SavePmLeavePostingModel;
import admin.payroll.models.SavePmLeaveTypeModel;

@Service
public interface LeaveManagementService {

	@Transactional
	ResponseDTO savePmLeaveAccounting(@Valid SavePmLeaveAccountingModel payload);

	@Transactional
	ResponseDTO getAllPmLeaveManagement();

	@Transactional
	ResponseDTO getALlLeaveAccEmpAndLeave();

	@Transactional
	ResponseDTO getLeaveAccountingById(@Valid PmId payload);

	@Transactional
	ResponseDTO getLeaveAccountingByEmpandleave(@Valid PmId payload);

	@Transactional
	ResponseDTO deletingLeaveAccountingById(@Valid PmId payload);

	@Transactional
	ResponseDTO savePmLeavePosting(@Valid SavePmLeavePostingModel payload);

	@Transactional
	ResponseDTO getAllPmLeavePosting();

	@Transactional
	ResponseDTO getLeavePostingById(@Valid PmId payload);

	@Transactional
	ResponseDTO deletingLeavePostingById(@Valid PmId payload);

	@Transactional
	ResponseDTO savePmLeaveType(@Valid SavePmLeaveTypeModel payload);

	@Transactional
	ResponseDTO getALlLeavePostEmpAndLeave();

	@Transactional
	ResponseDTO getAllPmLeaveType();

	@Transactional
	ResponseDTO getLeaveTypeById(@Valid PmId payload);

	@Transactional
	ResponseDTO deletingLeaveTypeById(@Valid PmId payload);

	@Transactional
	ResponseDTO getLeaveTypeDatas(@Valid SavePmLeaveTypeModel payload);

}
