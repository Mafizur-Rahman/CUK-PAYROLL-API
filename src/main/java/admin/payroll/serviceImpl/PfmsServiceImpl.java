package admin.payroll.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import admin.payroll.entity.PmPfmsTempEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePfmsTempModel;
import admin.payroll.repo.PmPfmsTempRepo;
import admin.payroll.service.PfmsService;
import admin.payroll.utils.StringConstants;

@Service
public class PfmsServiceImpl implements PfmsService {

	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");

	SimpleDateFormat Dateformatter = new SimpleDateFormat("dd-MMM-yyyy");

	public static final Logger log = LoggerFactory.getLogger(PfmsServiceImpl.class);

	@Autowired
	private PmPfmsTempRepo pmPfmsTempRepo;

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
				pmPfmsTempEntity.setLogIp(request.getRemoteAddr());
				try {
					pmPfmsTempEntity.setUploadedDate(Dateformatter.parse(data.getUploadedDate()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.pmPfmsTempRepo.save(pmPfmsTempEntity);
			});
			return new ResponseDTO(StringConstants.Uploaded, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			log.error("saveUnitSetup {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

}
