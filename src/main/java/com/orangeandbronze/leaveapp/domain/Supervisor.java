package com.orangeandbronze.leaveapp.domain;

public class Supervisor extends Employee {
	
	public Supervisor(long employeeId, EmployeeRecord record) {
		super(employeeId, record);
	}
	
	public Supervisor(long employeeId, EmployeeRecord record, LeaveCredits credits) {
		super(employeeId, record, credits);
	}
	
	public void approve(LeaveApplication leaveApplication) {
		leaveApplication.approve();
	}
	
	public void disapprove(LeaveApplication leaveApplication) {
		leaveApplication.disapprove();
	}
}
