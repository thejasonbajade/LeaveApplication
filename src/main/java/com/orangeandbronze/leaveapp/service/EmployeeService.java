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
	
	public void fileLeave(int employeeId, LocalDate startDate, LocalDate endDate, LeaveType leaveType, String reason, int approverId);

	public void cancelLeaveApplication(int employeeId, int leaveId);

	public void approveLeaveApplication(int approverId, int leaveId);
	
	public void disapproveLeaveApplication(int approverId, int leaveId);

	public void changeLeaveApplicationToNotTaken(int approverId, int leaveId);

	public void findLeaveApplicationsForSupervisor(int supervisorId);
	
	public int addEmployee(Employee employee);
	
	public Employee viewEmployee(int employeeId);
	
	public List<Employee> viewAllEmployee();
	
	public List<Employee> findAllSupervisor();
}

