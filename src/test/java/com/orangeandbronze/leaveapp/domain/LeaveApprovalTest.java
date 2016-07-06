package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class LeaveApprovalTest {

	private LeaveApplication newLeaveApplication;
	Employee filer;
	private Supervisor supervisor;
	private Admin admin;

	@Before
	public void setUp() {
		filer = new Employee();
		supervisor = new Supervisor();
		admin = new Admin();
		newLeaveApplication = generateLeaveApplication(filer);
	}

	private LeaveApplication generateLeaveApplication(Employee filer) {
		Calendar startDate = new GregorianCalendar(2016,11,5);
		Calendar endDate = new GregorianCalendar(2016,11,12);
		return filer.fileLeave(startDate, endDate, LeaveType.SICK_LEAVE, supervisor);
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
