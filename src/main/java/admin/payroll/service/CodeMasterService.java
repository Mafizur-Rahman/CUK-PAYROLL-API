package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

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

@Service
public interface CodeMasterService {

	@Transactional
	ResponseDTO saveEDCodeMaster(@Valid SaveEDCodeModel payload);

	@Transactional
	ResponseDTO saveGeneralCodes(@Valid SaveGeneralCodeModel payload);

	@Transactional
	ResponseDTO saveBankDetails(@Valid SaveBankMasterModel payload);

	@Transactional
	ResponseDTO savePayMatrix(@Valid SavePayMatrixModel payload);

	@Transactional
	ResponseDTO saveRateMaster(@Valid SaveRateMasterModel payload);

	@Transactional
	ResponseDTO saveDesignationMaster(@Valid SaveDesignationMasterModel payload);

	@Transactional
	ResponseDTO getDescriptionBasedOnEDCode(@Valid CodeAndDescModel payload);

	@Transactional
	ResponseDTO getDescriptionBasedOnDesignationCode(@Valid CodeAndDescModel payload);

	@Transactional
	ResponseDTO getCodeType();

	@Transactional
	ResponseDTO getBankDetails(String bankName, String ifscCode);

	@Transactional
	ResponseDTO getPayMatrix(@Valid GetPayMatrixModel payload);

	@Transactional
	ResponseDTO designationCode();

	@Transactional
	ResponseDTO getRateMaster(@Valid CodeAndDescModel payload);

	@Transactional
	ResponseDTO findAllData();

	@Transactional
	ResponseDTO deleteCodeMasterData(@Valid CodeModel payload);

	@Transactional
	ResponseDTO checkEdCodeExist(@Valid CodeModel payload);

	@Transactional
	ResponseDTO getDesignationCode(@Valid DesignationCodeModel payload);

	@Transactional
	ResponseDTO findAllDataForGeneralCode();

	@Transactional
	ResponseDTO deleteGeneralCodeData(@Valid CodeModel payload);

	@Transactional
	ResponseDTO getAllDesignationData();

	@Transactional
	ResponseDTO deleteDesignationData(@Valid CodeModel payload);

	@Transactional
	ResponseDTO checkDesignationMaster(@Valid CodeModel payload);

	@Transactional
	ResponseDTO getAllBankData();

	@Transactional
	ResponseDTO deleteBankData(@Valid CodeModel payload);

	@Transactional
	ResponseDTO checkBankCodeExist(@Valid CodeModel payload);

	@Transactional
	ResponseDTO getAllPayMatrixData();

	@Transactional
	ResponseDTO deletePayMatrixData(@Valid CodeModel payload);

	@Transactional
	ResponseDTO getAllRateMasterData();

	@Transactional
	ResponseDTO checkRateMaster(@Valid CodeModel payload);

	@Transactional
	ResponseDTO deleteRateMasterData(@Valid CodeModel payload);

	@Transactional
	ResponseDTO getAllEDCodeAndDesc();

	@Transactional
	ResponseDTO saveCodeTypeData(@Valid SaveCodeTypeModel payload);

	@Transactional
	ResponseDTO getCodeTypeData(@Valid CodeAndDescModel payload);

	@Transactional
	ResponseDTO deleteCodeTypeData(@Valid CodeModel payload);

	@Transactional
	ResponseDTO getAllCodeTypeData();

	@Transactional
	ResponseDTO getAllDepartmentMasterData();

	@Transactional
	ResponseDTO saveDepartmentMasterData(@Valid SaveDepartmentModel payload);

	@Transactional
	ResponseDTO getDepartmentMasterData(@Valid CodeAndDescModel payload);

	@Transactional
	ResponseDTO deleteDepartmentMasterData(@Valid CodeModel payload);

	@Transactional
	ResponseDTO getDescription(@Valid EmployeeID payload);

	@Transactional
	ResponseDTO getBankDetailsBasedOnBankcode(@Valid GetBankDetails payload);

	@Transactional
	ResponseDTO getGeneralCodesBasedOnId(@Valid CodeModel payload);

	@Transactional
	ResponseDTO checkGeneralCodeExist(@Valid CodeListAndCodeTypeModel payload);

	@Transactional
	ResponseDTO saveRateMasterUpdated(@Valid RateMasterModel payload);

	@Transactional
	ResponseDTO saveSeventhMatrix(@Valid SaveSeventhMatrixModel payload);

	@Transactional
	ResponseDTO getAllSeventhMatrixData();

	@Transactional
	ResponseDTO getSeventhMatrix(@Valid GetSeventhMatrixModel payload);

	@Transactional
	ResponseDTO editSeventhMatrix(@Valid EditSeventhMatrixModel payload);

	@Transactional
	ResponseDTO deleteSeventhMatrix(@Valid EditSeventhMatrixModel payload);

	@Transactional
	ResponseDTO getAllBankName();

}
