<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Admin Tabs" pageEncoding="UTF-8"%>
<ul class="nav nav-tabs">
	<li class="active"><a href="account_info"> Leave Credits </a></li>
	<c:if test="${sessionScope.user.admin}">
		<li><a href="edit_profile"> Edit Profile </a></li>
		<li><a href="/leaveapplication/view_employee_leave_history/${user.employeeId}"> Leave History </a></li>
		<li><a href="notify_lwop"> Notify LWOP </a></li>
		<li><a href="notify_awol"> Notify AWOL </a></li>
	</c:if>
</ul>