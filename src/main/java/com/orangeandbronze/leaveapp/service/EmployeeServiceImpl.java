package com.orangeandbronze.leaveapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveDetails;
import com.orangeandbronze.leaveapp.domain.LeaveType;
import com.orangeandbronze.leaveapp.repository.DepartmentRepository;
import com.orangeandbronze.leaveapp.repository.EmployeeRepository;
import com.orangeandbronze.leaveapp.repository.LeaveApplicationRepository;

@Service
@Transactional
@Component("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	private LeaveApplicationRepository leaveApplicationRepository;
	private DepartmentRepository departmentRepository;

	@Autowired
	public EmployeeServiceImpl(
			EmployeeRepository employeeRepository, 
			LeaveApplicationRepository leaveApplicationRepository,
			DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.leaveApplicationRepository = leaveApplicationRepository;
		this.departmentRepository = departmentRepository;
	}
	
	@Override
	public int fileLeave(
			long employeeId, LocalDate startDate, boolean isStartHalfDay,
			LocalDate endDate, boolean isEndHalfDay, LeaveType leaveType, String reason, long approverId) {
		Employee employee = employeeRepository.findBy(employeeId);
		Employee approver = employeeRepository.findBy(approverId);
		LeaveDetails leaveDetails = new LeaveDetails(startDate, isStartHalfDay, endDate, isEndHalfDay, leaveType, reason);
		LeaveApplication leaveApplication =  employee.fileLeave(leaveDetails, approver); 
		return leaveApplicationRepository.insert(leaveApplication);
	}

	public void cancelLeaveApplication(long employeeId, long leaveId) {
		Employee employee = employeeRepository.findBy(employeeId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		employee.cancel(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void approveLeaveApplication(long approverId, long leaveId) {
		Employee approver = employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		approver.approve(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
		employeeRepository.update(leaveApplication.getFiler());
	}	

	public void disapproveLeaveApplication(long approverId, long leaveId) {
		Employee approver =  employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		approver.disapprove(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void changeLeaveApplicationToNotTaken(long approverId, long leaveId) {
		Employee approver = employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		approver.changeToNotTaken(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}
	
	public List<LeaveApplication> findLeaveApplicationsForSupervisor(long supervisorId) {
		return leaveApplicationRepository.findLeaveApplicationsForSupervisor(supervisorId);
	}
	
	@Override
	public int addEmployee(Employee employee) {
		return employeeRepository.add(employee);
	}

	@Override
	public Employee viewEmployee(long employeeId) {
		return employeeRepository.findBy(employeeId);
	}

	@Override
	public List<Employee> viewAllEmployee() {
		return employeeRepository.findAll();
	}
	
	@Override
	public List<Employee> findAllSupervisor() {
		return employeeRepository.findAllSupervisors();
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Department> findAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public int updateLeaveCreditsOf(Employee employee) {
		return employeeRepository.updateLeaveCreditsOf(employee);
	}

	@Override
	public int regularizeEmployeeWithId(long employeeId) {
		return employeeRepository.regularize(employeeId);
	}

	@Override
	public int updateEmployee(Employee employee) {
		return employeeRepository.update(employee);
	}

	@Override
	public Employee findEmployeeWithId(long employeeId) {
		return employeeRepository.findBy(employeeId);
	}

	@Override
	public int deactivateEmployeeAccountWithId(long employeeId) {
		return employeeRepository.deactivate(employeeId);
	}
}