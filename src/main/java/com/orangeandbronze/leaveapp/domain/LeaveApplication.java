package com.orangeandbronze.leaveapp.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LeaveApplication {

	private long leaveId;
	private LocalDate startDate;
	private boolean isStartHalfDay;
	private boolean isEndHalfDay;
	private LocalDate endDate;
	private LocalDate dateFiled;
	private LeaveType leaveType;
	private LeaveStatus leaveStatus;
	private Employee filer;
	private Employee approver;

	private String reason;
	private float numberOfLeaveDays;

	public LeaveApplication(LocalDate startDate, boolean isStartHalfDay, LocalDate endDate, boolean isEndHalfDay, LeaveType leaveType,
			String reason,Employee filer, Employee approver) {
		this(0, startDate, isStartHalfDay, endDate, isEndHalfDay, LocalDate.now(), leaveType, LeaveStatus.PENDING, reason, filer, approver);
	}

	public LeaveApplication(int leaveId, LocalDate startDate, boolean isStartHalfDay, LocalDate endDate, boolean isEndHalfDay, LocalDate dateFiled, LeaveType leaveType, 
			 LeaveStatus leaveStatus, String reason, Employee filer, Employee approver) {
		checkIfEndDateIsBeforeStartDate(startDate, endDate);
		this.leaveId = leaveId;
		this.startDate = startDate;
		this.isStartHalfDay = isStartHalfDay;
		this.endDate = endDate;
		this.isEndHalfDay = isEndHalfDay;
		this.dateFiled = dateFiled;
		this.leaveType = leaveType;
		this.leaveStatus = leaveStatus;
		this.reason = reason;
		this.filer = filer;
		this.approver = approver;
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

	public void cancel() {
		this.leaveStatus = LeaveStatus.CANCELLED;
	}

	public void approve() {
		if(this.leaveStatus == LeaveStatus.PENDING)
			this.leaveStatus = LeaveStatus.SUPERVISOR_APPROVED;
		else if(this.leaveStatus == LeaveStatus.SUPERVISOR_APPROVED) {
			this.leaveStatus = LeaveStatus.ADMIN_APPROVED;
			filer.getLeaveCredits().deductLeaveCreditsOfType(leaveType, numberOfLeaveDays);
		}
	}

	public void disapprove() {
		if(this.leaveStatus == LeaveStatus.PENDING)
			this.leaveStatus = LeaveStatus.SUPERVISOR_DISAPPROVED;
		else if(this.leaveStatus == LeaveStatus.SUPERVISOR_APPROVED)
			this.leaveStatus = LeaveStatus.ADMIN_DISAPPROVED;
	}

	public LeaveStatus getStatus() {
		return leaveStatus;
	}

	public void changeToNotTaken() {
		leaveStatus = LeaveStatus.NOT_TAKEN;
	}

	public float getNumberOfLeaveDays() {
		return numberOfLeaveDays;
	}

	public long getLeaveId() {
		return leaveId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getReason() {
		return reason;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public Employee getFiler() {
		return filer;
	}

	public Employee getApprover() {
		return approver;
	}

	public LocalDate getDateFiled() {
		return dateFiled;
	}
	
	public boolean isStartHalfDay() {
		return isStartHalfDay;
	}
	
	public boolean isEndHalfDay() {
		return isEndHalfDay;
	}
}
