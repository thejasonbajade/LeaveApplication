package com.orangeandbronze.leaveapp.domain;

public class LeaveCredits {
	public static final LeaveCredits ZERO = new LeaveCredits.Builder().build();
	
	private final float vacationLeaveCreditsIncreaseRate = (float) 1.25;
	private final float sickLeaveCreditsIncreaseRate = (float) 1.25;
	
	private float vacationLeaveCredits;
	private float sickLeaveCredits;
	private float emergencyLeaveCredits;
	private float offsetLeaveCredits;
	private float soloParentLeaveCredits;
	
	public static class Builder{
		private float vacationLeaveCredits = 0;
		private float sickLeaveCredits = 0;
		private float emergencyLeaveCredits = 0;
		private float offsetLeaveCredits = 0;
		private float soloParentLeaveCredits = 0;
		
		public Builder(){
			
		}
		
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
		
		public Builder offsetLeaveCredits(float value){
			offsetLeaveCredits = value;
			return this;
		}
		
		public Builder soloParentLeaveCredits(float value){
			soloParentLeaveCredits = value;
			return this;
		}
		
		public LeaveCredits build(){
			return new LeaveCredits(this);
		}
	}
	
	private LeaveCredits(Builder builder) {
		this.vacationLeaveCredits = builder.vacationLeaveCredits;
		this.sickLeaveCredits = builder.sickLeaveCredits;
		this.emergencyLeaveCredits = builder.emergencyLeaveCredits;
		this.offsetLeaveCredits = builder.offsetLeaveCredits;
		this.soloParentLeaveCredits = builder.soloParentLeaveCredits;
	}
	
	public float getLeaveCreditsOfType(LeaveType type){
		switch (type) {
		case VACATION_LEAVE:
			return vacationLeaveCredits;
		case SICK_LEAVE:
			return sickLeaveCredits;
		case EMERGENCY_LEAVE:
			return emergencyLeaveCredits;
		case SOLO_PARENT_LEAVE:
			return soloParentLeaveCredits;
		case OFFSET_LEAVE:
			return offsetLeaveCredits;
		default:
			return 0;
		}
	}

	public float getOffsetLeaveCredits() {
		return offsetLeaveCredits;
	}

	public float getSoloParentLeaveCredits() {
		return soloParentLeaveCredits;
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

	public void increaseVacationLeaveCredits() {
		vacationLeaveCredits += vacationLeaveCreditsIncreaseRate;
	}

	public void increaseSickLeaveCredits() {
		sickLeaveCredits += sickLeaveCreditsIncreaseRate;
	}

	public void deductLeaveCreditsOfType(LeaveType type, float creditsToDeduct) {
		switch (type) {
		case SICK_LEAVE:
			deductSickLeaveCreditsBy(creditsToDeduct);
			break;
		case VACATION_LEAVE:
			deductVacationLeaveCreditsBy(creditsToDeduct);
			break;
		case EMERGENCY_LEAVE:
			deductEmergencyLeaveCreditsBy(creditsToDeduct);
			break;
		case SOLO_PARENT_LEAVE:
			deductSoloParentLeaveCreditsBy(creditsToDeduct);
			break;
		case OFFSET_LEAVE:
			deductOffsetLeaveCreditsBy(creditsToDeduct);
		}
	}

	private void deductOffsetLeaveCreditsBy(float creditsToDeduct) {
		offsetLeaveCredits -= creditsToDeduct;
	}

	private void deductSoloParentLeaveCreditsBy(float creditsToDeduct) {
		soloParentLeaveCredits -= creditsToDeduct;
	}

	private void deductEmergencyLeaveCreditsBy(float creditsToDeduct) {
		emergencyLeaveCredits -= creditsToDeduct;
	}

	private void deductVacationLeaveCreditsBy(float creditsToDeduct) {
		vacationLeaveCredits -= creditsToDeduct;
	}

	private void deductSickLeaveCreditsBy(float creditsToDeduct) {
		sickLeaveCredits -= creditsToDeduct;
	}
	
}
