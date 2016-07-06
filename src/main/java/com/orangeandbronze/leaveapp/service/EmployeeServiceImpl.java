package com.orangeandbronze.leaveapp.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orangeandbronze.leaveapp.domain.Admin;
import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveType;
import com.orangeandbronze.leaveapp.domain.Supervisor;
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

	public void fileLeave(long employeeId, LocalDate startDate, LocalDate endDate, LeaveType leaveType, String reason, long approverId) {
		Employee employee = employeeRepository.findBy(employeeId);
		Supervisor supervisor = (Supervisor) employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication =  employee.fileLeave(startDate, endDate, leaveType, reason, supervisor); 
		leaveApplicationRepository.insert(leaveApplication);
	}

	public void cancelLeaveApplication(long employeeId, long leaveId) {
		Employee employee = employeeRepository.findBy(employeeId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		employee.cancel(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void approveLeaveApplication(long approverId, long leaveId) {
		//Supervisor approver = (Supervisor) employeeRepository.findBy(approverId);
		//Supervisor approver =  new Supervisor(1, "Jason", "Bajade");
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		//approver.approve(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void disapproveLeaveApplication(long approverId, long leaveId) {
		Supervisor approver = (Supervisor) employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		approver.disapprove(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void changeLeaveApplicationToNotTaken(long approverId, long leaveId) {
		Admin approver = (Admin) employeeRepository.findBy(approverId);
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(leaveId);
		approver.changeToNotTaken(leaveApplication);
		leaveApplicationRepository.updateLeaveStatus(leaveApplication);
	}

	public void findLeaveApplicationsForSupervisor(long supervisorId) {
		//Employee supervisor = employeeDao.findBy(supervisorId);
		Collection<LeaveApplication> leaveApplications = leaveApplicationRepository.findLeaveApplicationsForSupervisor(supervisorId);
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

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Department> findAllDepartments() {
		return departmentRepository.findAll();
	}
}