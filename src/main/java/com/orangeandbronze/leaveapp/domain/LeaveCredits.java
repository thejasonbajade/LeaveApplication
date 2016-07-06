package com.orangeandbronze.leaveapp.domain;

public class LeaveCredits {
	public static final LeaveCredits ZERO = new LeaveCredits.Builder().build();
	
	private static final float VACATION_LEAVE_CREDITS_INCREASE_RATE = (float) 1.25;
	private static final float SICK_LEAVE_CREDITS_INCREASE_RATE = (float) 1.25;
	
	private float vacationLeaveCredits;
	private float sickLeaveCredits;
	private float emergencyLeaveCredits;
	private float offsetLeaveCredits;
	private float soloParentLeaveCredits;
	
	private float lwopCount;
	
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
		vacationLeaveCredits += VACATION_LEAVE_CREDITS_INCREASE_RATE;
	}

	public void increaseSickLeaveCredits() {
		sickLeaveCredits += SICK_LEAVE_CREDITS_INCREASE_RATE;
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

	public float getLwopCount() {
		return lwopCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(emergencyLeaveCredits);
		result = prime * result + Float.floatToIntBits(lwopCount);
		result = prime * result + Float.floatToIntBits(offsetLeaveCredits);
		result = prime * result + Float.floatToIntBits(sickLeaveCredits);
		result = prime * result + Float.floatToIntBits(soloParentLeaveCredits);
		result = prime * result + Float.floatToIntBits(vacationLeaveCredits);
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
		LeaveCredits other = (LeaveCredits) obj;
		if (Float.floatToIntBits(emergencyLeaveCredits) != Float.floatToIntBits(other.emergencyLeaveCredits))
			return false;
		if (Float.floatToIntBits(lwopCount) != Float.floatToIntBits(other.lwopCount))
			return false;
		if (Float.floatToIntBits(offsetLeaveCredits) != Float.floatToIntBits(other.offsetLeaveCredits))
			return false;
		if (Float.floatToIntBits(sickLeaveCredits) != Float.floatToIntBits(other.sickLeaveCredits))
			return false;
		if (Float.floatToIntBits(soloParentLeaveCredits) != Float.floatToIntBits(other.soloParentLeaveCredits))
			return false;
		if (Float.floatToIntBits(vacationLeaveCredits) != Float.floatToIntBits(other.vacationLeaveCredits))
			return false;
		return true;
	}
	
	
}
