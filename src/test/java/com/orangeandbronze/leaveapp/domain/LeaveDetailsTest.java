package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class LeaveDetailsTest {
	
	private LeaveDetails leaveDetails;
	
	private LeaveDetails generateLeaveDetails(LocalDate startDate, boolean isStartHalfDay, LocalDate endDate, boolean isEndHalfDay) {
		return new LeaveDetails(startDate, isStartHalfDay, endDate, isEndHalfDay, LeaveType.SICK_LEAVE, "I am sick");
	}

	private void assertGetNumberOfLeaveDaysEvaluatesTo(float expected) {
		float numberOfLeaveDays = leaveDetails.getNumberOfLeaveDays();
		assertTrue("Number of leave days should be " + expected + 
				"\nNumber of leave days computed was " + numberOfLeaveDays, 
				expected == numberOfLeaveDays);
	}

	@Test
	public void leaveApplicationWithIntervalContainingOnlyWeekdaysShouldHaveCorrectNumberOfLeaveDays() 
			throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JUNE,20);
		LocalDate endDate = LocalDate.of(2016,Month.JUNE,24);
		leaveDetails = generateLeaveDetails(startDate, false, endDate, false);
		assertGetNumberOfLeaveDaysEvaluatesTo(5);
	}

	@Test
	public void leaveApplicationWithIntervalContainingWeekendsShouldHaveCorrectNumberOfLeaveDays() 
			throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JULY,9);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,16);
		leaveDetails = generateLeaveDetails(startDate, false, endDate, false);
		assertGetNumberOfLeaveDaysEvaluatesTo(5);
	}
	
	@Test
	public void leaveApplicationWithIntervalContainingHolidaysShouldHaveCorrectNumberOfLeaveDays() throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.AUGUST,29);
		LocalDate endDate = LocalDate.of(2016,Month.AUGUST,31);
		leaveDetails = generateLeaveDetails(startDate, false, endDate, false);
		assertGetNumberOfLeaveDaysEvaluatesTo(2);
	}
	
	@Test
	public void leaveApplicationWithHalfDayStartAndEndDate() throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JULY,25);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,26);
		leaveDetails = generateLeaveDetails(startDate, true, endDate, true);
		assertGetNumberOfLeaveDaysEvaluatesTo(1);
	}
	
	@Test
	public void leaveApplicationWithHalfDayStartDate() throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JULY,25);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,26);
		leaveDetails = generateLeaveDetails(startDate, true, endDate, false);
		assertGetNumberOfLeaveDaysEvaluatesTo((float) 1.5);
	}

	@Test
	public void leaveApplicationWithEndDateBeforeStartDateRaisesAnException() {
		LocalDate startDate = LocalDate.of(2016,Month.NOVEMBER,12);
		LocalDate endDate = LocalDate.of(2016,Month.NOVEMBER,5);
		try {
			generateLeaveDetails(startDate, false, endDate, false);
			fail("Instantiating a LeaveApplication object should "
					+ "throw an exception if the end date "
					+ "is before start date.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
}
