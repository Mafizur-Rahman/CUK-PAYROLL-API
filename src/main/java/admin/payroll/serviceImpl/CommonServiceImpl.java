package admin.payroll.serviceImpl;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import admin.payroll.entity.PmEedEntity;
import admin.payroll.entity.PmGecEntity;
import admin.payroll.entity.PmUserRightsEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.CodeAndCodeTypeModel;
import admin.payroll.models.EedMode;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SelectBoxModel;
import admin.payroll.repo.EmpMastRepo;
import admin.payroll.repo.PmEedRepo;
import admin.payroll.repo.PmGecRepo;
import admin.payroll.repo.PmUserRepo;
import admin.payroll.repo.PmUserRightRepo;
import admin.payroll.repo.PmUserRightsRepo;
import admin.payroll.service.CommonService;
import admin.payroll.utils.StringConstants;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	PmGecRepo pmGecRepo;
	
	@Autowired
	PmEedRepo pmEedRepo;
	
	@Autowired
	private PmUserRightRepo pmUserRightRepo;

	@Autowired
	private PmUserRepo pmUserRepo;
	
	@Autowired
	private EmpMastRepo empMastRepo;
	
	@Override
	public Object getSelectBox(String selectBoxName) {
		try {
			JSONParser parser = new JSONParser();
			//Object obj = parser.parse(new FileReader("src/main/resources/dropdownInputBox.json"));
			Resource resource = new ClassPathResource("dropdownInputBox.json");
			InputStreamReader reader = new InputStreamReader(resource.getInputStream());
			Object obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;
			return jsonObject.get(selectBoxName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
		}

		return null;
	}

	@Override
	public ResponseDTO getDataForSelectBox(String selectBoxName) {
		try {
			String codeType = (String) getSelectBox(selectBoxName);
			List<PmGecEntity> selectBoxData = pmGecRepo.findByCodeType(codeType);
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					selectBoxData);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public ResponseDTO test(Map<String, Object> data) {
		Map<String, Object> map2 = new HashMap<>();
		List<Map<String, Object>> map = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("noOfItems", "y");
		map1.put("data", "Q");
		map1.put("values", "P");
		map1.put("String", "string");
		map.add(map1);
		map2.put("Success", "fail");
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), map2);
	}

	@Override
	public ResponseDTO test1(Map<String, Object> data) {

		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
	}

	@Override
	public List<PmGecEntity> getDataBasedOnCode(String code, String codeType) {
		try {
			List<PmGecEntity> data = pmGecRepo.getDataForBasedOnCodeAndCodeType(code, codeType);
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	@Override
	public ResponseDTO getEDbasedOnType(@Valid EedMode payload) {
		try {
			Sort sort = Sort.by("shortDesc");
			if(payload.getFormType().equals("payRates")) {	  
				List<PmEedEntity> data=pmEedRepo.getEedForPayRates(sort);
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
			}
			else if(payload.getFormType().equals("currentMonth")) {
				List<PmEedEntity> data=pmEedRepo.getEedForcurrentMonth(sort);
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
				}
			else if(payload.getFormType().equals("regularRecoveries")) {
				List<PmEedEntity> data=pmEedRepo.getEedForregularRecoveries(sort);
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
				}
			else if(payload.getFormType().equals("installmentRecoveries")) {
				List<PmEedEntity> data=pmEedRepo.getEedForinstallmentRecoveries(sort);
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
				}
		} catch (Exception e) {
			System.out.println();
		}

	 return null;
	}

	@Override
	public ResponseDTO getEmpCategoryByClass(@Valid SelectBoxModel payload) {
		try {
			String codeType=payload.getSelectBoxName();
			List<PmGecEntity> data = pmGecRepo.findByCodeType(codeType);
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<PmUserRightsEntity> getUserRightsByLoginId(String loginId) {
		return this.pmUserRightRepo.getPmUserRightById(loginId);
	}

	@Override
	public boolean checkUserExistance(String loginId) {
		return this.pmUserRepo.findByUserName(loginId).isPresent();
	}

	@Override
	public ResponseDTO getDashboardStatistics() {
		Map<String, Integer> map = new HashMap<>();
		map.put("drtcCount", this.empMastRepo.getDRTCCount());
		map.put("drdsCount", this.empMastRepo.getDRDSCount());
		map.put("others", this.empMastRepo.getOthersCount());
		map.put("scBCount", this.empMastRepo.getScBCount());
		map.put("scCCount", this.empMastRepo.getScCCount());
		map.put("scDCount", this.empMastRepo.getScDCount());
		map.put("scECount", this.empMastRepo.getScECount());
		map.put("scFCount", this.empMastRepo.getScFCount());
		map.put("scGCount", this.empMastRepo.getScGCount());
		map.put("scHCount", this.empMastRepo.getScHCount());
		map.put("total", this.empMastRepo.getAllCount());
		map.put("toCount", this.empMastRepo.getTOCount());
		return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), map);
	}
	


}
