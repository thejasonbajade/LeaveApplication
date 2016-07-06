package com.orangeandbronze.leaveapp.domain;

import java.time.LocalDate;

public class Employee {
	private final long employeeId;
	private final LeaveCredits credits;
	private final EmployeeRecord record;
	
	private boolean isAdmin;
	private boolean isSupervisor;
	private boolean isHR;
	
	public Employee(long employeeId, EmployeeRecord record) {
		this(employeeId, record, LeaveCredits.ZERO);
	}

	public Employee(long employeeId, EmployeeRecord record, LeaveCredits credits) {
		this.employeeId = employeeId;
		this.record = record;
		this.credits = credits;
	}

	public LeaveApplication fileLeave(
			LocalDate startDate, boolean isStartHalfDay, LocalDate endDate, boolean isEndHalfDay, LeaveType leaveType, String reason, Employee approver) {
		LeaveApplication leaveApplication = new LeaveApplication(startDate, isStartHalfDay, endDate, isEndHalfDay, leaveType, reason, this, approver);
		return leaveApplication;
	}

	public void cancel(LeaveApplication leaveApplication) {
		leaveApplication.cancel();
	}
	
	public void changeToNotTaken(LeaveApplication leaveApplication) {
		leaveApplication.changeToNotTaken();
	}
	
	public void regularize(Employee employee) {
		employee.regularize();
	}	

	private void regularize() {
		record.changeEmploymentStatusToRegular();
	}
	
	public void approve(LeaveApplication leaveApplication) {
		leaveApplication.approve();
	}
	
	public void disapprove(LeaveApplication leaveApplication) {
		leaveApplication.disapprove();
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
	
	public void grantAdminPrivelages(){
		isAdmin = true;
	}
	
	public void grantSupervisorPrivileges(){
		isSupervisor = true;
	}
	
	public void grantHRPrivileges(){
		isHR = true;
	}
	
	public void revokeAdminPrivileges(){
		isAdmin = false;
	}
	
	public void revokeSupervisorPrivileges(){
		isSupervisor = false;
	}
	
	public void revokeHRPrivileges(){
		isHR = false;
	}

	public LeaveCredits getCredits() {
		return credits;
	}

	public EmployeeRecord getRecord() {
		return record;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean isSupervisor() {
		return isSupervisor;
	}

	public boolean isHR() {
		return isHR;
	}
}
