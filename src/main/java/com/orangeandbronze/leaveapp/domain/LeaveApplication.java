package com.orangeandbronze.leaveapp.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LeaveApplication {

	private long leaveId;
	private LeaveDetails leaveDetails;
	private LeaveStatus leaveStatus;
	private Employee filer;
	private Employee approver;

	public LeaveApplication(LeaveDetails leaveDetails, Employee filer, Employee approver) {
		this(0, leaveDetails, LeaveStatus.PENDING, filer, approver);
	}

	public LeaveApplication(int leaveId, LeaveDetails leaveDetails, LeaveStatus leaveStatus, Employee filer, Employee approver) {
		this.leaveId = leaveId;
		this.leaveDetails = leaveDetails;
		this.leaveStatus = leaveStatus; 
		this.filer = filer;
		this.approver = approver;
	}

	public void cancel() {
		this.leaveStatus = LeaveStatus.CANCELLED;
	}

	public void approve() {
		if(this.leaveStatus == LeaveStatus.PENDING)
			this.leaveStatus = LeaveStatus.SUPERVISOR_APPROVED;
		else if(this.leaveStatus == LeaveStatus.SUPERVISOR_APPROVED) {
			this.leaveStatus = LeaveStatus.ADMIN_APPROVED;
			filer.getLeaveCredits().deductLeaveCreditsOfType(leaveDetails.getLeaveType(), leaveDetails.getNumberOfLeaveDays());
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

	public long getLeaveId() {
		return leaveId;
	}

	public LeaveDetails getLeaveDetails() {
		return leaveDetails;
	}
	
	public Employee getFiler() {
		return filer;
	}

	public Employee getApprover() {
		return approver;
	}
}
