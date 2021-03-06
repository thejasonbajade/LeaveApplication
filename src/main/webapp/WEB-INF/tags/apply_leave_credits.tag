<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Leave Credits Display Tag" pageEncoding="UTF-8"%>
<div class="col-md-12 col-md-offset-2" id="leaveCreditsPanel">
	<div class="col-md-2">
		<div class="panel panel-info" id="vlpanel">
			<div class="panel-heading">
				<p>Vacation Leave Balance</p>
			</div>
			<div class="panel-body">
				<h3>${user.leaveCredits.vacationLeaveCredits}</h3>
			</div>
		</div>
	</div>
	<div class="col-md-2">
		<div class="panel panel-default" id="slpanel">
			<div class="panel-heading">
				<p>Sick Leave Balance</p>
			</div>
			<div class="panel-body">
				<h3>${user.leaveCredits.sickLeaveCredits}</h3>
			</div>
		</div>
	</div>
	<div class="col-md-2">
		<div class="panel panel-default" id="elpanel">
			<div class="panel-heading">
				<p>Emergency Leave Balance</p>
			</div>
			<div class="panel-body">
				<h3>${user.leaveCredits.emergencyLeaveCredits}</h3>
			</div>
		</div>
	</div>
	<div class="col-md-2">
		<div class="panel panel-default" id="olpanel">
			<div class="panel-heading">
				<p>Offset Leave Balance</p>
			</div>
			<div class="panel-body">
				<h3>${user.leaveCredits.offsetLeaveCredits}</h3>
			</div>
		</div>
	</div>
</div>