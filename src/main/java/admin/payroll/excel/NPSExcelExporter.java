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

public class NPSExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<PaybillEntity> listUsers;

	public NPSExcelExporter(List<PaybillEntity> listUsers) {
		this.listUsers = listUsers;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("NPS");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
	
		createCell(row, 0, "Emp.No", style);
		createCell(row, 1, "Emp Name", style);
		createCell(row, 2, "Designation", style);
		createCell(row, 3, "PRAN NO", style);
		createCell(row, 4, "Basic", style);
		createCell(row, 5, "DA", style);
		createCell(row, 6, "TIER-1", style);
		createCell(row, 7, "GOVT_CONT", style);
		createCell(row, 8, "TOTAL", style);
		createCell(row, 9, "Rg/Arr", style);
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if(value instanceof Double) {
			cell.setCellValue((Double) value);
		}
		else {
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
			createCell(row, columnCount++, user.getEmpNo(), style);
			createCell(row, columnCount++, user.getName(), style);
			createCell(row, columnCount++, user.getDesig(), style);
			createCell(row, columnCount++, user.getPran(), style);
			createCell(row, columnCount++, user.getBasic(), style);
			createCell(row, columnCount++, user.getDa(), style);
			createCell(row, columnCount++, user.getGmcRec(), style);
			createCell(row, columnCount++, user.getGmcCpf(), style);
			createCell(row, columnCount++, user.getNpsTotal(), style);	
			createCell(row, columnCount++, user.getGmcArr(), style);
	

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
