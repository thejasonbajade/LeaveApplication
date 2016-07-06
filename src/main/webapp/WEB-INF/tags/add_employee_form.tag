<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Add Employee Form Tag" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="submit_add_employee" method="post" id="addemployeeform" class="col-md-8 col-md-offset-2">
	<div class="form-group col-md-12">
		<label class="col-md-4"> First Name </label>
		<div class="col-md-8">
			<input type="text" name="firstName" class="form-control">
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Last Name </label>
		<div class="col-md-8">
			<input type="text" name="lastName" class="form-control">
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Email </label>
		<div class="col-md-8">
			<input type="text" name="email" class="form-control" value="jasonbajade@orangeandbronze.com">
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Contact Number </label>
		<div class="col-md-8">
			<input type="text" name="contactnumber" class="form-control" value="09xxxxxxxxxxx">
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Employment Date </label>
		<div class="col-md-8">
			<input type="date" name="employmentdate" class="form-control">
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Employee Status </label>
		<div class="col-md-3">
			<input type="checkbox" class="form-control" name="employeestatus" id="employeestatus" checked data-toggle="toggle" data-on="Regular" data-off="Probationary" data-onstyle="primary" data-offstyle="default" data-width="100%">
		</div>
		<div class="col-md-5">
			<input type="date" name="regularizationdate" id="regularizationdate" class="form-control">
		</div>
	</div>
	
	<div class="form-group col-md-12">
		<label class="col-md-4"> Employee Position </label>
		<div class="col-md-8">
			<input type="text"  name="employeeposition" class="form-control" value="Senior Enterprise Engineer">
		</div>
	</div>

	<div class="form-group col-md-12">
		<label class="col-md-4"> Department </label>
		<div class="col-md-8">
			<select class="form-control" name="department">
				<option style="display: none" value="default">Select
					Department</option>
				<c:forEach items="${departments}" var="department">
					<option value="${department.id}">${department.name}</option>
				</c:forEach>
				
				<!-- loop departments from db -->
			</select>
		</div>
	</div>
		
	<div class="form-group col-md-12" id="regulardatediv">
		<label class="col-md-4"> Solo Parent </label>
		<div class="col-md-3">
			<input type="checkbox" checked data-toggle="toggle" checked data-on="Solo Parent" data-off="Non Solo Parent" data-onstyle="primary" data-offstyle="default" data-width="100%">
		</div>
	</div>
	

	<div class="form-group col-md-12">
		<label class="col-md-4"> Roles </label>
		<div class="col-md-8">
			<div class="checkbox">
				<label><input type="checkbox" id="adminrole" name="roles" value="admin"> Admin </label>
			</div>
			<div class="checkbox">
				<label><input type="checkbox" id="supervisorrole" name="roles" value="supervisor"> Supervisor </label>
			</div>
			<div class="checkbox">
				<label><input type="checkbox" id="hrrole" name="roles" value="hr"> HR </label>
			</div>
		</div>
	</div>
	<input type="submit" class="btn btn-primary" value="Submit">
</form>