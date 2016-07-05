package com.orangeandbronze.leaveapp.domain;

public class Admin extends Supervisor {
	
	public Admin(long employeeId, EmployeeRecord record) {
		super(employeeId, record);
	}
	
	public Admin(long employeeId, EmployeeRecord record, LeaveCredits credits) {
		super(employeeId, record, credits);
	}
	
	public void changeToNotTaken(LeaveApplication leaveApplication) {
		leaveApplication.changeToNotTaken();
	}
	
	public void regularize(Employee employee) {
		employee.regularize();
	}	
}
