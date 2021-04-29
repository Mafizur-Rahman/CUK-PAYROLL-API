package admin.payroll.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.PmDaArrearDtlEntity;
import admin.payroll.entity.PmRateEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.DAProcessModel;
import admin.payroll.models.FromToModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.repo.EmpMastRepo;
import admin.payroll.repo.PmDaArrearDtlRepo;
import admin.payroll.repo.PmDesigRepo;
import admin.payroll.repo.PmRateRepo;
import admin.payroll.service.DaArrearService;
import admin.payroll.utils.StringConstants;

@Service
public class DaArrearServiceImpl implements DaArrearService {

	@Autowired
	PmDaArrearDtlRepo pmDaArrearDtlRepo;

	@Autowired
	PmRateRepo pmRateRepo;

	@Autowired
	EmpMastRepo empMastRepo;

	@Autowired
	PmDesigRepo pmDesigRepo;

	@Override
	public ResponseDTO daProcess(@Valid DAProcessModel payload) {
		pmDaArrearDtlRepo.da_arr_proc(payload.getGroupUnit(), payload.getBillUnit(), payload.getFromMonth(),
				payload.getToMonth());
		return new ResponseDTO(StringConstants.Processed, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
	}

	@Override
	public ResponseDTO reportView(@Valid FromToModel payload) {
		String billUnit = null;
		try {
			List<String> empIds = new ArrayList<>();
			List<PmDaArrearDtlEntity> datas = pmDaArrearDtlRepo.findPayPeriodBetweenAndGroupUnit(
					payload.getFromPeriod(), payload.getToPeriod(), payload.getGroupUnit());
			for (PmDaArrearDtlEntity data : datas) {
				if (!empIds.contains(data.getEmpNo()))
					empIds.add(data.getEmpNo());
			}

			List<Map<String, Object>> finalList = new ArrayList<>();
			PmRateEntity rate = pmRateRepo.getByEarningDeduction("E0020");
			int totalArrear = 0;
			String empNo;
			String empName;
			String designationCode;
			Map<String, Object> finalMap = new HashMap<>();

			if (!empIds.isEmpty() && empIds != null) {
				for (String empId : empIds) {
					Map<String, Object> finalMap1 = new HashMap<>();
					Map<String, Object> basicDetailsMap = new HashMap<>();
					List<Map<String, Object>> arrearList = new ArrayList<>();
					List<PmDaArrearDtlEntity> arrearDatas = pmDaArrearDtlRepo.findPayPeriodBetweenAndGroupUnitAndEmpNo(
							payload.getFromPeriod(), payload.getToPeriod(), payload.getGroupUnit(), empId);
					totalArrear = 0;
					for (PmDaArrearDtlEntity data : arrearDatas) {
						Map<String, Object> map = new HashMap<>();
						map.put("basic", data.getNetBasic());
						map.put("rate", rate.getRate());
						EmpMastEntity empData = empMastRepo.findByEmpId(data.getEmpNo());
						map.put("payRate", empData.getPayRate());
						map.put("period", data.getPayPeriod());
						map.put("drawnOld", data.getAmtDrawn());
						map.put("dueNew", data.getAmtDue());
						map.put("arrear", data.getAmtArrear());
						billUnit = data.getBillUnit();
						arrearList.add(map);
						totalArrear += Integer.parseInt(data.getAmtArrear());
						empName = empData.getName();
						empNo = empData.getEmpId();
						designationCode = empData.getDesig();
						String desigShortDesc = pmDesigRepo.findDescBasedOnCode(designationCode);
						basicDetailsMap.put("empName", empName);
						basicDetailsMap.put("empNo", empNo);
						basicDetailsMap.put("designation", desigShortDesc);
						basicDetailsMap.put("total", totalArrear);

					}
					finalMap1.put("numericData", arrearList);
					finalMap1.put("empDetails", basicDetailsMap);
					finalList.add(finalMap1);
				}

			}
			finalMap.put("data", finalList);
			finalMap.put("fromPeriod", payload.getFromPeriod());
			finalMap.put("toPeriod", payload.getToPeriod());
			finalMap.put("groupUnit", payload.getGroupUnit());
			finalMap.put("billUnit", billUnit);
			finalMap.put("organisationName", "GTRE-BANGELORE");
			finalMap.put("statementName", "Statement Of DA/Arrear");

			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), finalMap);

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public ResponseDTO paymentStatement(@Valid FromToModel payload) {
//		try {
//
//			List<PmDaArrearDtlEntity> datas = pmDaArrearDtlRepo.findPayPeriodBetweenAndGroupUnit(
//					payload.getFromPeriod(), payload.getToPeriod(), payload.getGroupUnit());
//			List<Map<String, Object>> arrearList = new ArrayList<>();
//
//			int totalArrear = 0;
//			String empNo;
//			String empName;
//			String designationCode;
//			Map<String, Object> finalMap = new HashMap<>();
//			Map<String, Object> basicDetailsMap = new HashMap<>();
//			for (PmDaArrearDtlEntity data : datas) {
//				EmpMastEntity empData = empMastRepo.findByEmpId(data.getEmpNo());
//				totalArrear += Integer.parseInt(data.getAmtArrear());
//				empName = empData.getName();
//				empNo = empData.getEmpId();
//				designationCode = empData.getDesig();
//				String desigShortDesc = pmDesigRepo.findDescBasedOnCode(designationCode);
//				basicDetailsMap.put("empName", empName);
//				basicDetailsMap.put("empNo", empNo);
//				basicDetailsMap.put("designation", desigShortDesc);
//				basicDetailsMap.put("accountNo", empData.getBankNo());
//				basicDetailsMap.put("total", totalArrear);
//				basicDetailsMap.put("fromPeriod", payload.getFromPeriod());
//				basicDetailsMap.put("toPeriod", payload.getToPeriod());
//				basicDetailsMap.put("groupUnit", data.getGroupUnit());
//				basicDetailsMap.put("billUnit", data.getBillUnit());
//				basicDetailsMap.put("organisationName", "GTRE-BANGELORE");
//				basicDetailsMap.put("statementName", "Statement Of DA/Arrear");
//			}
//			finalMap.put("numericData", arrearList);
//			finalMap.put("empDetails", basicDetailsMap);
//
//			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), finalMap);
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return null;
//	}

		String billUnit = null;
		try {
			List<String> empIds = new ArrayList<>();
			List<PmDaArrearDtlEntity> datas = pmDaArrearDtlRepo.findPayPeriodBetweenAndGroupUnit(
					payload.getFromPeriod(), payload.getToPeriod(), payload.getGroupUnit());
			for (PmDaArrearDtlEntity data : datas) {
				if (!empIds.contains(data.getEmpNo()))
					empIds.add(data.getEmpNo());
			}

			List<Map<String, Object>> finalList = new ArrayList<>();
			PmRateEntity rate = pmRateRepo.getByEarningDeduction("E0020");
			int totalArrear = 0;
			String empNo;
			String empName;
			String designationCode;
			Map<String, Object> finalMap = new HashMap<>();

			if (!empIds.isEmpty() && empIds != null) {
				for (String empId : empIds) {
					Map<String, Object> finalMap1 = new HashMap<>();
					Map<String, Object> basicDetailsMap = new HashMap<>();
					List<Map<String, Object>> arrearList = new ArrayList<>();
					List<PmDaArrearDtlEntity> arrearDatas = pmDaArrearDtlRepo.findPayPeriodBetweenAndGroupUnitAndEmpNo(
							payload.getFromPeriod(), payload.getToPeriod(), payload.getGroupUnit(), empId);
					totalArrear = 0;
					for (PmDaArrearDtlEntity data : arrearDatas) {
						Map<String, Object> map = new HashMap<>();
						EmpMastEntity empData = empMastRepo.findByEmpId(data.getEmpNo());
						billUnit = data.getBillUnit();
						arrearList.add(map);
						totalArrear += Integer.parseInt(data.getAmtArrear());
						empName = empData.getName();
						empNo = empData.getEmpId();
						designationCode = empData.getDesig();
						String desigShortDesc = pmDesigRepo.findDescBasedOnCode(designationCode);
						basicDetailsMap.put("empName", empName);
						basicDetailsMap.put("accountNo", empData.getBankNo());
						basicDetailsMap.put("empNo", empNo);
						basicDetailsMap.put("designation", desigShortDesc);
						basicDetailsMap.put("amount", data.getAmtArrear());
						basicDetailsMap.put("total", totalArrear);

					}
					finalMap1.put("empDetails", basicDetailsMap);
					finalList.add(finalMap1);
				}

			}
			finalMap.put("data", finalList);
			finalMap.put("fromPeriod", payload.getFromPeriod());
			finalMap.put("toPeriod", payload.getToPeriod());
			finalMap.put("groupUnit", payload.getGroupUnit());
			finalMap.put("billUnit", billUnit);
			finalMap.put("organisationName", "GTRE-BANGELORE");
			finalMap.put("statementName", "Statement Of DA/Arrear");

			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), finalMap);

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
