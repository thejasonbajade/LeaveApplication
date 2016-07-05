package com.orangeandbronze.leaveapp.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LeaveApplication {

	private int leaveId;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate dateFiled;
	private LeaveType leaveType;
	private LeaveStatus leaveStatus;
	private Employee filer;
	private Supervisor approver;
	private String reason;
	private float numberOfLeaveDays;

	public LeaveApplication(LocalDate startDate, LocalDate endDate, LeaveType leaveType,
			String reason,Employee filer, Supervisor approver) {
		this(0, startDate, endDate, LocalDate.now(), leaveType, LeaveStatus.PENDING, reason, filer, approver);
	}

	public LeaveApplication(int leaveId, LocalDate startDate, LocalDate endDate, LocalDate dateFiled, LeaveType leaveType, 
			 LeaveStatus leaveStatus, String reason, Employee filer, Supervisor approver) {
		checkIfEndDateIsBeforeStartDate(startDate, endDate);
		this.leaveId = leaveId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dateFiled = dateFiled;
		this.leaveType = leaveType;
		this.leaveStatus = leaveStatus;
		this.reason = reason;
		this.filer = filer;
		this.approver = approver;
		numberOfLeaveDays = countNumberOfLeaveDays(startDate, endDate);
	}

	private int countNumberOfLeaveDays(LocalDate startDate, LocalDate endDate) {
		int numberOfLeaveDays = 1;
		for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			if(!isWeekEnd(date) && !Holiday.isHoliday(date))
				numberOfLeaveDays++;
		}	
		/*
		 float numberOfLeaveDaysF = (float) numberOfLeaveDays;
		 if(startHalfDay == true)
		 	numberOfLeaveDaysF -= -0.5;
		 if(endtHalfDay == true)
		 	numberOfLeaveDaysF -= -0.5;

		 return numberOfLeaveDaysF
		 */
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

	public int getLeaveId() {
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

	public Supervisor getApprover() {
		return approver;
	}

	public LocalDate getDateFiled() {
		return dateFiled;
	}
}
