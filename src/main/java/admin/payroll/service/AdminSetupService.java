package admin.payroll.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import admin.payroll.entity.PmRolesEntity;
import admin.payroll.entity.PmUsersEntity;
import admin.payroll.models.PmId;
import admin.payroll.models.PmLoginId;
import admin.payroll.models.PmUserName;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePmRolesModel;
import admin.payroll.models.SavePmUnitSetupModel;
import admin.payroll.models.SavePmUserRightModel;
import admin.payroll.models.SavePmUsersModel;
import admin.payroll.models.UnitIDModel;

@Service
public interface AdminSetupService {

	@Transactional
	ResponseDTO saveUnitSetup(@Valid SavePmUnitSetupModel payload);

	@Transactional
	ResponseDTO getUnitSetupByUniId(@Valid UnitIDModel payload);
	
	@Transactional
	ResponseDTO savePmUser(@Valid SavePmUsersModel payload);
	
	@Transactional
	ResponseDTO getAllPmUser();
	
	@Transactional
	ResponseDTO getPmUserById(@Valid PmId payload);
	
	@Transactional
	ResponseDTO deletingPmUserById(@Valid PmId payload);
	
	@Transactional
	ResponseDTO getPmUserByName(@Valid PmUserName payload);
	
	@Transactional
	ResponseDTO checkPmUserExit(@Valid PmId payload);
	
	//now create a declaration for PmRoles
	
	@Transactional
	ResponseDTO savePmRoles(@Valid SavePmRolesModel payload);
	
	@Transactional
	ResponseDTO getAllPmRoles();
	
	@Transactional
	ResponseDTO getPmRoleByID(@Valid PmId payload);
	
	@Transactional
	ResponseDTO deletePmRoleById(@Valid PmId payload);
	
	@Transactional
	ResponseDTO searchPmRoleById(@Valid PmId payload);
	
	@Transactional
	ResponseDTO searchPmRoleByDescription(@Valid PmUserName payload);
	
	//create the declaration for PmUserRight
	
	@Transactional
	ResponseDTO savePmUserRight(@Valid SavePmUserRightModel payload);
	
	@Transactional
	ResponseDTO getAllPmUserRight();
	
	@Transactional
	ResponseDTO getPmUserRightById(@Valid PmId payload);
	@Transactional
	ResponseDTO deletePmUserRightById(@Valid PmId payload);
	
	@Transactional
	ResponseDTO searchPmUserRightById(@Valid PmId payload);
	
	@Transactional
	ResponseDTO searchPmUserRightByUserIdOrRoleId(@Valid PmId payload);

	@Transactional
	ResponseDTO getPmUserByLoginId(@Valid PmLoginId loginId);
	@Transactional
	List<PmUsersEntity> listAll();


}
