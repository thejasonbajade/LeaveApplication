package com.leaveapplication.leaveapp.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.EmployeeRecord;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveDetails;
import com.orangeandbronze.leaveapp.domain.LeaveStatus;
import com.orangeandbronze.leaveapp.domain.LeaveType;
import com.orangeandbronze.leaveapp.jdbc.JdbcLeaveApplicationRepository;

public class JdbcLeaveApplicationRepositoryTest {
	
	private JdbcLeaveApplicationRepository leaveApplicationRepository;
	
	@Before
	public void setUp() throws Exception {
		leaveApplicationRepository = new JdbcLeaveApplicationRepository(createTestDataSource());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFindLeaveById() throws Exception {
		LeaveApplication leaveApplication = leaveApplicationRepository.findBy(1);
		assertNotNull(leaveApplication);
		assertEquals(1, leaveApplication.getLeaveId());
		assertEquals("I am Sick", leaveApplication.getLeaveDetails().getReason());
		assertEquals(LeaveStatus.PENDING, leaveApplication.getStatus());
	}
	
	@Test
	public void testFindAllLeaveApplications() {
		Collection<LeaveApplication> leaveApplications = leaveApplicationRepository.findAllLeaveApplications();
		
		assertNotNull(leaveApplications);
		assertEquals(5, leaveApplications.size());
	}

	@Test
	public void testFindLeaveApplicationsForSupervisor() {
		Collection<LeaveApplication> leaveApplications = leaveApplicationRepository.findLeaveApplicationsForSupervisor(2);
		
		assertNotNull(leaveApplications);
		assertEquals(5, leaveApplications.size());
	}
	
	@Test
	public void testFindLeaveApplicationsByEmployee() {
		Collection<LeaveApplication> leaveApplications = leaveApplicationRepository.findLeaveApplicationsByEmployee(1);
		
		assertNotNull(leaveApplications);
		assertEquals(1, leaveApplications.size());
	}
	
	@Test
	public void testInsertLeaveApplication() {
		Employee employee = generateEmployee(1);
		Employee supervisor = generateEmployee(2);
		LeaveDetails details = new LeaveDetails(LocalDate.of(2016, Month.JULY, 1), false, LocalDate.of(2016, Month.JULY, 4), false, LeaveType.SICK_LEAVE, "I am sick");
		LeaveApplication leaveApplicationExpected = new LeaveApplication(1, details, LeaveStatus.PENDING, employee, supervisor);
		
		int affectedRow = leaveApplicationRepository.insert(leaveApplicationExpected);
		
		LeaveApplication leaveApplicationActual = leaveApplicationRepository.findBy(1);
		
		assertEquals(1, affectedRow);
		assertTrue(leaveApplicationExpected.getStatus() == leaveApplicationActual.getStatus());
	}
	
	@Test
	public void testUpdateLeaveApplicationStatus() {
		LeaveApplication leaveApplicationExpected = leaveApplicationRepository.findBy(1);
		
		leaveApplicationRepository.updateLeaveStatus(leaveApplicationExpected);
		
		LeaveApplication leaveApplicationActual = leaveApplicationRepository.findBy(1);
		assertEquals(LeaveStatus.SUPERVISOR_APPROVED, leaveApplicationActual.getStatus());
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
	
	private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
			.setName("LeaveApp")
			.addScript("classpath:/leaveapp/testdb/LeaveApp.sql")
			.addScript("classpath:/leaveapp/testdb/test-data.sql")
			.build();
	}	
}
