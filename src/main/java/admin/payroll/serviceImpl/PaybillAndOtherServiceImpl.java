package admin.payroll.serviceImpl;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.PaybillAndOtherEntity;
import admin.payroll.entity.PaybillEntity;
import admin.payroll.entity.PmSalHdrEntity;
import admin.payroll.entity.PmSysMasterEntity;
import admin.payroll.entity.PmUnitSetupEntity;
import admin.payroll.entity.RegimentalPaybillEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.ClassModel;
import admin.payroll.models.PunchingMediaModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SosDateModel;
import admin.payroll.repo.EmpMastRepo;
import admin.payroll.repo.PaybillAndOtherRepo;
import admin.payroll.repo.PaybillRepo;
import admin.payroll.repo.PmDesigRepo;
import admin.payroll.repo.PmSalHdrRepo;
import admin.payroll.repo.PmSysMasterRepo;
import admin.payroll.repo.PmUnitSetupRepo;
import admin.payroll.repo.RegimentalPaybillRepo;
import admin.payroll.service.PaybillAndOtherService;
import admin.payroll.utils.StringConstants;
import lombok.Data;

@Service
public class PaybillAndOtherServiceImpl implements PaybillAndOtherService {

	private static final Logger log = LoggerFactory.getLogger(PaybillAndOtherServiceImpl.class);

	@Autowired
	PaybillRepo paybillRepo;

	@Autowired
	PaybillAndOtherRepo paybillAndOtherRepo;

	@Autowired
	PmSysMasterRepo pmSysMasterRepo;

	@Autowired
	PmUnitSetupRepo pmUnitSetupRepo;

	@Autowired
	EmpMastRepo empMastRepo;

	@Autowired
	PmDesigRepo pmDesigRepo;
	
	@Autowired
	PmSalHdrRepo pmSalHdrRepo;

	@Autowired
	RegimentalPaybillRepo regimentalPaybillRepo;

	@Override
	public ResponseDTO getPayBill() {
		try {
			List<PaybillEntity> data = paybillRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	@Override
	public ResponseDTO payBillAndOther() {
		try {
			List<PaybillAndOtherEntity> data = paybillAndOtherRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	@Override
	public ResponseDTO payBillByClass(@Valid ClassModel payload) {
		try {
			List<PaybillEntity> data = paybillRepo.findAllByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("findAllByClass {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO GetMonth() {
		try {
			List<Map<String, Object>> output = new ArrayList<>();
			List<PmSysMasterEntity> datas = pmSysMasterRepo.findAll();
			for (PmSysMasterEntity data : datas) {
				Map<String, Object> finalOutput = new HashMap<>();

				int paycall = Integer.parseInt(data.getPayCalPeriod());
				int monthno = paycall % 100;
				String month = Month.of(monthno).name();
				int year = Integer.parseInt(data.getPayCalPeriod().substring(0, 4));
				String monthYear = month + "-" + year;

				finalOutput.put("monthYear", monthYear);
				finalOutput.put("year", year);
				finalOutput.put("month", month);
				finalOutput.put("groupUnit", data.getGroupUnit());
				finalOutput.put("billUnit", data.getBillUnit());
				finalOutput.put("fromDate", data.getFromDate());
				finalOutput.put("toDate", data.getToDate());
				finalOutput.put("payCalPeriod", data.getPayCalPeriod());
				finalOutput.put("wagePeriod", data.getWagePeriod());
				finalOutput.put("paycommission", data.getPaycommission());
				finalOutput.put("mStatus", data.getMStatus());
				finalOutput.put("mBackup", data.getMBackup());

				output.add(finalOutput);

			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), output);
		} catch (Exception e) {
			log.error("getMonthOpeningData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO GetOrgName() {
		try {
			List<Map<String, Object>> output = new ArrayList<>();
			List<PmUnitSetupEntity> datas = pmUnitSetupRepo.findAll();
			for (PmUnitSetupEntity data : datas) {
				Map<String, Object> finalOutput = new HashMap<>();

				finalOutput.put("orgName", data.getShortName() + "," + data.getCityLocation());
				finalOutput.put("uniId", data.getUniId());
				finalOutput.put("shortName", data.getShortName());
				finalOutput.put("longName", data.getLongName());
				finalOutput.put("address1", data.getAddress1());
				finalOutput.put("address2", data.getAddress2());
				finalOutput.put("address3", data.getAddress3());
				finalOutput.put("cityLocation", data.getCityLocation());
				finalOutput.put("sate", data.getSate());
				finalOutput.put("phoneNo", data.getPhoneNo());
				finalOutput.put("emailId", data.getPhoneNo());
				finalOutput.put("gstin", data.getGstin());
				finalOutput.put("panNo", data.getPanNo());

				output.add(finalOutput);

			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), output);
		} catch (Exception e) {
			log.error("getMonthOpeningData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO RetirementListForNextMonth(@Valid SosDateModel payload) {
		try {
			List<Map<String, Object>> output = new ArrayList<>();
			List<EmpMastEntity> datas = empMastRepo.findBySosDAte(Sort.by(Sort.Direction.ASC, "sosDate"),
					payload.getSosDate());
			for (EmpMastEntity data : datas) {
				Map<String, Object> finalOutput = new HashMap<>();

				String desigShortDesc = pmDesigRepo.findDescBasedOnCode(data.getDesig());

				finalOutput.put("empId", data.getEmpId());
				finalOutput.put("name", data.getName());
				finalOutput.put("desig", desigShortDesc);
				finalOutput.put("dtBirth", data.getDtBirth());
				finalOutput.put("sosDate", data.getSosDate());

				output.add(finalOutput);

			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), output);
		} catch (Exception e) {
			log.error("getMonthOpeningData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO PayBillSummary() {
		List<PaybillEntity> datas = paybillRepo.findAll();
		Map<String, Object> finalOutput = new HashMap<>();

		finalOutput.put("basic", datas.stream().mapToDouble(d -> d.getBasic()).sum());
		// finalOutput.put("basic", basicArr.stream().mapToDouble(a -> a).sum());
		finalOutput.put("da", datas.stream().mapToDouble(d -> d.getDa()).sum());
		finalOutput.put("ta", datas.stream().mapToDouble(d -> d.getTa()).sum());
		finalOutput.put("daOnTa", datas.stream().mapToDouble(d -> d.getDaOnTa()).sum());
		finalOutput.put("hra", datas.stream().mapToDouble(d -> d.getHra()).sum());
		finalOutput.put("sPay", datas.stream().mapToDouble(d -> d.getSpay()).sum());
		finalOutput.put("gmcCpf", datas.stream().mapToDouble(d -> d.getGmcCpf()).sum());
		finalOutput.put("gmcArr", datas.stream().mapToDouble(d -> d.getGmcArr()).sum());
		finalOutput.put("gmcRec", datas.stream().mapToDouble(d -> d.getGmcRec()).sum());
		finalOutput.put("gpfSub", datas.stream().mapToDouble(d -> d.getGpfSub()).sum());
		finalOutput.put("gpfRef", datas.stream().mapToDouble(d -> d.getGpfRef()).sum());
		finalOutput.put("cpfInd", datas.stream().mapToDouble(d -> d.getCpfInd()).sum());
		finalOutput.put("cpfArrs", datas.stream().mapToDouble(d -> d.getCpfArrs()).sum());
		finalOutput.put("pliCess", datas.stream().mapToDouble(d -> d.getPliCess()).sum());
		finalOutput.put("cgeis", datas.stream().mapToDouble(d -> d.getCgeis()).sum());
		finalOutput.put("cghs", datas.stream().mapToDouble(d -> d.getCghs()).sum());
		finalOutput.put("iTax", datas.stream().mapToDouble(d -> d.getITax()).sum());
		finalOutput.put("cess", datas.stream().mapToDouble(d -> d.getCess()).sum());
		finalOutput.put("cess2", datas.stream().mapToDouble(d -> d.getCess2()).sum());
		finalOutput.put("hbaInt", datas.stream().mapToDouble(d -> d.getHba()).sum());
		finalOutput.put("carAdvInt", datas.stream().mapToDouble(d -> d.getCarAdvInt()).sum());
		finalOutput.put("vehAdvInt", datas.stream().mapToDouble(d -> d.getVehAdvInt()).sum());
		finalOutput.put("comAdvInt", datas.stream().mapToDouble(d -> d.getCompAdvInt()).sum());
		finalOutput.put("ssLic", datas.stream().mapToDouble(d -> d.getSsLic()).sum());
		finalOutput.put("lpwf", datas.stream().mapToDouble(d -> d.getLpwf()).sum());
		finalOutput.put("ltcRec", datas.stream().mapToDouble(d -> d.getLtcRec()).sum());
		finalOutput.put("medRec", datas.stream().mapToDouble(d -> d.getMedRec()).sum());
		finalOutput.put("taDaRec", datas.stream().mapToDouble(d -> d.getTdaRec()).sum());
		finalOutput.put("penalIn", datas.stream().mapToDouble(d -> d.getPenalIn()).sum());
		finalOutput.put("misRec", datas.stream().mapToDouble(d -> d.getMisRec()).sum());
		return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), finalOutput);

	}

	@Override
	public ResponseDTO GetRegimentalPaybill() {
		try {
			List<RegimentalPaybillEntity> data = regimentalPaybillRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getItax() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getByItax();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while getting Itax");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	
	@Override
	public ResponseDTO getItaxByClass(@Valid ClassModel payload) {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getItaxByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while getting Itax");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}



	@Override
	public ResponseDTO getNps() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getNps();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED, list);
		} catch (Exception e) {
			log.error("getting error while featch npstotal");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getGroupInsurance() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getGroupInsurance();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while getting data from gis in paybillEntity");

		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getMiscRecoverySchedule() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getMiscRecoverySchedule();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while getting miscsch from regPayBillEntity");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getMiscRecoveryScheduleByClass(ClassModel payload) {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getMiscRecoveryScheduleByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while getting miscsch from regPayBillEntity");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	@Override
	public ResponseDTO getEducationLoan() {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getEducationLoan();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while fetching education loan from regimentalPayBillEntity");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getEducationLoanByClass(ClassModel payload) {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getEducationLoanByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while fetching education loan from regimentalPayBillEntity");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	@Override
	public ResponseDTO getIbLoan() {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getIbLoan();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED, list);
		} catch (Exception e) {
			log.error("error while featching Ibloan");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO grtReimbursmentTfee() {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.grtReimbursmentTfee();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED, list);
		} catch (Exception e) {
			log.error("error while featching rtf");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getCgoClubRecovery() {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getCgoClubRecovery();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching cgoClub");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	
	@Override
	public ResponseDTO getCgoClubRecoveryByClass(ClassModel payload) {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getCgoClubRecoveryByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching cgoClub");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	@Override
	public ResponseDTO getMahilaKalyanManch() {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getMahilaKalyanManch();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching mkManch");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getFamilyReliefFund() {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getFamilyReliefFund();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching frfund");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getSSLic() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getSSLic();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching licFeec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getSSLicByClass(ClassModel payload) {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getSSLicByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching licFeec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	
	@Override
	public ResponseDTO getCghsRecovery() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getCghsRecovery();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching cgsh");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	
	@Override
	public ResponseDTO getCghsRecoveryByClass(@Valid ClassModel payload) {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getCghsRecoveryByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching cgsh {}",e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getMiscRecovery() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getMiscRecovery();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getUnionn() {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getUnionn();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getUnionnByClass(ClassModel payload) {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getUnionnByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	@Override
	public ResponseDTO getDromi() {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getDromi();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getDromiByClass(ClassModel payload) {
		try {
			List<RegimentalPaybillEntity> list = regimentalPaybillRepo.getDromiByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	@Override
	public ResponseDTO getPli() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getPli();
//			List<PliEntity> list = paybillRepo.getPli();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	
	@Override
	public ResponseDTO getPliByClass(ClassModel payload) {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getPliByClass(payload.getClasss());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getNonCghsRecovery() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getNonCghsRecovery();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getGis() {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getGis();
//			List<PliEntity> list = paybillRepo.getPli();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getGisByClass(@Valid ClassModel payload) {
		try {
			List<PaybillAndOtherEntity> list = paybillAndOtherRepo.getGisByClass(payload.getClasss());
//			List<PliEntity> list = paybillRepo.getPli();
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("error while featching misRec {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getPunchingMedia() {
		try {
			PunchingMediaModel model = this.paybillAndOtherRepo.getPunchingMedia();
			return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), model);
		}
		catch (Exception e) {
			log.error("error while fetching punching media");
		}
		return new ResponseDTO(StringConstants.fail, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO getDivisonWiseEmployee() {
		List<PmSalHdrEntity> list = pmSalHdrRepo.findAll();
		
		@Data
		class Model {
			 String divison;
			 int count = 0;
			 List<String> names = new ArrayList<>();
		}
		
		
		Map<String, Model> map = new TreeMap<>();
		
		for(PmSalHdrEntity data: list) {
			Sort sort=Sort.by("division");
			EmpMastEntity empdata= empMastRepo.getNameByempId(data.getEmpNo(),sort);
			if (map.containsKey(empdata.getDivision())) {
				Model m = map.get(empdata.getDivision());
				m.count++;
				m.names.add(empdata.getName());
			}
			else {
				Model m = new Model();
				m.divison = empdata.getDivision();
				m.count++;
				m.names.add(empdata.getName());
				map.put(empdata.getDivision(), m);
			}
		}
		
		return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), map.values());
	}

	
}
