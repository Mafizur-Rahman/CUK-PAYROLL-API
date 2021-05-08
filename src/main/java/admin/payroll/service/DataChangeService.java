package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import admin.payroll.models.CodeAndDescModel;
import admin.payroll.models.EditCurrentMonthEdModel;
import admin.payroll.models.EditInstalRecovModel;
import admin.payroll.models.EditPmPraModel;
import admin.payroll.models.EditRegRecovModel;
import admin.payroll.models.EmpAndEdCodeModel;
import admin.payroll.models.EmpNoAndPayPeriodModel;
import admin.payroll.models.GetCurrentMonthEdModel;
import admin.payroll.models.GetPayRatesModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePmPraModel;
import admin.payroll.models.SaveRegRecovModel;
import admin.payroll.models.SaveCurrentMonthEdModel;
import admin.payroll.models.SaveInstalRecovModel;
import admin.payroll.models.SavePmPayMasterModel;

@Service
public interface DataChangeService {

	@Transactional
	ResponseDTO getPayRates(@Valid GetPayRatesModel payload);

	@Transactional
	ResponseDTO savePayRate(@Valid SavePmPraModel payload);

	@Transactional
	ResponseDTO saveCurrentMonthED(@Valid SaveCurrentMonthEdModel payload);

	@Transactional
	ResponseDTO saveRegularRecoveries(@Valid SaveRegRecovModel payload);

	@Transactional
	ResponseDTO saveInstallRecov(@Valid SaveInstalRecovModel payload);

	@Transactional
	ResponseDTO dataChangeServive();

	@Transactional
	ResponseDTO getCurrentMonthEdEmployeeWise(@Valid GetCurrentMonthEdModel payload);

	@Transactional
	ResponseDTO getCurrentMonthEdEDCodeWise(@Valid GetCurrentMonthEdModel payload);

	@Transactional
	ResponseDTO getInstallmentRecoveries(@Valid EmpAndEdCodeModel payload);

	@Transactional
	ResponseDTO getRegularRecoveries(@Valid EmpAndEdCodeModel payload);

	@Transactional
	ResponseDTO deletePayRate(Integer id);

	@Transactional
	ResponseDTO editPayRate(@Valid EditPmPraModel payload);

	@Transactional
	ResponseDTO editRegularRecoveries(@Valid EditRegRecovModel payload);

	@Transactional
	ResponseDTO editCurrentMonthED(@Valid EditCurrentMonthEdModel payload);

	@Transactional
	ResponseDTO editInstallRecov(@Valid EditInstalRecovModel payload);

	@Transactional
	ResponseDTO checkSalaryProcessedOrNot();

	@Transactional
	ResponseDTO checkPayRateExist(EmpAndEdCodeModel model);
	@Transactional
	ResponseDTO savePmPayMaster(@Valid SavePmPayMasterModel payload);
	
	
	@Transactional
	ResponseDTO getByEmpNoAndPayperiod(@Valid EmpNoAndPayPeriodModel payload);
	@Transactional
	ResponseDTO deleteByEmpNoAndPayperiodAndEarnindeduction(@Valid EmpNoAndPayPeriodModel payload);

}
