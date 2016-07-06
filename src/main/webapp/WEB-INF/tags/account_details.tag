<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Leave Credits Display Tag" pageEncoding="UTF-8"%>
<div class="col-md-12">
	<div class="col-md-3">
		<div class="col-md-2">
			<span class="fa-stack fa-4x"> <i
				class="fa fa-circle fa-stack-2x"></i> <i
				class="fa fa-user fa-stack-1x fa-inverse" style="color: orange"></i>
			</span>
		</div>
	</div>
	<div class="col-md-6">
		First Name: ${user.employeeRecord.firstName}<br /> Last Name: <br />
		Email Address: ${user.supervisor}<br /> Contact Number: <br />
	</div>
</div>