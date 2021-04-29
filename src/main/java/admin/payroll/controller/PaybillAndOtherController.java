package admin.payroll.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.models.ClassModel;
import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SosDateModel;
import admin.payroll.service.PaybillAndOtherService;

@RequestMapping("/payBillandOther")
@RestController
public class PaybillAndOtherController {

	private static final Logger log = LoggerFactory.getLogger(PaybillAndOtherController.class);

	@Autowired
	PaybillAndOtherService paybillAndOtherService;

	@GetMapping("/payBill")
	public ResponseDTO payBill() {
		log.debug("getting getAllCodeMasterData");
		return paybillAndOtherService.getPayBill();
	}
	
	@GetMapping("/payBillAndOther")
	public ResponseDTO payBillAndOther() {
		log.debug("getting getAllCodeMasterData");
		return paybillAndOtherService.payBillAndOther();
	}

	@PostMapping("/payBillByClass")
	public ResponseDTO payBillByClass(@RequestBody @Valid ClassModel payload, BindingResult bindings) {
		log.debug("getting getAllCodeMasterData");
		return paybillAndOtherService.payBillByClass(payload);
	}

	@GetMapping("/GetMonth")
	public ResponseDTO GetMonth() {
		log.debug("getting getAllCodeMasterData");
		return paybillAndOtherService.GetMonth();
	}

	@GetMapping("/GetOrgName")
	public ResponseDTO GetOrgName() {
		log.debug("getting getAllCodeMasterData");
		return paybillAndOtherService.GetOrgName();
	}

	@PostMapping("/RetirementListForNextMonth")
	public ResponseDTO RetirementListForNextMonth(@RequestBody @Valid SosDateModel payload, BindingResult bindings) {
		log.debug("getting RetirementListForNextMonth");
		return paybillAndOtherService.RetirementListForNextMonth(payload);
	}

	@GetMapping("/PayBillSummary")
	public ResponseDTO PayBillSummary() {
		log.debug("getting PayBillSummary");
		return paybillAndOtherService.PayBillSummary();
	}

	@GetMapping("/GetRegimentalPaybill")
	public ResponseDTO GetRegimentalPaybill() {
		log.debug("getting GetRegimentalPaybill");
		return paybillAndOtherService.GetRegimentalPaybill();
	}

	// get all data from paybillEntity whrer incometax is present

	@GetMapping("/getItax")
	public ResponseDTO getItax() {
		log.debug("getting income tax form PaybillEntity");
		return paybillAndOtherService.getItax();
	}
	
	@PostMapping("/getItaxByClass")
	public ResponseDTO getItaxByClass(@RequestBody @Valid ClassModel payload, BindingResult bindings) {
		log.debug("getting getItaxByClass");
		return paybillAndOtherService.getItaxByClass(payload);
	}

	// get all data from paybillEntity where npsTotal is present

	@GetMapping("/getNps")
	public ResponseDTO getNps() {
		log.debug("getting data from paybillEntity where Nps present");
		return paybillAndOtherService.getNps();
	}

	// get all data from paybillentity where groupInsurance is present
	@GetMapping("/getGroupInsurance")
	public ResponseDTO getGroupInsurance() {
		log.debug("getting data of groupInsurance");
		return paybillAndOtherService.getGroupInsurance();
	}

	// get all data from regPayBillEntity where miscsch is present

	@GetMapping("/getMiscRecoverySchedule")
	public ResponseDTO getMiscRecoverySchedule() {
		log.debug("getting data from misCsch from regPayBillEntity");
		return paybillAndOtherService.getMiscRecoverySchedule();
	}

	@PostMapping("/getMiscRecoveryScheduleByClass")
	public ResponseDTO getMiscRecoveryScheduleByClass(@RequestBody ClassModel payload) {
		log.debug("getting data from misCsch from regPayBillEntity");
		return paybillAndOtherService.getMiscRecoveryScheduleByClass(payload);
	}
	// get all data from regPayBillEntity where educationLoan is present

	@GetMapping("/getEducationLoan")
	public ResponseDTO getEducationLoan() {
		log.debug("gettting data from regPayBillEntity where education loan is present");
		return paybillAndOtherService.getEducationLoan();
	}

	@PostMapping("/getEducationLoanByClass")
	public ResponseDTO getEducationLoanByClass(@RequestBody ClassModel payload) {
		log.debug("gettting data from regPayBillEntity where education loan is present");
		return paybillAndOtherService.getEducationLoanByClass(payload);
	}

	// get all data from regPayBillEntity where ibloan present

	@GetMapping("/getIbLoan")
	public ResponseDTO getIbLoan() {
		log.debug("getting ibloan from regPayBillEntity");
		return paybillAndOtherService.getIbLoan();
	}

	// get all data from regPayBillEntity where rtf present

	@GetMapping("/grtReimbursmentTfee")
	public ResponseDTO grtReimbursmentTfee() {
		log.debug("getting rtf from regPayBillEntity");
		return paybillAndOtherService.grtReimbursmentTfee();
	}

	// get all data from regPayBillEntity where cgoClub present

	@GetMapping("/getCgoClubRecovery")
	public ResponseDTO getCgoClubRecovery() {
		log.debug("getting cgoClub from regPayBillEntity");
		return paybillAndOtherService.getCgoClubRecovery();
	}

	@PostMapping("/getCgoClubRecoveryByClass")
	public ResponseDTO getCgoClubRecoveryByClass(@RequestBody ClassModel payload) {
		log.debug("getting cgoClub from regPayBillEntity");
		return paybillAndOtherService.getCgoClubRecoveryByClass(payload);
	}

	
	// get all data from regPayBillEntity where mkManch present

	@GetMapping("/getMahilaKalyanManch")
	public ResponseDTO getMahilaKalyanManch() {
		log.debug("getting mkManch from regPayBillEntity");
		return paybillAndOtherService.getMahilaKalyanManch();
	}

	// get all data from regPayBillEntity where frFund present

	@GetMapping("/getFamilyReliefFund")
	public ResponseDTO getFamilyReliefFund() {
		log.debug("getting frFund from regPayBillEntity");
		return paybillAndOtherService.getFamilyReliefFund();
	}

	// get all data from regPayBillEntity where licFee present

	@GetMapping("/getSSLic")
	public ResponseDTO getSSLic() {
		log.debug("getting licFee from regPayBillEntity");
		return paybillAndOtherService.getSSLic();
	}

	
	@PostMapping("/getSSLicByClass")
	public ResponseDTO getSSLicByClass(@RequestBody ClassModel payload) {
		log.debug("getting licFee from regPayBillEntity");
		return paybillAndOtherService.getSSLicByClass(payload);
	}
	// get all data from regPayBillEntity where cghs present

	@GetMapping("/getCghsRecovery")
	public ResponseDTO getCghsRecovery() {
		log.debug("getting cghs from regPayBillEntity");
		return paybillAndOtherService.getCghsRecovery();
	}
	
	@PostMapping("/getCghsRecoveryByClass")
	public ResponseDTO getCghsRecoveryByClass(@RequestBody @Valid ClassModel payload, BindingResult bindings) {
		log.debug("getting getCghsRecoveryByClass");
		return paybillAndOtherService.getCghsRecoveryByClass(payload);
	}

	
	@GetMapping("/getNonCghsRecovery")
	public ResponseDTO getNonCghsRecovery() {
		log.debug("getting non cghs from regPayBillEntity");
		return paybillAndOtherService.getNonCghsRecovery();
	}
	
	// get all data from regPayBillEntity where misRec present

	@GetMapping("/getMiscRecovery")
	public ResponseDTO getMiscRecovery() {
		log.debug("getting misRec from regPayBillEntity");
		return paybillAndOtherService.getMiscRecovery();
	}

	// get all data from regPayBillEntity where unionn present
	@GetMapping("/getUnionn")
	public ResponseDTO getUnionn() {
		log.debug("getting unionn from regPayBillEntity");
		return paybillAndOtherService.getUnionn();
	}

	@PostMapping("/getUnionnByClass")
	public ResponseDTO getUnionnByClass(@RequestBody ClassModel payload) {
		log.debug("getting unionn from regPayBillEntity");
		return paybillAndOtherService.getUnionnByClass(payload);
	}
	@GetMapping("/getDromi")
	public ResponseDTO getDromi() {
		log.debug("getting unionn from regPayBillEntity");
		return paybillAndOtherService.getDromi();
	}
	
	@PostMapping("/getDromiByClass")
	public ResponseDTO getDromiByClass(@RequestBody ClassModel payload) {
		log.debug("getting unionn from regPayBillEntity");
		return paybillAndOtherService.getDromiByClass(payload);
	}
	
	@GetMapping("/getPli")
	public ResponseDTO getPli() {
		return paybillAndOtherService.getPli();
	}
	
	@PostMapping("/getPliByClass")
	public ResponseDTO getPli(@RequestBody ClassModel payload) {
		return paybillAndOtherService.getPliByClass(payload);
	}
	
	@GetMapping("/getGis")
	public ResponseDTO getGis() {
		return paybillAndOtherService.getGis();
	}
	
	@PostMapping("/getGisByClass")
	public ResponseDTO getGisByClass(@RequestBody @Valid ClassModel payload, BindingResult bindings) {
		log.debug("getting getGisByClass");
		return paybillAndOtherService.getGisByClass(payload);
	}
	
	@GetMapping("/getPunchingMedia")
	public ResponseDTO getPunchingMedia() {
		return paybillAndOtherService.getPunchingMedia();
	}
	
	@GetMapping("/getDivisonWiseEmployee")
	public ResponseDTO getDivisonWiseEmployee() {
		return paybillAndOtherService.getDivisonWiseEmployee();
	}
}