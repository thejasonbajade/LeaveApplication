package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class LeaveApplicationTest {
	
	private LeaveApplication leaveApplication;
	private LeaveDetails leaveDetails;
	
	private LeaveApplication generateLeaveApplication(LocalDate startDate, LocalDate endDate) {
		leaveDetails = new LeaveDetails(startDate, false, endDate, false, LeaveType.SICK_LEAVE, "I'm Sick");
		return new LeaveApplication(leaveDetails, null, null);
	}

	private void assertGetNumberOfLeaveDaysEvaluatesTo(float expected) {
		assertTrue("Number of leave days computed was " + leaveApplication.getLeaveDetails().getNumberOfLeaveDays(), 
				expected == leaveApplication.getLeaveDetails().getNumberOfLeaveDays());
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
		LocalDate startDate = LocalDate.of(2016,Month.JULY,29);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,31);
		leaveApplication = generateLeaveApplication(startDate, endDate);
		assertGetNumberOfLeaveDaysEvaluatesTo(2);
	}
	
	@Test
	public void leaveApplicationWithHalfDayStartAndEndDate() throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JULY,25);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,26);

		leaveDetails = new LeaveDetails(startDate, true, endDate, true, LeaveType.SICK_LEAVE, "I'm Sick");
		leaveApplication = new LeaveApplication(leaveDetails, null, null);;
		assertGetNumberOfLeaveDaysEvaluatesTo(1);
	}
	
	@Test
	public void leaveApplicationWithHalfDayStartDate() throws Exception {
		LocalDate startDate = LocalDate.of(2016,Month.JULY,25);
		LocalDate endDate = LocalDate.of(2016,Month.JULY,26);
		leaveDetails = new LeaveDetails(startDate, true, endDate, false, LeaveType.SICK_LEAVE, "I'm Sick");
		leaveApplication = new LeaveApplication(leaveDetails, null, null);
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
