package com.orangeandbronze.leaveapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.repository.LeaveApplicationRepository;

@Service
@Transactional
@Component("leaveApplicationService")
public class LeaveApplicationServiceImpl implements LeaveApplicationService {
	
	private LeaveApplicationRepository leaveApplicationRepository;
	
	@Autowired
	public LeaveApplicationServiceImpl(LeaveApplicationRepository leaveApplicationRepository) {
		this.leaveApplicationRepository = leaveApplicationRepository;
	}

	@Override
	public LeaveApplication findBy(int leaveId) {
		return leaveApplicationRepository.findBy(leaveId);
	}

	@Override
	public List<LeaveApplication> findAllLeaveApplications() {
		return leaveApplicationRepository.findAllLeaveApplications();
	}

	@Override
	public List<LeaveApplication> findLeaveApplicationsByEmployee(int employeeId) {
		return leaveApplicationRepository.findLeaveApplicationsByEmployee(employeeId);
	}

	@Override
	public List<LeaveApplication> findLeaveApplicationsForSupervisor(int supervisorId) {
		return leaveApplicationRepository.findLeaveApplicationsForSupervisor(supervisorId);
	}

	@Override
	public int insert(LeaveApplication leaveApplication) {
		return leaveApplicationRepository.insert(leaveApplication);
	}

	@Override
	public List<String> findAllCommentsForLeave(int leaveId) {
		return leaveApplicationRepository.findAllCommentsForLeave(leaveId);
	}

}
