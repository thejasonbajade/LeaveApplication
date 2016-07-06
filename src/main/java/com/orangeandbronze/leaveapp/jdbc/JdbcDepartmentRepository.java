package com.orangeandbronze.leaveapp.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.repository.DepartmentRepository;

@Repository
public class JdbcDepartmentRepository implements DepartmentRepository{

	private JdbcTemplate jdbcTemplate;
	private DepartmentMapper rowMapper = new DepartmentMapper();
	
	@Autowired
	public JdbcDepartmentRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Department findBy(long departmentID) {
		return jdbcTemplate.queryForObject("SELECT * FROM Department WHERE id = ?", rowMapper, departmentID);
	}

	@Override
	public List<Department> findAll() {
		return jdbcTemplate.query("SELECT * FROM Department", rowMapper);
	}

	private class DepartmentMapper implements RowMapper<Department> {

		@Override
		public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Department(rs.getLong("ID"), rs.getString("DepartmentName"));
		}
	
	}

}
