package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import admin.payroll.models.BonusChangeDataModel;
import admin.payroll.models.BonusPreProcessModel;
import admin.payroll.models.PreProcessModel;
import admin.payroll.models.ResponseDTO;

@Service
public interface BonusService {

	@Transactional
	ResponseDTO preBonus(@Valid PreProcessModel payload);

	@Transactional
	ResponseDTO changeData(@Valid BonusChangeDataModel payload);
	
	@Transactional
	ResponseDTO getPmBonusMstData();
	
	@Transactional
	ResponseDTO getPmBonusMstDataByEmpId(@Valid BonusChangeDataModel payload );
	
	@Transactional
	ResponseDTO bonusReports();
	
	@Transactional
	ResponseDTO bonusReportsByClassAndFyr(@Valid BonusChangeDataModel payload);
	
	@Transactional
	ResponseDTO bonusPaymentByClassAndFyr(@Valid BonusChangeDataModel payload);
	
	@Transactional
	ResponseDTO bonusPayment();

	@Transactional
	ResponseDTO checkIfBonusProcessed(BonusChangeDataModel payload);

	@Transactional
	ResponseDTO reverseBonusProcess(BonusChangeDataModel pyalod);

}
