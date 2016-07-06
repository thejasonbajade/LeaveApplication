package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class LeaveApplicationTest {
	
	private LeaveApplication leaveApplication;
	
	private LeaveApplication generateLeaveApplication(LocalDate startDate, LocalDate endDate) {
		final long filer_id = 1;
		final long approver_id = 2;
		EmployeeRecord record = new EmployeeRecord.Builder("John", "Cena",
				LocalDate.of(2010, 12, 3), 
				new Department(1, "Party"), 
				"youcantseeme@orangeandbronze.com", 
				"professional wrestler")
				.build();
		Employee filer = new Employee(filer_id, record);
		Supervisor approver = new Supervisor(approver_id, record);
		return new LeaveApplication(startDate, 
				endDate, 
				LeaveType.SICK_LEAVE, 
				"I am sick", 
				filer, 
				approver);
	}

	private void assertGetNumberOfLeaveDaysEvaluatesTo(int expected) {
		float numberOfLeaveDays = leaveApplication.getNumberOfLeaveDays();
		assertTrue("Number of leave days should be " + expected + 
				"\nNumber of leave days computed was " + numberOfLeaveDays, 
				expected == numberOfLeaveDays);
	}

	@Test
	public void leaveApplicationWithIntervalContainingOnlyWeekdaysShouldHaveCorrectNumberOfLeaveDays() 
			throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JUNE,20);
		LocalDate endDate = LocalDate.of(2016,Month.JUNE,24);
		leaveApplication = generateLeaveApplication(startDate, endDate);
		assertGetNumberOfLeaveDaysEvaluatesTo(5);
	}

	@Test
	public void leaveApplicationWithIntervalContainingWeekendsShouldHaveCorrectNumberOfLeaveDays() 
			throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JULY,1);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,5);
		leaveApplication = generateLeaveApplication(startDate, endDate);
		assertGetNumberOfLeaveDaysEvaluatesTo(3);
	}
	
	@Test
	public void leaveApplicationWithIntervalContainingHolidaysShouldHaveCorrectNumberOfLeaveDays() throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.AUGUST,29);
		LocalDate endDate = LocalDate.of(2016,Month.AUGUST,31);
		leaveApplication = generateLeaveApplication(startDate, endDate);
		assertGetNumberOfLeaveDaysEvaluatesTo(2);
	}

	@Test
	public void leaveApplicationWithEndDateBeforeStartDateRaisesAnException() {
		LocalDate startDate = LocalDate.of(2016,Month.NOVEMBER,12);
		LocalDate endDate = LocalDate.of(2016,Month.NOVEMBER,5);
		try {
			generateLeaveApplication(startDate, endDate);
			fail("Instantiating a LeaveApplication object should "
					+ "throw an exception if the end date "
					+ "is before start date.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
}
