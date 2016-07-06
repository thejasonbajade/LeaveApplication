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
		float numberOfLeaveDays = 0;
		for(LocalDate date = startDate; date.isBefore(endDate) || date.equals(endDate); date = date.plusDays(1)) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateFiled == null) ? 0 : dateFiled.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + (isEndHalfDay ? 1231 : 1237);
		result = prime * result + (isStartHalfDay ? 1231 : 1237);
		result = prime * result + ((leaveType == null) ? 0 : leaveType.hashCode());
		result = prime * result + Float.floatToIntBits(numberOfLeaveDays);
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeaveDetails other = (LeaveDetails) obj;
		if (dateFiled == null) {
			if (other.dateFiled != null)
				return false;
		} else if (!dateFiled.equals(other.dateFiled))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (isEndHalfDay != other.isEndHalfDay)
			return false;
		if (isStartHalfDay != other.isStartHalfDay)
			return false;
		if (leaveType != other.leaveType)
			return false;
		if (Float.floatToIntBits(numberOfLeaveDays) != Float.floatToIntBits(other.numberOfLeaveDays))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	
}
