package com.orangeandbronze.leaveapp.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.orangeandbronze.leaveapp.domain.Admin;
import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.EmployeeRecord;
import com.orangeandbronze.leaveapp.domain.EmploymentStatus;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveStatus;
import com.orangeandbronze.leaveapp.domain.LeaveType;
import com.orangeandbronze.leaveapp.domain.Supervisor;

public class EmployeeTest {

	private Employee employee;
	private Supervisor supervisor;
	private Admin admin;
	private LeaveApplication leaveApplication; 
	private LeaveApplication newLeaveApplication;
	private LocalDate startDate;
	private LocalDate endDate;

	@Before
	public void setUp() {
		employee = generateEmployee();
		supervisor = generateSupervisor();
		admin = generateAdmin();
		startDate = LocalDate.of(2016, 11, 5);
		endDate = LocalDate.of(2016, 11, 12);
		newLeaveApplication = employee.fileLeave(startDate, endDate, LeaveType.SICK_LEAVE, supervisor);
	}

	private Admin generateAdmin() {
		final long emp_id = 24;
		EmployeeRecord record = generateRecord();
		return new Admin(emp_id, record);
	}

	private EmployeeRecord generateRecord() {
		return new EmployeeRecord.Builder("John", "Cena", "youcantseeme@orangeandbronze.com", "vice-president", generateDepartment(), LocalDate.now()).build();
	}
	
	private Department generateDepartment(){
		return new Department(1, "Party");
	}

	private Supervisor generateSupervisor() {
		final long id = 1;
		EmployeeRecord record = generateRecord();
		return new Supervisor(id, record);
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
		supervisor.approve(newLeaveApplication);
		assertTrue("Leave Application status should be " 
				+ LeaveStatus.SUPERVISOR_APPROVED + " when "
				+ "the supervisor approves the leave.", 
				newLeaveApplication.getStatus() == LeaveStatus.SUPERVISOR_APPROVED);
	}

	@Test
	public void adminApprovesALeaveApplicationApprovedByASupervisor() throws Exception {
		supervisor.approve(newLeaveApplication);
		admin.approve(newLeaveApplication);
		assertTrue("Leave Application status should be " 
				+ LeaveStatus.ADMIN_APPROVED + " when "
				+ "the admin approves the leave.", 
				newLeaveApplication.getStatus() == LeaveStatus.ADMIN_APPROVED);
	}

	@Test
	public void disapproveLeaveApplicationBySupervisor() {
		leaveApplication = new LeaveApplication(startDate, endDate, LeaveType.SICK_LEAVE, "I am sick", LeaveStatus.PENDING, employee, supervisor);
		supervisor.disapprove(leaveApplication);

		assertEquals(LeaveStatus.SUPERVISOR_DISAPPROVED, leaveApplication.getStatus());
	}

	@Test
	public void disapproveLeaveApplicationByAdmin() {
		leaveApplication = new LeaveApplication(startDate, endDate, LeaveType.SICK_LEAVE, LeaveStatus.SUPERVISOR_APPROVED, employee, supervisor);
		admin.disapprove(leaveApplication);

		assertEquals(LeaveStatus.ADMIN_DISAPPROVED, leaveApplication.getStatus());
	}

	@Test
	public void changeLeaveApplicationToNotTakenByAdmin() {
		leaveApplication = new LeaveApplication(startDate, endDate, LeaveType.SICK_LEAVE, LeaveStatus.ADMIN_APPROVED, employee, supervisor);
		admin.changeToNotTaken(leaveApplication);
		assertEquals(LeaveStatus.NOT_TAKEN, leaveApplication.getStatus());
	}

	@Test
	public void aNonRegularEmployeeShouldHaveAProbationaryStatus() throws Exception {
		assertTrue(employee.getStatus() == EmploymentStatus.PROBATIONARY);
	}

	@Test
	public void adminRegularizingAnEmployee() throws Exception {
		admin.regularize(employee);
		assertTrue(employee.getStatus() == EmploymentStatus.REGULAR);
	}
}
