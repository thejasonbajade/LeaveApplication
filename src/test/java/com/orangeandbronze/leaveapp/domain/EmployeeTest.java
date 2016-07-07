package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.EmployeeRecord;
import com.orangeandbronze.leaveapp.domain.EmploymentStatus;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveStatus;
import com.orangeandbronze.leaveapp.domain.LeaveType;


public class EmployeeTest {

	private Employee employee;
	private LeaveApplication leaveApplication; 
	private LeaveApplication newLeaveApplication;
	private LeaveDetails leaveDetails;
	private LocalDate startDate;
	private LocalDate endDate;

	@Before
	public void setUp() {
		employee = generateEmployee();
		startDate = LocalDate.of(2016, 11, 5);
		endDate = LocalDate.of(2016, 11, 12);
		leaveDetails = new LeaveDetails(startDate, true, endDate, true, LeaveType.SICK_LEAVE, "I'm Sick");
		newLeaveApplication = employee.fileLeave(leaveDetails, null);
	}

	private EmployeeRecord generateRecord() {
		return new EmployeeRecord.Builder("John", "Cena", LocalDate.now(), generateDepartment(), "youcantseeme@orangeandbronze.com", "vice-president").build();
	}
	
	private Department generateDepartment(){
		return new Department(1, "Party");
	}

	private Employee generateEmployee() {
		final long id = 42;
		EmployeeRecord record = generateRecord();
		return new Employee(id, record);
	}

	@Test
	public void employeeCancelsALeave() throws Exception {
		employee.cancel(newLeaveApplication);
		assertTrue("Leave Application status should be " 
				+ LeaveStatus.CANCELLED + "when the filer"
				+ " cancels the leave",
				newLeaveApplication.getStatus() == LeaveStatus.CANCELLED);
	}

	@Test
	public void supervisorApprovesALeaveApplicationOfAnEmployee() 
			throws Exception {
		employee.approve(newLeaveApplication);
		assertTrue("Leave Application status should be " 
				+ LeaveStatus.SUPERVISOR_APPROVED + " when "
				+ "the supervisor approves the leave.", 
				newLeaveApplication.getStatus() == LeaveStatus.SUPERVISOR_APPROVED);
	}

	@Test
	public void adminApprovesALeaveApplicationApprovedByASupervisor() throws Exception {
		employee.approve(newLeaveApplication);
		employee.approve(newLeaveApplication);
		assertTrue("Leave Application status should be " 
				+ LeaveStatus.ADMIN_APPROVED + " when "
				+ "the admin approves the leave.", 
				newLeaveApplication.getStatus() == LeaveStatus.ADMIN_APPROVED);
	}

	@Test
	public void disapproveLeaveApplicationBySupervisor() {
		leaveApplication = new LeaveApplication(1, leaveDetails, LeaveStatus.PENDING, employee, null);
		employee.disapprove(leaveApplication);

		assertEquals(LeaveStatus.SUPERVISOR_DISAPPROVED, leaveApplication.getStatus());
	}

	@Test
	public void disapproveLeaveApplicationByAdmin() {
		leaveApplication = new LeaveApplication(1, leaveDetails, LeaveStatus.SUPERVISOR_APPROVED, employee, null);
		employee.disapprove(leaveApplication);

		assertEquals(LeaveStatus.ADMIN_DISAPPROVED, leaveApplication.getStatus());
	}

	@Test
	public void changeLeaveApplicationToNotTakenByAdmin() {
		leaveApplication = new LeaveApplication(1, leaveDetails, LeaveStatus.ADMIN_APPROVED, employee, null);
		employee.changeToNotTaken(leaveApplication);
		assertEquals(LeaveStatus.NOT_TAKEN, leaveApplication.getStatus());
	}

	@Test
	public void aNonRegularEmployeeShouldHaveAProbationaryStatus() throws Exception {
		assertTrue(employee.getEmployeeRecord().getStatus() == EmploymentStatus.PROBATIONARY);
	}

	@Test
	public void adminRegularizingAnEmployee() throws Exception {
		employee.regularize(employee);
		assertTrue(employee.getEmployeeRecord().getStatus() == EmploymentStatus.REGULAR);
	}
}
