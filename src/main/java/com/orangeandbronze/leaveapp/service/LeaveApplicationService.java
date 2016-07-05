package com.orangeandbronze.leaveapp.service;

import java.util.List;

import com.orangeandbronze.leaveapp.domain.LeaveApplication;

public interface LeaveApplicationService {
	
	public LeaveApplication findBy(int leaveId);
	
	public List<LeaveApplication> findAllLeaveApplications();
	
	public List<LeaveApplication> findLeaveApplicationsByEmployee(long employeeId);
	
	public List<LeaveApplication> findLeaveApplicationsForSupervisor(long supervisorId);
	
	public int insert(LeaveApplication leaveApplication);
	
	public List<String> findAllCommentsForLeave(long leaveId); 
}
