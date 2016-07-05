package com.orangeandbronze.leaveapp.domain;

public class LeaveCredits {
	private float vacationLeaveCredits;
	private float sickLeaveCredits;
	private float emergencyLeaveCredits;
	private float soloParentLeaveCredits;
	private float offsetLeaveCredits;
	
	private float lwopCount;
	
	public static class Builder{
		private float vacationLeaveCredits = 0;
		private float sickLeaveCredits = 0;
		private float emergencyLeaveCredits = 0;
		private float soloParentLeaveCredits = 0;
		private float offsetLeaveCredits = 0;
		private float lwopCount = 0;
		
		public Builder() { }
		
		public Builder vacationLeaveCredits(float value){
			vacationLeaveCredits = value;
			return this;
		}
		
		public Builder sickLeaveCredits(float value){
			sickLeaveCredits = value;
			return this;
		}
		
		public Builder emergencyLeaveCredits(float value){
			emergencyLeaveCredits = value;
			return this;
		}
		
		public Builder soloParentLeaveCredits(float value){
			soloParentLeaveCredits = value;
			return this;
		}
		
		public Builder offsetLeaveCredits(float value){
			offsetLeaveCredits = value;
			return this;
		}
		
		public Builder lwopCount(float value) {
			lwopCount = value;
			return this;
		}

		public LeaveCredits build() {
			return new LeaveCredits(this);
		}
	}

	private LeaveCredits(Builder builder) {
		this.vacationLeaveCredits = builder.vacationLeaveCredits;
		this.sickLeaveCredits = builder.sickLeaveCredits;
		this.emergencyLeaveCredits = builder.emergencyLeaveCredits;
		this.soloParentLeaveCredits = builder.soloParentLeaveCredits;
		this.offsetLeaveCredits = builder.offsetLeaveCredits;
		this.lwopCount = builder.lwopCount;
	}

	public float getVacationLeaveCredits() {
		return vacationLeaveCredits;
	}

	public float getSickLeaveCredits() {
		return sickLeaveCredits;
	}

	public float getEmergencyLeaveCredits() {
		return emergencyLeaveCredits;
	}

	public float getSoloParentLeaveCredits() {
		return soloParentLeaveCredits;
	}

	public float getOffsetLeaveCredits() {
		return offsetLeaveCredits;
	}

	public float getLwopCount() {
		return lwopCount;
	}

	public void increaseVacationLeaveCredits() {
		vacationLeaveCredits += 1.25;
	}

	public float getLeaveCreditsOfType(LeaveType type) {
		switch (type) {
		case VACATION_LEAVE: return getVacationLeaveCredits();
		case SICK_LEAVE: return getSickLeaveCredits();
		case EMERGENCY_LEAVE: return getEmergencyLeaveCredits();
		case SOLO_PARENT_LEAVE: return getSoloParentLeaveCredits();
		case OFFSET_LEAVE: return getOffsetLeaveCredits();
		default:
			return 0;
		}
	}

	public void increaseSickLeaveCredits() {
		sickLeaveCredits += 1.25;
	}

	public void deductLeaveCreditsOfType(LeaveType type, float creditsToDeduct) {
		switch (type) {
		case VACATION_LEAVE: deductVacationLeaveCredits(creditsToDeduct); break;
		case SICK_LEAVE: deductSickLeaveCredits(creditsToDeduct); break;
		case EMERGENCY_LEAVE: deductEmergencyLeaveCredits(creditsToDeduct); break;
		case SOLO_PARENT_LEAVE: deductSoloParentLeaveCredits(creditsToDeduct); break;
		case OFFSET_LEAVE: deductOffsetLeaveCredits(creditsToDeduct); break;
		}
	}

	private void deductOffsetLeaveCredits(float creditsToDeduct) {
		offsetLeaveCredits -= creditsToDeduct;
		if(isNegative(offsetLeaveCredits)){
			lwopCount += creditsToDeduct;
			offsetLeaveCredits = 0;
		}
	}

	private void deductSoloParentLeaveCredits(float creditsToDeduct) {
		soloParentLeaveCredits -= creditsToDeduct;
		if(isNegative(soloParentLeaveCredits)){
			deductVacationLeaveCredits(creditsToDeduct);
			soloParentLeaveCredits = 0;
		}
	}

	private void deductEmergencyLeaveCredits(float creditsToDeduct) {
		emergencyLeaveCredits -= creditsToDeduct;
		if(isNegative(emergencyLeaveCredits)){
			deductVacationLeaveCredits(creditsToDeduct);
			emergencyLeaveCredits = 0;
		}
	}

	private void deductVacationLeaveCredits(float creditsToDeduct) {
		vacationLeaveCredits -= creditsToDeduct;
		if(isNegative(vacationLeaveCredits)){
			lwopCount += creditsToDeduct;
			vacationLeaveCredits = 0;
		}
	}

	private boolean isNegative(float leaveCredits) {
		return leaveCredits < 0;
	}

	private void deductSickLeaveCredits(float creditsToDeduct) {
		sickLeaveCredits -= creditsToDeduct;
		if(isNegative(sickLeaveCredits)){
			lwopCount += creditsToDeduct;
			sickLeaveCredits = 0;
		}
	}
	
}
