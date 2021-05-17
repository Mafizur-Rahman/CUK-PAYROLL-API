package admin.payroll.config;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import admin.payroll.entity.AddressEntity;
import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.EmpPmMedEntity;
import admin.payroll.entity.GpfAdvEntity;
import admin.payroll.entity.ITaxDataEntity;
import admin.payroll.entity.ItaxArrearEntity;
import admin.payroll.entity.KinEntity;
import admin.payroll.entity.PmBankEntity;
import admin.payroll.entity.PmBonusMstEntity;
import admin.payroll.entity.PmCodeTypeEntity;
import admin.payroll.entity.PmDeptEntity;
import admin.payroll.entity.PmDesigEntity;
import admin.payroll.entity.PmEedEntity;
import admin.payroll.entity.PmEmpDatesEntity;
import admin.payroll.entity.PmEmpDocsEntity;
import admin.payroll.entity.PmGecEntity;
import admin.payroll.entity.PmKinFeeEntity;
import admin.payroll.entity.PmLeaveTypeEntity;
import admin.payroll.entity.PmLoanEntity;
import admin.payroll.entity.PmPayMatrixEntity;
import admin.payroll.entity.PmPfmsTempEntity;
import admin.payroll.entity.PmPraEntity;
import admin.payroll.entity.PmQualificationEntity;
import admin.payroll.entity.PmRateEntity;
import admin.payroll.entity.PmRedEntity;
import admin.payroll.entity.PmRolesEntity;
import admin.payroll.entity.PmUnitSetupEntity;
import admin.payroll.entity.PmUserRightsEntity;
import admin.payroll.entity.PmUsersEntity;
import admin.payroll.entity.SeventhMatrixEntity;
import admin.payroll.entity.TaxCalcEntity;
import admin.payroll.models.BonusChangeDataModel;
import admin.payroll.models.GpfAdvSaveModel;
import admin.payroll.models.ItaxArrearModel;
import admin.payroll.models.ItaxDataModel;
import admin.payroll.models.SaveBankMasterModel;
import admin.payroll.models.SaveCodeTypeModel;
import admin.payroll.models.SaveCurrentMonthEdModel;
import admin.payroll.models.SaveDataMasterModel;
import admin.payroll.models.SaveDepartmentModel;
import admin.payroll.models.SaveDesignationMasterModel;
import admin.payroll.models.SaveDocumentModel;
import admin.payroll.models.SaveEDCodeModel;
import admin.payroll.models.SaveEmployeeAddressModel;
import admin.payroll.models.SaveEmployeeDatasModel;
import admin.payroll.models.SaveEmployeeQualificationModel;
import admin.payroll.models.SaveGeneralCodeModel;
import admin.payroll.models.SaveInstalRecovModel;
import admin.payroll.models.SaveKINFeeMasterModel;
import admin.payroll.models.SaveKINMasterModel;
import admin.payroll.models.SavePayMatrixModel;
import admin.payroll.models.SavePfmsTempModel;
import admin.payroll.models.SavePmLeaveTypeModel;
import admin.payroll.models.SavePmPraModel;
import admin.payroll.models.SavePmRolesModel;
import admin.payroll.models.SavePmUnitSetupModel;
import admin.payroll.models.SavePmUserRightModel;
import admin.payroll.models.SavePmUsersModel;
import admin.payroll.models.SaveRateMasterModel;
import admin.payroll.models.SaveRegRecovModel;
import admin.payroll.models.SaveSeventhMatrixModel;
import admin.payroll.models.TaxCalcModel;

public class Convertor {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static PmEedEntity convertToPMEed(SaveEDCodeModel payload) {
		return MAPPER.convertValue(payload, PmEedEntity.class);
	}

	public static PmGecEntity convertToPmGec(SaveGeneralCodeModel payload) {
		return MAPPER.convertValue(payload, PmGecEntity.class);
	}

	public static PmBankEntity convertToPMBank(@Valid SaveBankMasterModel payload) {
		return MAPPER.convertValue(payload, PmBankEntity.class);
	}

	public static PmPayMatrixEntity convertToPayMatrix(@Valid SavePayMatrixModel payload) {
		return MAPPER.convertValue(payload, PmPayMatrixEntity.class);
	}

	public static PmRateEntity convertToRateMaster(@Valid SaveRateMasterModel payload) {
		return MAPPER.convertValue(payload, PmRateEntity.class);
	}

	public static PmDesigEntity convertToDesigMaster(@Valid SaveDesignationMasterModel payload) {
		return MAPPER.convertValue(payload, PmDesigEntity.class);
	}

	public static EmpMastEntity convertToSaveEmployee(@Valid SaveEmployeeDatasModel payload) {
		return MAPPER.convertValue(payload, EmpMastEntity.class);
	}

	public static AddressEntity convertToSaveAddress(@Valid SaveEmployeeAddressModel payload) {
		return MAPPER.convertValue(payload, AddressEntity.class);
	}

	public static PmQualificationEntity convertToSaveQualification(@Valid SaveEmployeeQualificationModel payload) {
		return MAPPER.convertValue(payload, PmQualificationEntity.class);
	}

	public static KinEntity convertToSaveKinMaster(@Valid SaveKINMasterModel payload) {
		return MAPPER.convertValue(payload, KinEntity.class);
	}

	public static PmKinFeeEntity convertToSaveKinFeeMaster(@Valid SaveKINFeeMasterModel payload) {
		return MAPPER.convertValue(payload, PmKinFeeEntity.class);

	}

	public static PmEmpDatesEntity convertToSaveDataMaster(@Valid SaveDataMasterModel payload) {
		return MAPPER.convertValue(payload, PmEmpDatesEntity.class);
	}

	public static PmEmpDocsEntity convertToSaveDocument(@Valid SaveDocumentModel payload) {
		return MAPPER.convertValue(payload, PmEmpDocsEntity.class);
	}

	public static PmPraEntity convertToPmPraEntity(@Valid SavePmPraModel payload) {
		return MAPPER.convertValue(payload, PmPraEntity.class);
	}

	public static EmpPmMedEntity convertToEmpPmMedEntity(@Valid SaveCurrentMonthEdModel payload) {
		return MAPPER.convertValue(payload, EmpPmMedEntity.class);
	}

	public static PmRedEntity convertToPmRedEntity(@Valid SaveRegRecovModel payload) {
		return MAPPER.convertValue(payload, PmRedEntity.class);
	}

	public static PmLoanEntity convertToPmLoanEntity(@Valid SaveInstalRecovModel payload) {
		return MAPPER.convertValue(payload, PmLoanEntity.class);
	}

	public static PmCodeTypeEntity convertToPmCodeType(@Valid SaveCodeTypeModel payload) {
		return MAPPER.convertValue(payload, PmCodeTypeEntity.class);
	}

	public static PmDeptEntity convertToPmDept(SaveDepartmentModel payload) {
		return MAPPER.convertValue(payload, PmDeptEntity.class);
	}

	public static PmUnitSetupEntity convertToPmUnitSetup(@Valid SavePmUnitSetupModel payload) {
		return MAPPER.convertValue(payload, PmUnitSetupEntity.class);
	}

	public static PmBonusMstEntity convertToPMBpnusMst(@Valid BonusChangeDataModel payload) {
		return MAPPER.convertValue(payload, PmBonusMstEntity.class);
	}

	public static PmUsersEntity convertToPmUserEntity(@Valid SavePmUsersModel payload) {
		return MAPPER.convertValue(payload, PmUsersEntity.class);
	}

	public static PmRolesEntity convertToPmRolesEntity(@Valid SavePmRolesModel payload) {
		return MAPPER.convertValue(payload, PmRolesEntity.class);
	}

	public static PmUserRightsEntity convertToPmUserRightEntity(@Valid SavePmUserRightModel payload) {
		return MAPPER.convertValue(payload, PmUserRightsEntity.class);
	}

	public static ITaxDataEntity convertToITaxDataEntity(@Valid ItaxDataModel payload) {
		return MAPPER.convertValue(payload, ITaxDataEntity.class);
	}

	public static TaxCalcEntity convertToTaxCalcEntity(@Valid TaxCalcModel payload) {
		return MAPPER.convertValue(payload, TaxCalcEntity.class);
	}

	// create converter for itaxArrearEntity by kishan pandey

	public static ItaxArrearEntity convertToItaxArrearEntity(@Valid ItaxArrearModel payload) {
		return MAPPER.convertValue(payload, ItaxArrearEntity.class);
	}

	public static GpfAdvEntity convertToGpfAdvEntity(@Valid GpfAdvSaveModel gpfAdvSaveModel) {
		return MAPPER.convertValue(gpfAdvSaveModel, GpfAdvEntity.class);
	}

	public static SeventhMatrixEntity convertToSeventhMtrix(@Valid SaveSeventhMatrixModel payload) {
		return MAPPER.convertValue(payload, SeventhMatrixEntity.class);
	}

	public static PmLeaveTypeEntity convertToPmLeaveTypeEntity(@Valid SavePmLeaveTypeModel payload) {
		return MAPPER.convertValue(payload, PmLeaveTypeEntity.class);
	}

	public static PmPfmsTempEntity convertToPmPfmsTempEntity(@Valid SavePfmsTempModel payload) {
		return MAPPER.convertValue(payload, PmPfmsTempEntity.class);
	}

}