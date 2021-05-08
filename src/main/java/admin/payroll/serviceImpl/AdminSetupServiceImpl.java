package admin.payroll.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import admin.payroll.config.Convertor;
import admin.payroll.entity.PmRolesEntity;
import admin.payroll.entity.PmUnitSetupEntity;
import admin.payroll.entity.PmUserRightsEntity;
import admin.payroll.entity.PmUsersEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.PmId;
import admin.payroll.models.PmLoginId;
import admin.payroll.models.PmUserName;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePmRolesModel;
import admin.payroll.models.SavePmUnitSetupModel;
import admin.payroll.models.SavePmUserRightModel;
import admin.payroll.models.SavePmUsersModel;
import admin.payroll.models.UnitIDModel;
import admin.payroll.repo.PmRoleRepo;
import admin.payroll.repo.PmUnitSetupRepo;
import admin.payroll.repo.PmUserRepo;
import admin.payroll.repo.PmUserRightRepo;
import admin.payroll.service.AdminSetupService;
import admin.payroll.utils.StringConstants;

@Service
public class AdminSetupServiceImpl implements AdminSetupService {

	private static final Logger log = LoggerFactory.getLogger(AdminSetupServiceImpl.class);

	@Autowired
	private PmUnitSetupRepo pmUnitSetupRepo;

	@Autowired
	private PmUserRepo pmUserRepo;

	@Autowired
	private PmRoleRepo pmRoleRepo;

	@Autowired
	private PmUserRightRepo pmUserRightRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseDTO saveUnitSetup(@Valid SavePmUnitSetupModel payload) {
		try {
			PmUnitSetupEntity pmUnitSetup = Convertor.convertToPmUnitSetup(payload);
			pmUnitSetupRepo.save(pmUnitSetup);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveUnitSetup {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getUnitSetupByUniId(@Valid UnitIDModel payload) {
		try {
			List<PmUnitSetupEntity> data = pmUnitSetupRepo.findAllById(payload.getUnitId());
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getUnitSetupByUniId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	// now create api implementation for pmUser

	@Override
	public ResponseDTO savePmUser(@Valid SavePmUsersModel payload) {

		try {
			PmUsersEntity pmUser = Convertor.convertToPmUserEntity(payload);
			
			pmUser.setPassword(this.passwordEncoder.encode(pmUser.getPassword()));
			pmUserRepo.save(pmUser);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("savePmUser {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getAllPmUser() {

		try {

			List<PmUsersEntity> list = pmUserRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);

		} catch (Exception e) {

			log.error("getPmUserData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	public List<PmUsersEntity> listAll() {
		return pmUserRepo.findAll(Sort.by("emailId").ascending());
	}

	@Override
	public ResponseDTO getPmUserById(@Valid PmId payload) {
		try {

			List<PmUsersEntity> list = pmUserRepo.findPmUserById(payload.getId());

			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);

		} catch (Exception e) {

			log.error("getting pmUserDataById {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO deletingPmUserById(@Valid PmId payload) {

		try {
			pmUserRepo.deleteById(payload.getId());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("delete PmUserById {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getPmUserByName(@Valid PmUserName payload) {

		try {

			List<PmUsersEntity> list = pmUserRepo.findPmUserByName(payload.getUserName());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {

			log.error("getting pmUserByName by {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO checkPmUserExit(@Valid PmId payload) {
		try {

			boolean Exit = false;
			List<PmUsersEntity> list = null;

			list = pmUserRepo.findPmUserById(payload.getId());

			if (!list.isEmpty()) {

				Exit = true;
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED, Exit);
			} else {
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.NO_CONTENT, Exit);
			}
		} catch (Exception e) {

		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	// now create the implementation for PmRoles

	@Override
	public ResponseDTO savePmRoles(@Valid SavePmRolesModel payload) {

		try {
			PmRolesEntity pmRoles = Convertor.convertToPmRolesEntity(payload);
			pmRoleRepo.save(pmRoles);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("error in saving pmRoles {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getAllPmRoles() {
		try {
			List<PmRolesEntity> list = pmRoleRepo.findAll();
			return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error fetching getAllPmRoles {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getPmRoleByID(@Valid PmId payload) {
		try {
			List<PmRolesEntity> list = pmRoleRepo.getPmRolesById(payload.getId());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {

			log.error("error while fetching gerPmRoleById", e);

		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO deletePmRoleById(@Valid PmId payload) {
		try {

			pmRoleRepo.deleteById(payload.getId());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("error while deleting deleteRolebyName");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO searchPmRoleById(@Valid PmId payload) {
		try {
			List<PmRolesEntity> list = pmRoleRepo.searchPmRoleById(payload.getId());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while fetching data searchPmRoleById");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO searchPmRoleByDescription(@Valid PmUserName payload) {
		try {
			List<PmRolesEntity> list = null;

			list = pmRoleRepo.searchPmRoleByDescription(payload.getUserName());
			if (list.isEmpty()) {
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
			}

			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);

		} catch (Exception e) {
			log.error("error while searchPmRoleByDescription {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	// Create the implementation for PmUserRightEntity

	@Override
	public ResponseDTO savePmUserRight(@Valid SavePmUserRightModel payload) {
		try {
			this.pmUserRightRepo.deletePmUserRightByuserId(payload.getUserId());
			List<PmUserRightsEntity> userRights = new ArrayList<>();
			if (payload.getRolesData() != null && payload.getRolesData().size() > 0) {
				payload.getRolesData().forEach(row -> {
					PmUserRightsEntity userRightEntity = this.modelMapper.map(row, PmUserRightsEntity.class);
					userRightEntity.setUserId(payload.getUserId());
					userRightEntity.setLogUser(payload.getLogUser());
					userRights.add(userRightEntity);
				});
			}
			this.pmUserRightRepo.saveAll(userRights);

//			// Integer id = payload.getId();
//			String userId = payload.getUserId();
//			String logUser = payload.getLogUser();
//			Date logDate = payload.getLogDate();
//			String logTime = payload.getLogTime();
//
//			List roleId = payload.getRoleId();
//			List isInsert = payload.getIsInsert();
//			List isDelete = payload.getIsDelete();
//			List isUpdate = payload.getIsUpdate();
//			List isView = payload.getIsView();
//
//			PmUserRightsEntity pmUserRight = new PmUserRightsEntity();
//			for (int i = 0; i <= payload.getRoleId().size() - 1; i++) {
//				pmUserRight.setUserId(userId);
//				pmUserRight.setLogUser(logUser);
//				pmUserRight.setLogDate(logDate);
//				pmUserRight.setLogTime(logTime);
//				pmUserRight.setRoleId(roleId.get(i).toString());
//
//				if ((isInsert.get(i).toString()).equals("Y")) {
//					pmUserRight.setIsInsert("Y");
//
//				} else {
//					pmUserRight.setIsInsert("N");
//
//				}
//
//				if ((isDelete.get(i).toString()).equals("Y")) {
//					pmUserRight.setIsDelete("Y");
//
//				} else {
//					pmUserRight.setIsDelete("N");
//
//				}
//
//				if ((isUpdate.get(i).toString()).equals("Y")) {
//					pmUserRight.setIsUpdate("Y");
//
//				} else {
//					pmUserRight.setIsUpdate("N");
//
//				}
//				if ((isView.get(i).toString()).equals("Y")) {
//					pmUserRight.setIsView("Y");
//
//				} else {
//					pmUserRight.setIsView("N");
//
//				}
//				pmUserRightRepo.save(pmUserRight);
//
//			}
//
//			// PmUserRightsEntity pmUserRight =
//			// Convertor.convertToPmUserRightEntity(payload);

			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("save PmUserRight {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getAllPmUserRight() {

		try {
			List<PmUserRightsEntity> list = pmUserRightRepo.findAll();
			if (list.isEmpty()) {
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("getting allPmUserRight {}", e);

		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getPmUserRightById(@Valid PmId payload) {
		try {
			List<Map<String, Object>> list = new ArrayList<>();
			List<PmUserRightsEntity> datas = pmUserRightRepo.getPmUserRightById(payload.getUserId());
//			for (PmUserRightsEntity data : datas) {
//				Map<String, Object> map = new HashMap<>();
//
//				map.put("userId", data.getUserId());
//				map.put("roleId", data.getRoleId());
////				map.put("isInsert", data.getIsInsert());
////				map.put("isDelete", data.getIsDelete());
////				map.put("isUpdate", data.getIsUpdate());
////				map.put("isView", data.getIsView());
//				map.put("logUser", data.getLogUser());
//				map.put("logDate", data.getLogDate());
//				map.put("logtime", data.getLogTime());
//				list.add(map);
//			}
//
//			if (list.isEmpty()) {
//				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
//			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), datas);

		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO deletePmUserRightById(@Valid PmId payload) {
		try {

			pmUserRightRepo.deletePmUserRightByuserId(payload.getUserId());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			System.out.println(e);
			log.error("error while deleting pmUserRightById {}", e);

		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO searchPmUserRightById(@Valid PmId payload) {
		try {
			List<PmUserRightsEntity> list = pmUserRightRepo.getPmUserRightById(payload.getUserId());
			if (list.isEmpty()) {

				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.NOT_FOUND, null);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while searchPmUserRightById");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO searchPmUserRightByUserIdOrRoleId(@Valid PmId payload) {
		try {
			List<PmUserRightsEntity> list = pmUserRightRepo.searchPmUserRightByUserIdOrRoleId(payload.getId());
			if (list.isEmpty()) {
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.NOT_FOUND.NOT_FOUND,
						null);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);

		} catch (Exception e) {
			log.debug("error searchPmUserRightByUserIdOrRoleId in impl");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO getPmUserByLoginId(@Valid PmLoginId loginId) {
		try {
			Optional<PmUsersEntity> userEntity = this.pmUserRepo.findByUserName(loginId.getLoginId());
			if (userEntity.isPresent()) {
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						userEntity);
			} else {
				return new ResponseDTO("User Not Found", APISTATUS.FAIL, HttpStatus.NOT_FOUND.value(), null);
			}
		} catch (Exception e) {
			log.debug("getPmUserByLoginId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

}
