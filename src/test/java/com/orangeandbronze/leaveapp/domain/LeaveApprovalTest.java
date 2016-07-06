package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

public class LeaveApprovalTest {

	private LeaveApplication newLeaveApplication;
	private Employee filer;
	private Employee supervisor;
	private Employee admin;

	@Before
	public void setUp() {
		filer = generateEmployee(1);
		supervisor = generateEmployee(2);
		admin = generateEmployee(3);
		newLeaveApplication = generateLeaveApplication(filer);
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

	private LeaveApplication generateLeaveApplication(Employee filer) {
		LocalDate startDate = LocalDate.of(2016, Month.DECEMBER, 5);
		LocalDate endDate = LocalDate.of(2016, Month.DECEMBER, 12);
		LeaveDetails details = new LeaveDetails(startDate, false, endDate, false, LeaveType.SICK_LEAVE, "I am sick");
		return filer.fileLeave(details, supervisor);
	}

	private void assertGetLeaveStausEvaluatesTo(LeaveStatus status) {
		assertTrue(newLeaveApplication.getStatus() == status);
	}

	@Test
	public void leaveApprovedByASupervisorShouldBeInSupervisorApprovedStatus() 
			throws Exception {
		supervisor.approve(newLeaveApplication);
		assertGetLeaveStausEvaluatesTo(LeaveStatus.SUPERVISOR_APPROVED);
	}
	
	@Test
	public void leaveApprovedByAnAdminShouldBeInAdminApprovedStatus() 
			throws Exception {
		supervisor.approve(newLeaveApplication);
		admin.approve(newLeaveApplication);
		assertGetLeaveStausEvaluatesTo(LeaveStatus.ADMIN_APPROVED);
	}
	
	@Test
	public void leaveDisapprovedBySupervisorShouldBeInSupervisorDisapprovedStatus() 
			throws Exception {
		supervisor.disapprove(newLeaveApplication);
		assertGetLeaveStausEvaluatesTo(LeaveStatus.SUPERVISOR_DISAPPROVED);
	}
	
	@Test
	public void leaveDisapprovedByAdminShouldBeInAdminDisapprovedStatus() 
			throws Exception {
		supervisor.approve(newLeaveApplication);
		admin.disapprove(newLeaveApplication);
		assertGetLeaveStausEvaluatesTo(LeaveStatus.ADMIN_DISAPPROVED);
	}
}
