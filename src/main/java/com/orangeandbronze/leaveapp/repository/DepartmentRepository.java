package com.orangeandbronze.leaveapp.repository;

import java.util.List;

import com.orangeandbronze.leaveapp.domain.Department;

public interface DepartmentRepository {
	public Department findBy(long departmentID);
	public List<Department> findAll();
}
