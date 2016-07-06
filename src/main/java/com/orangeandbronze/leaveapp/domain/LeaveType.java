package com.orangeandbronze.leaveapp.domain;

public enum LeaveType {
	SICK_LEAVE(15.0, "SICK_LEAVE"), VACATION_LEAVE(15.0, "VACATION_LEAVE"), EMERGENCY_LEAVE(3.0, "EMERGENCY_LEAVE"), 
	OFFSET_LEAVE(Float.MAX_VALUE, "OFFSET_LEAVE"), SOLO_PARENT_LEAVE(Float.MAX_VALUE, "OFFSET_LEAVE");

	private final float maxValue;
	private final String name;

	private LeaveType(double maxValue, String name) {
		this.maxValue = (float) maxValue;
		this.name = name;
	}

	public float getMaxValue() {
		return maxValue;
	}

	@Override
	public String toString() {
		return name;
	}
}
