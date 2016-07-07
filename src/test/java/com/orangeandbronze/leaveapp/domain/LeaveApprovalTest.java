package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class LeaveApprovalTest {

	private LeaveApplication newLeaveApplication;
	Employee filer;

	@Before
	public void setUp() {
		filer = new Employee();
		newLeaveApplication = generateLeaveApplication(filer);
	}

	private LeaveApplication generateLeaveApplication(Employee filer) {
		LocalDate startDate = LocalDate.of(2016,11,5);
		LocalDate endDate = LocalDate.of(2016,11,12);
		LeaveDetails leaveDetails = new LeaveDetails();
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
