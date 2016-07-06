<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<t:wrapper>
	<div class="container-fluid" id="content">
		<div class="row">
			<t:menu />
			<div class="col-md-10" id="mainContent">
				<div class="col-md-12">
					<h1>Employee Leave History</h1>
					<div class="panel panel-default">
						<div class="panel-body">
							<t:account_details user="${user}"></t:account_details>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<t:admin_tabs />
					<div class="panel panel-default" id="mainContentBottom">
						<div class="panel-body">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Status (Supervisor)</th>
										<th>Status (HR)</th>
										<th>Name</th>
										<th>Leave Type</th>
										<th>Date Filed</th>
										<th>Start Date</th>
										<th>End Date</th>
										<th>Duration</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${leaveApplications}" var="leaveApplication">
										<tr>
											<c:choose>
												<c:when test="${leaveApplication.status == 'PENDING'}">
													<td><span class="label label-warning">Pending</span></td>
													<td><span class="label label-warning">Pending</span></td>
												</c:when>
												<c:when
													test="${leaveApplication.status == 'SUPERVISOR_APPROVED'}">
													<td><span class="label label-success">Approved</span></td>
													<td><span class="label label-warning">Pending</span></td>
												</c:when>
												<c:when
													test="${leaveApplication.status == 'ADMIN_APPROVED'}">
													<td><span class="label label-success">Approved</span></td>
													<td><span class="label label-success">Approved</span></td>
												</c:when>
												<c:when
													test="${leaveApplication.status == 'SUPERVISOR_DISAPPROVED'}">
													<td><span class="label label-danger">Disapproved</span></td>
													<td><span class="label label-danger">Disapproved</span></td>
												</c:when>
												<c:when
													test="${leaveApplication.status == 'ADMIN_DISAPPROVED'}">
													<td><span class="label label-success">Approved</span></td>
													<td><span class="label label-danger">Disapproved</span></td>
												</c:when>
												<c:when test="${leaveApplication.status == 'NOT_TAKEN'}">
													<td><span class="label label-default">Not Taken</span></td>
													<td><span class="label label-default">Not Taken</span></td>
												</c:when>
												<c:when test="${leaveApplication.status == 'CANCELLED'}">
													<td><span class="label label-default">Cancelled</span></td>
													<td><span class="label label-default">Cancelled</span></td>
												</c:when>
											</c:choose>
											<td>${leaveApplication.filer.employeeRecord.firstName}
												${leaveApplication.filer.employeeRecord.lastName}</td>
											<td>${leaveApplication.leaveDetails.leaveType.toString()}</td>
											<td>${leaveApplication.leaveDetails.dateFiled.format(formatter)}</td>
											<td>${leaveApplication.leaveDetails.startDate.format(formatter)}</td>
											<td>${leaveApplication.leaveDetails.endDate.format(formatter)}</td>
											<td>${leaveApplication.leaveDetails.numberOfLeaveDays}</td>
											<td><a
												href="#leaveApplication${leaveApplication.leaveId}"
												data-toggle="modal"
												data-target="#leaveApplication${leaveApplication.leaveId}">
													<i class="fa fa-eye text-primary" aria-hidden="true"
													title="View"></i>
											</a> <c:if test="${leaveApplication.status == 'PENDING'}">
													<a href="cancel_leave/${leaveApplication.leaveId}"> <span
														class="label label-danger"><i class="fa fa-times"
															aria-hidden="true" title="Cancel"></i></span>
													</a>
												</c:if></td>
										</tr>
										<t:leave_details leaveApplication="${leaveApplication}" />
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</t:wrapper>