package com.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.EmployeeAttendance;
@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
	List<EmployeeAttendance> findByEmployeeCode(String employeeCode);
	List<EmployeeAttendance> findByEmployeeName(String employeeName);
	List<EmployeeAttendance> findByEmployeeNameAndAttendanceDate(String employeeName, String attendanceDate);
	List<EmployeeAttendance> findByEmployeeCodeAndAttendanceDate(String code, String attendanceDate);
	List<EmployeeAttendance> findByAttendanceDate(String date);
    List<EmployeeAttendance> findByemployeeCodeAndAttendanceDateBetween(
            String employeeCode, String startDate, String endDate);
}