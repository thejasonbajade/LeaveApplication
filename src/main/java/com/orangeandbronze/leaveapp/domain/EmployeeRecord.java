package com.orangeandbronze.leaveapp.domain;

import java.time.LocalDate;

public class EmployeeRecord {
	private final String firstName;
	private final String lastName;
	private final String middleName;
	private final String email;
	private final Department department;
	private final LocalDate employmentDate;
	private final String contactNumber;
	private final String position;
	private EmploymentStatus status;
	private LocalDate regularizationDate;
	private boolean isSoloParent;
	
	public static class Builder{
		private final String firstName;
		private final String lastName;
		private final String email;
		private final String position;
		private final Department department;
		private LocalDate employmentDate;
		
		private String middleName = "";
		private EmploymentStatus status = EmploymentStatus.PROBATIONARY;
		private String contactNumber = "";
		private LocalDate regularizationDate = LocalDate.now();
		private boolean isSoloParent = false;
		
		public Builder(String firstName, String lastName, String email, String position, Department department, LocalDate employmentDate) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.department = department;
			this.employmentDate = employmentDate;
			this.position = position;
		}
		
		public Builder status(EmploymentStatus status){
			this.status = status;
			return this;
		}
		
		public Builder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}
		
		public Builder contactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
			return this;
		}
		
		public Builder regularizationDate(LocalDate regularizationDate){
			this.regularizationDate = regularizationDate;
			return this;
		}
		
		public Builder isSoloParent(boolean isSoloParent){
			this.isSoloParent = isSoloParent;
			return this;
		}
		
		public EmployeeRecord build(){
			return new EmployeeRecord(this);
		}
	}

	private EmployeeRecord(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.middleName = builder.middleName;
		this.email = builder.email;
		this.status = builder.status;
		this.employmentDate = builder.employmentDate;
		this.department = builder.department;
		this.contactNumber = builder.contactNumber;
		this.position = builder.position;
		this.regularizationDate = builder.regularizationDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getEmail() {
		return email;
	}

	public Department getDepartment() {
		return department;
	}

	public LocalDate getEmploymentDate() {
		return employmentDate;
	}

	public EmploymentStatus getStatus() {
		return status;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getPosition() {
		return position;
	}

	public LocalDate getRegularizationDate() {
		return regularizationDate;
	}

	public boolean isSoloParent() {
		return isSoloParent;
	}

}
