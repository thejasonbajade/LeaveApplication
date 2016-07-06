package com.orangeandbronze.leaveapp.domain;

import java.util.Collection;

public class Department {
	private final long id;
	private final String name;
	private Collection<Employee> employees;
	
	public Department(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
