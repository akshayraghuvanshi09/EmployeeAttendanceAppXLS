package com.api.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.EmployeeAttendance;
import com.api.repo.EmployeeAttendanceRepository;

@Service
public class EmployeeAttendanceService {
	@Autowired
	private EmployeeAttendanceRepository attendanceRepository;

//	@Autowired
//	private Helper helper;

//	public void saveEmployeeAttendance(MultipartFile file) {
//
//		List<EmployeeAttendance> attendanceList = helper.uploadAttendanceFile(file);
//		attendanceRepository.saveAll(attendanceList);
//	}

	public List<EmployeeAttendance> getEmployeeAttendances() {
		return attendanceRepository.findAll();
	}

	public List<EmployeeAttendance> getEmpByEmployeeCode(String code) {
		return attendanceRepository.findByEmployeeCode(code);
	}

	public List<EmployeeAttendance> getEmpByEmployeeName(String name) {
		return attendanceRepository.findByEmployeeName(name);
	}

	public List<EmployeeAttendance> getEmpByEmployeeNameAndDate(String name, String date) {
		return attendanceRepository.findByEmployeeNameAndAttendanceDate(name, date);
	}

	public List<EmployeeAttendance> getEmpByEmployeeCodeAndDate(String code, String date) {
		return attendanceRepository.findByEmployeeCodeAndAttendanceDate(code, date);
	}

	public List<EmployeeAttendance> getEmpByAttendanceDate(String date) {
		return attendanceRepository.findByAttendanceDate(date);
	}

	public List<EmployeeAttendance> getEmployeeInDurationLessThanEightHours(String date) {
		List<EmployeeAttendance> attendanceDate = getEmpByAttendanceDate(date);
		return attendanceDate.stream().filter(attendance -> {
			String inDurationStr = attendance.getInDuration();
			if (inDurationStr != null) {
				LocalTime inDurationTime = LocalTime.parse(inDurationStr, DateTimeFormatter.ofPattern("HH:mm"));
				int inDurationMinutes = inDurationTime.getHour() * 60 + inDurationTime.getMinute();
				return inDurationMinutes < 8 * 60;
			}
			return false;
		}).collect(Collectors.toList());
	}

	public List<EmployeeAttendance> getPreviousDayAttendanceByEmployeeCode(String employeeCode) {
		LocalDate previousDay = LocalDate.now().minusDays(1);
		String previousDayStr = previousDay.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		return attendanceRepository.findByEmployeeCodeAndAttendanceDate(employeeCode, previousDayStr);
	}

	public List<EmployeeAttendance> getCurrentMonthAttendanceByEmployeeId(String employeeCode) {
		LocalDate currentDate = LocalDate.now();
		LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
		String firstDayOfMonthStr = firstDayOfMonth.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		LocalDate previousDayOfCurrentMonth = currentDate.minusDays(1);
		String previousDayOfCurrentMonthStr = previousDayOfCurrentMonth.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

		return attendanceRepository.findByemployeeCodeAndAttendanceDateBetween(employeeCode, firstDayOfMonthStr.toString(),
				previousDayOfCurrentMonthStr.toString());
	}

	  public List<EmployeeAttendance> getCurrentWeekAttendanceByEmployeeId(String employeeCode) {
	        LocalDate currentDate = LocalDate.now();
	        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
	        LocalDate previousDayOfCurrentWeek = currentDate.minusDays(1);

	        return attendanceRepository.findByemployeeCodeAndAttendanceDateBetween(
	        		employeeCode, startOfWeek.toString(), previousDayOfCurrentWeek.toString());
	    }
}