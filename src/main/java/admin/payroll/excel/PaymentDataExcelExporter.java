package admin.payroll.excel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import admin.payroll.entity.PaybillEntity;

public class PaymentDataExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<PaybillEntity> listUsers;

	public PaymentDataExcelExporter(List<PaybillEntity> listUsers) {
		this.listUsers = listUsers;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("PaymentData");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "CPSMS Beneficiary Code", style);
		createCell(row, 1, "Scheme Specific Id", style);
		createCell(row, 2, " Beneficiary Name", style);
		createCell(row, 3, "Purpose", style);
		createCell(row, 4, "Centre Share Payment Amount", style);
		createCell(row, 5, "State Share Payment Amount", style);
		createCell(row, 6, "Payment From Date", style);
		createCell(row, 7, "Payment To Date", style);

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
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (PaybillEntity user : listUsers) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, user.getGpfNo(), style);
			createCell(row, columnCount++, user.getEmpNo(), style);
			createCell(row, columnCount++, user.getName(), style);
			createCell(row, columnCount++, user.getBankNo(), style);
			createCell(row, columnCount++, user.getNetPay(), style);
			createCell(row, columnCount++, user.getBasic(), style);
			createCell(row, columnCount++, user.getDesig(), style);
			createCell(row, columnCount++, user.getAdharNo(), style);

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
