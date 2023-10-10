package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.entity.EmployeeAttendance;
import com.api.service.EmployeeAttendanceService;

@RestController
@RequestMapping("/api")
public class EmployeeAttendanceController {
	@Autowired
	private EmployeeAttendanceService attendanceService;

//	@PostMapping("/upload")
//	public ResponseEntity<String> uploadAttendanceFile(@RequestParam("file") MultipartFile file) {
//		attendanceService.saveEmployeeAttendance(file);
//		return ResponseEntity.ok("File uploaded and data saved successfully.");
//
//	}

	@GetMapping("/getAttendances")
	public ResponseEntity<List<EmployeeAttendance>> getEmployeeAttendances() {
		List<EmployeeAttendance> employeeAttendances = attendanceService.getEmployeeAttendances();
		return ResponseEntity.ok(employeeAttendances);
	}

	@GetMapping("/getAttendanceByEmpCode/{id}")
	public ResponseEntity<List<EmployeeAttendance>> getEmployeeAttendanceByEmployeeCode(
			@PathVariable("id") String code) {
		List<EmployeeAttendance> empByEmployeeCode = attendanceService.getEmpByEmployeeCode(code);
		return ResponseEntity.ok(empByEmployeeCode);
	}

	@GetMapping("/getAttendanceByEmpName/{name}")
	public ResponseEntity<List<EmployeeAttendance>> getEmployeeAttendanceByEmployeeName(
			@PathVariable("name") String name) {
		List<EmployeeAttendance> empByEmployeeName = attendanceService.getEmpByEmployeeName(name);
		return ResponseEntity.ok(empByEmployeeName);
	}

	@GetMapping("/getAttendanceByEmpNameAndDate/{name}/{date}")
	public ResponseEntity<List<EmployeeAttendance>> getEmployeeAttendanceByEmployeeNameAndDate(
			@PathVariable("name") String name, @PathVariable("date") String date) {
		List<EmployeeAttendance> empByEmployeeNameAndDate = attendanceService.getEmpByEmployeeNameAndDate(name, date);
		return ResponseEntity.ok(empByEmployeeNameAndDate);
	}

	@GetMapping("/getAttendanceByEmpCodeAndDate/{code}/{date}")
	public ResponseEntity<List<EmployeeAttendance>> getEmployeeAttendanceByEmployeeCodeAndDate(
			@PathVariable("code") String name, @PathVariable("date") String date) {
		List<EmployeeAttendance> codeAndDate = attendanceService.getEmpByEmployeeCodeAndDate(name, date);
		return ResponseEntity.ok(codeAndDate);
	}

	@GetMapping("/getAttendanceByAttendanceDate/{date}")
	public ResponseEntity<List<EmployeeAttendance>> getEmployeeAttendanceByAttendanceDate(
			@PathVariable("date") String date) {
		List<EmployeeAttendance> attencancesByDate = attendanceService.getEmpByAttendanceDate(date);
		return ResponseEntity.ok(attencancesByDate);
	}

	@GetMapping("/getEmployeeLessThanEightHours/{date}")
	public ResponseEntity<List<EmployeeAttendance>> getEmployeeInDurationLessThanEightHours(
			@PathVariable("date") String date) {
		List<EmployeeAttendance> lessThanEightHours = attendanceService.getEmployeeInDurationLessThanEightHours(date);
		return ResponseEntity.ok(lessThanEightHours);
	}

	@GetMapping("/previousDayAttendance")
	public ResponseEntity<List<EmployeeAttendance>> getPreviousDayAttendance(@RequestParam String employeeCode) {
		List<EmployeeAttendance> previousDayAttendance = attendanceService
				.getPreviousDayAttendanceByEmployeeCode(employeeCode);

		return ResponseEntity.ok(previousDayAttendance);
	}

	@GetMapping("/currentMonthAttendance")
	public ResponseEntity<List<EmployeeAttendance>> getCurrentMonthAttendance(@RequestParam String employeeCode) {
		List<EmployeeAttendance> currentMonthAttendance = attendanceService
				.getCurrentMonthAttendanceByEmployeeId(employeeCode);

		return ResponseEntity.ok(currentMonthAttendance);
	}
	
	@GetMapping("/currentWeekAttendance")
    public ResponseEntity<List<EmployeeAttendance>> getCurrentWeekAttendance(
            @RequestParam String employeeCode) {
        List<EmployeeAttendance> currentWeekAttendance = 
            attendanceService.getCurrentWeekAttendanceByEmployeeId(employeeCode);
        
        return ResponseEntity.ok(currentWeekAttendance);
    }

}