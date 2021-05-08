package admin.payroll.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.PaybillAndOtherEntity;
import admin.payroll.entity.PaybillEntity;
import admin.payroll.entity.PmGecEntity;
import admin.payroll.entity.RegimentalPaybillEntity;
import admin.payroll.models.ClassModel;
import admin.payroll.models.CodeAndCodeTypeModel;
import admin.payroll.models.EedMode;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SosDateModel;

@Service
public interface PaybillAndOtherService {

	@Transactional
	ResponseDTO getPayBill();

	@Transactional
	List<PaybillEntity> getPaybillEntityExcel();
	
	@Transactional
	ResponseDTO payBillByClass(@Valid ClassModel payload);

	@Transactional
	ResponseDTO GetMonth();

	@Transactional
	ResponseDTO GetOrgName();

	@Transactional
	ResponseDTO RetirementListForNextMonth(@Valid SosDateModel payload);

	@Transactional
	ResponseDTO PayBillSummary();

	@Transactional
	ResponseDTO GetRegimentalPaybill();

	@Transactional
	List<RegimentalPaybillEntity> getRegimentalPaybillExcel();
	
	@Transactional
	ResponseDTO getItax();
	@Transactional
	List<PaybillAndOtherEntity> getItaxExcel();

	@Transactional
	ResponseDTO getItaxByClass(@Valid ClassModel payload);

	@Transactional
	ResponseDTO getNps();

	@Transactional
	ResponseDTO getGroupInsurance();

	@Transactional
	ResponseDTO getMiscRecoverySchedule();

	@Transactional
	ResponseDTO getEducationLoan();

	@Transactional
	ResponseDTO getIbLoan();

	@Transactional
	ResponseDTO grtReimbursmentTfee();

	@Transactional
	ResponseDTO getCgoClubRecovery();

	@Transactional
	ResponseDTO getMahilaKalyanManch();

	@Transactional
	ResponseDTO getFamilyReliefFund();

	@Transactional
	ResponseDTO getSSLic();
	@Transactional
	List<PaybillAndOtherEntity> listAll();

	@Transactional
	ResponseDTO getCghsRecovery();

	@Transactional
	List<PaybillAndOtherEntity> getCghsRecoveryExcel();
	
	@Transactional
	ResponseDTO getCghsRecoveryByClass(@Valid ClassModel payload);

	@Transactional
	ResponseDTO getMiscRecovery();

	@Transactional
	ResponseDTO getUnionn();

	@Transactional
	ResponseDTO getDromi();

	@Transactional
	ResponseDTO getPli();

	@Transactional
	ResponseDTO getNonCghsRecovery();

	@Transactional
	ResponseDTO payBillAndOther();

	@Transactional
	ResponseDTO getGis();
	@Transactional
	List<PaybillAndOtherEntity> getGisAll();

	@Transactional
	ResponseDTO getGisByClass(@Valid ClassModel payload);

	@Transactional
	ResponseDTO getSSLicByClass(ClassModel payload);

	@Transactional
	ResponseDTO getPliByClass(ClassModel payload);

	@Transactional
	ResponseDTO getCgoClubRecoveryByClass(ClassModel payload);

	@Transactional
	ResponseDTO getEducationLoanByClass(ClassModel payload);

	@Transactional
	ResponseDTO getDromiByClass(ClassModel payload);

	@Transactional
	ResponseDTO getUnionnByClass(ClassModel payload);

	@Transactional
	ResponseDTO getMiscRecoveryScheduleByClass(ClassModel payload);

	@Transactional
	ResponseDTO getPunchingMedia();

	@Transactional
	ResponseDTO getDivisonWiseEmployee();
	@Transactional
	List<PaybillAndOtherEntity> getPliExcel();
	@Transactional
	List<PaybillAndOtherEntity> getMiscRecoveryScheduleExcel();
	@Transactional
	List<RegimentalPaybillEntity> getCgoClubRecoveryExcel();
	@Transactional
	List<RegimentalPaybillEntity> getEducationLoanExcel();
	@Transactional
	List<RegimentalPaybillEntity> getDromiExcel();
	@Transactional
	List<RegimentalPaybillEntity> getUnionExcel();
	@Transactional
	List<EmpMastEntity> RetirementListForNextMonthExcel(@Valid SosDateModel payload);
	@Transactional
	List<PaybillAndOtherEntity> getNonCghsRecoveryExcel();
	@Transactional
	List<RegimentalPaybillEntity> getNGORegimentalPaybillExcel();

	@Transactional
	List<PaybillEntity> getSalDataForValidationExcel();
	@Transactional
	List<PaybillEntity> getPaymentDataExcel();

}
