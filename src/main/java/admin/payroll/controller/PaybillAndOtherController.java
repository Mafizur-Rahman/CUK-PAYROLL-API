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
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import admin.payroll.entity.EmpMastEntity;
import admin.payroll.entity.PaybillAndOtherEntity;
import admin.payroll.entity.PaybillEntity;
import admin.payroll.entity.RegimentalPaybillEntity;
import admin.payroll.excel.CghsRecoveryExcelExporter;
import admin.payroll.excel.CgoClubRecoveryExcelExporter;
import admin.payroll.excel.DromiExcelExporter;
import admin.payroll.excel.EducationLoanExcelExporter;
import admin.payroll.excel.FamilyRelifFundExcelExporter;
import admin.payroll.excel.GisExcelExporter;
import admin.payroll.excel.ItaxExcelExporter;
import admin.payroll.excel.LPWCExcelExporter;
import admin.payroll.excel.MKMYExcelExporter;
import admin.payroll.excel.MiscrecoveryScheduleExcelExporter;
import admin.payroll.excel.NGOExcelExporter;
import admin.payroll.excel.NPSExcelExporter;
import admin.payroll.excel.NonCghsExcelExporter;
import admin.payroll.excel.PayBillExcelExporter;
import admin.payroll.excel.PaymentDataExcelExporter;
import admin.payroll.excel.PliExcelExporter;
import admin.payroll.excel.RetirementListExcelExporter;
import admin.payroll.excel.SSLicExcelExporter;
import admin.payroll.excel.SalaryValidationExcelExporter;
import admin.payroll.excel.UnionExcelExporter;
import admin.payroll.models.ClassModel;
import admin.payroll.models.DataForValidationModel;
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

	// salary data validation execl api
	// data for validation grid view
	@GetMapping("/getSalDataForValidation")
	public ResponseDTO getSalDataForValidation() {
		log.debug("getting getAllCodeMasterData");
		return paybillAndOtherService.getSalDataForValidation();
	}

	// data for validation excel
	@GetMapping("/getSalDataForValidationExcel")
	public void SalaryDataValidationExcelExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=salaryValidation_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<DataForValidationModel> listUsers = paybillAndOtherService.getSalDataForValidationExcel();

		SalaryValidationExcelExporter excelExporter = new SalaryValidationExcelExporter(listUsers);

		excelExporter.export(response);
	}

	// dbt01 read excel file
	@PostMapping(path = "/readDbt01ExcelFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseDTO readExcelFile(@RequestPart MultipartFile excelFile) {
		return this.paybillAndOtherService.readDbt01ExcelFile(excelFile);
	}

//payment validated data
	@GetMapping("/getPaymentDataExcel")
	public void PaymentDataExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		DataForValidationModel entity = new DataForValidationModel();
//		// entity.setGpfNo("BTLHY14635517");
//		entity.setEmpNo("VISVAS");
//		entity.setName("VIMA SHG Society");
//		entity.setBankNo("Visvas Scheme");
//		entity.setNetPay(500.00);
//		entity.setBasic(0.00);
//		entity.setDesig("11/01/2020");
//		entity.setAdharNo("11/30/2020");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=paymentData_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<DataForValidationModel> listUsers = paybillAndOtherService.getPaymentDataExcel();
		listUsers.add(entity);

		PaymentDataExcelExporter excelExporter = new PaymentDataExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@GetMapping("/getBankExcel")
	public void PayBillExcelExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Bank_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillEntity> listUsers = paybillAndOtherService.getPaybillEntityExcel();

		PayBillExcelExporter excelExporter = new PayBillExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@GetMapping("/getNPSExcel")
	public void NPSExcelExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=NPS_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillEntity> listUsers = paybillAndOtherService.getPaybillEntityExcel();

		NPSExcelExporter excelExporter = new NPSExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@GetMapping("/getLPWCExcel")
	public void LPWCExcelExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=LPWC_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillEntity> listUsers = paybillAndOtherService.getPaybillEntityExcel();

		LPWCExcelExporter excelExporter = new LPWCExcelExporter(listUsers);

		excelExporter.export(response);
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

	@PostMapping("/RetirementListForNextMonthExcel")

	public void RetirementexportToExcel(HttpServletResponse response, @RequestBody @Valid SosDateModel payload)
			throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=RetirementList_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<EmpMastEntity> listUsers = paybillAndOtherService.RetirementListForNextMonthExcel(payload);

		RetirementListExcelExporter excelExporter = new RetirementListExcelExporter(listUsers);

		excelExporter.export(response);

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

	@GetMapping("/GetMKMYRegimentalPaybillExcel")
	public void MKMYRegimentalPaybillExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=MKMR_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<RegimentalPaybillEntity> listUsers = paybillAndOtherService.getRegimentalPaybillExcel();

		MKMYExcelExporter excelExporter = new MKMYExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@GetMapping("/GetFamilyRelifFundExcel")
	public void FamilyRelifFundExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=MKMR_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<RegimentalPaybillEntity> listUsers = paybillAndOtherService.getRegimentalPaybillExcel();

		FamilyRelifFundExcelExporter excelExporter = new FamilyRelifFundExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@GetMapping("/GetNGORegimentalPaybillExcel")
	public void NGORegimentalPaybillExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=NGO_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<RegimentalPaybillEntity> listUsers = paybillAndOtherService.getNGORegimentalPaybillExcel();

		NGOExcelExporter excelExporter = new NGOExcelExporter(listUsers);

		excelExporter.export(response);
	}
	// get all data from paybillEntity whrer incometax is present

	@GetMapping("/getItax")
	public ResponseDTO getItax() {
		log.debug("getting income tax form PaybillEntity");
		return paybillAndOtherService.getItax();
	}

	@GetMapping("/getItaxExcel")
	public void ItaxExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Itax_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillAndOtherEntity> listUsers = paybillAndOtherService.getItaxExcel();

		ItaxExcelExporter excelExporter = new ItaxExcelExporter(listUsers);

		excelExporter.export(response);
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

	@GetMapping("/gettMiscRecoveryScheduleExcel")
	public void MiscRecoveryScheduleExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=MiscRecoverySchedule_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillAndOtherEntity> listUsers = paybillAndOtherService.getMiscRecoveryScheduleExcel();

		MiscrecoveryScheduleExcelExporter excelExporter = new MiscrecoveryScheduleExcelExporter(listUsers);

		excelExporter.export(response);
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

	@GetMapping("/getEducationLoanExcel")
	public void EducationLoanExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=EducationLoan_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<RegimentalPaybillEntity> listUsers = paybillAndOtherService.getEducationLoanExcel();

		EducationLoanExcelExporter excelExporter = new EducationLoanExcelExporter(listUsers);

		excelExporter.export(response);
	}

	//
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

	@GetMapping("/getCgoClubRecoveryExcel")
	public void CgoClubRecoveryExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=CgoClubRecovery_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<RegimentalPaybillEntity> listUsers = paybillAndOtherService.getCgoClubRecoveryExcel();

		CgoClubRecoveryExcelExporter excelExporter = new CgoClubRecoveryExcelExporter(listUsers);

		excelExporter.export(response);
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

	// Excel generate in SSLIC

	@GetMapping("/getSSLicExcel")
	public void SSLicexportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=SSLic_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillAndOtherEntity> listUsers = paybillAndOtherService.listAll();

		SSLicExcelExporter excelExporter = new SSLicExcelExporter(listUsers);

		excelExporter.export(response);
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

	@GetMapping("/getCghsRecoveryExcel")
	public void CghsRecoveryExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=CghsRecovery_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillAndOtherEntity> listUsers = paybillAndOtherService.getCghsRecoveryExcel();

		CghsRecoveryExcelExporter excelExporter = new CghsRecoveryExcelExporter(listUsers);

		excelExporter.export(response);
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

	@GetMapping("/getNonCghsRecoveryExcel")
	public void NonCghsExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=NonCghs_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillAndOtherEntity> listUsers = paybillAndOtherService.getNonCghsRecoveryExcel();

		NonCghsExcelExporter excelExporter = new NonCghsExcelExporter(listUsers);

		excelExporter.export(response);
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

	@GetMapping("/getUnionExcel")
	public void UnionExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Union_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<RegimentalPaybillEntity> listUsers = paybillAndOtherService.getUnionExcel();
		UnionExcelExporter excelExporter = new UnionExcelExporter(listUsers);
		excelExporter.export(response);
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

	@GetMapping("/getDromiExcel")
	public void DromiExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Dromi_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<RegimentalPaybillEntity> listUsers = paybillAndOtherService.getDromiExcel();
		DromiExcelExporter excelExporter = new DromiExcelExporter(listUsers);
		excelExporter.export(response);
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

	@GetMapping("/getPliExcel")
	public void PliExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Pli" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillAndOtherEntity> listUsers = paybillAndOtherService.getPliExcel();

		PliExcelExporter excelExporter = new PliExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@PostMapping("/getPliByClass")
	public ResponseDTO getPli(@RequestBody ClassModel payload) {
		return paybillAndOtherService.getPliByClass(payload);
	}

	@GetMapping("/getGis")
	public ResponseDTO getGis() {
		return paybillAndOtherService.getGis();
	}

	@GetMapping("/getGisExcel")
	public void GisexportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Gis_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PaybillAndOtherEntity> listUsers = paybillAndOtherService.getGisAll();

		GisExcelExporter excelExporter = new GisExcelExporter(listUsers);

		excelExporter.export(response);
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