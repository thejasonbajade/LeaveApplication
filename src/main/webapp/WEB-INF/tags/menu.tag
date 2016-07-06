<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Menu Tag" pageEncoding="UTF-8"%>
<div class="col-md-2 remove-padding" id="verticalNav">
	<ul class="list-group">
		<a href="account_info" role="button" class="list-group-item"> 
			<i class="fa fa-user fa-3x" aria-hidden="true"></i>  <br/>
			<p> Account Info </p> 
		</a>
		<a href="apply_leave" role="button" class="list-group-item"> 
			<i class="fa fa-pencil fa-3x" aria-hidden="true"></i> 
			<p> Apply for Leave </p> 
		</a>
		<a href="view_leave_history" role="button" class="list-group-item"> 
			<i class="fa fa-history fa-3x" aria-hidden="true"></i> 
			<p> Leave History </p>
		</a>
		<c:if test="${user.admin || user.HR} ">
		<li class="dropdown">
			<a id="employeesMenu" role="button" class="list-group-item dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
				<i class="fa fa-users fa-3x" aria-hidden="true"></i> 
				<p> Employees </p>
			</a>
			<ul class="dropdown-menu dropdown-menu-right" id="dropdownId">
				<a href="add_employee" role="button" class="list-group-item">
					<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i> 
					 Add Employee 
				</a>
				<a href="view_all_employees"  role="button" class="list-group-item"> 
					<i class="fa fa-list-ul fa-2x" aria-hidden="true"></i> 
					 Employee List
				</a>
			</ul>
		</li>
		</c:if>
		<c:if test="${user.supervisor || user.admin || user.HR} ">
			<c:if test="${user.supervisor}">
				<a href="view_leave_histories_supervisor" role="button" class="list-group-item"> 
			</c:if>	
			<c:if test="${user.admin || user.HR}">
				<a href="view_all_leave_histories" role="button" class="list-group-item"> 
			</c:if>
			<span class="badge">5</span>
				<i class="fa fa-list-alt fa-3x" aria-hidden="true"></i> 
				<p> Leave List </p>
			</a>
		</c:if>
		<c:if test="${user.admin || user.HR}">
			<a href="reports" role="button" class="list-group-item">
				<i class="fa fa-file-text fa-3x" aria-hidden="true"></i> 
				<p> Reports </p>
			</a>
		</c:if>
	</ul>
</div>