package com.orangeandbronze.leaveapp.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.LeaveType;
import com.orangeandbronze.leaveapp.domain.Supervisor;

@Service
public interface EmployeeService {
	
	public void fileLeave(long employeeId, LocalDate startDate, LocalDate endDate, LeaveType leaveType, String reason, long approverId);

	public void cancelLeaveApplication(long employeeId, long leaveId);

	public void approveLeaveApplication(long approverId, long leaveId);
	
	public void disapproveLeaveApplication(long approverId, long leaveId);

	public void changeLeaveApplicationToNotTaken(long approverId, long leaveId);

	public void findLeaveApplicationsForSupervisor(long supervisorId);
	
	public int addEmployee(Employee employee);
	
	public Employee viewEmployee(long employeeId);
	
	public List<Employee> viewAllEmployee();
	
	public List<Employee> findAllSupervisor();

	public List<Employee> findAllEmployees();
}

