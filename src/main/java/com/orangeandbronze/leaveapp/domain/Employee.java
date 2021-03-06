package com.orangeandbronze.leaveapp.domain;

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

	public LeaveApplication fileLeave(LeaveDetails leaveDetails, Employee approver) {
		LeaveApplication leaveApplication = new LeaveApplication(leaveDetails, this, approver);
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
	
	private boolean employeeIsRegular() {
		return record.getStatus() == EmploymentStatus.REGULAR;
	}

	public void gainLeaveCredits() {
		if(employeeIsRegular()){
			credits.increaseSickLeaveCredits();
			credits.increaseVacationLeaveCredits();
		}
	}
	
	public void resetLwopCount() {
		credits.resetLwopCount();
	}

	public void refreshCredits() {
		if(employeeIsRegular()){
			credits.resetVacationLeaveCredits();
			credits.resetSickLeaveCredits();
			credits.resetEmergencyLeaveCredits();
			if(employeeIsSoloParent())
				credits.resetSoloParentLeaveCredits();
		}
	}

	private boolean employeeIsSoloParent() {
		return record.isSoloParent();
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

	public void terminate(Employee employee) {
		employee.terminate();
	}

	private void terminate() {
		record.changeEmploymentStatusToTerminated();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
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
		Employee other = (Employee) obj;
		if (employeeId != other.employeeId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return record.getFirstName() + " " + record.getLastName() + " with id " + employeeId;
	}
	
	
}
