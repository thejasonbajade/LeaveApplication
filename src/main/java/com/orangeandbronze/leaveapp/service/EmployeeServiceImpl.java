package com.orangeandbronze.leaveapp.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orangeandbronze.leaveapp.domain.*;
import com.orangeandbronze.leaveapp.repository.*;

@Service
@Transactional
@Component("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	private LeaveApplicationRepository leaveApplicationRepository;

	@Autowired
	public EmployeeServiceImpl(
			EmployeeRepository employeeRepository, 
			LeaveApplicationRepository leaveApplicationRepository) {
		this.employeeRepository = employeeRepository;
		this.leaveApplicationRepository = leaveApplicationRepository;
	}

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
		//Employee employee = new Employee(); //TODO: finalize Employee parameters
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
	
	public List<Employee> findAllSupervisor() {
		return employeeRepository.findAllSupervisors();
	}
}