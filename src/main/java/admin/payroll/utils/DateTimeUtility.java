package admin.payroll.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtility {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public static String getCurrentTimeString() {
		return LocalTime.now().format(FORMATTER);
	}
	
	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}
}
