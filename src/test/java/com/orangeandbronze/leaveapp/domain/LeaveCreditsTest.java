package com.orangeandbronze.leaveapp.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.orangeandbronze.leaveapp.domain.LeaveCredits;
import com.orangeandbronze.leaveapp.domain.LeaveType;

public class LeaveCreditsTest {
	
	private LeaveCredits.Builder builder;
	private LeaveCredits credits;
	
	@Before
	public void setUp() {
		builder = new LeaveCredits.Builder();
	}
	
	@Test
	public void increasingVacationLeaveCreditsFromZero() throws Exception {
		credits = builder.build();
		credits.increaseVacationLeaveCredits();
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.VACATION_LEAVE, 1.25);
	}
	
	private void assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType type, double expected) {
		assertEquals(expected, credits.getLeaveCreditsOfType(type), 0);
	}

	@Test
	public void increasingVacationLeaveCredits() throws Exception {
		credits = builder.vacationLeaveCredits((float) 1.25).build();
		credits.increaseVacationLeaveCredits();
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.VACATION_LEAVE, 2.50);
	}
	
	@Test
	public void increasingSickLeaveCreditsFromZero() throws Exception {
		credits = builder.build();
		credits.increaseSickLeaveCredits();
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.SICK_LEAVE, 1.25);
	}
	
	@Test
	public void increasingSickLeaveCredits() throws Exception {
		credits = builder.sickLeaveCredits((float) 1.25).build();
		credits.increaseSickLeaveCredits();
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.SICK_LEAVE, 2.50);
	}
	
	@Test
	public void deductingSickLeaveCredits() throws Exception {
		credits = builder.sickLeaveCredits(15).build();
		credits.deductLeaveCreditsOfType(LeaveType.SICK_LEAVE, 1);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.SICK_LEAVE, 14);
	}
	
	@Test
	public void deductingVacationLeaveCredits() throws Exception {
		credits = builder.vacationLeaveCredits(15).build();
		credits.deductLeaveCreditsOfType(LeaveType.VACATION_LEAVE, 1);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.VACATION_LEAVE, 14);
	}
	
	@Test
	public void deductingEmergencyLeaveCredits() throws Exception {
		credits = builder.emergencyLeaveCredits(3).build();
		credits.deductLeaveCreditsOfType(LeaveType.EMERGENCY_LEAVE, 1);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.EMERGENCY_LEAVE, 2);
	}
	
	@Test
	public void deductingOffsetLeaveCredits() throws Exception {
		credits = builder.offsetLeaveCredits(5).build();
		credits.deductLeaveCreditsOfType(LeaveType.OFFSET_LEAVE, 1);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.OFFSET_LEAVE, 4);
	}
	
	@Test
	public void exceedingVacationLeaveDaysShouldIncreasLWOPCount() throws Exception {
		credits = builder.build();
		credits.deductLeaveCreditsOfType(LeaveType.VACATION_LEAVE, 3);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.VACATION_LEAVE, 0);
		assertLWOPCountEvaluatesTo(3);
	}
	
	@Test
	public void exceedingEmergencyLeaveDaysShouldBeDeductedFromVacationLeaveCredits() throws Exception {
		credits = builder.vacationLeaveCredits(9).build();
		credits.deductLeaveCreditsOfType(LeaveType.EMERGENCY_LEAVE, 3);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.EMERGENCY_LEAVE, 0);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.VACATION_LEAVE, 6);
	}
	
	@Test
	public void exceedingSoloParentLeaveDaysShouldBeDeductedFromVacationLeaveCredits() throws Exception {
		credits = builder.vacationLeaveCredits(9).build();
		credits.deductLeaveCreditsOfType(LeaveType.SOLO_PARENT_LEAVE, 3);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.SOLO_PARENT_LEAVE, 0);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.VACATION_LEAVE, 6);
	}
	
	@Test
	public void exceedingSickLeaveDaysShouldIncreaseLWOPCount() throws Exception {
		credits = builder.build();
		credits.deductLeaveCreditsOfType(LeaveType.SICK_LEAVE, 3);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.SICK_LEAVE, 0);
		assertLWOPCountEvaluatesTo(3);
	}
	
	@Test
	public void exceedingOffsetLeaveDaysShouldIncreaseLWOPCount() throws Exception {
		credits = builder.build();
		credits.deductLeaveCreditsOfType(LeaveType.OFFSET_LEAVE, 3);
		assertLeaveCreditsOfALeaveTypeEvaluatesTo(LeaveType.OFFSET_LEAVE, 0);
		assertLWOPCountEvaluatesTo(3);
	}

	private void assertLWOPCountEvaluatesTo(float lwopCount) {
		assertTrue(lwopCount == credits.getLwopCount());
	}

}
