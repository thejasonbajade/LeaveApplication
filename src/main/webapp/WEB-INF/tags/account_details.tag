<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Leave Credits Display Tag" pageEncoding="UTF-8"%>
<%@attribute name="user" required="true"
	type="com.orangeandbronze.leaveapp.domain.Employee"%>
<div class="col-md-12">
	<div class="col-md-2">
		<div class="col-md-2">
			<span class="fa-stack fa-4x"> <i
				class="fa fa-circle fa-stack-2x"></i> <i
				class="fa fa-user fa-stack-1x fa-inverse" style="color: orange"></i>
			</span>
		</div>
	</div>
	<div class="col-md-2">
		<p><b>Name:</b></p>
		<p><b>Email Address:</b></p>
		<p><b>Department:</b></p>
		<p><b>Position:</b></p>
		<p><b>Status:</b></p>
	</div>
	<div class="col-md-8">
		<p>${user.employeeRecord.firstName} ${user.employeeRecord.lastName}</p>
		<p>${user.employeeRecord.email}</p>
		<p>${user.employeeRecord.department.name}</p>
		<p>${user.employeeRecord.position}</p>
		<p>${user.employeeRecord.status}</p>
	</div>
	<c:if test="${sessionScope.user.admin}">
		<div class="col-md-10 col-md-offset-2">
			<c:if test="${user.employeeRecord.status == 'PROBATIONARY'}">
				<form action="regularize_employee">
					<input type="hidden" name="employeeid" value="${user.employeeId}" />
					<input type="submit" class="btn btn-primary" value="Regularize">
				</form>
			</c:if>
			<form action="deactivate_account">
				<input type="hidden" name="employeeid" value="${user.employeeId}" />
				<input style="display: inline" type="submit" class="btn btn-primary" value="Deactivate">
			</form>
		</div>
	</c:if>
</div>