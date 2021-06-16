package admin.payroll.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.exceptions.InvalidJsonException;
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
import admin.payroll.service.CodeMasterService;
import admin.payroll.utils.StringConstants;

@RestController
@RequestMapping(value = "/codeMaster")
public class CodeMasterController {

	private static final Logger log = LoggerFactory.getLogger(CodeMasterController.class);

	@Autowired
	CodeMasterService codeMasterService;

//tested
	@GetMapping("/getAllCodeMasterData")
	public ResponseDTO gettingAllData() {
		log.debug("getting getAllCodeMasterData");
		return codeMasterService.findAllData();
	}

	@GetMapping("/getAllEDCodeAndDesc")
	public ResponseDTO getAllEDCodeAndDesc() {
		log.debug("getting getAllEDCodeAndDesc");
		return codeMasterService.getAllEDCodeAndDesc();
	}

//test
	@PostMapping("/getEdCodeMaster")
	public ResponseDTO EDCodeMaster(@RequestBody @Valid CodeAndDescModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getEdCodeMaster data");
			return codeMasterService.getDescriptionBasedOnEDCode(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// tested
	@PostMapping("/saveEDCode")
	public ResponseDTO saveEDCodeMaster(@RequestBody @Valid SaveEDCodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveEDCode");
			return codeMasterService.saveEDCodeMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

//tested
	@PostMapping("/deleteCodeMasterData")
	public ResponseDTO deleteCodeMasterData(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deleteCodeMasterData");
			return codeMasterService.deleteCodeMasterData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

//tested
	@PostMapping("/checkEdCodeExist")
	public ResponseDTO checkEdCodeExist(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("checkEdCodeExist");
			return codeMasterService.checkEdCodeExist(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// tested
	@GetMapping("/getAllGeneralCodeData")
	public ResponseDTO getAllGeneralCodeData() {
		log.debug("getAllGeneralCodeData");
		return codeMasterService.findAllDataForGeneralCode();
	}

	// tested
	@PostMapping("/getGeneralCodesData")
	public ResponseDTO getGeneralCodesData(@RequestBody @Valid CodeAndDescModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getGeneralCodesData");
			return codeMasterService.getDescriptionBasedOnDesignationCode(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getGeneralCodesBasedOnId")
	public ResponseDTO getGeneralCodesBasedOnId(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getGeneralCodesData");
			return codeMasterService.getGeneralCodesBasedOnId(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// tested
	@PostMapping("/deleteGeneralCodeData")
	public ResponseDTO deleteGeneralCodeData(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deleteGeneralCodeData");
			return codeMasterService.deleteGeneralCodeData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// table Pm_codeType (not added)
	// tested
	@GetMapping("/getCodeType")
	public ResponseDTO getCodeType() {
		log.debug("getCodeType");
		return codeMasterService.getCodeType();

	}

	// tested
	@PostMapping("/checkGeneralCodeExist")
	public ResponseDTO checkGeneralCodeExist(@RequestBody @Valid CodeListAndCodeTypeModel payload,
			BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("checkGeneralCodeExist");
			return codeMasterService.checkGeneralCodeExist(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

///isko krna h
	// tested
	@PostMapping("/saveGeneralCodes")
	public ResponseDTO saveGeneralCodes(@RequestBody @Valid SaveGeneralCodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveGeneralCodes");
			return codeMasterService.saveGeneralCodes(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// tested
	@GetMapping("/getAllDesignationData")
	public ResponseDTO getAllDesignationData() {
		log.debug("getAllDesignationData");
		return codeMasterService.getAllDesignationData();

	}

	// Created By Mafi
	// tested
	@PostMapping("/getDesignationCodeData")
	public ResponseDTO getDesignationCode(@RequestBody @Valid DesignationCodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getDesignationCodeData");
			return codeMasterService.getDesignationCode(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// tested
	@PostMapping("/checkDesignationMaster")
	public ResponseDTO checkDesignationMaster(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("checkDesignationMaster");
			return codeMasterService.checkDesignationMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// tested
	@PostMapping("/saveDesignationMaster")
	public ResponseDTO saveDesignationMaster(@RequestBody @Valid SaveDesignationMasterModel payload,
			BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveDesignationMaster");
			return codeMasterService.saveDesignationMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// tested
	@PostMapping("/deleteDesignationData")
	public ResponseDTO deleteDesignationData(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deleteDesignationData");
			return codeMasterService.deleteDesignationData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

//tested
	@GetMapping("/getAllBankData")
	public ResponseDTO getAllBankData() {
		log.debug("getAllBankData");
		return codeMasterService.getAllBankData();
	}

//tested
	@PostMapping("/saveBankDetails")
	public ResponseDTO saveBankDetails(@RequestBody @Valid SaveBankMasterModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveBankDetails");
			return codeMasterService.saveBankDetails(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

//tested
	@PostMapping("/getBankDetails")
	public ResponseDTO generalCodes(@RequestBody @Valid GetBankDetails payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getBankDetails");
			return codeMasterService.getBankDetails(payload.getBankName(), payload.getIfscCode());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getBankDetailsBasedOnBankcode")
	public ResponseDTO getBankDetailsBasedOnBankcode(@RequestBody @Valid GetBankDetails payload,
			BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getBankDetails");
			return codeMasterService.getBankDetailsBasedOnBankcode(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

//tested
	@PostMapping("/deleteBankData")
	public ResponseDTO deleteBankData(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deleteBankData");
			return codeMasterService.deleteBankData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// GET BANK NAME BY BANKCODE-17-05-2021
	@GetMapping("/getBankName")
	public ResponseDTO getAllEmployeeNameAndId() {
		log.debug("getting AllEmployeeNameAndId");
		return codeMasterService.getAllBankName();
	}

//tested
	@PostMapping("/checkBankCodeExist")
	public ResponseDTO checkBankCodeExist(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("checkBankCodeExist");
			return codeMasterService.checkBankCodeExist(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	// tested
	@GetMapping("/getAllPayMatrixData")
	public ResponseDTO getAllPayMatrixData() {
		log.debug("getAllPayMatrixData");
		return codeMasterService.getAllPayMatrixData();
	}

	// tested
	@PostMapping("/getPayMatrix")
	public ResponseDTO payMatrix(@RequestBody @Valid GetPayMatrixModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getPayMatrix");
			return codeMasterService.getPayMatrix(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

//tested
	@PostMapping("/savePayMatrix")
	public ResponseDTO savePayMatrix(@RequestBody @Valid SavePayMatrixModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("savePayMatrix");
			return codeMasterService.savePayMatrix(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	// tested
	@PostMapping("/deletePayMatrixData")
	public ResponseDTO deletePayMatrixData(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deletePayMatrixData");
			return codeMasterService.deletePayMatrixData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

//tested
	@PostMapping("/saveRateMaster")
	public ResponseDTO saveRateMaster(@RequestBody @Valid SaveRateMasterModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving saveRateMaster");
			return codeMasterService.saveRateMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getRateMaster")
	public ResponseDTO getRateMaster(@RequestBody @Valid CodeAndDescModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getRateMaster");
			return codeMasterService.getRateMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getAllRateMasterData")
	public ResponseDTO getAllRateMasterData() {
		log.debug("getAllPayMatrixData");
		return codeMasterService.getAllRateMasterData();
	}

	@PostMapping("/checkRateMaster")
	public ResponseDTO checkRateMaster(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("checkRateMaster");
			return codeMasterService.checkRateMaster(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/deleteRateMasterData")
	public ResponseDTO deleteRateMasterData(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deleteRateMasterData");
			return codeMasterService.deleteRateMasterData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@GetMapping("/getAllCodeTypeData")
	public ResponseDTO getAllCodeTypeData() {
		log.debug("getting getAllCodeTypeData");
		return codeMasterService.getAllCodeTypeData();
	}

	@PostMapping("/saveCodeTypeData")
	public ResponseDTO saveCodeTypeData(@RequestBody @Valid SaveCodeTypeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveCodeTypeData");
			return codeMasterService.saveCodeTypeData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getCodeTypeData")
	public ResponseDTO getCodeTypeData(@RequestBody @Valid CodeAndDescModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getCodeTypeData");
			return codeMasterService.getCodeTypeData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/deleteCodeTypeData")
	public ResponseDTO deleteCodeTypeData(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deleteCodeTypeData");
			return codeMasterService.deleteCodeTypeData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@GetMapping("/getAllDepartmentMasterData")
	public ResponseDTO getAllDepartmentMasterData() {
		log.debug("getting getAllDepartmentMasterData");
		return codeMasterService.getAllDepartmentMasterData();
	}

	@PostMapping("/saveDepartmentMasterData")
	public ResponseDTO saveDepartmentMasterData(@RequestBody @Valid SaveDepartmentModel payload,
			BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveDepartmentMasterData");
			return codeMasterService.saveDepartmentMasterData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getDepartmentMasterData")
	public ResponseDTO getDepartmentMasterData(@RequestBody @Valid CodeAndDescModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getCodeTypeData");
			return codeMasterService.getDepartmentMasterData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/deleteDepartmentMasterData")
	public ResponseDTO deleteDepartmentMasterData(@RequestBody @Valid CodeModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deleteDepartmentMasterData");
			return codeMasterService.deleteDepartmentMasterData(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getDescriptionBasedOnEd")
	public ResponseDTO getDescription(@RequestBody @Valid EmployeeID payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getDescriptionBasedOnEd");
			return codeMasterService.getDescription(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// we are using this one for rate master
	@PostMapping("/saveRateMasterUpdated")
	public ResponseDTO saveRateMasterUpdated(@RequestBody @Valid RateMasterModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting SaveRateMaster");
			return codeMasterService.saveRateMasterUpdated(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// Save Paymatrix Created on 10-11-2020
	@PostMapping("/saveSeventhMatrix")
	public ResponseDTO saveSeventhMatrix(@RequestBody @Valid SaveSeventhMatrixModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveSeventhMatrix");
			return codeMasterService.saveSeventhMatrix(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	// Get All Paymatrix data Created on 10-11-2020
	@GetMapping("/getAllSeventhMatrixData")
	public ResponseDTO getAllSeventhMatrixData() {
		log.debug("getAllSeventhMatrixData");
		return codeMasterService.getAllSeventhMatrixData();
	}

	// Get Paymatrix data by Parameter Created on 10-11-2020
	@PostMapping("/getSeventhMatrix")
	public ResponseDTO getSeventhMatrix(@RequestBody @Valid GetSeventhMatrixModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getSeventhMatrix");
			return codeMasterService.getSeventhMatrix(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// Edit Paymatrix data by Parameter Created on 10-11-2020
	@PostMapping("/editSeventhMatrix")
	public ResponseDTO editSeventhMatrix(@RequestBody @Valid EditSeventhMatrixModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("editSeventhMatrix");
			return codeMasterService.editSeventhMatrix(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	// Delete Paymatrix data by Parameter Created on 10-11-2020
	@PostMapping("/deleteSeventhMatrix")
	public ResponseDTO deleteSeventhMatrix(@RequestBody @Valid EditSeventhMatrixModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("deleteSeventhMatrix");
			return codeMasterService.deleteSeventhMatrix(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

}
