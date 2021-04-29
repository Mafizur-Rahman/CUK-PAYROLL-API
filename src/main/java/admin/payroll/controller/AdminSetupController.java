package admin.payroll.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.entity.PmUsersEntity;
import admin.payroll.enums.APISTATUS;
import admin.payroll.exceptions.InvalidJsonException;
import admin.payroll.models.PmId;
import admin.payroll.models.PmLoginId;
import admin.payroll.models.PmUserName;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePmRolesModel;
import admin.payroll.models.SavePmUnitSetupModel;
import admin.payroll.models.SavePmUserRightModel;
import admin.payroll.models.SavePmUsersModel;
import admin.payroll.models.UnitIDModel;
import admin.payroll.service.AdminSetupService;
import admin.payroll.utils.StringConstants;
import admin.payroll.utils.UserExcelExporter;

@RestController
@RequestMapping(value = "/adminSetup")
public class AdminSetupController {

	private static final Logger log = LoggerFactory.getLogger(AdminSetupController.class);

	@Autowired
	AdminSetupService adminSetupService;

	@PostMapping("/saveUnitSetup")
	public ResponseDTO saveUnitSetup(@RequestBody @Valid SavePmUnitSetupModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saveUnitSetup");
			return adminSetupService.saveUnitSetup(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getUnitSetupByUniId")
	public ResponseDTO getUnitSetupByUniId(@RequestBody @Valid UnitIDModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getUnitSetupByUniId");
			return adminSetupService.getUnitSetupByUniId(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	// create a api for PmUser

	@PostMapping("/savePmUser")
	public ResponseDTO savePmUser(@RequestBody @Valid SavePmUsersModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("savePmUser");
			return adminSetupService.savePmUser(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);

		}

	}

	@GetMapping("/getPmUser")
	public ResponseDTO getPmUser() {
		log.debug("getting all PmUser");
		return adminSetupService.getAllPmUser();
	}

	@PostMapping("/getPmUserById")
	public ResponseDTO getPmUserById(@RequestBody @Valid PmId payload) {
		log.debug("getting Pm user by id");
		return adminSetupService.getPmUserById(payload);

	}

	@GetMapping("/users/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PmUsersEntity> listUsers = adminSetupService.listAll();

		UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@PostMapping("/getPmUserByLoginId")
	public ResponseDTO getPmUserByLoginId(@RequestBody @Valid PmLoginId loginId) {
		log.debug("getPmUserByLoginId");
		return adminSetupService.getPmUserByLoginId(loginId);
	}

	@PostMapping("/deletePmUserById")
	public ResponseDTO deletePmUserById(@RequestBody @Valid PmId payload) {
		log.debug("deletting pmUser by id");
		return adminSetupService.deletingPmUserById(payload);

	}

	@PostMapping("/getPmUserByName")
	public ResponseDTO getPmUserByName(@RequestBody @Valid PmUserName payload) {
		log.debug("getting pm user by name");
		return adminSetupService.getPmUserByName(payload);
	}

	@PostMapping("/checkPmUserExit")
	public ResponseDTO checkPmUserExit(@RequestBody @Valid PmId payload) {

		log.debug("check pmUser exit");
		return adminSetupService.checkPmUserExit(payload);
	}

	// create a API for PmRoles
	@PostMapping("/savePmRoles")
	public ResponseDTO savePmRole(@RequestBody @Valid SavePmRolesModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("SavePmRoles");
			return adminSetupService.savePmRoles(payload);
		} else {

			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getAllPmRoles")
	public ResponseDTO getAllPmRoles() {
		log.debug("gettin all pmRoles");
		return adminSetupService.getAllPmRoles();
	}

	@PostMapping("/getPmRolesById")
	public ResponseDTO getPmRoleById(@RequestBody @Valid PmId payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting pmRole byj id");
			return adminSetupService.getPmRoleByID(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/deletePmRoleById")
	public ResponseDTO deletePmRoleByName(@RequestBody @Valid PmId payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("gettig pmRole by name");
			return adminSetupService.deletePmRoleById(payload);
		} else {

			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}
	// create the API for Search operation

	@PostMapping("/searchPmRoleById")
	public ResponseDTO searchPmRoleById(@RequestBody @Valid PmId payload, BindingResult bindings) {
		if (!bindings.hasFieldErrors()) {
			log.debug("search PmRole ById");
			return adminSetupService.searchPmRoleById(payload);
		} else {

			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/searchPmRoleByDescription")
	public ResponseDTO searchPmRoleByDescription(@RequestBody @Valid PmUserName payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("search PmRole ByDescription");
			return adminSetupService.searchPmRoleByDescription(payload);
		} else {

			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}
	// create API for PmUserRight

	@PostMapping("/savePmUserRights")
	public ResponseDTO savePmUserRight(@RequestBody @Valid SavePmUserRightModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("savePmUserRight");
			return adminSetupService.savePmUserRight(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getALLPmUserRight")
	public ResponseDTO getAllPmUserRight() {
		log.debug("getting allPmUserRight");
		return adminSetupService.getAllPmUserRight();
	}

	@PostMapping("/getPmUserRightByuserId")
	public ResponseDTO getPmUserRightById(@RequestBody @Valid PmId payload) {
		log.debug("getting pmUserRightById");
		return adminSetupService.getPmUserRightById(payload);

	}

	@PostMapping("/deletePmUserRightById")
	public ResponseDTO deletePmUserRightById(@RequestBody @Valid PmId payload, BindingResult bindings) {

		if (!bindings.hasErrors()) {
			log.debug("deletePmUserRightById");
			return adminSetupService.deletePmUserRightById(payload);

		} else {
			return new ResponseDTO(StringConstants.INVALID_INPUT, APISTATUS.FAIL, HttpStatus.BAD_REQUEST, null);
		}
	}

	@PostMapping("/searchPmUserRightById")
	public ResponseDTO searchPmUserRightById(@RequestBody @Valid PmId payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("searchPmUserRightById");
			return adminSetupService.searchPmUserRightById(payload);

		} else {
			log.error("error while searchPmUserRightById");
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/searchPmUserRightByUserIdOrRoleId")
	public ResponseDTO searchPmUserRightByUserIdOrRoleId(@RequestBody @Valid PmId payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("featching searchPmUserRightByUserIdOrRoleId");
			return adminSetupService.searchPmUserRightByUserIdOrRoleId(payload);

		} else {
			log.error("error searchPmUserRightByUserIdOrRoleId");
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}
}
