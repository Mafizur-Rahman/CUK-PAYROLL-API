package admin.payroll.serviceImpl;

import java.util.ArrayList;
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

import admin.payroll.config.Convertor;
import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.PmBonusMstEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.BonusChangeDataModel;
import admin.payroll.models.PreProcessModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.repo.EmpMastRepo;
import admin.payroll.repo.PmBonusMstReRepo;
import admin.payroll.repo.PmDesigRepo;
import admin.payroll.service.BonusService;
import admin.payroll.utils.StringConstants;

@Service
public class BonusServiceImpl implements BonusService {

	public static final Logger log = LoggerFactory.getLogger(BonusServiceImpl.class);
	@Autowired
	PmBonusMstReRepo pmBonusMstReRepo;
	
	@Autowired
	EmpMastRepo empMastRepo;
	
	@Autowired
	PmDesigRepo pmDesigRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseDTO preBonus(@Valid PreProcessModel payload) {
		try {
			log.debug("calling preBonus");
			pmBonusMstReRepo.preBonus(payload.getSelectGroupUnit(),payload.getFinancialYear(),payload.getAmount());
			return new ResponseDTO(StringConstants.Processed, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("error in preBonus");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO changeData(@Valid BonusChangeDataModel payload) {
		try {
	//	PmBonusMstEntity pmBonusMstData = Convertor.convertToPMBpnusMst(payload);
		PmBonusMstEntity pmBonusMstData = this.modelMapper.map(payload, PmBonusMstEntity.class);
		pmBonusMstData.setLockStatus("Y");
		pmBonusMstReRepo.save(pmBonusMstData);
		
		return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		}
		catch (Exception e) {
			log.error("error in changeData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);

	}

	
	//create by kishan pandey
	
	@Override
	public ResponseDTO getPmBonusMstData() {
		try {
			List<PmBonusMstEntity> list ;
			list= pmBonusMstReRepo.findAll();
			if(!list.isEmpty()) {
				log.debug("data fetched");
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
			}
			return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),null);
		} catch (Exception e) {
			log.error("error getPmBonusMstData");
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO getPmBonusMstDataByEmpId(@Valid BonusChangeDataModel payload) {
		try {
			List<PmBonusMstEntity> list = pmBonusMstReRepo.getPmBonusMstDataByEmpId(payload.getEmployeeId());
			if(!list.isEmpty()) {
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
			}
			return new ResponseDTO(StringConstants.NotFound,APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO bonusReports() {
		try {
			List<PmBonusMstEntity> list = pmBonusMstReRepo.findAll();
			List<Map<String, Object>> list3 = new ArrayList<>();

			if(!list.isEmpty()){	
				
				for(PmBonusMstEntity entity : list) {
					Map<String , Object> hashmap = new HashMap<>();

					hashmap.put("employeeId", entity.getEmployeeId());
					System.out.println(entity.getEmployeeId());
					hashmap.put("name", entity.getName());
					hashmap.put("class", entity.getClasses());
					hashmap.put("desig", pmDesigRepo.findDescBasedOnCode(entity.getDesig()));
					hashmap.put("payRate", entity.getPayRate().toString());
					hashmap.put("bonusAmount", entity.getBonusAmount());
					hashmap.put("financialyear", entity.getFinancialYear());
					list3.add(hashmap);
					
					}
			
			log.debug("data fetched");
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list3);
			}
			else {
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
			}
				
			
		} catch (Exception e) {
			log.error("error in bonusReports{}", e);
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO bonusReportsByClassAndFyr(@Valid BonusChangeDataModel payload) {
		try {
			List<PmBonusMstEntity> list = pmBonusMstReRepo.bonusReportsByClassAndFyr(payload.getClasses(),payload.getFinancialYear());
			List<Map<String, Object>> list3 = new ArrayList<>();

			if(!list.isEmpty()) {
				

				for(PmBonusMstEntity entity : list) {
					Map<String , Object> hashmap = new HashMap<>();

					hashmap.put("employeeId", entity.getEmployeeId());
					hashmap.put("name", entity.getName());
					hashmap.put("class", entity.getClasses());
					hashmap.put("desig", pmDesigRepo.findDescBasedOnCode(entity.getDesig()));
					hashmap.put("payRate", entity.getPayRate().toString());
					hashmap.put("bonusAmount", entity.getBonusAmount());
					hashmap.put("financialyear", entity.getFinancialYear());
					list3.add(hashmap);
					
					}
				
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list3);
			}
			return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			log.error("error in bonusReportsByClassAndFyr");
			System.out.println(e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	// Done by kishan pandey 
	//getting the data form two table empmast and Bonus for bonus report
	@Override
	public ResponseDTO bonusPaymentByClassAndFyr(@Valid BonusChangeDataModel payload) {
		try {
			List<PmBonusMstEntity> list1 = pmBonusMstReRepo.bonusReportsByClassAndFyr(payload.getClasses(),payload.getFinancialYear());
			List<Map<String, Object>> list2 = new ArrayList<>();
			if(!list1.isEmpty()) {
				for(PmBonusMstEntity entity: list1) {
					Map<String , Object> hashmap = new HashMap<>();
					hashmap.put("employeeId", entity.getEmployeeId());
					hashmap.put("name", entity.getName());
					hashmap.put("class", entity.getClasses());
					hashmap.put("desig", pmDesigRepo.findDescBasedOnCode(entity.getDesig()));					
					hashmap.put("bonusAmount", entity.getBonusAmount());
					hashmap.put("financialyear", entity.getFinancialYear());
					List<EmpMastEntity> list3 = empMastRepo.getEMpDesig(entity.getEmployeeId());
					if(!list3.isEmpty()) {
					list3.stream().forEach((c) -> {
						if(c.getBanknoNew()!=null) {
							hashmap.put("accountNo", c.getBanknoNew().toString());
						}else {
							hashmap.put("accountNo", null);
						}
					}
					
					);}else {
					hashmap.put("accountNo",null);
					}
					list2.add(hashmap);
										
				}
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list2);
			}
			return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			log.error("error in bonusPaymentByClassAndFyr ");
			System.out.println(e);
			
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO bonusPayment() {
		try {
			List<PmBonusMstEntity> list1 = pmBonusMstReRepo.findAll();
			List<Map<String, Object>> list2 = new ArrayList<>();
			if(!list1.isEmpty()) {
				for(PmBonusMstEntity entity: list1) {
					Map<String , Object> hashmap = new HashMap<>();
					hashmap.put("employeeId", entity.getEmployeeId());
					hashmap.put("name", entity.getName());
					hashmap.put("class", entity.getClasses());
					hashmap.put("desig", pmDesigRepo.findDescBasedOnCode(entity.getDesig()));					
					hashmap.put("bonusAmount", entity.getBonusAmount());
					hashmap.put("financialyear", entity.getFinancialYear());
					List<EmpMastEntity> list3 = empMastRepo.getEMpDesig(entity.getEmployeeId());
					if(!list3.isEmpty()) {
					list3.stream().forEach((c) -> {
						if(c.getBanknoNew()!=null) {
							hashmap.put("accountNo", c.getBanknoNew().toString());
						}else {
							hashmap.put("accountNo", null);
						}
					}
					
					);}else {
					hashmap.put("accountNo",null);
					}
					list2.add(hashmap);
										
				}
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list2);
			}
			return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			log.error("error in bonusPaymentByClassAndFyr {}",e);
			System.out.println(e);
			
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO checkIfBonusProcessed(BonusChangeDataModel payload) {
		try {
			List<PmBonusMstEntity> list  = this.pmBonusMstReRepo.bonusReportsByClassAndFyr(payload.getClasses(), payload.getFinancialYear());
			return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), !list.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseDTO(StringConstants.fail, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO reverseBonusProcess(BonusChangeDataModel payload) {
		try {
			List<PmBonusMstEntity> bonusData=pmBonusMstReRepo.bonusReportsByClassAndFyr(payload.getClasses(),payload.getFinancialYear());
			if (bonusData.isEmpty()) {
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), true);
			}
			List<PmBonusMstEntity> list = this.pmBonusMstReRepo.checkLockStatus(payload.getClasses(), payload.getFinancialYear());
			if (list.isEmpty()) {
				this.pmBonusMstReRepo.reverseBonusProcess(payload.getClasses(),payload.getFinancialYear());
				return new ResponseDTO(StringConstants.Processed, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), true);		
			}	
			else {
				return new ResponseDTO(StringConstants.success, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseDTO(StringConstants.fail, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
	
	

}
