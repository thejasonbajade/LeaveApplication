package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class LeaveApplicationTest {
	
	private LeaveApplication leaveApplication;
	
	private LeaveApplication generateLeaveApplication(LocalDate startDate, LocalDate endDate) {
		return new LeaveApplication(startDate, endDate, LeaveType.SICK_LEAVE, "I'm Sick", new Employee(1, "A", "B"), new Supervisor(2, "C", "D"));
	}

	private void assertGetNumberOfLeaveDaysEvaluatesTo(int expected) {
		assertTrue("Number of leave days computed was " + leaveApplication.getNumberOfLeaveDays(), 
				expected == leaveApplication.getNumberOfLeaveDays());
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
