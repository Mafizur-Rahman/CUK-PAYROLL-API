package admin.payroll.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import admin.payroll.config.Convertor;
import admin.payroll.entity.PmBankEntity;
import admin.payroll.entity.PmCodeTypeEntity;
import admin.payroll.entity.PmDeptEntity;
import admin.payroll.entity.PmDesigEntity;
import admin.payroll.entity.PmEedEntity;
import admin.payroll.entity.PmGecEntity;
import admin.payroll.entity.PmPayMatrixEntity;
import admin.payroll.entity.PmRateEntity;
import admin.payroll.entity.SeventhMatrixEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.CodeAndDescModel;
import admin.payroll.models.CodeListAndCodeTypeModel;
import admin.payroll.models.CodeModel;
import admin.payroll.models.DesignationCodeModel;
import admin.payroll.models.EditSeventhMatrixModel;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.GetBankDetails;
import admin.payroll.models.GetPayMatrixModel;
import admin.payroll.models.GetSeventhMatrixModel;
import admin.payroll.models.RateMasterModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SaveBankMasterModel;
import admin.payroll.models.SaveCodeTypeModel;
import admin.payroll.models.SaveDepartmentModel;
import admin.payroll.models.SaveDesignationMasterModel;
import admin.payroll.models.SaveEDCodeModel;
import admin.payroll.models.SaveGeneralCodeModel;
import admin.payroll.models.SavePayMatrixModel;
import admin.payroll.models.SaveRateMasterModel;
import admin.payroll.models.SaveSeventhMatrixModel;
import admin.payroll.repo.PmBankRepo;
import admin.payroll.repo.PmCodeTypeRepo;
import admin.payroll.repo.PmDeptEntityRepo;
import admin.payroll.repo.PmDesigRepo;
import admin.payroll.repo.PmEedRepo;
import admin.payroll.repo.PmGecRepo;
import admin.payroll.repo.PmPayMatrixRepo;
import admin.payroll.repo.PmRateRepo;
import admin.payroll.repo.SeventhMatrixRepo;
import admin.payroll.service.CodeMasterService;
import admin.payroll.service.CommonService;
import admin.payroll.utils.StringConstants;

@Service
public class CodeMasterServiceImpl implements CodeMasterService {

	private static final Logger log = LoggerFactory.getLogger(CodeMasterServiceImpl.class);

	@Autowired
	PmEedRepo pmEedRepo;

	@Autowired
	PmGecRepo pmGecRepo;

	@Autowired
	PmBankRepo pmBankRepo;

	@Autowired
	PmRateRepo pmRateRepo;

	@Autowired
	PmDesigRepo pmDesigRepo;

	@Autowired
	CommonService commonService;

	@Autowired
	PmPayMatrixRepo pmPayMatrixRepo;

	@Autowired
	PmCodeTypeRepo pmCodeTypeRepo;

	@Autowired
	PmDeptEntityRepo pmDeptEntityRepo;

	@Autowired
	private SeventhMatrixRepo seventhMatrixRepo;

	@Override
	public ResponseDTO saveEDCodeMaster(@Valid SaveEDCodeModel payload) {
		try {
			PmEedEntity pmEedMasterData = Convertor.convertToPMEed(payload);
			pmEedRepo.save(pmEedMasterData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveEDCodeMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveGeneralCodes(@Valid SaveGeneralCodeModel payload) {
		try {

			String codeType = payload.getCodeType();
			Integer id = payload.getId();
			List code = payload.getCode();
			List shortDesc = payload.getShortDesc();
			List longDesc = payload.getLongDesc();
			String logIp = payload.getLogIp();
			String logUser = payload.getLogUser();
			String isActive = payload.getIsActive();

			for (int i = 0; i <= code.size() - 1; i++) {
				PmGecEntity pmGecData = new PmGecEntity();
				pmGecData.setCode(code.get(i).toString());
				pmGecData.setCodeType(codeType);
				pmGecData.setShortDesc(shortDesc.get(i).toString());
				pmGecData.setLongDesc(longDesc.get(i).toString());
				pmGecData.setId(id);
				pmGecData.setIsActive(isActive);

				pmGecData.setLogUser(logUser);
				pmGecData.setLogIp(logIp);
				pmGecRepo.save(pmGecData);
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveGeneralCodes {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO saveBankDetails(@Valid SaveBankMasterModel payload) {
		try {
			PmBankEntity pmPmBankData = Convertor.convertToPMBank(payload);
			pmBankRepo.save(pmPmBankData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveBankDetails {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO savePayMatrix(@Valid SavePayMatrixModel payload) {
		try {
			PmPayMatrixEntity payMatrixData = Convertor.convertToPayMatrix(payload);
			pmPayMatrixRepo.save(payMatrixData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("savePayMatrix {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO saveRateMaster(@Valid SaveRateMasterModel payload) {
		try {
			PmRateEntity patData = Convertor.convertToRateMaster(payload);
			pmRateRepo.save(patData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveRateMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveDesignationMaster(@Valid SaveDesignationMasterModel payload) {
		try {
			PmDesigEntity pmgnData = Convertor.convertToDesigMaster(payload);
			pmDesigRepo.save(pmgnData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveDesignationMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getDescriptionBasedOnEDCode(@Valid CodeAndDescModel payload) {
		try {
			List<PmEedEntity> data = pmEedRepo.findAllByDesigShortDescAndcode(payload.getDesc(), payload.getCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getDescriptionBasedOnEDCode {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getDescriptionBasedOnDesignationCode(@Valid CodeAndDescModel payload) {
		try {
			List<PmGecEntity> data = pmGecRepo.getDataBasedOnCodeAndDescription(payload.getCode(), payload.getDesc(),
					payload.getCodeType());

			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);

		} catch (Exception e) {
			log.error("getDescriptionBasedOnDesignationCode {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getCodeType() {
		try {
			List<PmCodeTypeEntity> data = pmCodeTypeRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getCodeType {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getBankDetails(String bankName, String ifscCode) {
		try {
			List<PmBankEntity> data = pmBankRepo.findByBankNameAndIfscCode(bankName, ifscCode);
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getBankDetails {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getPayMatrix(@Valid GetPayMatrixModel payload) {
		try {
			PmPayMatrixEntity data = pmPayMatrixRepo.getMatrixData(payload.getLevelNo(), payload.getCellNo());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getPayMatrix {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO designationCode() {
		try {
			List<String> code = pmDesigRepo.getDesignatedCodeFromPmDesig();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), code);
		} catch (Exception e) {
			log.error("designationCode {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getRateMaster(@Valid CodeAndDescModel payload) {

		try {
			List<PmRateEntity> data = pmRateRepo.findAllById(payload.getCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getRateMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO findAllData() {
		try {
			Sort sort = Sort.by("shortDesc");
			List<PmEedEntity> data = pmEedRepo.findAllActive(sort);
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("findAllData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO deleteCodeMasterData(@Valid CodeModel payload) {
		try {
			pmEedRepo.deleteById(payload.getCode());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteCodeMasterData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO checkEdCodeExist(@Valid CodeModel payload) {
		try {
			boolean data = false;
			Optional<PmEedEntity> datas = pmEedRepo.findById(payload.getCode());
			if (datas.isPresent()) {
				data = true;
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("checkEdCodeExist {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getDesignationCode(@Valid DesignationCodeModel payload) {
		try {
			List<PmDesigEntity> data = pmDesigRepo.findByDesigCodeAndDesigShortDesc(payload.getCode(),
					payload.getDesc());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getDesignationCode {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO findAllDataForGeneralCode() {
		try {
			Sort sort = Sort.by("shortDesc");
			List<PmGecEntity> datas = pmGecRepo.findAllActive(sort);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), datas);
		} catch (Exception e) {
			log.error("findAllDataForGeneralCode {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteGeneralCodeData(@Valid CodeModel payload) {
		try {
			pmGecRepo.deleteGeneralCode(payload.getId(), payload.getCode(), payload.getCodeType());
			// pmGecRepo.deleteByIdAndCodeTypeAndCode(payload.getId(),
			// payload.getCodeType(), payload.getCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
			log.error("deleteGeneralCodeData {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO checkGeneralCodeExist(@Valid CodeListAndCodeTypeModel payload) {
		try {
			boolean data = false;

			List<Map<String, Object>> list = new ArrayList<>();
			List code = payload.getCode();
			for (int i = 0; i <= code.size() - 1; i++) {
				Map<String, Object> map = new HashMap<>();
				String code1 = code.get(i).toString();
				List<PmGecEntity> datas = pmGecRepo.findByCodeTypeandCode(code1, payload.getCodeType());

				if (!datas.isEmpty()) {
					data = true;
				}
				map.put(code.get(i).toString(), data);
				list.add(map);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);

		} catch (Exception e) {
			log.error("checkGeneralCodeExist {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAllDesignationData() {
		try {
			Sort sort = Sort.by("desigShortDesc");
			List<PmDesigEntity> data = pmDesigRepo.findAllActive(sort);
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getAllDesignationData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO deleteDesignationData(@Valid CodeModel payload) {
		try {
			pmDesigRepo.deleteById(payload.getCode());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteDesignationData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO checkDesignationMaster(@Valid CodeModel payload) {
		try {
			boolean data = false;
			Optional<PmDesigEntity> datas = pmDesigRepo.findById(payload.getCode());
			if (datas.isPresent()) {
				data = true;
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);

		} catch (Exception e) {
			log.error("checkDesignationMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAllBankData() {
		try {
			List<PmBankEntity> data = pmBankRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getAllBankData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO deleteBankData(@Valid CodeModel payload) {
		try {
			pmBankRepo.deleteById(payload.getCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteBankData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO checkBankCodeExist(@Valid CodeModel payload) {
		try {
			boolean data = false;
			Optional<PmBankEntity> datas = pmBankRepo.findById(payload.getCode());
			if (datas.isPresent()) {
				data = true;
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("checkBankCodeExist {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getAllPayMatrixData() {
		try {
			List<PmPayMatrixEntity> data = pmPayMatrixRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getAllPayMatrixData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO deletePayMatrixData(@Valid CodeModel payload) {
		try {
			pmPayMatrixRepo.deleteById(payload.getCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deletePayMatrixData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getAllRateMasterData() {
		try {
			Sort sort = Sort.by("earningDeduction");
			List<PmRateEntity> data = pmRateRepo.findAllaActive(sort);
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getAllDesignationData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO checkRateMaster(@Valid CodeModel payload) {
		try {
			boolean data = false;
			Optional<PmRateEntity> datas = pmRateRepo.findById(payload.getCode());
			if (datas.isPresent()) {
				data = true;
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);

		} catch (Exception e) {
			log.error("checkRateMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteRateMasterData(@Valid CodeModel payload) {
		try {
			pmRateRepo.deleteById(payload.getCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteRateMasterData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getAllEDCodeAndDesc() {
		List<String> finalOutput = new ArrayList<>();
		try {
			List<PmEedEntity> data = pmEedRepo.findAll();
			for (PmEedEntity codeDesc : data) {
				String CodeAndDesc = codeDesc.getEarningDeduction() + ", " + codeDesc.getShortDesc();
				finalOutput.add(CodeAndDesc);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					finalOutput);

		} catch (Exception e) {
			log.error("getAllEDCodeAndDesc {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO saveCodeTypeData(SaveCodeTypeModel payload) {
		try {
			PmCodeTypeEntity pmCodeType = Convertor.convertToPmCodeType(payload);
			pmCodeTypeRepo.save(pmCodeType);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveEDCodeMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getCodeTypeData(@Valid CodeAndDescModel payload) {
		try {
			List<PmCodeTypeEntity> data = pmCodeTypeRepo.getCode(payload.getCode(), payload.getDesc());

			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);

		} catch (Exception e) {
			log.error("getCodeTypeData {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteCodeTypeData(@Valid CodeModel payload) {

		try {
			pmCodeTypeRepo.deleteById(payload.getCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteCodeMasterData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAllCodeTypeData() {
		try {
			List<PmCodeTypeEntity> data = pmCodeTypeRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("findAllData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getAllDepartmentMasterData() {
		try {
			List<PmDeptEntity> data = pmDeptEntityRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("findAllData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO saveDepartmentMasterData(SaveDepartmentModel payload) {
		try {
			PmDeptEntity pmCodeType = Convertor.convertToPmDept(payload);
			pmDeptEntityRepo.save(pmCodeType);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveEDCodeMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getDepartmentMasterData(@Valid CodeAndDescModel payload) {
		try {
			List<PmDeptEntity> data = pmDeptEntityRepo.getCode(payload.getCode(), payload.getDesc());

			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);

		} catch (Exception e) {
			log.error("getCodeTypeData {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteDepartmentMasterData(@Valid CodeModel payload) {

		try {
			pmDeptEntityRepo.deleteById(payload.getCode());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteCodeMasterData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getDescription(@Valid EmployeeID payload) {
		try {
			String description = pmEedRepo.getDescription(payload.getEmployeeId());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					description);
		} catch (Exception e) {
			log.error("getDesigBasedOnEmpId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getBankDetailsBasedOnBankcode(@Valid GetBankDetails payload) {
		try {
			List<PmBankEntity> data = pmBankRepo.getBankDetailsBasedOnBankcode(payload.getBankCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getDesigBasedOnEmpId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getGeneralCodesBasedOnId(@Valid CodeModel payload) {
		try {
			List<PmGecEntity> data = pmGecRepo.findAllById(payload.getId());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getDesigBasedOnEmpId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveRateMasterUpdated(@Valid RateMasterModel payload) {
		try {

			String edCode = payload.getEarningDeduction();
			List payCommList = payload.getPayCommission();
			List toDateList = payload.getToDate();
			List rateList = payload.getRate();
			List fromDateList = payload.getFromDate();
			List isActiveList = payload.getIsActive();

			String logIp = payload.getLogIp();
			String logUser = payload.getLogUser();

			System.out.println(isActiveList.size());

			for (int i = 0; i <= isActiveList.size() - 1; i++) {
				PmRateEntity patData = new PmRateEntity();
				String payComm = payCommList.get(i).toString();
				String toDate = null;
				LocalDate toDate1 = null;
				String isActive = isActiveList.get(i).toString();

				String rate = rateList.get(i).toString();
				String fromDate = fromDateList.get(i).toString();

				SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");

				if (!(toDateList.get(i) == null)) {
					toDate = toDateList.get(i).toString();
//					isActive = "Y";
//					toDate1 = (Date) formatter3.parse(toDate.toString());
					toDate1 = LocalDate.parse((String) toDateList.get(i));
				}

//				Date fromDate1 = (Date) formatter3.parse(fromDate.toString());

				LocalDate fromDate1 = LocalDate.parse((String) fromDateList.get(i));
				patData.setEarningDeduction(edCode);
				patData.setFromDate(fromDate1);
				patData.setIsActive(isActive);
				patData.setLogIp(logIp);
				patData.setLogUser(logUser);
				patData.setPayComission(Integer.parseInt(payComm));
				patData.setRate(Double.parseDouble(rate));
				patData.setToDate(toDate1);
				pmRateRepo.save(patData);
			}

			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveRateMaster {}", e);
		}
		// return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS,
		// HttpStatus.ACCEPTED.value(), null);
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveSeventhMatrix(@Valid SaveSeventhMatrixModel payload) {
		try {
			SeventhMatrixEntity seventhMatrixData = Convertor.convertToSeventhMtrix(payload);
			seventhMatrixRepo.save(seventhMatrixData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveEDCodeMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAllSeventhMatrixData() {
		try {
			List<SeventhMatrixEntity> data = seventhMatrixRepo.findAll();
			if (!data.isEmpty())
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
			else
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getAllSeventhMatrixData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getSeventhMatrix(@Valid GetSeventhMatrixModel payload) {
		try {
			List<SeventhMatrixEntity> data = seventhMatrixRepo.findAllByPaybandAndLevel(payload.getPayBand(),
					payload.getLevelPay(), payload.getScaleNo());
			if (!data.isEmpty())
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
			else
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getDesigBasedOnEmpId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO editSeventhMatrix(@Valid EditSeventhMatrixModel payload) {
		try {
			List<SeventhMatrixEntity> data = seventhMatrixRepo.editByPaybandAndLevel(payload.getPayBand(),
					payload.getGp(), payload.getPayStep());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("editSeventhMatrix {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteSeventhMatrix(@Valid EditSeventhMatrixModel payload) {
		try {
			seventhMatrixRepo.deleteSeventhMatrix(payload.getPayBand(), payload.getGp(), payload.getPayStep());
			// pmGecRepo.deleteByIdAndCodeTypeAndCode(payload.getId(),
			// payload.getCodeType(), payload.getCode());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
			log.error("deleteSeventhMatrix {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getAllBankName() {
		try {
			List<PmBankEntity> data = pmBankRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getAll Banke Name {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
}
