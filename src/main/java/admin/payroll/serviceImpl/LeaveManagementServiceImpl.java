package admin.payroll.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import admin.payroll.config.Convertor;
import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.PmLeaveAccountingEntity;
import admin.payroll.entity.PmLeavePostingEntity;
import admin.payroll.entity.PmLeaveTypeEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.PmId;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePmLeaveAccountingModel;
import admin.payroll.models.SavePmLeavePostingModel;
import admin.payroll.models.SavePmLeaveTypeModel;
import admin.payroll.repo.EmpMastRepo;
import admin.payroll.repo.PmLeaveAccountingRepo;
import admin.payroll.repo.PmLeavePostingRepo;
import admin.payroll.repo.PmLeaveTypeRepo;
import admin.payroll.service.LeaveManagementService;
import admin.payroll.utils.StringConstants;

@Service
public class LeaveManagementServiceImpl implements LeaveManagementService {

	private static final Logger log = LoggerFactory.getLogger(LeaveManagementServiceImpl.class);

	@Autowired
	private PmLeaveAccountingRepo pmLeaveAccountingRepo;

	@Autowired
	private PmLeaveTypeRepo pmLeaveTypeRepo;

	@Autowired
	private PmLeavePostingRepo pmLeavePostingRepo;

	@Autowired
	EmpMastRepo empMastRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseDTO savePmLeaveAccounting(@Valid SavePmLeaveAccountingModel payload) {
		try {
			PmLeaveAccountingEntity pmUser = modelMapper.map(payload, PmLeaveAccountingEntity.class);
			pmLeaveAccountingRepo.save(pmUser);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("savePmUser {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getAllPmLeaveManagement() {
		try {
			List<PmLeaveAccountingEntity> list = pmLeaveAccountingRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("getPmUserData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getALlLeaveAccEmpAndLeave() {
		try {
			List<PmLeaveAccountingEntity> list = pmLeaveAccountingRepo.findAll();
			List<SavePmLeaveAccountingModel> leaveAccList = new ArrayList<SavePmLeaveAccountingModel>();
			for (PmLeaveAccountingEntity emp : list) {
				SavePmLeaveAccountingModel pmUser = modelMapper.map(emp, SavePmLeaveAccountingModel.class);
				EmpMastEntity empEntity = empMastRepo.findByEmpId(emp.getEmpNo());
				pmUser.setEmpName(empEntity.getName());
				List<PmLeaveTypeEntity> leaveEntity = pmLeaveTypeRepo.findPmLeaveTypeById(emp.getLeaveCode());
				pmUser.setLeaveDescr(leaveEntity.get(0).getLeaveDesc());
				leaveAccList.add(pmUser);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					leaveAccList);
		} catch (Exception e) {
			log.error("getPmUserData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getLeaveAccountingById(@Valid PmId payload) {
		try {
			List<PmLeaveAccountingEntity> list = pmLeaveAccountingRepo.findPmLeaveAccountingById(payload.getEmpNo());
			List<SavePmLeaveAccountingModel> leaveAccList = new ArrayList<SavePmLeaveAccountingModel>();
			for (PmLeaveAccountingEntity emp : list) {
				SavePmLeaveAccountingModel pmUser = modelMapper.map(emp, SavePmLeaveAccountingModel.class);
				EmpMastEntity empEntity = empMastRepo.findByEmpId(emp.getEmpNo());
				pmUser.setEmpName(empEntity.getName());
				List<PmLeaveTypeEntity> leaveEntity = pmLeaveTypeRepo.findPmLeaveTypeById(emp.getLeaveCode());
				pmUser.setLeaveDescr(leaveEntity.get(0).getLeaveDesc());
				leaveAccList.add(pmUser);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					leaveAccList);
		} catch (Exception e) {
			log.error("getting pmUserDataById {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getLeaveAccountingByEmpandleave(@Valid PmId payload) {
		try {
			List<PmLeaveAccountingEntity> list = pmLeaveAccountingRepo
					.findPmLeaveAccountingByEmpAndLeave(payload.getEmpNo(), payload.getLeaveCode());

			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("getting pmUserDataById {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO deletingLeaveAccountingById(@Valid PmId payload) {
		try {
			pmLeaveAccountingRepo.deleteById(payload.getEmpNo());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("delete PmUserById {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO savePmLeavePosting(@Valid SavePmLeavePostingModel payload) {
		try {
			PmLeavePostingEntity pmUser = modelMapper.map(payload, PmLeavePostingEntity.class);
			pmLeavePostingRepo.save(pmUser);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("savePmUser {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getAllPmLeavePosting() {
		try {
			List<PmLeavePostingEntity> list = pmLeavePostingRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("getPmUserData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getALlLeavePostEmpAndLeave() {
		List<PmLeavePostingEntity> list = pmLeavePostingRepo.findAll();
		List<SavePmLeavePostingModel> leavePostList = new ArrayList<SavePmLeavePostingModel>();

		for (PmLeavePostingEntity emp : list) {
			SavePmLeavePostingModel pmUser = modelMapper.map(emp, SavePmLeavePostingModel.class);
			EmpMastEntity empEntity = empMastRepo.findByEmpId(emp.getEmpNo());
			pmUser.setEmpName(empEntity.getName());
			List<PmLeaveTypeEntity> leaveEntity = pmLeaveTypeRepo.findPmLeaveTypeById(emp.getLeaveCode());
			pmUser.setLeaveDescr(leaveEntity.get(0).getLeaveDesc());
			leavePostList.add(pmUser);
		}
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
				leavePostList);

	}

	@Override
	public ResponseDTO getLeavePostingById(@Valid PmId payload) {
		try {

			List<PmLeavePostingEntity> list = pmLeavePostingRepo.findPmLeavePostingById(payload.getEmpNo());
			List<SavePmLeavePostingModel> leavePostList = new ArrayList<SavePmLeavePostingModel>();
			for (PmLeavePostingEntity emp : list) {
				SavePmLeavePostingModel pmUser = modelMapper.map(emp, SavePmLeavePostingModel.class);
				EmpMastEntity empEntity = empMastRepo.findByEmpId(emp.getEmpNo());
				pmUser.setEmpName(empEntity.getName());
				List<PmLeaveTypeEntity> leaveEntity = pmLeaveTypeRepo.findPmLeaveTypeById(emp.getLeaveCode());
				pmUser.setLeaveDescr(leaveEntity.get(0).getLeaveDesc());
				leavePostList.add(pmUser);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					leavePostList);
		} catch (Exception e) {
			log.error("getting pmUserDataById {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO deletingLeavePostingById(@Valid PmId payload) {
		try {
			pmLeavePostingRepo.deleteById(payload.getEmpNo());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("delete PmUserById {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO savePmLeaveType(@Valid SavePmLeaveTypeModel payload) {
		try {
			PmLeaveTypeEntity pmUser = Convertor.convertToPmLeaveTypeEntity(payload);
			pmLeaveTypeRepo.save(pmUser);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("savePmUser {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getAllPmLeaveType() {
		try {
			List<PmLeaveTypeEntity> list = pmLeaveTypeRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("getPmUserData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getLeaveTypeById(@Valid PmId payload) {
		try {
			List<PmLeaveTypeEntity> list = pmLeaveTypeRepo.findPmLeaveTypeById(payload.getLeaveCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("getting pmUserDataById {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO deletingLeaveTypeById(@Valid PmId payload) {
		try {
			pmLeaveTypeRepo.deleteById(payload.getLeaveCode());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("delete PmUserById {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getLeaveTypeDatas(@Valid SavePmLeaveTypeModel payload) {
		try {
			List<PmLeaveTypeEntity> empmast = pmLeaveTypeRepo.getLeaveTypeDatas(payload.getLeaveCode(),
					payload.getLeaveDesc());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					empmast);
		} catch (Exception e) {
			log.error("getEmployeeDatas {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
}
