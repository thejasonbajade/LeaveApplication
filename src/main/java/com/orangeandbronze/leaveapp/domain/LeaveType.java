package com.orangeandbronze.leaveapp.domain;

public enum LeaveType {
	SICK_LEAVE(15.0, "SL"), VACATION_LEAVE(15.0, "VL"), EMERGENCY_LEAVE(3.0, "EL"), 
	OFFSET_LEAVE(Float.MAX_VALUE, "Offset"), SOLO_PARENT_LEAVE(Float.MAX_VALUE, "SPL");

	private final float maxValue;
	private final String name;

	private LeaveType(double maxValue, String name) {
		this.maxValue = (float) maxValue;
		this.name = name;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public String toAcronym() {
		return name;
	}
}
