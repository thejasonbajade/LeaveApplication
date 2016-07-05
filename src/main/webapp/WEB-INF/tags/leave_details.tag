<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Leave Details Tag" pageEncoding="UTF-8"%>
<div class="modal fade" id="leaveApplication${leaveId}">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Leave Details</h4>
			</div>
			<div class="modal-body" style="height:450px">
			
				<div class="col-md-12">
					<label class="col-md-4"> Name </label>
					<div class="col-md-8">
						<p> Jason Bajade </p>
					</div>
				</div>
				
				<div class="col-md-12">
					<label class="col-md-4"> Status </label>
					<div class="col-md-8">
						<p> Approved </p>
					</div>
				</div>
				
				<div class="col-md-12">
					<label class="col-md-4"> Leave Type </label>
					<div class="col-md-8">
						<p> Sick Leave </p>
					</div>
				</div>
				
				<div class="col-md-12">
					<label class="col-md-4"> Supervisor </label>
					<div class="col-md-8">
						<p> Supervisor Name </p>
					</div>
				</div>
				
				<div class="col-md-12">
					<label class="col-md-4"> Start Date </label>
					<div class="col-md-8">
						<p> Month XX, XXXX </p>
					</div>
				</div>
				
				<div class="col-md-12">
					<label class="col-md-4"> End Date </label>
					<div class="col-md-8">
						<p> Month XX, XXXX </p>
					</div>
				</div>

				<div class="col-md-12">
					<label class="col-md-4"> Num of days </label>
					<div class="col-md-8">
						
					</div>
				</div>
				
				<div class="col-md-12">
					<label class="col-md-4"> Reason </label>
					<div class="col-md-8">
						<p>
							Reason here. . . .
						</p>
					</div>
				</div>
				
				<div class="col-md-12">
					<label class="col-md-4"> Comment </label>
					<div class="col-md-8">
						<p>
							Comment here. . . .
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>