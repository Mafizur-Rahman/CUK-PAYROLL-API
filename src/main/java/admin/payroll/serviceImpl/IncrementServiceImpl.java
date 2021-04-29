package admin.payroll.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.payroll.entity.IncrementMainEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.DniModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.repo.IncrementMainRepo;
import admin.payroll.service.IncrementService;
import admin.payroll.utils.StringConstants;
//import jdk.internal.org.jline.utils.Log;

@Service
public class IncrementServiceImpl implements IncrementService{

	@Autowired
	private IncrementMainRepo incrementmainRepo;
	
	public static final Logger log = LoggerFactory.getLogger(IncrementServiceImpl.class);
	@Override
	public ResponseDTO annualIncrementProducer(@Valid DniModel payload) {
		try {
			//Date date = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    String strDate= formatter.format(payload.getDni());
			incrementmainRepo.incrementProcess1(strDate);
			System.out.println(" yes i am here");
			return new ResponseDTO(StringConstants.Processed, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.NOT_ACCEPTABLE, null);
	}

	@Override
	public ResponseDTO getAllIncrementMain() {
		try {
			
		List<IncrementMainEntity> list = incrementmainRepo.findAll();
		if(!list.isEmpty()) {
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED, list);
		}
		return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
			
		} catch (Exception e) {
			log.error("getting error getAllIncre");
		}
		
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO getIncrementMainByClass(@Valid IncrementMainEntity payload) {
		try {
			List<IncrementMainEntity> list ;
			list= incrementmainRepo.getIncrementMainByClass(payload.getClasses());
			if(!list.isEmpty()) {
				log.debug("getIncrementMainByClass");
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
			}
			return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED, null);
		} catch (Exception e) {
			log.error("errro getIncrementMainByClass");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,null);
	}

}
