package admin.payroll.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import admin.payroll.config.Convertor;
import admin.payroll.entity.AddressEntity;
import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.KinEntity;
import admin.payroll.entity.PmDeptEntity;
import admin.payroll.entity.PmEmpDatesEntity;
import admin.payroll.entity.PmEmpDocsEntity;
import admin.payroll.entity.PmGecEntity;
import admin.payroll.entity.PmKinFeeEntity;

import admin.payroll.entity.PmQualificationEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.CodeModel;
import admin.payroll.models.DocumentPathModel;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.EmployeeProfileModel;
import admin.payroll.models.GetKinModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SaveDataMasterModel;
import admin.payroll.models.SaveDocumentModel;
import admin.payroll.models.SaveEmployeeAddressModel;
import admin.payroll.models.SaveEmployeeDatasModel;
import admin.payroll.models.SaveEmployeeQualificationModel;
import admin.payroll.models.SaveKINFeeMasterModel;
import admin.payroll.models.SaveKINMasterModel;
import admin.payroll.models.SavePmPayMasterModel;
import admin.payroll.repo.AddressRepo;
import admin.payroll.repo.EmpMastRepo;
import admin.payroll.repo.KinRepo;
import admin.payroll.repo.PmBankRepo;
import admin.payroll.repo.PmDeptEntityRepo;
import admin.payroll.repo.PmDesigRepo;
import admin.payroll.repo.PmEmpDatesRepo;
import admin.payroll.repo.PmEmpDocsRepo;
import admin.payroll.repo.PmGecRepo;
import admin.payroll.repo.PmKinFeeRepo;
import admin.payroll.repo.PmPayMasterRepo;
import admin.payroll.repo.PmPayMatrixRepo;
import admin.payroll.repo.PmQualificationRepo;
import admin.payroll.service.CommonService;
import admin.payroll.service.EmployeeManagementService;
import admin.payroll.utils.StringConstants;

@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeManagementServiceImpl.class);

	@Autowired
	KinRepo kinRepo;

	@Autowired
	EmpMastRepo empMastRepo;

	@Autowired
	AddressRepo addressRepo;
	
	@Autowired
	PmKinFeeRepo pmKinFeeRepo;

	@Autowired
	PmEmpDocsRepo pmEmpDocsRepo;

	@Autowired
	PmEmpDatesRepo pmEmpDatesRepo;

	@Autowired
	CommonService commonService;

	@Autowired
	PmGecRepo pmGecRepo;

	@Autowired
	PmBankRepo pmBankRepo;

	@Autowired
	PmQualificationRepo pmQualificationRepo;

	@Autowired
	PmPayMatrixRepo pmPayMatrixRepo;

	@Autowired
	PmDesigRepo pmDesigRepo;

	@Autowired
	PmDeptEntityRepo pmDeptEntityRepo;

	@Autowired
	private ModelMapper modelMapper;

	// controller(Api hiting )->service->ServiceImpl(Bus)(Utiils)->Rep(ata)

	@Override
	public ResponseDTO saveEmployeeData(@Valid SaveEmployeeDatasModel payload) {
		try {
			///EmpMastEntity pmEedMasterData = Convertor.convertToSaveEmployee(payload);
			EmpMastEntity pmEedMasterData = this.modelMapper.map(payload, EmpMastEntity.class);
			empMastRepo.save(pmEedMasterData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveEmployeeData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}


	@Override
	public ResponseDTO saveAddress(@Valid SaveEmployeeAddressModel payload) {
		try {
			AddressEntity employeeData = Convertor.convertToSaveAddress(payload);
			AddressEntity a = addressRepo.save(employeeData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);

		} catch (Exception e) {
			log.error("saveAddress {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO saveQualification(@Valid SaveEmployeeQualificationModel payload) {
		try {
			PmQualificationEntity employeeQualificationData = Convertor.convertToSaveQualification(payload);
			pmQualificationRepo.save(employeeQualificationData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveQualification {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveKinMaster(@Valid SaveKINMasterModel payload) {
		try {
			//KinEntity kinData = Convertor.convertToSaveKinMaster(payload);
			KinEntity kinData = this.modelMapper.map(payload, KinEntity.class);
			kinRepo.save(kinData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveKinMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveKinFeeMaster(@Valid SaveKINFeeMasterModel payload) {
		try {
			PmKinFeeEntity kinFeeData = Convertor.convertToSaveKinFeeMaster(payload);
			pmKinFeeRepo.save(kinFeeData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveKinFeeMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveDataMaster(@Valid SaveDataMasterModel payload) {
		try {
			PmEmpDatesEntity dataMasterData = Convertor.convertToSaveDataMaster(payload);
			pmEmpDatesRepo.save(dataMasterData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("saveDataMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAllEmployeeIds() {
		try {
			// List<String> empmast = empMastRepo.getAllEmployee();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("getAllEmployeeIds {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getEmployeeDatas(@Valid EmployeeProfileModel payload) {
		try {
			List<EmpMastEntity> empmast = empMastRepo.getEmployeeDatas(payload.getEmployeeid(),
					payload.getEmployeeName());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					empmast);
		} catch (Exception e) {
			log.error("getEmployeeDatas {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getPayMatrixLevel() {
		try {
			List<String> data = pmPayMatrixRepo.getPayMatrixLevel();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getPayMatrixLevel {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getPayMatrixCell() {
		try {
			List<String> data = pmPayMatrixRepo.getPayMatrixCell();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getPayMatrixCell {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getEmployeeName(String empId) {
		try {
			String name = empMastRepo.FindEmpName(empId);
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), name);
		} catch (Exception e) {
			log.error("getEmployeeName {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteEmployee(String employeeId) {
		try {
			empMastRepo.deleteById(employeeId);
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteEmployeeName {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO checkDuplicateEmployeeName(String employeeId) {

		try {
			boolean data = false;
			Optional<EmpMastEntity> datas = empMastRepo.findById(employeeId);
			if (datas.isPresent()) {
				data = true;
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("checkDuplicateEmployeeName {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

////Afdddress

	@Override
	public ResponseDTO deleteQualification(String id) {
		try {
			pmQualificationRepo.deleteById(Integer.parseInt(id));
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteQualification {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

//	@Override
//	public ResponseDTO getAllEmployee() {
//		try {
//			List<EmpMastEntity> data = empMastRepo.findAll();
//			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
//		} catch (Exception e) {
//			log.error("getAllEmployee {}", e);
//		}
//		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
//				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
//	}

	@Override
	public ResponseDTO getAllEmployee() {
		try {
			List<Map<String, Object>> list = new ArrayList<>();
			List<EmpMastEntity> datas = empMastRepo.findAll();
			for (EmpMastEntity empData : datas) {
				Map<String, Object> finalOutput = new HashMap<>();
//				DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//				Date DtBirth = empData.getDtBirth();
//				Date DtApt = empData.getDtAppt();
//				Date SosDate = empData.getSosDate();
//				LocalDate dtBirth = Instant.ofEpochMilli(DtBirth.getTime()).atZone(ZoneId.systemDefault())
//						.toLocalDate();
//				LocalDate dtAppt = Instant.ofEpochMilli(DtApt.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
//				LocalDate sosDate = Instant.ofEpochMilli(SosDate.getTime()).atZone(ZoneId.systemDefault())
//						.toLocalDate();
//				String dateString = FOMATTER.format(dtBirth);
//				String apptString = FOMATTER.format(dtAppt);
//				String sosDateString = FOMATTER.format(sosDate);
				String desigShortDesc = pmDesigRepo.findDescBasedOnCode(empData.getDesig());
				String classShortDesc = pmGecRepo.findDescBasedOnCode(empData.getClasss());
				Long Category = empData.getCatId();
				String category = String.valueOf(Category);
				String categoryShortDesc = pmGecRepo.findCategoryBasedOnCode(category);
				finalOutput.put("dtBirth", empData.getDtBirth());
				finalOutput.put("sosDate", empData.getSosDate());
				finalOutput.put("desig", desigShortDesc);
				finalOutput.put("classs", classShortDesc);
				finalOutput.put("catId", categoryShortDesc);
				finalOutput.put("dtAppt", empData.getDtAppt());
				finalOutput.put("name", empData.getName());
				finalOutput.put("empId", empData.getEmpId());
				list.add(finalOutput);

			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("getAddressData {}", e);
		}
		return null;

	}

	@Override
	public ResponseDTO deleteKinMaster(@Valid CodeModel payload) {
		try {
			// kinRepo.deleteById(id);
			kinRepo.deleteKinData(payload.getCode(), payload.getId());
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteKinMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteKinFeeMaster(int id) {
		try {
			pmKinFeeRepo.deleteById(id);
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteKinFeeMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteDataMaster(int id) {
		try {
			pmEmpDatesRepo.deleteById(id);
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteDataMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAllDivisionDepartment() {
		try {
			List<PmDeptEntity> data = pmDeptEntityRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getAllDivisionDepartment {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAllKinData() {
		try {
			List<KinEntity> data = kinRepo.findAll();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getAllKinData {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAddress(@Valid EmployeeProfileModel payload) {
		try {
			List<Map<String, Object>> list = new ArrayList();
			Map<String, Object> finalOutput = new HashMap<>();
			if (payload.getEmployeeid() != null) {
				String empName = empMastRepo.FindEmpName(payload.getEmployeeid());
				finalOutput = getAddressData(payload.getEmployeeid(), empName);
				if (!finalOutput.isEmpty())
					list.add(finalOutput);
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						list);
			} else if (payload.getEmployeeid() == null && payload.getEmployeeName() != null) {
				List<EmpMastEntity> empIds = empMastRepo.FindAllEmpIds(payload.getEmployeeName());
				for (EmpMastEntity empData : empIds) {
					Map<String, Object> finalOutput1 = new HashMap<>();
					finalOutput1 = getAddressData(empData.getEmpId(), empData.getName());
					list.add(finalOutput1);
				}
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						list);

			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);

		} catch (Exception e) {
			log.error("getAddress {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	private Map<String, Object> getAddressData(String empId, String name) {
		try {
			Map<String, Object> finalOutput = new HashMap<>();
			Optional<AddressEntity> datas = addressRepo.findByEmployeeId(empId);
			if (datas.isPresent()) {
				String addressData = datas.get().getCaddrss() + " " + datas.get().getCaddrss1() + " "
						+ datas.get().getCaddrss2() + " " + datas.get().getCaddrss3();
				finalOutput.put("address", addressData);
				finalOutput.put("empName", name);
				finalOutput.put("empId", datas.get().getEmpId());
			}
			return finalOutput;
		} catch (Exception e) {
			log.error("getAddressData {}", e);
		}
		return null;

	}

	@Override
	public ResponseDTO getQualification(@Valid EmployeeProfileModel payload) {
		try {
			List<Map<String, Object>> finalOutput = new ArrayList<>();
			if (payload.getEmployeeid() != null) {
				String empName = empMastRepo.FindEmpName(payload.getEmployeeid());
				finalOutput = getQualificationData(payload.getEmployeeid(), empName);
			} else if (payload.getEmployeeid() == null && payload.getEmployeeName() != null) {
				List<EmpMastEntity> empIds = empMastRepo.FindAllEmpIds(payload.getEmployeeName());
				for (EmpMastEntity empData : empIds) {
					finalOutput = getQualificationData(empData.getEmpId(), empData.getName());
				}
			}
			if (finalOutput.size() > 0) {
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						finalOutput);
			} else
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						finalOutput);

		} catch (Exception e) {
			log.error("getQualification {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	private List<Map<String, Object>> getQualificationData(String empId, String name) {
		try {
			List<Map<String, Object>> output = new ArrayList<>();
			List<PmQualificationEntity> datas = pmQualificationRepo.findByEmployeeId(empId);
			for (PmQualificationEntity qual : datas) {
				List<PmGecEntity> qualification = (List<PmGecEntity>) commonService
						.getDataBasedOnCode(qual.getQualCode(), "QC");
				for (PmGecEntity data : qualification) {
					Map<String, Object> finalOutput = new HashMap<>();
					finalOutput.put("qualification", data.getShortDesc());
					finalOutput.put("Year", qual.getPassingYear());
					finalOutput.put("university", qual.getUniversityName());
					finalOutput.put("sNo", qual.getId());
					finalOutput.put("grade", qual.getGradeMarks());
					finalOutput.put("qualificationAcquired", qual.getQualificationAcquired());
					output.add(finalOutput);

				}
			}
			return output;

		} catch (Exception e) {
			log.error("getQualificationData {}", e);
		}
		return null;

	}

	@Override
	public ResponseDTO getKin(@Valid EmployeeProfileModel payload) {
		try {
			List<Map<String, Object>> finalOutput = new ArrayList<>();
			if (payload.getEmployeeid() != null) {
				String empName = empMastRepo.FindEmpName(payload.getEmployeeid());
				finalOutput = getKinData(payload.getEmployeeid(), empName);
			} else if (payload.getEmployeeName() != null) {
				List<EmpMastEntity> empIds = empMastRepo.FindAllEmpIds(payload.getEmployeeName());
				for (EmpMastEntity empData : empIds) {
					finalOutput = getKinData(empData.getEmpId(), empData.getName());

				}

			}
			if (finalOutput.size() > 0)
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						finalOutput);
			else
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						finalOutput);

		} catch (Exception e) {
			log.error("getKin {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	private List<Map<String, Object>> getKinData(String empId, String name) {
		try {
			List<Map<String, Object>> finalList = new ArrayList<>();
			List<KinEntity> datas = kinRepo.findByEmpId(empId);
			for (KinEntity data : datas) {

				// relation description data already available in KIN table. We 
				// don't need to fetch from general code.
//				List<PmGecEntity> relation = (List<PmGecEntity>) commonService.getDataBasedOnCode(data.getReln(),
//						"REL");
			//	for (PmGecEntity data2 : relation) {
//					Map<String, Object> finalOutput = new HashMap<>();
					Map<String, Object> finalOutput = new HashMap<>();
//				finalOutput.put("id", data.getId());
					finalOutput.put("KinName", data.getName());
					finalOutput.put("gender", data.getSex());
					finalOutput.put("relation", data.getReln());
					finalOutput.put("dateOfBirth", data.getBDate());
					finalOutput.put("serlNo", data.getSerlNo());

//			finalOutput.put("id", "test");
//			finalOutput.put("KinName", "test");
//			finalOutput.put("gender", "test");
//			finalOutput.put("relation", "test");
//			finalOutput.put("dateOfBirth", "test");

					finalList.add(finalOutput);
				}
		//	}
			return finalList;

		} catch (Exception e) {
			log.error("getKinData {}", e);
		}
		return null;
	}

	@Override
	public ResponseDTO getKinMaster(@Valid EmployeeProfileModel payload) {
		try {
			List<Map<String, Object>> finalOutput = new ArrayList<>();
			if (payload.getEmployeeid() != null) {
				String empName = empMastRepo.FindEmpName(payload.getEmployeeid());
				finalOutput = getKinMasterData(payload.getEmployeeid(), empName);
			} else if (payload.getEmployeeid() == null && payload.getEmployeeName() != null) {
				List<EmpMastEntity> empIds = empMastRepo.FindAllEmpIds(payload.getEmployeeName());
				for (EmpMastEntity empData : empIds) {
					finalOutput = getKinMasterData(empData.getEmpId(), empData.getName());

				}

			}
			if (!finalOutput.isEmpty())
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					finalOutput);
			else
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						finalOutput);

		} catch (Exception e) {
			log.error("getKinMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	private List<Map<String, Object>> getKinMasterData(String employeeid, String empName) {
		try {
			List<Map<String, Object>> finalOutput1 = new ArrayList<>();
			List<PmKinFeeEntity> datas1 = pmKinFeeRepo.findByEmpId(employeeid);
			for (PmKinFeeEntity datas : datas1) {
				List<KinEntity> kinData1 = kinRepo.findByEmpIdAndSerl(datas.getEmpId(), datas.getKinId());
				for (KinEntity kinData : kinData1) {
					// System.out.println(kinData);
					String codeType = "FEET";
					String feeTypeShortDesc = pmGecRepo.findByCodeTypeandCode1(datas.getFeeType(), codeType);

					Map<String, Object> finalOutput = new HashMap<>();
					finalOutput.put("id", datas.getId());
					finalOutput.put("serlNo", datas.getKinId());
					finalOutput.put("KinName", kinData.getName());
					finalOutput.put("gender", kinData.getSex());
					finalOutput.put("feeType", feeTypeShortDesc);
					finalOutput.put("amount", datas.getFeeAmt());
					finalOutput1.add(finalOutput);

				}
			}
			return finalOutput1;

		} catch (Exception e) {
			log.error("getKinMasterData {}", e);
		}
		return null;
	}

	@Override
	public ResponseDTO getDataMaster(@Valid EmployeeProfileModel payload) {
		try {
			List<Map<String, Object>> finalOutput = new ArrayList();
			if (payload.getEmployeeid() != null) {
				String empName = empMastRepo.FindEmpName(payload.getEmployeeid());
				finalOutput = getDataMaster(payload.getEmployeeid(), empName);
			} else if (payload.getEmployeeid() == null && payload.getEmployeeName() != null) {
				List<EmpMastEntity> empIds = empMastRepo.FindAllEmpIds(payload.getEmployeeName());
				for (EmpMastEntity empData : empIds) {
					finalOutput = getDataMaster(empData.getEmpId(), empData.getName());
				}
			}
			if (!finalOutput.isEmpty())
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					finalOutput);
			else
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						finalOutput);

		} catch (Exception e) {
			log.error("getDataMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	private List<Map<String, Object>> getDataMaster(String employeeid, String empName) {
		try {
			List<Map<String, Object>> finalOutput1 = new ArrayList<>();
			List<PmEmpDatesEntity> datas1 = pmEmpDatesRepo.findByEmpId(employeeid);
			for (PmEmpDatesEntity datas : datas1) {
				List<PmGecEntity> dates = (List<PmGecEntity>) commonService
						.getDataBasedOnCode(datas.getDateType(), "DT");
				for (PmGecEntity data : dates) {
				Map<String, Object> finalOutput = new HashMap<>();
				finalOutput.put("id", datas.getId());
				finalOutput.put("dateType", data.getShortDesc());
				finalOutput.put("date", datas.getEmpDate());
				finalOutput1.add(finalOutput);
			}
			}
			return finalOutput1;

		} catch (Exception e) {
			log.error("getDataMaster {}", e);
		}
		return null;
	}

	@Override
	public ResponseDTO getDocumentMaster(@Valid EmployeeProfileModel payload) {
		try {
			List<Map<String, Object>> finalOutput = new ArrayList<>();
			if (payload.getEmployeeid() != null) {
				String empName = empMastRepo.FindEmpName(payload.getEmployeeid());
				finalOutput = getDocumentMasterData(payload.getEmployeeid(), empName);
			} else if (payload.getEmployeeid() == null && payload.getEmployeeName() != null) {
				List<EmpMastEntity> empIds = empMastRepo.FindAllEmpIds(payload.getEmployeeName());
				for (EmpMastEntity empData : empIds) {
					finalOutput = getDocumentMasterData(empData.getEmpId(), empData.getName());

				}

			}
			if (!finalOutput.isEmpty())
				return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					finalOutput);
			else
				return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						finalOutput);

		} catch (Exception e) {
			log.error("getDocumentMaster {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	private List<Map<String, Object>> getDocumentMasterData(String employeeid, String empName) {
		try {
			List<Map<String, Object>> list = new ArrayList<>();

			List<PmEmpDocsEntity> datas = pmEmpDocsRepo.findDataByEmpId(employeeid);
			for (PmEmpDocsEntity data : datas) {
				List<PmGecEntity> doc = (List<PmGecEntity>) commonService.getDataBasedOnCode(data.getDocType(), "DC");
				for (PmGecEntity data2 : doc) {
					Map<String, Object> finalOutput = new HashMap<>();
					finalOutput.put("id", data.getId());
					finalOutput.put("docType", data2.getShortDesc());
					finalOutput.put("filename", data.getFileName());
					finalOutput.put("filepath", data.getFilePath());
					list.add(finalOutput);
				}
			}
			return list;

		} catch (Exception e) {
			log.error("getDocumentMasterData {}", e);
		}
		return null;
	}

	@Override
	public ResponseDTO deleteDocument(int id) {
		try {
			Optional<PmEmpDocsEntity> data = pmEmpDocsRepo.findById(id);
			if (data.isPresent()) {
				String filePath = data.get().getFilePath();
				Path filepath = Paths.get(filePath.toString());
				Files.delete(filepath);
				pmEmpDocsRepo.deleteById(id);
				return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						null);
			}
			return new ResponseDTO(StringConstants.NotFound, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteDocument {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

//	@Override
//	public ResponseDTO getDesigBasedOnEmpId(String employeeId) {
//		try {
//			String desig = empMastRepo.getEMpDesig(employeeId);
//			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), desig);
//		} catch (Exception e) {
//			log.error("getDesigBasedOnEmpId {}", e);
//		}
//		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
//				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
//
//	}
	@Override
	public ResponseDTO getDesigBasedOnEmpId(String employeeId) {
//		List<String> finalList = new ArrayList<>();
		try {
			List<EmpMastEntity> desigcode = empMastRepo.getEMpDesig(employeeId);
			for (EmpMastEntity desigDesc : desigcode) {
				String desig = pmDesigRepo.findDesc(desigDesc.getDesig());
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						desig);
			}
		} catch (Exception e) {
			log.error("getDesigBasedOnEmpId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getAllEmployeeNameAndId() {

		List<String> finalList = new ArrayList<>();
		try {
			List<EmpMastEntity> data = empMastRepo.findAll();
			for (EmpMastEntity nameId : data) {
				String nameAndId = nameId.getEmpId() + " " + nameId.getName();
				finalList.add(nameAndId);
				return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
						finalList);

			}
		} catch (Exception e) {
			log.error("getDesigBasedOnEmpId {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

	}

	@Override
	public ResponseDTO getClassAndDesigntion(String employeeId) {
		EmpMastEntity data = empMastRepo.findEmployeeClassANdDesig(employeeId);
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
	}

//	@Override
//	public ResponseDTO getAllAddress() {
//		List<AddressEntity> data = addressRepo.findAll();
//
//		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
//	}
	@Override
	public ResponseDTO getAllAddress() {
		try {
			List<Map<String, Object>> list = new ArrayList<>();
			List<AddressEntity> datas = addressRepo.findAll();
			for (AddressEntity addressData : datas) {
				Map<String, Object> finalOutput = new HashMap<>();
				String addressDatas = addressData.getCaddrss() + " " + addressData.getCaddrss1() + " "
						+ addressData.getCaddrss2() + " " + addressData.getCaddrss3();
				String name = empMastRepo.FindEmpName(addressData.getEmpId());
				finalOutput.put("empId", addressData.getEmpId());
				finalOutput.put("name", name);
				finalOutput.put("address", addressDatas);
				list.add(finalOutput);
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			log.error("getAddressData {}", e);
		}
		return null;

	}

	@Override
	public ResponseDTO checkAddress(@Valid EmployeeID payload) {
		try {
			boolean data = false;
			AddressEntity datas = addressRepo.findByEmpId(payload.getEmployeeId());
			if (datas != null) {
				data = true;
			}
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("checkDuplicateEmployeeName {}", e);
		}

		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getGradePay() {
		try {
			List<String> data = pmPayMatrixRepo.getGradePay();
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			log.error("getPayMatrixLevel {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteAddress(String id) {
		try {

			addressRepo.deleteById(id);
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("deleteDocument {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getAllAddressData(@Valid EmployeeID payload) {
		Optional<AddressEntity> datas = addressRepo.findByEmployeeId(payload.getEmployeeId());
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), datas);
	}

	@Override
	public ResponseDTO getAllQualification(@Valid EmployeeID payload) {
		List<PmQualificationEntity> datas = pmQualificationRepo.findByEmployeeId(payload.getEmployeeId());
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), datas);
	}

	@Override
	public ResponseDTO getAllQualificationById(Integer id) {
		Optional<PmQualificationEntity> datas = pmQualificationRepo.findById(id);
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), datas);
	}

//Modified on 21-10-2020-Because Primary changed from ID to EMPID and SERLNO
//	@Override
//	public ResponseDTO getAllKinById(Integer id) {
//		Optional<KinEntity> data = kinRepo.findById(id);
//		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
//	}
	@Override
	public ResponseDTO getAllKinById(@Valid GetKinModel payload) {
		List<KinEntity> data = kinRepo.findByEmpIdAndSerlNo(payload.getEmpId(), payload.getSerlNo());
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
	}

	@Override
	public ResponseDTO getAllKinFeeById(Integer id) {
		Optional<PmKinFeeEntity> datas = pmKinFeeRepo.findById(id);
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), datas);
	}

//	@Override
//	public ResponseDTO getAllKinFeeById(Integer id) {
//		
//		List<Map<String, Object>> list = new ArrayList<>();
//		List<PmKinFeeEntity> datas = pmKinFeeRepo.findByID(id);
//		for (PmKinFeeEntity kinFeeData : datas) {
//			Map<String, Object> finalOutput = new HashMap<>();
//			KinEntity kinData = kinRepo.findByEmpIdAndSerl(kinFeeData.getEmpId(),kinFeeData.getKinId());
//			finalOutput.put("id",kinFeeData.getId());
//			finalOutput.put("empId",kinFeeData.getEmpId());
//			finalOutput.put("kinId",kinFeeData.getKinId() );
//			finalOutput.put("name",kinData.getName() );
//			finalOutput.put("feeType",kinFeeData.getFeeType() );
//			finalOutput.put("financialYear",kinFeeData.getFinancialYear() );
//			finalOutput.put("feeAmt",kinFeeData.getFeeAmt() );
//			finalOutput.put("claim",kinFeeData.getClaim() );
//			finalOutput.put("receiptNo",kinFeeData.getReceiptNo());
//			list.add(finalOutput);
//		}
//		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
//		}

	@Override
	public ResponseDTO getAllDocumentById(Integer id) {
		Optional<PmEmpDocsEntity> data = pmEmpDocsRepo.findById(id);
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
	}

	private static String commonbasepath = "C:\\AdminProjectDocuments\\";
//	private static String commonbasepath = "E:\\#Mafi\\ADMIN\\ADMIN-UI\\src\\assets\\documents\\";

	@Override
	public ResponseDTO saveDocument(MultipartFile file, String types, String documentName, String documentsof,
			String kinName, String financialYear, String empId, String logUser, String logIp) {

		PmEmpDocsEntity documentData = new PmEmpDocsEntity();

		try {

			Path path = Paths.get(commonbasepath + empId);
			Files.createDirectories(path);
			LocalDateTime datetime = LocalDateTime.now();
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("ddmmyyyyhhmmss");
			String datetimeString = datetime.format(pattern);
			String fileName = datetimeString + "_" + StringUtils.cleanPath(file.getOriginalFilename());
			String fileExt = fileName.split("\\.")[fileName.split("\\.").length - 1];
//			if (Pattern.matches("pdf", fileExt) && file.getBytes().length != 0
//					&& file.getBytes().length < 2000000) {
			Path path1 = Paths.get(commonbasepath + empId);
//			Path filepath = Paths.get(path1.toString() + File.separator + types + "." + fileExt);
			Path filepath = Paths.get(path1.toString() + File.separator + fileName);
			Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

//			} else {
//				log.debug("saveFinalSubmission {}");
//				validationError(path);
//			}

			documentData.setDocType(types);
			documentData.setDocumentOf(documentsof);
			documentData.setFileName(fileName);
			documentData.setFilePath(filepath.toString());
			documentData.setEmpId(empId);
			documentData.setFinancialYear(financialYear);
			documentData.setKinName(kinName);
			documentData.setDocumentName(documentName);
			documentData.setLogIp(logIp);
			documentData.setLogUser(logUser);
			pmEmpDocsRepo.save(documentData);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("getting saveUserData" + e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.BAD_REQUEST.value(), "fail");
	}

	@Override
	public ResponseDTO getDocumentById(Integer id) {
		Optional<PmEmpDocsEntity> data = pmEmpDocsRepo.findById(id);
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
	}

	@Override
	public ResponseDTO editDocumentById(MultipartFile file, String types, String documentName, String documentsof,
			String kinName, String financialYear, String empId, String logUser, String logIp, int id) {
		try {
			Path path = Paths.get(commonbasepath + empId);
			Files.createDirectories(path);
			PmEmpDocsEntity data = pmEmpDocsRepo.findById(id).get();
			if (file != null) {
				LocalDateTime datetime = LocalDateTime.now();
				DateTimeFormatter pattern = DateTimeFormatter.ofPattern("ddmmyyyyhhmmss");
				String datetimeString = datetime.format(pattern);
				String fileName = datetimeString + "_"+ StringUtils.cleanPath(file.getOriginalFilename());
				String fileExt = fileName.split("\\.")[fileName.split("\\.").length - 1];
				
//			if (Pattern.matches("pdf", fileExt) && file.getBytes().length != 0
//					&& file.getBytes().length < 2000000) {
				Path path1 = Paths.get(commonbasepath + empId);
//				Path filepath = Paths.get(path1.toString() + File.separator + types + "." + fileExt);
				File fileToBeDeleted = new File(data.getFilePath());
				fileToBeDeleted.delete();
				Path filepath = Paths.get(path1.toString() + File.separator + fileName);
				Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
				data.setFileName(fileName);
				data.setFilePath(filepath.toString());
				
				
			}
			data.setDocType(types);
			data.setDocumentOf(documentsof);
			data.setEmpId(empId);
			data.setFinancialYear(financialYear);
			data.setKinName(kinName);
			data.setDocumentName(documentName);
			data.setLogIp(logIp);
			data.setLogUser(logUser);
			pmEmpDocsRepo.save(data);
//			} else {
//				log.debug("saveFinalSubmission {}");
//				validationError(path);
//			}
			
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.error("getting saveUserData" + e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.BAD_REQUEST.value(), "fail");
	}

	@Override
	public ResponseDTO getAllDocumentMaster() {
		List<PmEmpDocsEntity> data = pmEmpDocsRepo.findAll();
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
	}

	@Override
	public ResponseDTO getDateById(Integer id) {
		Optional<PmEmpDatesEntity> data = pmEmpDatesRepo.findById(id);
		return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
	}

	@Override
	public ResponseDTO test() {
		try {
			String data = empMastRepo.getData("Narendra Singh");
			return new ResponseDTO(StringConstants.FetchSuccess, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), data);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
	
	@Override
	public ResponseEntity<?> getDocumentByPath(String path, HttpServletRequest request) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(StringConstants.NotFound, APISTATUS.FAIL, HttpStatus.NOT_FOUND, null));
			}
			
			String contentType = null;
			contentType = request.getServletContext().getMimeType(file.getAbsolutePath());
			if (contentType == null) {
				// default type
				contentType = "application/octet-stream";
			}
			
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"" + file.getName() + "\"")
					.body(resource);
		}
		catch (Exception e) {
			log.debug("getDocumentByPath {}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(StringConstants.fail, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null));
	}



}
