<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Leave Credits Display Tag" pageEncoding="UTF-8"%>

<div class="col-md-12" id="leaveCreditsPanel">
	<div class="col-md-3">
		<div class="panel panel-default">
			<div class="panel-body">
				<h1>${user.leaveCredits.vacationLeaveCredits}</h1>
			</div>
			<div class="panel-footer">
				<p> Vacation Leave Credits </p>
			</div>
		</div>
	</div>
	<div class="col-md-3">
		<div class="panel panel-default">
			<div class="panel-body">
				<h1>${user.leaveCredits.sickLeaveCredits}</h1>
			</div>
			<div class="panel-footer">
				<p> Sick Leave Credits </p>
			</div>
		</div>
	</div>
	<div class="col-md-3">
		<div class="panel panel-default">
			<div class="panel-body">
				<h1>${user.leaveCredits.emergencyLeaveCredits}</h1>
			</div>
			<div class="panel-footer">
				<p> Emergency Leave Credits </p>
			</div>
		</div>
	</div>
	<div class="col-md-3">
		<div class="panel panel-default">
			<div class="panel-body">
				<h1>${user.leaveCredits.offsetLeaveCredits}</h1>
			</div>
			<div class="panel-footer">
				<p> Offset Credits </p>
			</div>
		</div>
	</div>					
</div>