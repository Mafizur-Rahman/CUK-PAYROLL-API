package admin.payroll.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import admin.payroll.entity.PmGecEntity;
import admin.payroll.entity.PmUserRightsEntity;
import admin.payroll.models.CodeAndCodeTypeModel;
import admin.payroll.models.EedMode;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SelectBoxModel;

@Service
public interface CommonService {

	Object getSelectBox(String selectBoxName);

	@Transactional
	ResponseDTO getDataForSelectBox(String selectBoxName);

	ResponseDTO test(Map<String, Object> data);

	ResponseDTO test1(Map<String, Object> data);

	@Transactional
	List<PmGecEntity> getDataBasedOnCode(String code, String codeType);
	
	@Transactional
	ResponseDTO getEDbasedOnType(@Valid EedMode payload);

	@Transactional
	ResponseDTO getEmpCategoryByClass(@Valid SelectBoxModel payload);
	
	List<PmUserRightsEntity> getUserRightsByLoginId(String loginId);

	boolean checkUserExistance(String loginId);

	ResponseDTO getDashboardStatistics();
}
