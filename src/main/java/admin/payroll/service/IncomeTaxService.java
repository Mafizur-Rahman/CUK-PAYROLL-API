package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import admin.payroll.entity.TaxCalcEntity;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.ItaxArrearModel;
import admin.payroll.models.ItaxDataListModel;
import admin.payroll.models.ItaxDataModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.TaxCalcModel;

public interface IncomeTaxService {
	
	@Transactional
	 ResponseDTO saveItaxData(@Valid ItaxDataListModel payload);
	
	@Transactional
	ResponseDTO itaxMonthlyProcess(@Valid ItaxDataModel payload); 
	
	@Transactional
	ResponseDTO saveTaxCalc(@Valid TaxCalcModel payload);
	
	@Transactional
	ResponseDTO saveItaxArrear(@Valid ItaxArrearModel payload);
	
	@Transactional
	ResponseDTO taxcalempidsixth(@Valid TaxCalcModel payload);
	
	@Transactional
	ResponseDTO getItaxDataByEmpId(@Valid ItaxArrearModel payload);
	
	@Transactional
	String checkMonthandYearDataPresentInSalaryDetail(@Valid ItaxDataModel payload);

	@Transactional
	ResponseDTO getTaxCalcByEmployeeId(EmployeeID employeeId);

}
