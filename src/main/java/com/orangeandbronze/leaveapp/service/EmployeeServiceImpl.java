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

	public void fileLeave(int employeeId, LocalDate startDate, LocalDate endDate, LeaveType leaveType, String reason, int approverId) {
		Employee employee = employeeRepository.findBy(employeeId);
		Supervisor supervisor = (Supervisor) employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication =  employee.fileLeave(startDate, endDate, leaveType, reason, supervisor); 
		leaveApplicationRepository.insert(leaveApplication);
	}

	public void cancelLeaveApplication(int employeeId, int leaveId) {
		Employee employee = employeeRepository.findBy(employeeId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		employee.cancel(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void approveLeaveApplication(int approverId, int leaveId) {
		//Supervisor approver = (Supervisor) employeeRepository.findBy(approverId);
		Supervisor approver =  new Supervisor(1, "Jason", "Bajade");
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		approver.approve(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void disapproveLeaveApplication(int approverId, int leaveId) {
		Supervisor approver = (Supervisor) employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		approver.disapprove(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void changeLeaveApplicationToNotTaken(int approverId, int leaveId) {
		Admin approver = (Admin) employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		approver.changeToNotTaken(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void findLeaveApplicationsForSupervisor(int supervisorId) {
		//Employee supervisor = employeeDao.findBy(supervisorId);
		Collection<LeaveApplication> leaveApplications = leaveApplicationRepository.findLeaveApplicationsForSupervisor(supervisorId);
	}
	
	@Override
	public int addEmployee(Employee employee) {
		//Employee employee = new Employee(); //TODO: finalize Employee parameters
		return employeeRepository.create(employee);
	}

	@Override
	public Employee viewEmployee(int employeeId) {
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
