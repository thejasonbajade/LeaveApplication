package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class LeaveApplicationTest {
	
	private LeaveApplication leaveApplication;
	
	private LeaveApplication generateLeaveApplication(LocalDate startDate, LocalDate endDate) {

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
	public void leaveApplicationWithHalfDayStartAndEndDate() throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JULY,25);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,26);
		leaveApplication = new LeaveApplication(startDate, true, endDate, true, LeaveType.SICK_LEAVE, "I'm Sick", null, null);;
		assertGetNumberOfLeaveDaysEvaluatesTo(1);
	}
	
	@Test
	public void leaveApplicationWithHalfDayStartDate() throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JULY,25);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,26);
		leaveApplication = new LeaveApplication(startDate, true, endDate, false, LeaveType.SICK_LEAVE, "I'm Sick", null, null);;
		assertGetNumberOfLeaveDaysEvaluatesTo((float)1.5);
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
