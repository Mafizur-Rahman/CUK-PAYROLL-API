package admin.payroll.excel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import admin.payroll.models.DataForValidationModel;

public class SalaryValidationExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<DataForValidationModel> listUsers;

	public SalaryValidationExcelExporter(List<DataForValidationModel> listUsers2) {
		this.listUsers = listUsers2;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("SalaryValidation");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Full Name In English", style);
		createCell(row, 1, "Full Name in Recognized Official Language", style);
		createCell(row, 2, "Gender", style);
		createCell(row, 3, "Address line 1", style);
		createCell(row, 4, "Address line 2", style);
		createCell(row, 5, "Address line 3", style);
		createCell(row, 6, "District", style);
		createCell(row, 7, "State", style);
		createCell(row, 8, "Country", style);
		createCell(row, 9, "Bank Name", style);
		createCell(row, 10, "IFSCCode", style);
		createCell(row, 11, "Account Number", style);
		createCell(row, 12, "Aadhaar Number", style);
		createCell(row, 13, "Pincode", style);
		createCell(row, 14, "Scheme Specific ID", style);
		createCell(row, 15, "Center Share Payment Amount", style);
		createCell(row, 16, "State Share Payment Amount", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Null) {
			cell.setCellValue((""));
		} else {
			cell.setCellValue(("") );
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (DataForValidationModel user : listUsers) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, user.getName(), style);
			createCell(row, columnCount++, "", style);
			createCell(row, columnCount++, user.getGender(), style);
			createCell(row, columnCount++, user.getCaddrss1(), style);
			createCell(row, columnCount++, user.getCaddrss2(), style);
			createCell(row, columnCount++, user.getCaddrss3(), style);
			createCell(row, columnCount++, user.getCdistrict(), style);
			createCell(row, columnCount++, user.getState2(), style);
			createCell(row, columnCount++, user.getNationality(), style);
			createCell(row, columnCount++, user.getBankName(), style);
			createCell(row, columnCount++, user.getIfscCode(), style);
			createCell(row, columnCount++, user.getBanknoNew(), style);
			createCell(row, columnCount++, user.getAdharNo(), style);
			createCell(row, columnCount++, user.getCpinCode(), style);
			createCell(row, columnCount++, "", style);
			createCell(row, columnCount++, user.getNetpay(), style);
			createCell(row, columnCount++, "", style);

		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

}
