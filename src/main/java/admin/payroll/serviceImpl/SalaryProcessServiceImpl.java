package admin.payroll.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.PmEedEntity;
import admin.payroll.entity.PmSalHdrEntity;
import admin.payroll.entity.PmSysMasterEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.GetMonthOpeningModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SalaryProcessModel;
import admin.payroll.models.SaveCloseMonthModel;
import admin.payroll.models.SaveMonthOpeningModel;
import admin.payroll.repo.CarRepository;
import admin.payroll.repo.EmpMastRepo;
import admin.payroll.repo.PmSalHdrRepo;
import admin.payroll.repo.PmSysMasterRepo;
import admin.payroll.service.SalaryProcessService;
import admin.payroll.utils.StringConstants;

@Service
public class SalaryProcessServiceImpl implements SalaryProcessService {
	
	private static final Logger log = LoggerFactory.getLogger(SalaryProcessServiceImpl.class);

	@Autowired
	PmSysMasterRepo pmSysMasterRepo;

	@Autowired
	EntityManager em;

	@Autowired
	EmpMastRepo empMastRepo;

	@Autowired
	CarRepository carRepository;
	
	@Autowired
	PmSalHdrRepo pmSalHdrRepo;

	@Override
	public ResponseDTO salaryProcess1(@Valid SalaryProcessModel payload) {
		try {
			pmSysMasterRepo.sp_salaryprocess1(payload.getGroupUnit(), payload.getPayPeriod());
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
	}

	@Override
	public ResponseDTO salaryProcess2(@Valid SalaryProcessModel payload) {
		try {
			pmSysMasterRepo.sp_salaryprocess2(payload.getGroupUnit(), payload.getPayPeriod());

		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
	}

	@Override
	public ResponseDTO salaryProcess3(@Valid SalaryProcessModel payload) {
		try {
			pmSysMasterRepo.sp_salaryprocess3(payload.getGroupUnit(), payload.getPayPeriod());

		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
	}

	@Override
	public ResponseDTO saveMonthOpening(@Valid SaveMonthOpeningModel payload) {
		PmSysMasterEntity datas = new PmSysMasterEntity();
		try {
			SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = (Date) formatter3.parse(payload.getFromDate().toString());
			Date toDate = (Date) formatter3.parse(payload.getToDate().toString());
			datas.setGroupUnit(payload.getGroupUnit());
			datas.setBillUnit(payload.getBillUnit());
			datas.setFromDate(fromDate);
			datas.setToDate(toDate);
			datas.setPayCalPeriod(payload.getPayPeriod());
			datas.setWagePeriod(payload.getWagePeriod());
			datas.setPaycommission(payload.getPayComission());
			datas.setMStatus(payload.getStatus());
			datas.setMBackup(payload.getMonthlyBackup());
			datas.setLOGDATE(new Date());
			datas.setLOGIP(payload.getLogIp());
			datas.setLOGUSER(payload.getLogUser());
			pmSysMasterRepo.save(datas);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	@Override
	public ResponseDTO getMonthOpening(@Valid GetMonthOpeningModel payload) {

		try {
			List<PmSysMasterEntity> datas = pmSysMasterRepo.findByBillUnit(payload.getBillUnit());
			List<Map<String, Object>> list = new ArrayList<>();
			for (PmSysMasterEntity data : datas) {
				Map<String, Object> map = new HashMap<>();
				String fromDate = data.getFromDate().toString().substring(0, 10);
				String toDate = data.getToDate().toString().substring(0, 10);

				map.put("billUnit", data.getBillUnit());
				map.put("fromDate", fromDate);
				map.put("groupUnit", data.getGroupUnit());
				map.put("mBackup", data.getMBackup());
				map.put("mStatus", data.getMStatus());
				map.put("pCalPeriod", data.getPayCalPeriod());
				map.put("patCommission", data.getPaycommission());
				map.put("toDate", toDate);
				map.put("wagePerios", data.getWagePeriod());
				list.add(map);
			}
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	@Override
	public ResponseDTO saveMonthclosing(@Valid SalaryProcessModel payload) {
		pmSysMasterRepo.pm_mnth_closing(payload.getGroupUnit(), payload.getPayPeriod());
		return new ResponseDTO(StringConstants.Processed, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
	}

	@Override
	public ResponseDTO reverseSalary(@Valid SalaryProcessModel payload) {
		pmSysMasterRepo.pm_sal_reverse(payload.getGroupUnit(), payload.getPayPeriod());
		return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
	}

	@Override
	public ResponseDTO checkSalaryProcess(@Valid SalaryProcessModel payload) {
		try {
			boolean data = false ;
			Optional<PmSalHdrEntity> datas =pmSalHdrRepo.findByGroupUnitAndPayperiod(payload.getGroupUnit(),payload.getPayPeriod());
			if (datas.isPresent()) {
				data = true;
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("checkSalaryProcess {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getMonthOpeningData() {
		try {
			List<PmSysMasterEntity> datas=pmSysMasterRepo.findAll();
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), datas);
		}		
		catch (Exception e) {
			log.error("getMonthOpeningData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

}