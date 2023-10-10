//package com.api.helper;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.api.entity.EmployeeAttendance;
//import com.api.service.EmployeeAttendanceService;
//
//@Component
//public class Helper {
//
//	@Autowired
//	private EmployeeAttendanceService attendanceService;
//
//	@SuppressWarnings("unchecked")
//	public List<EmployeeAttendance> uploadAttendanceFile(@RequestParam("file") MultipartFile file) {
//		try {
//			List<EmployeeAttendance> attendanceList = new ArrayList<>();
//
//			File tempFile = File.createTempFile("temp-", "-" + file.getOriginalFilename());
//			file.transferTo(tempFile);
//
//			FileInputStream fis = new FileInputStream(tempFile);
//			Workbook workbook = WorkbookFactory.create(fis);
//
//			Sheet sheet = workbook.getSheetAt(0);
//			
//			int rowIndexs =9;
//			Row rows = sheet.getRow(rowIndexs);
//			String date = getStringCellValue(rows.getCell(5));
//			System.out.println(date +"==================================");
//			// Assuming the first row is the header, start from the second row
//			System.out.println(sheet.getLastRowNum());
//			for (int rowIndex = 12; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//				Row row = sheet.getRow(rowIndex);
//
//				// Check if the row is null or empty
//				if (row == null || isEmptyRow(row)) {
//					continue; // Skip empty rows
//				}
//
//				EmployeeAttendance attendance = new EmployeeAttendance();
//				attendance.setAttendanceDate(date);
//				attendance.setEmployeeCode(getStringCellValue(row.getCell(3)));
//				attendance.setEmployeeName(getStringCellValue(row.getCell(5)));
//				attendance.setInDuration(getStringCellValue(row.getCell(7)));
//				attendance.setOutDuration(getStringCellValue(row.getCell(8)));
//				attendance.setPunchRecords(getStringCellValue(row.getCell(10)));
//				attendanceList.add(attendance);
//			}
//
//			// Delete the temporary file
//			//tempFile.delete();
//
//			return attendanceList;
//		} catch (Exception e) {
//			return (List<EmployeeAttendance>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Error uploading file: " + e.getMessage());
//		}
//	}
//
//	// ... (other code)
//
//	private String getStringCellValue(Cell cell) {
//
//		if (cell == null) {
//			return null;
//		}
//
//		switch (cell.getCellType()) {
//		case STRING:
//			return cell.getStringCellValue();
//		case NUMERIC:
//			// Depending on the formatting of the numeric cell, you might need to handle the
//			// value conversion
//			// Example: return String.valueOf(cell.getNumericCellValue());
//			return String.valueOf(cell.getNumericCellValue()); // You can customize the conversion based on your data
//		default:
//			return "null";
//		}
//	}
//
//	private boolean isEmptyRow(Row row) {
//		for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
//			Cell cell = row.getCell(cellIndex);
//			if (cell != null && cell.getCellType() != CellType.BLANK) {
//				return false; // Row is not empty
//			}
//		}
//		return true; // Row is empty
//	}
//	
//	//scheduler for uploding file
//	/*
//	 * @Scheduled(cron = "0 15 16 * * *") public void
//	 * uploadScheduledAttendanceFile() { try { // Fetch the file from the specific
//	 * location // For simplicity, let's assume the file is already fetched and
//	 * named "fetchedFile.xlsx" System.out.println("cron  is running"); File file =
//	 * new File("C:\\Users\\Its\\Downloads\\EmployeeInOutDuration.xls");
//	 * System.out.println(file.getName()); FileInputStream fis = new
//	 * FileInputStream(file); uploadAttendanceFile(fis);
//	 * 
//	 * // Rest of your upload logic...
//	 * 
//	 * // Delete the fetched file after processing file.delete(); } catch (Exception
//	 * e) { // Handle the exception e.printStackTrace(); } }
//	 */
//	
//}
