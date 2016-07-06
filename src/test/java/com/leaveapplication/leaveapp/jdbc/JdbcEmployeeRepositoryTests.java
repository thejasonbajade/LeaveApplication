package com.leaveapplication.leaveapp.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.EmployeeRecord;
import com.orangeandbronze.leaveapp.domain.EmploymentStatus;
import com.orangeandbronze.leaveapp.jdbc.JdbcEmployeeRepository;

public class JdbcEmployeeRepositoryTests {
	
	private JdbcEmployeeRepository employeeRepository;
	private long emp_id = 1;
	
	@Before
	public void setUp() throws Exception{
		DataSource dataSource = createTestDataSource();
		employeeRepository = new JdbcEmployeeRepository(dataSource);
	}

	@Test
	public void testFindBy() throws Exception{
		Employee employee = employeeRepository.findBy(emp_id);
		assertNotNull(employee);
		EmployeeRecord record = employee.getRecord();
		
		assertEquals("REGULAR", record.getStatus().toString());
		assertEquals("John", record.getFirstName());
		assertEquals("Cena", record.getLastName());
		assertEquals(emp_id, employee.getEmployeeId());
	}

	@Test
	public void testAddEmployee() throws Exception{
		final long id = 2;
		final long dept_id = 1;
		final String dept_name = "Party";
		final Department department = new Department(dept_id, dept_name);
		final String firstName = "Shia";
		final String lastName = "Labeouf";
		EmployeeRecord record = new EmployeeRecord.Builder(firstName, lastName, LocalDate.now(), department, "doit@orangeandbronze.com", "Scrum Master")
				.regularizationDate(LocalDate.now())
				.employmentStatus(EmploymentStatus.REGULAR)
				.build();
		Employee employee = new Employee(id, record);
		employeeRepository.add(employee);
		verifyInsertedValues(id, firstName, lastName);
	}

	private void verifyInsertedValues(long id, String expectedFirstName, String expectedLastName) {
		Employee employee = employeeRepository.findBy(id);
		assertNotNull(employee);
		
		EmployeeRecord record = employee.getRecord();
		
		assertEquals("REGULAR", record.getStatus().toString());
		assertEquals("Shia", record.getFirstName());
		assertEquals("Labeouf", record.getLastName());
		assertEquals(id, employee.getEmployeeId());
	}

	private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setName("employee")
				.addScript("/leaveapp/testdb/LeaveApp.sql")
				.addScript("/leaveapp/testdb/test-data.sql")
				.build();
	}

}
