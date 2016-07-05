package com.orangeandbronze.leaveapp.repository;

import java.util.List;

import com.orangeandbronze.leaveapp.domain.LeaveApplication;;

public interface LeaveApplicationRepository {

	public LeaveApplication findBy(int leaveId);
	
	public List<LeaveApplication> findAllLeaveApplications();
	
	public List<LeaveApplication> findLeaveApplicationsByEmployee(int employeeId);
	
	public List<LeaveApplication> findLeaveApplicationsForSupervisor(int supervisorId);
	
	public int updateLeaveStatus(LeaveApplication leaveApplication);
	
	public int insert(LeaveApplication leaveApplication);
	
	public List<String> findAllCommentsForLeave(int leaveId);
	
}
