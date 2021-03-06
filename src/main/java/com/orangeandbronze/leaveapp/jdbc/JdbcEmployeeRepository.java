package com.orangeandbronze.leaveapp.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.EmployeeRecord;
import com.orangeandbronze.leaveapp.domain.EmploymentStatus;
import com.orangeandbronze.leaveapp.domain.LeaveCredits;
import com.orangeandbronze.leaveapp.domain.LeaveType;
import com.orangeandbronze.leaveapp.repository.EmployeeRepository;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {
	
	private JdbcTemplate jdbcTemplate;
	private EmployeeMapper rowMapper = new EmployeeMapper();
	
	@Autowired
	public JdbcEmployeeRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Employee findBy(long emp_id) {
		return jdbcTemplate.queryForObject(SQL_FINDBY_ID, rowMapper , emp_id);
	}

	@Override
	public int updateLeaveCreditsOf(Employee employee) {
		final LeaveCredits credits = employee.getCredits();
		return jdbcTemplate.update("UPDATE Employee SET "
				+ "VLCredits = ?, "
				+ "SLCredits = ?, "
				+ "ELCredits = ?, "
				+ "SPCredits = ?, "
				+ "OffsetCredits = ? "
				+ "WHERE ID = ?", 
				credits.getVacationLeaveCredits(),
				credits.getSickLeaveCredits(),
				credits.getEmergencyLeaveCredits(),
				credits.getSoloParentLeaveCredits(),
				credits.getOffsetLeaveCredits(),
				employee.getEmployeeId());
	}
	
	@Override
	public int regularize(long employeeId) {
		return jdbcTemplate.update("UPDATE Employee SET "
				+ "EmploymentStatus = ?, "
				+ "RegularizationDate = ? "
				+ "WHERE ID = ?", 
				EmploymentStatus.REGULAR, 
				LocalDate.now().toString(),
				employeeId);
	}
	
	private static final String SQL_UPDATE = 
			"UPDATE EMPLOYEE SET FirstName = ?, LastName = ?, Email = ?,"
			+ "ContactNo = ?, EmploymentDate = ?, Position = ?, EmploymentStatus = ?, RegularizationDate = ?, isSoloParent = ?,"
			+ "VLCredits = ?, SLCredits = ?, ELCredits = ?, SPCredits = ?, OffsetCredits = ?, Department_ID = ?, isSupervisor = ?,"
			+ "isAdmin = ?, isHR = ? WHERE ID = ?";
	@Override
	public int update(Employee employee) {
		EmployeeRecord record = employee.getEmployeeRecord();
		Department department = record.getDepartment();
		LeaveCredits credits = employee.getLeaveCredits();
		return jdbcTemplate.update(SQL_UPDATE,	
				record.getFirstName(),
				record.getLastName(),
				record.getEmail(),
				record.getContactNumber(),
				record.getEmploymentDate().toString(),
				record.getPosition(),
				record.getStatus().toString(),
				record.getRegularizationDate().toString(),
				record.isSoloParent(),
				credits.getLeaveCreditsOfType(LeaveType.VACATION_LEAVE),
				credits.getLeaveCreditsOfType(LeaveType.SICK_LEAVE),
				credits.getLeaveCreditsOfType(LeaveType.EMERGENCY_LEAVE),
				credits.getLeaveCreditsOfType(LeaveType.SOLO_PARENT_LEAVE),
				credits.getLeaveCreditsOfType(LeaveType.OFFSET_LEAVE),
				department.getId(),
				employee.isSupervisor(),
				employee.isAdmin(),
				employee.isHR(),
				employee.getEmployeeId());
	}

	@Override
	public List<Employee> findAll() {
		return jdbcTemplate.query("SELECT * FROM Employee e JOIN department d ON e.Department_ID = d.ID", rowMapper);
	}

	@Override
	public List<Employee> findAllSupervisors() {
		return jdbcTemplate.query("SELECT * FROM Employee e JOIN department d ON e.Department_ID = d.ID WHERE e.isSupervisor = 1", rowMapper);
	}

	private static final String SQL_INSERT_EMPLOYEE =
			"INSERT INTO EMPLOYEE (FirstName, LastName, Email,"
			+ "ContactNo, EmploymentDate, Position, EmploymentStatus, RegularizationDate, isSoloParent,"
			+ "VLCredits, SLCredits, ELCredits, SPCredits, OffsetCredits, Department_ID, isSupervisor, isAdmin, isHR)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	@Override
	public int add(Employee employee) {
		EmployeeRecord record = employee.getEmployeeRecord();
		Department department = record.getDepartment();
		LeaveCredits credits = employee.getLeaveCredits();
		return jdbcTemplate.update(SQL_INSERT_EMPLOYEE,
				record.getFirstName(),
				record.getLastName(),
				record.getEmail(),
				record.getContactNumber(),
				record.getEmploymentDate().toString(),
				record.getPosition(),
				record.getStatus().toString(),
				record.getRegularizationDate().toString(),
				record.isSoloParent(),
				credits.getLeaveCreditsOfType(LeaveType.VACATION_LEAVE),
				credits.getLeaveCreditsOfType(LeaveType.SICK_LEAVE),
				credits.getLeaveCreditsOfType(LeaveType.EMERGENCY_LEAVE),
				credits.getLeaveCreditsOfType(LeaveType.SOLO_PARENT_LEAVE),
				credits.getLeaveCreditsOfType(LeaveType.OFFSET_LEAVE),
				department.getId(),
				employee.isSupervisor(),
				employee.isAdmin(),
				employee.isHR());
	}

	private static final String SQL_FINDBY_ID =
			"SELECT * FROM employee e JOIN department d ON e.Department_ID = d.ID WHERE e.ID = ?";

	public class EmployeeMapper implements RowMapper<Employee> {
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeRecord record = new EmployeeRecord.Builder(rs.getString("FirstName"), 
					rs.getString("LastName"), 
					rs.getDate("EmploymentDate").toLocalDate(), 
					mapDepartment(rs), 
					rs.getString("Email"), 
					rs.getString("Position"))
					.employmentStatus(EmploymentStatus.valueOf(rs.getString("EmploymentStatus")))
					.regularizationDate(
							rs.getDate("RegularizationDate") == null ? LocalDate.ofEpochDay(0) :
									rs.getDate("RegularizationDate").toLocalDate())
					.build();
			LeaveCredits credits = mapLeaveCredits(rs);
			Employee employee = new Employee(rs.getLong("ID"), record, credits);
			checkEmployeePrivileges(employee, rs);
			return employee;
		}

		private void checkEmployeePrivileges(Employee employee, ResultSet rs) throws SQLException {
			if(rs.getInt("isSupervisor") == 1)
				employee.grantSupervisorPrivileges();
			if(rs.getInt("isAdmin") == 1)
				employee.grantAdminPrivelages();
			if(rs.getInt("isHR") == 1)
				employee.grantHRPrivileges();
		}

		private LeaveCredits mapLeaveCredits(ResultSet rs) throws SQLException {
			LeaveCredits credits = new LeaveCredits.Builder()
					.vacationLeaveCredits(rs.getFloat("VLCredits"))
					.sickLeaveCredits(rs.getFloat("SLCredits"))
					.emergencyLeaveCredits(rs.getFloat("ELCredits"))
					.soloParentLeaveCredits(rs.getFloat("SPCredits"))
					.offsetLeaveCredits(rs.getFloat("OffsetCredits"))
					.build();
			return credits;
		}

		private Department mapDepartment(ResultSet rs) throws SQLException {
			Department department = new Department(rs.getLong("id"), rs.getString("DepartmentName"));
			return department;
		}
	
	}

	@Override
	public int[] employeeLeaveCreditsBatchAwarding(final List<Employee> employees) {
		int[] updateCount = jdbcTemplate.batchUpdate("UPDATE Employee SET "
				+ "VLCredits = ?, "
				+ "SLCredits = ?, "
				+ "ELCredits = ?, "
				+ "SPCredits = ?, "
				+ "OffsetCredits = ? "
				+ "WHERE ID = ?",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Employee employee = employees.get(i);
						employee.gainLeaveCredits();
						LeaveCredits credits = employee.getCredits();
						ps.setFloat(1, credits.getVacationLeaveCredits());
						ps.setFloat(2, credits.getSickLeaveCredits());
						ps.setFloat(3, credits.getEmergencyLeaveCredits());
						ps.setFloat(4, credits.getSoloParentLeaveCredits());
						ps.setFloat(5, credits.getOffsetLeaveCredits());
						ps.setLong(5, employee.getEmployeeId());
					}
					
					@Override
					public int getBatchSize() {
						return employees.size();
					}
				});
		return updateCount;
	}

	@Override
	public int[] employeeLeaveCreditsBatchReset(final List<Employee> employees) {
		int[] updateCount = jdbcTemplate.batchUpdate("UPDATE Employee SET "
				+ "VLCredits = ?, "
				+ "SLCredits = ?, "
				+ "ELCredits = ?, "
				+ "SPCredits = ?, "
				+ "OffsetCredits = ? "
				+ "WHERE ID = ?",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Employee employee = employees.get(i);
						employee.refreshCredits();
						LeaveCredits credits = employee.getCredits();
						ps.setFloat(1, credits.getVacationLeaveCredits());
						ps.setFloat(2, credits.getSickLeaveCredits());
						ps.setFloat(3, credits.getEmergencyLeaveCredits());
						ps.setFloat(4, credits.getSoloParentLeaveCredits());
						ps.setFloat(5, credits.getOffsetLeaveCredits());
						ps.setLong(5, employee.getEmployeeId());
					}
					
					@Override
					public int getBatchSize() {
						return employees.size();
					}
				});
		return updateCount;
	}

	@Override
	public int deactivate(long employeeId) {
		return jdbcTemplate.update("UPDATE Employee SET "
				+ "EmploymentStatus = ? "
				+ "WHERE ID = ?", 
				EmploymentStatus.TERMINATED, 
				LocalDate.now().toString(),
				employeeId);
	}
}