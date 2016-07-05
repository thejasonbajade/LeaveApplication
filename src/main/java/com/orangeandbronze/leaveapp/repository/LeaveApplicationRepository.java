package com.orangeandbronze.leaveapp.repository;

import java.util.List;

import com.orangeandbronze.leaveapp.domain.LeaveApplication;;

public interface LeaveApplicationRepository {

	public LeaveApplication findBy(long leaveId);
	
	public List<LeaveApplication> findAllLeaveApplications();
	
	public List<LeaveApplication> findLeaveApplicationsByEmployee(long employeeId);
	
	public List<LeaveApplication> findLeaveApplicationsForSupervisor(long supervisorId);
	
	public int updateLeaveStatus(LeaveApplication leaveApplication);
	
	public int insert(LeaveApplication leaveApplication);
	
	public List<String> findAllCommentsForLeave(long leaveId);
	
}
