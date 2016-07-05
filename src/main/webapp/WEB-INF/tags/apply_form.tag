<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@tag description="Leave Application Form Tag" pageEncoding="UTF-8"%>
<form action="view_leave_history" class="col-md-8 col-md-offset-2" id="leaveapplicationform" method="post">		
	<p> ${user.employeeRecord.firstName} </p>		
	<div class="form-group col-md-12">
		<label class="col-md-4"> Leave Type </label>
		<div class="col-md-8">
			<select class="form-control" name="leaveType">
				<option value="VACATION_LEAVE">Vacation Leave</option>
				<option value="EMERGENCY_LEAVE">Emergency Leave</option>
				<option value="SICK_LEAVE">Sick Leave</option>
				<option value="OFFSET">Offset</option>
				<option value="SOLO_PARENT_LEAVE">Solo Parent</option>
			</select>
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Supervisor </label>
		<div class="col-md-8">
			<select class="form-control" name="supervisorId">
				<option selected disabled> Supervisor </option>
				<c:forEach items="${supervisors}" var="supervisor">
					<option value="${supervisor.employeeId}"> ${supervisor.firstName} ${supervisor.lastName} </option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Start Date </label>
		<div class="col-md-6">
			<input type="date" id="startDate" name="startDate" class="form-control">
		</div>
		<div class="col-md-2">
			<input type="checkbox" name="startHalfDay" value="startHalfDay"> Half Day
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> End Date </label>
		<div class="col-md-6">
			<input type="date" id="endDate" name="endDate" class="form-control">
		</div>
		<div class="col-md-2">
			<input type="checkbox" name="endHalfDay" value="endHalfDay"> Half Day
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Number of Days </label>
		<div class="col-md-8">
			<input type="text" class="form-control" readonly name="duration" value="2">
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Reason </label>
		<div class="col-md-8">
			<textarea class="form-control" name="reason" rows="4"></textarea>
		</div>
	</div>
	
	<input type="submit" class="btn btn-primary" value="Submit">
</form>