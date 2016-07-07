package com.orangeandbronze.leaveapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveType;

@Service
public interface EmployeeService {
	
	public int fileLeave(long employeeId, LocalDate startDate, boolean isStartHalfDay, LocalDate endDate, boolean isEndHalfDay, LeaveType leaveType, String reason, long approverId);

	public void cancelLeaveApplication(long employeeId, long leaveId);

	public void approveLeaveApplication(long approverId, long leaveId);
	
	public void disapproveLeaveApplication(long approverId, long leaveId);

	public void changeLeaveApplicationToNotTaken(long approverId, long leaveId);

	public List<LeaveApplication> findLeaveApplicationsForSupervisor(long supervisorId);
	
	public int addEmployee(Employee employee);
	
	public int updateLeaveCreditsOf(Employee employee);
	
	public int updateEmploymentStatusOf(Employee employee);
	
	public Employee viewEmployee(long employeeId);
	
	public List<Employee> viewAllEmployee();
	
	public List<Employee> findAllSupervisor();

	public List<Employee> findAllEmployees();

	public List<Department> findAllDepartments();

	public int updateEmployee(Employee employee);
}
