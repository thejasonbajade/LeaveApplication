package com.orangeandbronze.leaveapp.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveStatus;
import com.orangeandbronze.leaveapp.domain.LeaveType;
import com.orangeandbronze.leaveapp.repository.LeaveApplicationRepository;

@Repository
public class JdbcLeaveApplicationRepository implements LeaveApplicationRepository{

	
	private JdbcTemplate jdbcTemplate;// = new JdbcTemplate(createTestDataSource());
	//@Autowired
	//private EmployeeRepository employeeRepository;
	@Autowired
	public JdbcLeaveApplicationRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcLeaveApplicationRepository() { }

	private class LeaveApplicationMapper implements RowMapper<LeaveApplication> {
		@Override
		public LeaveApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new LeaveApplication(
					rs.getInt("ID"),
					rs.getDate("StartDate").toLocalDate(),
					rs.getDate("EndDate").toLocalDate(),
					rs.getDate("DateFiled").toLocalDate(),
					LeaveType.valueOf(rs.getString("LeaveType")),
					LeaveStatus.valueOf(rs.getString("Status")),
					rs.getString("Reason"),
					null,
					null
					);


			//employeeRepository.findBy(rs.getInt("Employee_ID")),
			//(Supervisor) employeeRepository.findBy(rs.getInt("Supervisor_ID"))
			/*return new LeaveApplication(
					rs.getInt("ID"),
					calendarStart,
					calendarEnd,
					calendarDateFiled,
					LeaveType.valueOf(rs.getString("LeaveType")),
					LeaveStatus.valueOf(rs.getString("Status")),
					rs.getString("Reason")
					employeeRepository.findBy(rs.getInt("Employee_ID")),
					(Supervisor) employeeRepository.findBy(rs.getInt("Supervisor_ID"))
					);*/
		}
	}
	/*private class CommentMapper implements RowMapper<String> {
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Strin(rs.getString());
		}
	}*/

	@Override
	public LeaveApplication findBy(long leaveId) {

		return jdbcTemplate.queryForObject(
				"SELECT * FROM LeaveApplication WHERE ID = ?", new LeaveApplicationMapper(), leaveId);
	}

	@Override
	public List<LeaveApplication> findAllLeaveApplications() {
		return jdbcTemplate.query(
				"SELECT * FROM LeaveApplication ORDER BY DateFiled DESC", 
				new LeaveApplicationMapper());
	}

	@Override
	public List<LeaveApplication> findLeaveApplicationsByEmployee(long employeeId) {
		return jdbcTemplate.query(
				"SELECT * FROM LeaveApplication WHERE Employee_Id = ? ORDER BY DateFiled DESC", 
				new LeaveApplicationMapper(),
				employeeId);
	}

	@Override
	public List<LeaveApplication> findLeaveApplicationsForSupervisor(long supervisorId) {
		return jdbcTemplate.query(
				"SELECT * FROM LeaveApplication WHERE Supervisor_ID = ? ORDER BY DateFiled DESC", 
				new LeaveApplicationMapper(), 
				supervisorId);
	}

	@Override
	public int updateLeaveStatus(LeaveApplication leaveApplication) {
		return jdbcTemplate.update(
				"UPDATE LeaveApplication SET Status = ? WHERE ID = ?", 
				leaveApplication.getStatus(), 
				leaveApplication.getLeaveId());	
	}

	private static final String SQL_INSERT_LEAVE_APPLICATION =
			"INSERT INTO LeaveApplication (StartDate, EndDate, DateFiled, Duration, Reason, Status, LeaveType, Employee_ID, Supervisor_ID )"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public int insert(LeaveApplication leaveApplication) {
		return jdbcTemplate.update(
				SQL_INSERT_LEAVE_APPLICATION,
				leaveApplication.getStartDate().toString(),
				leaveApplication.getEndDate().toString(),
				leaveApplication.getDateFiled().toString(),
				leaveApplication.getNumberOfLeaveDays(),
				leaveApplication.getReason(),
				leaveApplication.getStatus().toString(),
				leaveApplication.getLeaveType().toString(),
				leaveApplication.getFiler().getEmployeeId(),
				leaveApplication.getApprover().getEmployeeId());
	}

	@Override
	public List<String> findAllCommentsForLeave(long leaveId) {
		/*jdbcTemplate.query(
				"SELECT * FROM Comments WHERE LeaveHistor_Id = ?", ,leaveId);*/		
		return null; 
	}

	/*private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
			.setName("LeaveApp")
			.addScript("classpath:/leaveapp/testdb/LeaveApp.sql")
			.addScript("classpath:/leaveapp/testdb/test-data.sql")
			.build();
	}	*/
}
