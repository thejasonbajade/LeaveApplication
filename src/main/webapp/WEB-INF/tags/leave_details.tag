<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Leave Details Tag" pageEncoding="UTF-8"%>
<%@attribute name="leaveApplication" required="true"
	type="com.orangeandbronze.leaveapp.domain.LeaveApplication"%>
<div class="modal fade" id="leaveApplication${leaveApplication.leaveId}">
	<div class="modal-dialog">


		<div class="modal-content">
			<div class="modal-body" style="min-height: 580px">
				<h4 class="modal-title">Leave Details</h4>
				<br />
				<div class="col-md-2">
					<span class="fa-stack fa-3x"> <i
						class="fa fa-circle fa-stack-2x"></i> <i
						class="fa fa-user fa-stack-1x fa-inverse" style="color:orange"></i>
					</span>
				</div>
				<div class="col-md-10"><br/>
					<p><b>Filer:</b>&nbsp;&nbsp;${leaveApplication.filer.employeeRecord.firstName}
						${leaveApplication.filer.employeeRecord.lastName}</p>
					<p><b>Department:</b>&nbsp;&nbsp;${leaveApplication.filer.employeeRecord.department.name}</p>
					<hr/>
				</div>
				<div class="col-md-12">
					<div class="col-md-4 col-md-offset-1">
						<p style><b>Status (Supervisor)</b></p><br/>
						<p><b>Status (Admin)</b></p><br/>
						<p><b>Leave Type</b></p><br/>
						<p><b>Supervisor</b></p><br/>
						<p><b>Start Date</b></p><br/>
						<p><b>End Date</b></p><br/>
						<p><b>Number of Days</b></p><br/>
						<p><b>Reason</b></p><br/>
					</div>
					<div class="col-md-7">
					<c:choose>
						<c:when test="${leaveApplication.status == 'PENDING'}">
							<p><span class="label label-warning">Pending</span></p><br/>
							<p><span class="label label-warning">Pending</span></p><br/>
						</c:when>
						<c:when test="${leaveApplication.status == 'SUPERVISOR_APPROVED'}">
							<p><span class="label label-success">Approved</span></p><br/>
							<p><span class="label label-warning">Pending</span></p><br/>
						</c:when>
						<c:when test="${leaveApplication.status == 'ADMIN_APPROVED'}">
							<p><span class="label label-success">Approved</span></p><br/>
							<p><span class="label label-success">Approved</span></p><br/>
						</c:when>
						<c:when
							test="${leaveApplication.status == 'SUPERVISOR_DISAPPROVED'}">
							<p><span class="label label-danger">Disapproved</span></p><br/>
							<p><span class="label label-danger">Disapproved</span></p><br/>
						</c:when>
						<c:when test="${leaveApplication.status == 'ADMIN_DISAPPROVED'}">
							<p><span class="label label-success">Approved</span></p><br/>
							<p><span class="label label-danger">Disapproved</span></p><br/>
						</c:when>
						<c:when test="${leaveApplication.status == 'NOT_TAKEN'}">
							<p><span class="label label-default">Not Taken</span></p><br/>
							<p><span class="label label-default">Not Taken</span></p><br/>
						</c:when>
						<c:when test="${leaveApplication.status == 'CANCELLED'}">
							<p><span class="label label-default">Cancelled</span></p><br/>
							<p><span class="label label-default">Cancelled</span></p><br/>
						</c:when>
					</c:choose>
					<p>${leaveApplication.leaveType.toString()}</p><br/>
					<p>${leaveApplication.approver.employeeRecord.firstName}
							${leaveApplication.approver.employeeRecord.lastName}</p><br/>
					<p>${leaveApplication.startDate.format(formatter)} &nbsp;&nbsp;
						<c:if test="${leaveApplication.startHalfDay}">
							<span class="label label-primary"> Half day </span>
						</c:if>
					</p><br/>
					<p>${leaveApplication.endDate.format(formatter)} &nbsp;&nbsp;
						<c:if test="${leaveApplication.endHalfDay}">
							<span class="label label-primary"> Half day </span>
						</c:if></p><br/>
					<p>${leaveApplication.numberOfLeaveDays}</p><br/>
					<p>${leaveApplication.reason}</p><br/>
					</div>
				</div>
				<!--<div class="col-md-12">
					<label class="col-md-4"></label>
					<div class="col-md-8">
						<p>Approved</p>
					</div>
				</div>
				<div class="col-md-12">
					<label class="col-md-4"><b>Status (Admin)</b></label>
					<div class="col-md-8">
						<p>Approved</p>
					</div>
				</div>

				<div class="col-md-12">
					<label class="col-md-4"><b>Leave Type</b></label>
					<div class="col-md-8">
						<p>${leaveApplication.leaveType.toString()}</p>
					</div>
				</div>

				<div class="col-md-12">
					<label class="col-md-4"><b>Supervisor</b></label>
					<div class="col-md-8">
						<p>${leaveApplication.approver.employeeRecord.firstName}
							${leaveApplication.approver.employeeRecord.lastName}</p>
					</div>
				</div>

				<div class="col-md-12">
					<label class="col-md-4"><b>Start Date</b></label>
					<div class="col-md-8">
						<p>${leaveApplication.startDate.format(formatter)}</p>
					</div>
				</div>

				<div class="col-md-12">
					<label class="col-md-4"><b>End Date</b></label>
					<div class="col-md-8">
						<p>${leaveApplication.endDate.format(formatter)}</p>
					</div>
				</div>

				<div class="col-md-12">
					<label class="col-md-4"><b>Number of Leave Days</b></label>
					<div class="col-md-8">
						<p>${leaveApplication.numberOfLeaveDays}</p>
					</div>
				</div>

				<div class="col-md-12">
					<label class="col-md-4"><b>Reason</b></label>
					<div class="col-md-8">
						<p>${leaveApplication.reason}</p>
					</div>
				</div>

				<div class="col-md-12">
					<label class="col-md-4"><b>bComment</b></label>
					<div class="col-md-8">
						<p>Comment here. . . .</p>
					</div>
				</div> -->
			</div>
		</div>
	</div>
</div>