<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:wrapper>
	<div class="container-fluid" id="content">
		<div class="row">
			<t:menu />
			<div class="col-md-10" id="mainContent">
				<div class="col-md-12">
					<h1>My Leave History</h1>
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
										<c:when test="${leaveApplication.status == 'SUPERVISOR_APPROVED'}">
											<td><span class="label label-success">Approved</span></td>
											<td><span class="label label-warning">Pending</span></td>
										</c:when>
										<c:when test="${leaveApplication.status == 'ADMIN_APPROVED'}">
											<td><span class="label label-success">Approved</span></td>
											<td><span class="label label-success">Approved</span></td>
										</c:when><c:when test="${leaveApplication.status == 'SUPERVISOR_DISAPPROVED'}">
											<td><span class="label label-danger">Disapproved</span></td>
											<td><span class="label label-danger">Disapproved</span></td>
										</c:when>
										<c:when test="${leaveApplication.status == 'ADMIN_DISAPPROVED'}">
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
									<td>${leaveApplication.filer.firstName} ${leaveApplication.filer.lastName}</td>
									<td>${leaveApplication.leaveType}</td>
									<td>${leaveApplication.dateFiled}</td>
									<td>${leaveApplication.startDate}</td>
									<td>${leaveApplication.endDate}</td>
									<td>${leaveApplication.numberOfLeaveDays}</td>
									<td><a href="#leaveApplication${leaveId}" data-toggle="modal" data-target="#leaveApplication${leaveId}">
											<i class="fa fa-eye text-primary" aria-hidden="true" title="View"></i> 
										</a>
										<a href="cancel_leave/${leaveApplication.leaveId}">
											<i class="fa fa-times text-danger" aria-hidden="true" value="Cancel"></i>
										</a>
									</td>
								</tr>
								<t:leave_details />
							</c:forEach>
							<tr>
								<td>Approved</td>
								<td>Approved</td>
								<td> Bajade</td>
								<td>SL</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><i class="fa fa-eye text-primary" aria-hidden="true"></i>
									<i class="fa fa-times text-danger" aria-hidden="true"></i></td>
							</tr>
						</tbody>
					</table>
					<div class="col-md-12" id="pagination">
						<ul class="pagination">
							<li class="active"><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</t:wrapper>