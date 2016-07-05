package com.orangeandbronze.leaveapp.repository;

import java.util.List;

import com.orangeandbronze.leaveapp.domain.Employee;

public interface EmployeeRepository {
	
	public Employee findBy(long employeeId);
	
	public List<Employee> findAll();
	
	public List<Employee> findAllSupervisors();
	
	public void update(Employee employee);

	public void add(Employee employee);
}	
