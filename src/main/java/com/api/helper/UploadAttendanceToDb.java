package com.api.helper;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.EmployeeAttendance;
import com.api.repo.EmployeeAttendanceRepository;
import com.api.service.EmployeeAttendanceService;
@Service
public class UploadAttendanceToDb {
	@Autowired
	private EmployeeAttendanceService attendanceService;
	
	@Autowired
	private EmployeeAttendanceRepository attendanceRepository;

	public void uploadAttendanceFile(File file) {
	    try {
	        List<EmployeeAttendance> attendanceList = new ArrayList<>();

	        FileInputStream fis = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(fis);

	        Sheet sheet = workbook.getSheetAt(0);

	        int rowIndexs = 9;
	        Row rows = sheet.getRow(rowIndexs);
	        String date = getStringCellValue(rows.getCell(5));
	        System.out.println(date + "==================================");
	        // Assuming the first row is the header, start from the second row
	        System.out.println(sheet.getLastRowNum());
	        for (int rowIndex = 12; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	            Row row = sheet.getRow(rowIndex);

	            // Check if the row is null or empty
	            if (row == null || isEmptyRow(row)) {
	                continue; // Skip empty rows
	            }

	            EmployeeAttendance attendance = new EmployeeAttendance();
	            attendance.setAttendanceDate(date);
	            attendance.setEmployeeCode(getStringCellValue(row.getCell(3)));
	            attendance.setEmployeeName(getStringCellValue(row.getCell(5)));
	            attendance.setInDuration(getStringCellValue(row.getCell(7)));
	            attendance.setOutDuration(getStringCellValue(row.getCell(8)));
	            attendance.setPunchRecords(getStringCellValue(row.getCell(10)));
	            attendanceList.add(attendance);
	        }

	        attendanceRepository.saveAll(attendanceList);
//	        return attendanceList;
	    } catch (Exception e) {
	        // Handle exceptions and return an appropriate response
	        e.printStackTrace(); // Print the stack trace for debugging
	      //  return null; // Return a suitable response indicating an error
	    }
	}
	private String getStringCellValue(Cell cell) {

		if (cell == null) {
			return null;
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			// Depending on the formatting of the numeric cell, you might need to handle the
			// value conversion
			// Example: return String.valueOf(cell.getNumericCellValue());
			return String.valueOf(cell.getNumericCellValue()); // You can customize the conversion based on your data
		default:
			return "null";
		}
	}

	private boolean isEmptyRow(Row row) {
		for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
			Cell cell = row.getCell(cellIndex);
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				return false; // Row is not empty
			}
		}
		return true; // Row is empty
	}

	// ... (other code)

	// Rest of your methods remain the same


}
