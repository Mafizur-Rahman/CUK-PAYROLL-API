package admin.payroll.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import admin.payroll.entity.GpfAdvEntity;
import admin.payroll.entity.GpfCloseBalEntity;
import admin.payroll.entity.GpfMastSubEntity;
import admin.payroll.entity.GpfRefundEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.models.AckNumber;
import admin.payroll.models.EmpIdFyrModel;
import admin.payroll.models.EmployeeID;
import admin.payroll.models.GpfAdvSaveModel;
import admin.payroll.models.GpfClosingSaveModel;
import admin.payroll.models.GpfEmpDisplayModel;
import admin.payroll.models.GpfMastSubProcessModel;
import admin.payroll.models.GpfRefundSaveModel;
import admin.payroll.models.GpfSaveModel;
import admin.payroll.models.GpfSaveModel.GpfSaveModelMonthWiseModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.repo.GpfAdvRepo;
import admin.payroll.repo.GpfCloseBalRepo;
import admin.payroll.repo.GpfMastSubRepo;
import admin.payroll.repo.GpfRefundRepo;
import admin.payroll.service.GpfService;
import admin.payroll.utils.StringConstants;

@Service
public class GpfServiceImpl implements GpfService {

	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");

	@Autowired
	GpfMastSubRepo gpfMastSubRepo;

	@Autowired
	GpfAdvRepo gpfAdvRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	GpfRefundRepo gpfRefundRepo;

	@Autowired
	GpfCloseBalRepo gpfCloseBalRepo;

	public static final Logger log = LoggerFactory.getLogger(GpfServiceImpl.class);

	@Override
	public ResponseDTO gpfMastSubProcess(@Valid GpfMastSubProcessModel payload) {
		try {
			log.debug("GpfMastSubProcess Start running");
			gpfMastSubRepo.gpfMastSubProcess(payload.getMonthId(), payload.getProcedureClass(),
					payload.getFinancialYear());
			return new ResponseDTO(StringConstants.Processed, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.debug("gpfMastSubProcess {}", e);
		}
		return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
				null);
	}

	@Override
	public ResponseDTO saveGpfMast(@Valid GpfSaveModel gpfSaveModel) {
		List<GpfSaveModelMonthWiseModel> gpfMonthWiseModels = gpfSaveModel.getGpfMonthWiseModels();
		try {
			if (gpfMonthWiseModels != null && gpfMonthWiseModels.size() > 0) {
				gpfMonthWiseModels.forEach(model -> {
					GpfMastSubEntity gpfMastSubEntity = new GpfMastSubEntity();
					gpfMastSubEntity.setEmpId(gpfSaveModel.getEmployeeId());
					gpfMastSubEntity.setFyr(gpfSaveModel.getFinancialYear());
					gpfMastSubEntity.setLockstatus(gpfSaveModel.getLockstatus());
					gpfMastSubEntity.setPayCalPeriod(model.getMonth());
					gpfMastSubEntity.setGpfSub(model.getSubscriptionAmount());
					gpfMastSubEntity.setDtfRom(model.getFromDate());
					gpfMastSubEntity.setDtto(model.getToDate());
					this.gpfMastSubRepo.save(gpfMastSubEntity);
				});
				return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
			}
		} catch (Exception e) {
			log.debug("saveGpfMast {}", e);
		}
		return new ResponseDTO(StringConstants.INVALID_INPUT, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO getGpfMastByEmpIdAndFy(EmpIdFyrModel empIdFyrModel) {
		try {
			List<GpfMastSubEntity> list = gpfMastSubRepo.getAllByEmpIdAndFyr(empIdFyrModel.getEmpId(),
					empIdFyrModel.getFyr());
			return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.NOT_FOUND, null);
		}
	}

	@Override
	public ResponseDTO getGpfMustSubByEmpIdAndFyr(@Valid GpfMastSubProcessModel payload) {
		try {
			log.debug("getGpfMustSubByEmpIdAndFyr implementation");
			List<GpfMastSubEntity> list = gpfMastSubRepo.getGpfMustSubByEmpIdAndFyr(payload.getEmpId(),
					payload.getFinancialYear());
			return new ResponseDTO(StringConstants.SUCCESS, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), list);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseDTO(StringConstants.ContactSupportErrorMsg, APISTATUS.FAIL,
					HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@Override
	public ResponseDTO getGpfEmployeeDisplayModel(EmployeeID empId) {
		try {
			GpfEmpDisplayModel gpfEmpDisplayModel = gpfMastSubRepo.getGpfEmpDisplayModel(empId.getEmployeeId());
			return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					gpfEmpDisplayModel);
		} catch (Exception e) {
			log.debug("getGpfEmployeeDisplayModel {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.NOT_FOUND, null);
	}

	@Override
	public ResponseDTO saveGpfAdv(@Valid GpfAdvSaveModel gpfAdvSaveModel) {
		try {
			GpfAdvEntity gpfAdvEntity = modelMapper.map(gpfAdvSaveModel, GpfAdvEntity.class);

			// Save Other Information
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			gpfAdvEntity.setLogDate(LocalDate.now());
			gpfAdvEntity.setLogTime(LocalTime.now().format(formatter));
			gpfAdvEntity.setLogIp(request.getRemoteAddr());

			gpfAdvRepo.save(gpfAdvEntity);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.debug("saveGpfAdv {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getGpfAdvanceByEmpId(EmployeeID id) {
		try {
			List<GpfAdvEntity> gpfAdvanceEntity = this.gpfAdvRepo.findByEmployeeId(id.getEmployeeId());
			return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					gpfAdvanceEntity);
		} catch (Exception e) {
			log.debug("getGpfAdvanceByEmpId {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getGpfAdvByAckNo(AckNumber id) {
		try {
			GpfAdvEntity gpfAdvanceEntity = this.gpfAdvRepo.findByAckNo(id.getAckNumber());
			return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					gpfAdvanceEntity);
		} catch (Exception e) {
			log.debug("getGpfAdvanceByEmpId {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO deleteGpfAdvByAckNo(AckNumber ackNumber) {
		try {
			this.gpfAdvRepo.deleteById(ackNumber.getAckNumber());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.debug("deleteGpfAdvByAckNo {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveGpfRefund(@Valid GpfRefundSaveModel gpfRefundSaveModel) {
		try {
			GpfRefundEntity gpfRefund = this.modelMapper.map(gpfRefundSaveModel, GpfRefundEntity.class);
			// Save Other Information
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			gpfRefund.setLogDate(LocalDate.now());
			gpfRefund.setLogTime(LocalTime.now().format(formatter));
			gpfRefund.setLogIp(request.getRemoteAddr());

			GpfAdvEntity gpfAdvance = this.gpfAdvRepo.findByAckNo(gpfRefund.getGpfReferenceNo());

			if (gpfAdvance.getBalanceAmount() != null) {
				if (gpfAdvance.getBalanceAmount() >= gpfRefundSaveModel.getRefundAmount())
					gpfAdvance.setBalanceAmount(gpfAdvance.getBalanceAmount() - gpfRefundSaveModel.getRefundAmount());
				else
					return new ResponseDTO("Refund Amount is greater than Balance Amount.", APISTATUS.FAIL,
							HttpStatus.ACCEPTED.value(), null);
			} else {
				gpfAdvance.setBalanceAmount(gpfAdvance.getAdvanceAmount() - gpfRefundSaveModel.getRefundAmount());
			}
			this.gpfRefundRepo.save(gpfRefund);
			this.gpfAdvRepo.save(gpfAdvance);
			return new ResponseDTO(StringConstants.Saved, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.debug("saveGpfRefund {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO getGpfRefundByEmployeeId(EmployeeID employeeId) {
		try {
			List<GpfRefundEntity> gpfRefundList = this.gpfRefundRepo.findByEmployeeId(employeeId.getEmployeeId());
			return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					gpfRefundList);
		} catch (Exception e) {
			log.debug("getGpfRefundByEmployeeId {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@PostMapping("/getGpfRefundByAckNo")
	public ResponseDTO getGpfRefundByAckNo(AckNumber ackNumber) {
		try {
			GpfRefundEntity gpfRefundEntity = this.gpfRefundRepo.findByAcknowledgementNumber(ackNumber.getAckNumber());
			return new ResponseDTO(StringConstants.Fetched, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(),
					gpfRefundEntity);
		} catch (Exception e) {
			log.debug("getGpfRefundByAckNo {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@Override
	public ResponseDTO deleteGpfRefundByAckNo(AckNumber ackNumber) {
		try {
			this.gpfRefundRepo.deleteById(ackNumber.getAckNumber());
			return new ResponseDTO(StringConstants.Deleted, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.debug("deleteGpfRefundByAckNo {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO readExcelFile(MultipartFile excelFile) {
		if (excelFile != null) {
			try (XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream())) {
				List<List<Object>> excelResponse = new ArrayList<>();
				XSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rows = sheet.iterator();
				while (rows.hasNext()) {
					List<Object> responseRow = new ArrayList<>();
					Row row = rows.next();
					Iterator<Cell> cells = row.cellIterator();
					while (cells.hasNext()) {
						Cell cell = cells.next();
						switch (cell.getCellType().toString()) {
						case "NUMERIC":
							responseRow.add(cell.getNumericCellValue());
							break;
						case "STRING":
							responseRow.add(cell.getStringCellValue());
							break;
						}
					}
					excelResponse.add(responseRow);
				}
				return new ResponseDTO("Excel Reading Successful", APISTATUS.SUCCESS, HttpStatus.ACCEPTED,
						excelResponse);
			} catch (Exception e) {
				log.debug("readExcelFile {}", e);
			}
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	@Override
	public ResponseDTO saveGpfCloseBal(@Valid GpfClosingSaveModel gpfClosingSaveModel) {
		try {
			gpfClosingSaveModel.getEmployeeData().forEach(data -> {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();
				GpfCloseBalEntity gpfCloseBal = new GpfCloseBalEntity();
				gpfCloseBal.setFinancialYear(gpfClosingSaveModel.getFinancialYear());
				gpfCloseBal.setEmployeeId(data.getEmployeeId());
				gpfCloseBal.setCloseBal(data.getAmount());
				gpfCloseBal.setLogDate(LocalDate.now());
				gpfCloseBal.setLogTime(LocalTime.now().format(formatter));
				gpfCloseBal.setLogIp(request.getRemoteAddr());
				this.gpfCloseBalRepo.save(gpfCloseBal);
			});
			return new ResponseDTO(StringConstants.Uploaded, APISTATUS.SUCCESS, HttpStatus.ACCEPTED.value(), null);
		} catch (Exception e) {
			log.debug("saveGpfCloseBal {}", e);
		}
		return new ResponseDTO(StringConstants.FAIL, APISTATUS.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}
}
