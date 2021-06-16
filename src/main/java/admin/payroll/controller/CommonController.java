package admin.payroll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.entity.PmGecEntity;
import admin.payroll.entity.PmUserRightsEntity;
import admin.payroll.exceptions.InvalidJsonException;
import admin.payroll.models.AuthenticationResponseDto;
import admin.payroll.models.CodeAndCodeTypeModel;
import admin.payroll.models.EedMode;
import admin.payroll.models.LoginModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SelectBoxModel;
import admin.payroll.repo.PmUserRepo;
import admin.payroll.service.CommonService;
import admin.payroll.utils.LdapClass;
import admin.payroll.utils.StringConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class CommonController {

	private static final Logger log = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	CommonService commonService;

	@Autowired
	LdapClass ldapClass;

	@Autowired
	private PmUserRepo pmUserRepo;
	/**
	 * 
	 * This End Point is for login in GTRE Intranet via LDAP.
	 */

//	@PostMapping("/ldapLogin")
//	public AuthenticationResponseDto ldapLogin(@RequestBody @Valid LoginModel payload, BindingResult binding) {
//
//		/*
//		 * GTRE LDAP Credentials USERNAME: admindevelop@mail.gtre.org PASSWORD: gtre123
//		 * 
//		 */
//
//		if (!binding.hasErrors()) {
//			if (LdapClass.authenticate(payload.getUsername(), payload.getPassword())) {
//				if (!this.commonService.checkUserExistance(payload.getUsername())) {
//					return new AuthenticationResponseDto(false, null, "User Does not exist", null);
//				}
//				List<PmUserRightsEntity> pmUserRights = this.commonService
//						.getUserRightsByLoginId(payload.getUsername());
//				return new AuthenticationResponseDto(true, payload.getUsername(), "Authentication Success",
//						pmUserRights);
//			}
//		}
//		return new AuthenticationResponseDto(false, null, "Authentication Fail! Enter Correct Login/Password", null);
//	}

	/**
	 * This is End Point is for Development Login For Application.
	 */
	@PostMapping("/ldapLogin")
	public AuthenticationResponseDto login(@RequestBody @Valid LoginModel authReqeustModel,
			BindingResult bindingResults) {
		@Data
		@AllArgsConstructor
		class Account {
			String username;
			String password;
		}

		Map<String, Account> accounts = new HashMap<>();
		accounts.put("sachinsingh.sk13@gmail.com", new Account("sachinsingh.sk13@gmail.com", "1234"));
		accounts.put("mafizur@gmail.com", new Account("mafizur@gmail.com", "1234"));
		accounts.put("disgentest@gmail.com", new Account("disgentest@gmail.com", "1234"));
		accounts.put("admindevelop@gmail.com", new Account("admindevelop@gmail.com", "1234"));

		// Check if this user exist in the temporary ldap.
		if (accounts.containsKey(authReqeustModel.getUsername())) {
			Account account = accounts.get(authReqeustModel.getUsername());

			// if user exist then check the credentials
			if (account.password.equals(authReqeustModel.getPassword())) {

				// if user credentials matched then check user exists in our DB.
				if (!this.commonService.checkUserExistance(account.getUsername())) {
					return new AuthenticationResponseDto(false, null, "User Does not exist", null);
				}

				// fetch user rights from database.
				List<PmUserRightsEntity> pmUserRights = this.commonService
						.getUserRightsByLoginId(account.getUsername());
				return new AuthenticationResponseDto(true, account.getUsername(), "Authentication Success",
						pmUserRights);
			}
		}
		return new AuthenticationResponseDto(false, null, "Authentication Fail! User Not Found!", null);

	}

//	@PostMapping("/login")
//	public AuthenticationResponseDto login(@RequestBody @Valid LoginModel authReqeustModel,
//			BindingResult bindingResults) {
//		@Data
//		@AllArgsConstructor
//		class Account {
//			String username;
//			String password;
//		}
//		PmUsersEntity accounts = pmUserRepo.findByUserName1(authReqeustModel.getUsername());
//
//		// Check if this user exist in the temporary ldap.
//		if (accounts.equals(authReqeustModel.getUsername())) {
//			Account account = new Account(null, null);
//			account.setUsername(authReqeustModel.getUsername());
//			account.setPassword(authReqeustModel.getPassword());
//			// if user exist then check the credentials
//			if (account.password.equals(authReqeustModel.getPassword())) {
//
//				// if user credentials matched then check user exists in our DB.
//				if (!this.commonService.checkUserExistance(account.getUsername())) {
//					return new AuthenticationResponseDto(false, null, "User Does not exist", null);
//				}
//
//				// fetch user rights from database.
//				List<PmUserRightsEntity> pmUserRights = this.commonService
//						.getUserRightsByLoginId(account.getUsername());
//				return new AuthenticationResponseDto(true, account.getUsername(), "Authentication Success",
//						pmUserRights);
//			}
//		}
//		return new AuthenticationResponseDto(false, null, "Authentication Fail! User Not Found!", null);
//
//	}

	@PostMapping("/getSelectBox")
	public Object EDCodeMaster(@RequestBody @Valid SelectBoxModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting master data");
			return commonService.getSelectBox(payload.getSelectBoxName());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getDataForSelectBox")
	public ResponseDTO getDataForSelectBox(@RequestBody @Valid SelectBoxModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting master data");
			return commonService.getDataForSelectBox(payload.getSelectBoxName());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/getDataBasedOnCode")
	public List<PmGecEntity> getDataForBasedOnCode(@RequestBody @Valid CodeAndCodeTypeModel payload,
			BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("getting getDataForBasedOnCode");
			return commonService.getDataBasedOnCode(payload.getCode(), payload.getCodeType());
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}

	}

	@PostMapping("/test")
	public ResponseDTO test(@RequestBody Map<String, Object> data) {

		return commonService.test(data);
	}

	@PostMapping("/test1")
	public ResponseDTO test1(@RequestBody Map<String, Object> data) {

		return commonService.test1(data);
	}

	@PostMapping("/getEDbasedOnType")
	public ResponseDTO getEDbasedOnType(@RequestBody @Valid EedMode payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving getEDbasedOnType");
			return commonService.getEDbasedOnType(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@PostMapping("/getEmpCategoryByClass")
	public ResponseDTO getEmpCategoryByClass(@RequestBody @Valid SelectBoxModel payload, BindingResult bindings) {
		if (!bindings.hasErrors()) {
			log.debug("saving getEDbasedOnType");
			return commonService.getEmpCategoryByClass(payload);
		} else {
			throw new InvalidJsonException(StringConstants.INVALID_INPUT, null);
		}
	}

	@GetMapping("/getDashboardStatistics")
	public ResponseDTO getDashboardStatistics() {
		return this.commonService.getDashboardStatistics();
	}

}
