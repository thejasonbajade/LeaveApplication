package com.orangeandbronze.leaveapp.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LeaveDetails {
	private LocalDate startDate;
	private boolean isStartHalfDay;
	private LocalDate endDate;
	private boolean isEndHalfDay;
	private LocalDate dateFiled;
	private LeaveType leaveType;
	private String reason;
	private float numberOfLeaveDays;
	
	public LeaveDetails(LocalDate startDate, boolean isStartHalfDay, LocalDate endDate, boolean isEndHalfDay, 
			LeaveType leaveType, String reason) {
		this(startDate, isStartHalfDay, endDate, isEndHalfDay, LocalDate.now(), leaveType, reason);
	}
	
	public LeaveDetails(LocalDate startDate, boolean isStartHalfDay,  LocalDate endDate, boolean isEndHalfDay,
			LocalDate dateFiled, LeaveType leaveType, String reason) {
		checkIfEndDateIsBeforeStartDate(startDate, endDate);
		this.startDate = startDate;
		this.isStartHalfDay = isStartHalfDay;
		this.isEndHalfDay = isEndHalfDay;
		this.endDate = endDate;
		this.dateFiled = dateFiled;
		this.leaveType = leaveType;
		this.reason = reason;
		numberOfLeaveDays = countNumberOfLeaveDays(startDate, endDate);
	}
	
	private float countNumberOfLeaveDays(LocalDate startDate, LocalDate endDate) {
		float numberOfLeaveDays = 1;
		for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			if(!isWeekEnd(date) && !Holiday.isHoliday(date))
				numberOfLeaveDays++;
		}	
		if(isStartHalfDay == true)
		 	numberOfLeaveDays -= 0.5;
		if(isEndHalfDay == true)
		 	numberOfLeaveDays -= 0.5;
		 
		 return numberOfLeaveDays;
	}

	private boolean isWeekEnd(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY || 
				date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	private void checkIfEndDateIsBeforeStartDate(LocalDate startDate, LocalDate endDate) {
		if(endDate.isBefore(startDate))
			throw new IllegalArgumentException(endDate + " is before " + startDate);
	}


	public LocalDate getStartDate() {
		return startDate;
	}

	public boolean isStartHalfDay() {
		return isStartHalfDay;
	}

	public boolean isEndHalfDay() {
		return isEndHalfDay;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public LocalDate getDateFiled() {
		return dateFiled;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public String getReason() {
		return reason;
	}

	public float getNumberOfLeaveDays() {
		return numberOfLeaveDays;
	}

}
