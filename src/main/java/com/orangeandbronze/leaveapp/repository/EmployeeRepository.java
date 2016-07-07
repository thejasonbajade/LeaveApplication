package com.orangeandbronze.leaveapp.repository;

import java.util.List;

import com.orangeandbronze.leaveapp.domain.Employee;

public interface EmployeeRepository {
	
	public Employee findBy(long employeeId);
	
	public List<Employee> findAll();
	
	public List<Employee> findAllSupervisors();
	
	public int[] employeeLeaveCreditsBatchAwarding(final List<Employee> employees);
	
	public int[] employeeLeaveCreditsBatchReset(final List<Employee> employees);
	
	public int updateLeaveCreditsOf(Employee employee);
	
	public int updateEmploymentStatusOf(Employee employee);

	public int add(Employee employee);

	public int update(Employee employee);
}	
