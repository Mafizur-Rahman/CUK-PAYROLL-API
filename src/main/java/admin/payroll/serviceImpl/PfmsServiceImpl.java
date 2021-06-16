package admin.payroll.serviceImpl;

import static admin.payroll.utils.DateTimeUtility.getCurrentDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import admin.payroll.entity.PmPfmsEntity;
import admin.payroll.entity.PmPfmsTempEntity;
import admin.payroll.entity.PmSalHdrEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePfmsTempModel;
import admin.payroll.repo.PmPfmsRepo;
import admin.payroll.repo.PmPfmsTempRepo;
import admin.payroll.repo.PmSalHdrRepo;
import admin.payroll.service.PfmsService;
import admin.payroll.utils.StringConstants;

@Service
public class PfmsServiceImpl implements PfmsService {

	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");

	SimpleDateFormat Dateformatter = new SimpleDateFormat("dd-MMM-yyyy");

	public static final Logger log = LoggerFactory.getLogger(PfmsServiceImpl.class);

	@Autowired
	private PmPfmsTempRepo pmPfmsTempRepo;

	@Autowired
	private PmPfmsRepo pmPfmsRepo;

	@Autowired
	private PmSalHdrRepo pmSalHdrRepo;

	@Override
	public ResponseDTO uploadDbt01ToTemp(@Valid SavePfmsTempModel payload) {
		try {
			pmPfmsTempRepo.deleteAll();

			payload.getDbt01Data().remove(0);
			payload.getDbt01Data().forEach(data -> {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();

				PmPfmsTempEntity pmPfmsTempEntity = new PmPfmsTempEntity();
				pmPfmsTempEntity.setAadharNumber(data.getAadharNumber());
				pmPfmsTempEntity.setAccountNumber(data.getAccountNumber());
				pmPfmsTempEntity.setAccountStatus(data.getAccountStatus());
				pmPfmsTempEntity.setAddressLine1(data.getAddressLine1());
				pmPfmsTempEntity.setAddressLine2(data.getAddressLine2());
				pmPfmsTempEntity.setAddressLine3(data.getAddressLine3());
				pmPfmsTempEntity.setBankName(data.getBankName());
				pmPfmsTempEntity.setCpsmsCode(data.getCpsmsCode());
				pmPfmsTempEntity.setCasteCategory(data.getCasteCategory());
				pmPfmsTempEntity.setCountry(data.getCountry());
				pmPfmsTempEntity.setDistrict(data.getDistrict());
				pmPfmsTempEntity.setFinancialYear(data.getFinancialYear());
				pmPfmsTempEntity.setGender(data.getGender());
				pmPfmsTempEntity.setIfscCode(data.getIfscCode());
				pmPfmsTempEntity.setName(data.getName());
				pmPfmsTempEntity.setNameReg(data.getNameReg());
				pmPfmsTempEntity.setPinCode(data.getPinCode());
				pmPfmsTempEntity.setQuarter(data.getQuarter());
				pmPfmsTempEntity.setSchemeCode(data.getSchemeCode());
				pmPfmsTempEntity.setState(data.getState());
				pmPfmsTempEntity.setSubventionAmount(data.getSubventionAmount());
				pmPfmsTempEntity.setUploadedBy(data.getUploadedBy());
				pmPfmsTempEntity.setLogUser(data.getLogUser());
				pmPfmsTempEntity.setLogIp(request.getRemoteAddr());

				try {
					Date date1 = new SimpleDateFormat("dd-mm-yyyy").parse(data.getUploadedDate());
					pmPfmsTempEntity.setUploadedDate(date1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//				try {
//					pmPfmsTempEntity.setUploadedDate(Dateformatter.parse(data.getUploadedDate()));
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
				this.pmPfmsTempRepo.save(pmPfmsTempEntity);
			});
			return new ResponseDTO(StringConstants.Uploaded, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			log.error("saveUnitSetup {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO validateDbt01() {
		try {
			pmPfmsRepo.deleteAll();
			List<PmPfmsTempEntity> dbt01TempData = pmPfmsTempRepo.findAll();
			dbt01TempData.forEach(data -> {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();
				PmPfmsEntity pmPfmsEntity = new PmPfmsEntity();
				pmPfmsEntity.setAadharNumber(data.getAadharNumber());
				pmPfmsEntity.setAccountNumber(data.getAccountNumber());
				pmPfmsEntity.setAccountStatus(data.getAccountStatus());
				pmPfmsEntity.setAddressLine1(data.getAddressLine1());
				pmPfmsEntity.setAddressLine2(data.getAddressLine2());
				pmPfmsEntity.setAddressLine3(data.getAddressLine3());
				pmPfmsEntity.setBankName(data.getBankName());
				pmPfmsEntity.setCasteCategory(data.getCasteCategory());
				pmPfmsEntity.setCountry(data.getCountry());
				pmPfmsEntity.setCpsmsCode(data.getCpsmsCode());
				pmPfmsEntity.setDistrict(data.getDistrict());
				pmPfmsEntity.setGender(data.getGender());
				pmPfmsEntity.setIfscCode(data.getIfscCode());
				pmPfmsEntity.setIsValidatedByPfms("Y");
				pmPfmsEntity.setLogIp(request.getRemoteAddr());
				pmPfmsEntity.setName(data.getName());
				pmPfmsEntity.setNameReg(data.getNameReg());
				pmPfmsEntity.setPinCode(data.getPinCode());
				pmPfmsEntity.setSchemeCode(data.getSchemeCode());
				pmPfmsEntity.setState(data.getState());
				pmPfmsEntity.setSubventionAmount(data.getSubventionAmount());
				pmPfmsEntity.setUploadedBy(data.getUploadedBy());
				pmPfmsEntity.setUploadedDate(data.getUploadedDate());
				pmPfmsEntity.setValidationDate(getCurrentDate());
				pmPfmsEntity.setVuId(data.getSchemeCode());
				pmPfmsEntity.setLogUser(data.getLogUser());
				pmPfmsEntity.setVpuId(null);
				pmPfmsRepo.save(pmPfmsEntity);

				List<PmSalHdrEntity> pmSalHdrEntity = pmSalHdrRepo.findAll();
			});
			return new ResponseDTO(StringConstants.UPDATED, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

}
