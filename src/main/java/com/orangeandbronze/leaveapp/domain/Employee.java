package com.orangeandbronze.leaveapp.domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

public class Employee {
	private final long employeeId;
	private final LeaveCredits credits;
	private final EmployeeRecord record;
	
	public Employee(long employeeId, EmployeeRecord record) {
		this(employeeId, record, LeaveCredits.ZERO);
	}

	public Employee(long employeeId, EmployeeRecord record, LeaveCredits credits) {
		this.employeeId = employeeId;
		this.record = record;
		this.credits = credits;
	}

	public LeaveApplication fileLeave(LocalDate startDate, LocalDate endDate, LeaveType leaveType, String reason, Supervisor approver) {
		LeaveApplication leaveApplication = new LeaveApplication(startDate, endDate, leaveType, reason, this, approver);
		return leaveApplication;
	}

	public void cancel(LeaveApplication leaveApplication) {
		leaveApplication.cancel();
	}
	
	public void regularize() {
		//record.regularize();
	}

	public long getEmployeeId() {
		return employeeId;
	}
	
	public EmployeeRecord getEmployeeRecord() {
		return record;
	}
	
	public LeaveCredits getLeaveCredits() {
		return credits;
	}
}
