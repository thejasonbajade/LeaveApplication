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
	
	private EmploymentStatus employmentStatus;
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
		
		public Builder(String firstName, String lastName, LocalDate employmentDate, Department department, String email,
				String position) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.department = department;
			this.employmentDate = employmentDate;
			this.position = position;
		}

		public Builder employmentStatus(EmploymentStatus status){
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
		this.employmentStatus = builder.status;
		this.employmentDate = builder.employmentDate;
		this.department = builder.department;
		this.contactNumber = builder.contactNumber;
		this.position = builder.position;
		this.regularizationDate = builder.regularizationDate;
		this.isSoloParent = builder.isSoloParent;
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
		return employmentStatus;
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

	public void changeEmploymentStatusToRegular() {
		employmentStatus = EmploymentStatus.REGULAR;
	}

	public void changeEmploymentStatusToTerminated() {
		employmentStatus = EmploymentStatus.TERMINATED;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employmentDate == null) ? 0 : employmentDate.hashCode());
		result = prime * result + ((employmentStatus == null) ? 0 : employmentStatus.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (isSoloParent ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((regularizationDate == null) ? 0 : regularizationDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRecord other = (EmployeeRecord) obj;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employmentDate == null) {
			if (other.employmentDate != null)
				return false;
		} else if (!employmentDate.equals(other.employmentDate))
			return false;
		if (employmentStatus != other.employmentStatus)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isSoloParent != other.isSoloParent)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (regularizationDate == null) {
			if (other.regularizationDate != null)
				return false;
		} else if (!regularizationDate.equals(other.regularizationDate))
			return false;
		return true;
	}
	
	
}
