package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.EmployeeRecord;
import com.orangeandbronze.leaveapp.domain.EmploymentStatus;

public class EmployeeTest {
	
	private Employee employee;
	private Employee admin;

	@Before
	public void setUp() {
		employee = generateEmployee(1);
		admin = generateEmployee(2);
	}

	private Employee generateEmployee(long id) {
		Department department = new Department(1, "Partying");
		EmployeeRecord record = new EmployeeRecord.Builder("John", 
				"Cena", 
				LocalDate.now(), 
				department, 
				"youcantseeme@orangeandbronze.com", 
				"Professional Wrestler")
				.build();
		return new Employee(id, record);
	}
	
	@Test
	public void regularizingAnEmployee() throws Exception {
		admin.regularize(employee);
		assertEquals(EmploymentStatus.REGULAR, employee.getRecord().getStatus());
	}
	
	@Test
	public void terminatingAnEmployee() throws Exception {
		admin.terminate(employee);
		assertEquals(EmploymentStatus.TERMINATED, employee.getRecord().getStatus());
	}
}
