package admin.payroll.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.EmpPmMedEntity;
import admin.payroll.entity.PmDesigEntity;
import admin.payroll.entity.PmLeavePostingEntity;
import admin.payroll.entity.PmLeaveTypeEntity;
import admin.payroll.entity.PmLoanEntity;
import admin.payroll.entity.PmPayMasteEntity;
import admin.payroll.entity.PmPraEntity;
import admin.payroll.entity.PmRedEntity;
import admin.payroll.entity.PmSalHdrEntity;
import admin.payroll.entity.PmSysMasterEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.EditCurrentMonthEdModel;
import admin.payroll.models.EditInstalRecovModel;
import admin.payroll.models.EditPmPraModel;
import admin.payroll.models.EditRegRecovModel;
import admin.payroll.models.EmpAndEdCodeModel;
import admin.payroll.models.EmpNoAndPayPeriodModel;
import admin.payroll.models.GetCurrentMonthEdModel;
import admin.payroll.models.GetPayRatesModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SaveCurrentMonthEdModel;
import admin.payroll.models.SaveInstalRecovModel;
import admin.payroll.models.SavePmLeavePostingModel;
import admin.payroll.models.SavePmPayMasterModel;
import admin.payroll.models.SavePmPayMasterModel.PayMasterRows;
import admin.payroll.models.SavePmPraModel;
import admin.payroll.models.SaveRegRecovModel;
import admin.payroll.repo.EmpPmMedRepo;
import admin.payroll.repo.PmDesigRepo;
import admin.payroll.repo.PmEedRepo;
import admin.payroll.repo.PmLoanRepo;
import admin.payroll.repo.PmPayMasterRepo;
import admin.payroll.repo.PmPraRepo;
import admin.payroll.repo.PmRedRepo;
import admin.payroll.repo.PmSysMasterRepo;
import admin.payroll.service.DataChangeService;
import admin.payroll.utils.StringConstants;

@Service
public class DataChangeServiceImpl implements DataChangeService {

	private static final Logger log = LoggerFactory.getLogger(DataChangeServiceImpl.class);

	@Autowired
	PmPraRepo pmPraRepo;

	@Autowired
	PmDesigRepo pmDesigRepo;

	@Autowired
	PmPayMasterRepo payMasterRepo;
	@Autowired
	PmEedRepo pmEedRepo;

	@Autowired
	EmpPmMedRepo empPmMedRepo;

	@Autowired
	PmRedRepo pmRedRepo;

	@Autowired
	PmLoanRepo pmLoanRepo;
	
	@Autowired
	PmSysMasterRepo pmSysMasterRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public ResponseDTO checkSalaryProcessedOrNot() {
		try {
			boolean data = false ;
			List<PmSysMasterEntity> datas =pmSysMasterRepo.findAll();
			for (PmSysMasterEntity sysdata : datas) {
				if(sysdata.getMStatus().equals("O"))
				data = true;
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("checkSalaryProcessedOrNot {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDTO savePayRate(@Valid SavePmPraModel payload) {
		try {
			String earningDeduction = payload.getEarningDeduction();
			String logIp = payload.getLogIp();
			String logUser = payload.getLogUser();
			List fromDateList = payload.getFromDate();
			List toDateList = payload.getToDate();
			List desigCodeList = payload.getDesigCode();
			// List scaleCodeList = payload.getScaleCode();
			List pmCellList = payload.getPmCell();
			List pmLevelList = payload.getPmLevel();
			List rateList = payload.getRate();
			// List localityClassList = payload.getLocalityClass();
			List officeOrderDateList = payload.getOfficeOrderDate();
			List OfficeOrderNoList = payload.getOfficeOrderNo();
			List remarkList = payload.getRemarks();
			List regimentalRemarksList = payload.getRegimentalRemarks();
			for (int i = 0; i <= fromDateList.size() - 1; i++) {
				String empNo = null;
				PmPraEntity pmPraEntity = new PmPraEntity();
				SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
				Date fromDate = (Date) formatter3.parse(fromDateList.get(i).toString());
				Date officeOrderDate = null;
				//if (!(officeOrderDateList.get(i) == null && !(officeOrderDateList.get(i) == "")))
				if (officeOrderDateList.get(i) != null &&  !"".equals(officeOrderDateList.get(i)))
					officeOrderDate = formatter3.parse(officeOrderDateList.get(i).toString());
				if (payload.getType().equalsIgnoreCase("edCode")) {
					List empNos = payload.getEmpNos();
					empNo = empNos.get(i).toString();
				} else {
					empNo = payload.getEmpNo();
				}
				PmPraEntity checkOldData = pmPraRepo.findByEmpNoAndEarningDeductionAndFromDate(empNo, earningDeduction,
						fromDate);
				Date toDate = null;
				String remarks = null;
				String regRemarks = null;
				if (checkOldData != null) {
					//if (!(toDateList.get(i) == null && !(officeOrderDateList.get(i) == "")))
					if (toDateList.get(i) != null &&  !"".equals(toDateList.get(i)))
						toDate = (Date) formatter3.parse(toDateList.get(i).toString());
					checkOldData.setToDate(toDate);
					checkOldData.setDesigCode(desigCodeList.get(i).toString());
					checkOldData.setPmCell(pmCellList.get(i).toString());
					checkOldData.setPmLevel(pmLevelList.get(i).toString());
					checkOldData.setRate(Double.parseDouble(rateList.get(i).toString()));
					checkOldData.setOfficeOrderDate(officeOrderDate);
					if (OfficeOrderNoList.get(i) != null)
					checkOldData.setOfficeOrderNo(OfficeOrderNoList.get(i).toString());
					if (!(remarkList.get(i) == null))
						remarks = remarkList.get(i).toString();
					checkOldData.setRemarks(remarks);
					if (!(regimentalRemarksList.get(i) == null))
						regRemarks = regimentalRemarksList.get(i).toString();
					checkOldData.setRegRemarks(regRemarks);
					checkOldData.setLogUser(logUser);
					pmPraRepo.save(checkOldData);

				}

				else {
					pmPraEntity.setDesigCode(desigCodeList.get(i).toString());
					pmPraEntity.setPmCell(pmCellList.get(i).toString());
					pmPraEntity.setPmLevel(pmLevelList.get(i).toString());
					pmPraEntity.setRate(Double.parseDouble(rateList.get(i).toString()));
					pmPraEntity.setOfficeOrderDate(officeOrderDate);
					pmPraEntity.setOfficeOrderNo(OfficeOrderNoList.get(i).toString());
					pmPraEntity.setRemarks(remarkList.get(i).toString());
					pmPraEntity.setRegRemarks(regimentalRemarksList.get(i).toString());

					pmPraEntity.setEmpNo(empNo);
					pmPraEntity.setEarningDeduction(earningDeduction);
					pmPraEntity.setLogIp(logIp);
					pmPraEntity.setLogUser(logUser);
					pmPraEntity.setFromDate(fromDate);
					pmPraRepo.save(pmPraEntity);
				}
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			log.error("saving savePayRate {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDTO saveCurrentMonthED(@Valid SaveCurrentMonthEdModel payload) {
		try {

			String earningDeduction = payload.getEarningDeduction();
			String logIp = payload.getLogIp();
			String logUser = payload.getLogUser();
			List payPeriodList = payload.getPayPeriod();
			List refNoList = payload.getRefNo();
			List refDateList = payload.getRefDate();
			List amtList = payload.getAmt();
			for (int i = 0; i <= payPeriodList.size() - 1; i++) {
				EmpPmMedEntity empPmMed = new EmpPmMedEntity();
				SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
				Date refDate = (Date) formatter3.parse(refDateList.get(i).toString());
				String payPeriod = payPeriodList.get(i).toString();
				String refNo = refNoList.get(i).toString();
				String empNo = null;
				if (payload.getType().equalsIgnoreCase("edCode")) {
					List empNos = payload.getEmpNos();
					empNo = empNos.get(i).toString();
				} else
					empNo = payload.getEmpNo();

				double amount = 0;
				if (!(amtList.get(i) == null))
					amount = Double.parseDouble(amtList.get(i).toString());

				EmpPmMedEntity oldData = empPmMedRepo.findByEmpNoAndEarningDeductionAndPayPeriodAndRefNo(empNo,
						earningDeduction, payPeriod, refNo);

				if (oldData != null) {
					oldData.setRefDate(refDate);
					oldData.setPayPeriod(payPeriod);
					oldData.setAmt(amount);
					empPmMedRepo.save(oldData);

				} else {
					empPmMed.setEmpNo(empNo);
					empPmMed.setRefDate(refDate);
					empPmMed.setPayPeriod(payPeriod);
					empPmMed.setRefNo(refNoList.get(i).toString());
					empPmMed.setAmt(amount);
					empPmMed.setLogIp(logIp);
					empPmMed.setLogUser(logUser);
					empPmMed.setEarningDeduction(earningDeduction);
					empPmMedRepo.save(empPmMed);
				}
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saving saveCurrentMonthED");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDTO saveRegularRecoveries(@Valid SaveRegRecovModel payload) {
		try {

			String earningDeduction = payload.getEarningDeduction();
			String logIp = payload.getLogIp();
			String logUser = payload.getLogUser();

			List refNoList = payload.getRefNo();
			List refDateList = payload.getRefDate();
			List startYearMm = payload.getStartYearMm();
			List endYearMm = payload.getEndYearMm();
			List amtList = payload.getAmt();
			for (int i = 0; i <= startYearMm.size() - 1; i++) {
				PmRedEntity pmRed = new PmRedEntity();
				SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
				String empNo = null;
				String refNos = refNoList.get(i).toString();
				String startYeMon = startYearMm.get(i).toString();
				Date refDate = (Date) formatter3.parse(refDateList.get(i).toString());

				String endYearMon = null;
				if (!(endYearMm.get(i) == null)) {
					endYearMon = endYearMm.get(i).toString();
				}

				double amount = 0;
				if (!(amtList.get(i) == null))
					amount = Double.parseDouble(amtList.get(i).toString());

				if (payload.getType().equalsIgnoreCase("edCode")) {
					List empNos = payload.getEmpNos();
					empNo = empNos.get(i).toString();
				} else
					empNo = payload.getEmpNo();

				PmRedEntity oldData = pmRedRepo.findByEmpNoAndEarningDeductionAndRefNoAndStartYearMm(empNo,
						earningDeduction, refNos, startYeMon);
				if (oldData != null) {
					oldData.setRefDate(refDate);
					oldData.setAmt(amount);
					oldData.setStartYearMm(startYeMon);
					oldData.setEndYearMm(endYearMon);
					pmRedRepo.save(oldData);
				} else {
					pmRed.setRefNo(refNos);
					pmRed.setRefDate(refDate);
					pmRed.setAmt(amount);
					pmRed.setStartYearMm(startYeMon);
					pmRed.setEndYearMm(endYearMon);
					pmRed.setLogIp(logIp);
					pmRed.setLogUser(logUser);
					pmRed.setEmpNo(empNo);
					pmRed.setLogIp(logIp);
					pmRed.setEarningDeduction(earningDeduction);
					pmRedRepo.save(pmRed);
				}
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saving saveCurrentMonthED");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDTO saveInstallRecov(@Valid SaveInstalRecovModel payload) {
		try {
			String earningDeduction = payload.getEarningDeduction();
			String logIp = payload.getLogIp();
			String logUser = payload.getLogUser();
			List refNoList = payload.getRefNo();
			List sancDateList = payload.getSancDate();
			List principalAmt = payload.getPrincipalAmt();
			List sancAmtList = payload.getSancAmt();
			List rateRecoveryList = payload.getRateRecovery();
			List totInstalmentList = payload.getTotInstalment();
			List amtRecoveredList = payload.getAmtRecovered();
			List instalmentRecoveredList = payload.getInstalmentRecovered();
			List startYearMmList = payload.getStartYearMm();
			List endYearMmList = payload.getEndYearMm();
			for (int i = 0; i <= refNoList.size() - 1; i++) {
				PmLoanEntity pmLoan = new PmLoanEntity();
				SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
				Date sancDate = (Date) formatter3.parse(sancDateList.get(i).toString());
				String refNo = refNoList.get(i).toString();
				String startYearMon = startYearMmList.get(i).toString();
				String empNo = null;

				double principalAmtt = Double.parseDouble(principalAmt.get(i).toString());
				double sancAmtt = Double.parseDouble(sancAmtList.get(i).toString());
				double rateRecovery = Double.parseDouble(rateRecoveryList.get(i).toString());
				double totInstalment = Double.parseDouble(totInstalmentList.get(i).toString());
				double amtRecovered = Double.parseDouble(amtRecoveredList.get(i).toString());
				double instalmentRecovered = Double.parseDouble(instalmentRecoveredList.get(i).toString());
				String endYearMn = null;
				if (!(endYearMmList.get(i) == null) && !endYearMmList.isEmpty())
					endYearMn = endYearMmList.get(i).toString();

				if (payload.getType().equalsIgnoreCase("edCode")) {
					List empNos = payload.getEmpNos();
					empNo = empNos.get(i).toString();
				} else
					empNo = payload.getEmpNo();

				PmLoanEntity oldData = pmLoanRepo.findByEmpNoAndEarningDeductionAndRefNoAndSancDateAndStartYearMm(empNo,
						earningDeduction, refNo, sancDate, startYearMon);
				if (oldData != null) {
					oldData.setPrincipalAmt(principalAmtt);
					oldData.setSancAmt(sancAmtt);
					oldData.setRateRecovery(rateRecovery);
					oldData.setTotInstalment(totInstalment);
					oldData.setAmtRecovered(amtRecovered);
					oldData.setInstalmentRecovered(instalmentRecovered);
					oldData.setEndYearMm(endYearMn);
					pmLoanRepo.save(oldData);
				} else {
					pmLoan.setRefNo(refNo);
					pmLoan.setSancDate(sancDate);
					pmLoan.setPrincipalAmt(principalAmtt);
					pmLoan.setSancAmt(sancAmtt);
					pmLoan.setRateRecovery(rateRecovery);
					pmLoan.setTotInstalment(totInstalment);
					pmLoan.setAmtRecovered(amtRecovered);
					pmLoan.setInstalmentRecovered(instalmentRecovered);
					pmLoan.setStartYearMm(startYearMon);
					pmLoan.setEndYearMm(endYearMn);

					pmLoan.setEmpNo(empNo);
					pmLoan.setLogIp(logIp);
					pmLoan.setLogUser(logUser);
					pmLoan.setLogIp(logIp);
					pmLoan.setEarningDeduction(earningDeduction);
					pmLoanRepo.save(pmLoan);
				}
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saving saveCurrentMonthED");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getPayRates(@Valid GetPayRatesModel payload) {
		try {
			List<Map<String, Object>> output = new ArrayList<>();
			Sort sort = Sort.by("fromDate");
			List<PmPraEntity> data = pmPraRepo.getPayRatesDatas(payload.getEdCode(), payload.getEmployeeNo(), sort);
			for (PmPraEntity payData : data) {
				Map<String, Object> finalData = new HashMap<>();
				String fromDate = null;
				String toDate = null;
				String officeOrderDate = null;
				if (payData.getFromDate() != null)
					fromDate = payData.getFromDate().toString().substring(0, 10);
				if (payData.getToDate() != null)
					toDate = payData.getToDate().toString().substring(0, 10);
				if (payData.getOfficeOrderDate() != null)
					officeOrderDate = payData.getOfficeOrderDate().toString().toString().substring(0, 10);

//				String shortDesc = pmDesigRepo.findDescBasedOnCode(payData.getDesigCode());
				finalData.put("fromDate", fromDate);
				finalData.put("toDate", toDate);
				finalData.put("pmCell", payData.getPmCell());
				finalData.put("pmlevel", payData.getPmLevel());
				finalData.put("rate", payData.getRate());
				finalData.put("offOrderDate", officeOrderDate);
				finalData.put("offOrderNo", payData.getOfficeOrderNo());
				finalData.put("remark", payData.getRemarks());
				finalData.put("regRemarks", payData.getRegRemarks());
				finalData.put("desigCode", payData.getDesigCode());
				output.add(finalData);

			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), output);
		} catch (Exception e) {
			System.out.println();
			log.error("saving getPayRates");

		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO dataChangeServive() {
		List<String> finalData = new ArrayList<>();
		try {
			List<PmDesigEntity> data = pmDesigRepo.findAll();
			for (PmDesigEntity desig : data) {
				String nameDesc = desig.getDesigCode() + "," + desig.getDesigShortDesc();
				finalData.add(nameDesc);
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), finalData);

		} catch (Exception e) {
			log.error("saving dataChangeServive");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getCurrentMonthEdEmployeeWise(GetCurrentMonthEdModel payload) {
		try {
			List<EmpPmMedEntity> data = empPmMedRepo.getEmployeeWiseData(payload.getCode(), payload.getEdCode(),
					payload.getPayPeriod());
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("saving getCurrentMonthEdEmployeeWise");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getCurrentMonthEdEDCodeWise(@Valid GetCurrentMonthEdModel payload) {
		return null;
//		List<EmpPmMedEntity> data = empPmMedRepo.getEdCodeWiseData(payload.getEdCode(), payload.getCode(),
//				payload.getPayPeriod());
//		return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
	}

	@Override
	public ResponseDTO getInstallmentRecoveries(@Valid EmpAndEdCodeModel payload) {
		try {
			List<PmLoanEntity> datas = pmLoanRepo.findByEmpNoAndEarningDeduction(payload.getEmpId(),
					payload.getEdCode());
			List<Map<String, Object>> list = new ArrayList<>();
			for (PmLoanEntity data : datas) {
				Map<String, Object> map = new HashMap<>();

				map.put("empNo", data.getEmpNo());
				map.put("earningDeduction", data.getEarningDeduction());
				map.put("refNo", data.getRefNo());
				map.put("sancDate", data.getSancDate().toString().substring(0, 10));
				map.put("principalAmt", data.getPrincipalAmt());
				map.put("sancAmt", data.getSancAmt());
				map.put("paycalPeriod", data.getPaycalPeriod());
				map.put("rateRecovery", data.getRateRecovery());
				map.put("totInstalment", data.getTotInstalment());
				map.put("amtRecovered", data.getAmtRecovered());
				map.put("instalmentRecovered", data.getInstalmentRecovered());
				map.put("startYearMm", data.getStartYearMm());
				map.put("endYearMm", data.getEndYearMm());
				list.add(map);
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("saving getInstallmentRecoveries");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getRegularRecoveries(@Valid EmpAndEdCodeModel payload) {
		try {
			List<Map<String, Object>> list = new ArrayList<>();
			Sort sort = Sort.by("startYearMm");
			List<PmRedEntity> datas = pmRedRepo.getRegularRecoveries(payload.getEmpId(), payload.getEdCode(), sort);
			for (PmRedEntity data : datas) {
				Map<String, Object> map = new HashMap<>();

				map.put("empNo", data.getEmpNo());
				map.put("earningDeduction", data.getEarningDeduction());
				map.put("refNo", data.getRefNo());
				map.put("refDate", data.getRefDate().toString().substring(0, 10));
				map.put("startYearMm", data.getStartYearMm());
				map.put("endYearMm", data.getEndYearMm());
				map.put("amt", data.getAmt());
				map.put("paycalPeriod",data.getPaycalPeriod());
				list.add(map);
			}

			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("saving getInstallmentRecoveries");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO deletePayRate(Integer id) {
		try {
			pmPraRepo.deleteById(String.valueOf(id.toString()));
			return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saving getInstallmentRecoveries");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO editPayRate(@Valid EditPmPraModel payload) {
		PmPraEntity data = pmPraRepo.findById(String.valueOf(payload.getId())).get();
		try {

			SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = (Date) formatter3.parse(payload.getFromDate().toString());
			Date officeOrderedDate = (Date) formatter3.parse(payload.getOfficeOrderDate().toString());
			Date toDate = (Date) formatter3.parse(payload.getToDate().toString());
			data.setDesigCode(payload.getDesigCode());
			data.setFromDate(fromDate);
			data.setOfficeOrderDate(officeOrderedDate);
			data.setOfficeOrderNo(payload.getOfficeOrderNo());
			data.setPmCell(payload.getPmCell());
			data.setPmLevel(payload.getPmLevel());
			data.setRate(Double.parseDouble(payload.getRate()));
			data.setRegRemarks(payload.getRegimentalRemarks());
			data.setRemarks(payload.getRemarks());
			data.setToDate(toDate);
			pmPraRepo.save(data);
			return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO editRegularRecoveries(@Valid EditRegRecovModel payload) {
		PmRedEntity data = pmRedRepo.findById(payload.getId()).get();
		try {

			SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
			Date refDate = (Date) formatter3.parse(payload.getRefDate().toString());
			data.setAmt(Double.parseDouble(payload.getAmt()));
			data.setEndYearMm(payload.getEndYearMm());
			data.setStartYearMm(payload.getStartYearMm());
			data.setRefNo(payload.getRefNo());
			data.setRefDate(refDate);
			pmRedRepo.save(data);
			return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO editCurrentMonthED(@Valid EditCurrentMonthEdModel payload) {
		EmpPmMedEntity data = empPmMedRepo.findById(payload.getId()).get();
		try {
			SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
			Date refDate = (Date) formatter3.parse(payload.getRefDate().toString());
			data.setAmt(Double.parseDouble(payload.getAmt()));
			data.setPayPeriod(payload.getPayPeriod());
			data.setRefNo(payload.getRefNo());
			data.setRefDate(refDate);
			data.setPayCalPeriod(payload.getPayCalPeriod());
			data.setPayPeriod(payload.getPayPeriod());
			empPmMedRepo.save(data);
			return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO editInstallRecov(@Valid EditInstalRecovModel payload) {
		PmLoanEntity data = pmLoanRepo.findById(payload.getId()).get();
		try {
			SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
			Date sancDate = (Date) formatter3.parse(payload.getSancDate().toString());
			data.setAmtRecovered(Double.parseDouble(payload.getAmtRecovered()));
			data.setEndYearMm(payload.getEndYearMm());
			data.setInstalmentRecovered(Double.parseDouble(payload.getInstalmentRecovered()));
			data.setPaycalPeriod(payload.getPayCalPeriod());
			data.setPrincipalAmt(Double.parseDouble(payload.getPrincipalAmt()));
			data.setRateRecovery(Double.parseDouble(payload.getRateRecovery()));
			data.setRefNo(payload.getRefNo());
			data.setSancAmt(Double.parseDouble(payload.getSancAmt()));
			data.setSancDate(sancDate);
			data.setStartYearMm(payload.getStartYearMm());
			data.setTotInstalment(Double.parseDouble(payload.getTotInstalment()));
			pmLoanRepo.save(data);
			return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO checkPayRateExist(EmpAndEdCodeModel model) {
		Optional<PmPraEntity> pmPra = this.pmPraRepo.checkPayRateExist(model.getEmpId(), model.getEdCode());
		return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), pmPra.isPresent());
	}
	
	@Override
	public ResponseDTO savePmPayMaster(@Valid SavePmPayMasterModel payload) {
		try {
			payload.getPayMasterRows().forEach(row -> {
				PmPayMasteEntity pmPayMasterData = this.modelMapper.map(row, PmPayMasteEntity.class);
				pmPayMasterData.setEmpNo(payload.getEmpNo());
				pmPayMasterData.setPayperiod(payload.getPayperiod());
				pmPayMasterData.setLogUser(payload.getLogUser());
				pmPayMasterData.setLogIp(payload.getLogIp());
				payMasterRepo.save(pmPayMasterData);			});
					
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveEmployeeData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	@Override
	public ResponseDTO getByEmpNoAndPayperiod(@Valid EmpNoAndPayPeriodModel payload) {
		List<PmPayMasteEntity> list = payMasterRepo.getByEmpNoAndPayperiod(payload.getEmpNo(), payload.getPayperiod());
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
				list);
	}

	@Override
	public ResponseDTO deleteByEmpNoAndPayperiodAndEarnindeduction(@Valid EmpNoAndPayPeriodModel payload) {
		try {		
			payMasterRepo.deleteByEmpNoAndPayperiodAndEarnindeduction(payload.getEmpNo(), payload.getPayperiod(),payload.getEarningdeduction());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteKinMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	
}

	
