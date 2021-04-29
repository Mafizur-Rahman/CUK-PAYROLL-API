package admin.payroll.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.payroll.config.Convertor;
import admin.payroll.entity.ITaxDataEntity;
import admin.payroll.entity.ItaxArrearEntity;
import admin.payroll.entity.SumTaxEntity;
import admin.payroll.entity.TaxCalcEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.ItaxArrearModel;
import admin.payroll.models.ItaxDataListModel;
import admin.payroll.models.ItaxDataModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.TaxCalcModel;
import admin.payroll.repo.IncomeTaxDataRepo;
import admin.payroll.repo.ItaxArrearRepo;
import admin.payroll.repo.PmSalDtlRepo;
import admin.payroll.repo.SumTaxRepo;
import admin.payroll.repo.TaxCalcRepo;
import admin.payroll.service.IncomeTaxService;
import admin.payroll.utils.StringConstants;

@Service
public class IncometaxServiceImpl implements IncomeTaxService {

	public static final Logger log = LoggerFactory.getLogger(IncometaxServiceImpl.class);

	@Autowired
	private IncomeTaxDataRepo itaxDataRepo;

	@Autowired
	private TaxCalcRepo taxCalcRepo;

	@Autowired
	private ItaxArrearRepo itaxArrearRepo;

	@Autowired
	private SumTaxRepo sumTaxRepo;
	
	@Autowired
	private PmSalDtlRepo pmSalDtlRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseDTO saveItaxData(@Valid ItaxDataListModel payload) {

		try {	
			Collection<ITaxDataEntity> taxData = new ArrayList<>();
			if (payload.getRowsData() != null) {
				payload.getRowsData().forEach(row -> {
					ITaxDataEntity dataEntity = this.modelMapper.map(row, ITaxDataEntity.class);
					dataEntity.setEmployeeId(payload.getEmployeeId());
					taxData.add(dataEntity);
				});
				this.itaxDataRepo.saveAll(taxData);
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		}
		catch (Exception e) {
			log.debug("saveItaxData {}", e);
		}
		return new ResponseDTO(StringConstants.INVALID_INPUT, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
//		try {
//			// ITaxDataEntity itaxData = Convertor.convertToITaxDataEntity(payload);
//			String empIdList = payload.getEmpId();
//			List mounthIdList = payload.getMounthId();
//			List yearIdList = payload.getYearId();
//			List addincList = payload.getAddinc();
//			List basicList = payload.getBasic();
//			List ceaList = payload.getCea();
//			List CghsCodeList = payload.getCghsCode();
//			List greadPayList = payload.getGradePay();
//			List hbaList = payload.getHba();
//			List HbaAdvIdList = payload.getHbaAdvid();
//			List hdfcList = payload.getHdfc();
//			List insCodeList = payload.getInsCode();
//			List itaxList = payload.getItax();
//			List misCreditList = payload.getMisCredit();
//			List otaList = payload.getOta();
//			List pfsubList = payload.getPfsub();
//			List pliList = payload.getPli();
//			List ppList = payload.getPp();
//			List ptaxcodeList = payload.getPtaxCode();
//			List qrsCodeList = payload.getQrsCode();
//			List regPliList = payload.getRegpli();
//			List spayList = payload.getSpay();
//			List sslicList = payload.getSslic();
//			List sslicpenList = payload.getSslicpen();
//			List taCodeList = payload.getTacode();
//			List varincList = payload.getVarinc();
//			List waMedList = payload.getWaMed();
//
//			for (int i = 0; i <= mounthIdList.size() - 1; i++) {
//				ITaxDataEntity itaxData = new ITaxDataEntity();
//				itaxData.setEmpId(empIdList);
//				itaxData.setBasic((double) ((Integer) (basicList.get(i))));
//				// itaxData.setBasic((Double)basicList.get(i));
//				itaxData.setMounthId((double) ((Integer) (mounthIdList.get(i))));
//				itaxData.setYearId((double) ((Integer) (mounthIdList.get(i))));
//				itaxData.setPfsub((double) ((Integer) (pfsubList.get(i))));
//				itaxData.setTacode((double) ((Integer) (taCodeList.get(i))));
//				itaxData.setItax((double) ((Integer) (itaxList.get(i))));
//				itaxData.setPp((double) ((Integer) (ppList.get(i))));
//
//				itaxData.setMisCredit((double) ((Integer) (misCreditList.get(i))));
//				itaxData.setHba((double) ((Integer) (hbaList.get(i))));
//				itaxData.setHdfc((double) ((Integer) (hdfcList.get(i))));
//				itaxData.setPli((double) ((Integer) (pliList.get(i))));
//				itaxData.setSslic((double) ((Integer) (sslicList.get(i))));
//				itaxData.setInsCode((double) ((Integer) (insCodeList.get(i))));
//				itaxData.setQrsCode((double) ((Integer) (qrsCodeList.get(i))));
//				itaxData.setOta((double) ((Integer) (otaList.get(i))));
//				itaxData.setAddinc((double) ((Integer) (addincList.get(i))));
//				// Double surch=
//				itaxData.setPTaxCode((double) ((Integer) (ptaxcodeList.get(i))));
//				itaxData.setRegpli((double) ((Integer) (regPliList.get(i))));
//				itaxData.setSpay((double) ((Integer) (spayList.get(i))));
//				itaxData.setCea((double) ((Integer) (ceaList.get(i))));
//				itaxData.setSslicpen((double) ((Integer) (sslicpenList.get(i))));
//				itaxData.setHbaAdvid((double) ((Integer) (HbaAdvIdList.get(i))));
//				// Double pfCode=
//				// Double cpfArrs;
//				// String opt;
//				// Double basicNew;
//				itaxData.setGradePay((double) ((Integer) (greadPayList.get(i))));
//				// Double surchNew;
//				// Double kvFee;
//				itaxData.setWaMed((double) ((Integer) (waMedList.get(i))));
//				itaxData.setVarinc((double) ((Integer) varincList.get(i)));
//				itaxData.setCghsCode((double) ((Integer) (CghsCodeList.get(i))));
//				// String levelPay;
//				itaxData.setPfsubGovt((double) ((Integer) (pfsubList.get(i))));
//				itaxDataRepo.save(itaxData);
//			}
//
//			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.OK, null);
//
//		} catch (Exception e) {
//			System.out.println("this is the error" + e);
//			log.error("not save iTaxdata ");
//		}
	
	}

	@Override
	public ResponseDTO itaxMonthlyProcess(@Valid ItaxDataModel payload) {
		try {
			itaxDataRepo.itaxMonthlyProcess(payload.getMounthId(), payload.getYearId());
			return new ResponseDTO(StringConstants.Processed, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.BAD_REQUEST, null);
	}

	@Override
	public ResponseDTO taxcalempidsixth(@Valid TaxCalcModel payload) {
		try {
			taxCalcRepo.taxcalempidsixth(payload.getEmployeeId());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			log.error("taxcalempidsixth producer not call");
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.BAD_REQUEST, null);
	}

	@Override
	@Transactional
	public ResponseDTO saveTaxCalc(@Valid TaxCalcModel payload) {
		try {
//			TaxCalcEntity taxCalc = Convertor.convertToTaxCalcEntity(payload);
			TaxCalcEntity taxCalc = this.modelMapper.map(payload, TaxCalcEntity.class);
			
			ItaxArrearEntity taxArrear = this.modelMapper.map(payload, ItaxArrearEntity.class);
			taxArrear.setEmpId(payload.getEmployeeId());
			taxArrear.setAddinCarr(payload.getAddinCarr());
			taxArrear.setAddinCarrPua(payload.getAddinCarrPua());
			taxArrear.setToTarrs(payload.getToTarrs());
			
			SumTaxEntity sumTaxEntity = this.modelMapper.map(payload, SumTaxEntity.class);
			
			this.taxCalcRepo.save(taxCalc);
			this.itaxArrearRepo.save(taxArrear);
			this.sumTaxRepo.save(sumTaxEntity);
			
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("not save saveTaxCalc");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);

	}

	@Override
	public ResponseDTO saveItaxArrear(@Valid ItaxArrearModel payload) {
		try {
			ItaxArrearEntity iTaxArrearData = Convertor.convertToItaxArrearEntity(payload);
			itaxArrearRepo.save(iTaxArrearData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
			log.error("not saveItaxData");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);

	}

	@Override
	public ResponseDTO getItaxDataByEmpId(@Valid ItaxArrearModel payload) {
		try {
			log.debug("impl getItaxDataByEmpId");
			List<ITaxDataEntity> listItaxData = itaxDataRepo.getItaxData(payload.getEmpId());
			System.out.println(listItaxData);
			List<ItaxArrearEntity> listItaxArrear = itaxArrearRepo.getItaxArrearByEmpId(payload.getEmpId());
			if (!(listItaxData.isEmpty() && listItaxArrear.isEmpty())) {
				Map<String, Object> map = new HashMap<>();
				map.put("listItaxData", listItaxData);
				map.put("listItaxArrear", listItaxArrear);
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), map);
			}
			return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public String checkMonthandYearDataPresentInSalaryDetail(@Valid ItaxDataModel payload) {
		try {
			String month = payload.getMounthId().toString();
			String year = payload.getYearId().toString();
			String payperiod = null;
			String month2 = month.substring(0, 2);
			String year2 = year.substring(0, 4);
			if (!(month2.isEmpty() && year2.isEmpty())) {
				payperiod = year2 + month2;
				System.out.println(payperiod);
			}
			String payperiod2 = pmSalDtlRepo.checkMonthandYearDataPresentInSalaryDetail(payperiod);
			if (!payperiod2.isEmpty()) {
				return payperiod2;
			}
			return payperiod;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public ResponseDTO getTaxCalcByEmployeeId(EmployeeID employeeId) {
		try {
			TaxCalcModel taxCalcModel = this.taxCalcRepo.getTaxCalcByEmployeeId(employeeId.getEmployeeId());
			//TaxCalcEntity taxCalcEntity = this.taxCalcRepo.getTaxCalcByEmployeeId(employeeId.getEmployeeId());
			return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), taxCalcModel);
		}
		catch (Exception e) {
			log.debug("getTaxCalcByEmployeeId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}
}
