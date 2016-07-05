<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:wrapper>
<div class="container-fluid" id="content">
	<div class="row">
		<t:menu/>
		<div class="col-md-10" id="mainContent">
			<div class="col-md-12">
				<h1>Edit Account Information</h1>
				<div class="panel panel-default">
					<div class="panel-body">
						<t:account_details/>
					</div>
				</div>
			</div>						
			<div class="col-md-12">
				<t:admin_tabs/>
					<div class="panel panel-default" id="mainContentBottom">
						<div class="panel-body">
							<form class="col-md-8 col-md-offset-2" id="editprofileform">
								<div class="form-group col-md-12">
									<label class="col-md-4"> First Name </label>
									<div class="col-md-8">
										<input type="text" class="form-control" name="firstname"
											value="Jason">
									</div>
								</div>

								<div class="form-group col-md-12">
									<label class="col-md-4"> Last Name </label>
									<div class="col-md-8">
										<input type="text" class="form-control" name="lastname"
											value="Bajade">
									</div>
								</div>

								<div class="form-group col-md-12">
									<label class="col-md-4"> Email </label>
									<div class="col-md-8">
										<span>jasonbajade@orangeandbronze.com</span>
									</div>
								</div>

								<div class="form-group col-md-12">
									<label class="col-md-4"> Contact Number </label>
									<div class="col-md-8">
										<input type="text" class="form-control" value="09"
											name="contactnumber">
									</div>
								</div>

								<div class="form-group col-md-12">
									<label class="col-md-4"> Employment Date </label>
									<div class="col-md-8">
										<input type="date" class="form-control" name="employmentdate"
											value="2016-01-09">
									</div>
								</div>
								<div class="form-group col-md-12">
									<label class="col-md-4"> Status </label>
									<div class="col-md-8">
										<label class="radio-inline"><input type="radio"
											id="employeestatus" name="employeestatus"
											value="probationary">Probationary</label> <label
											class="radio-inline"><input type="radio" checked
											id="employeestatus" name="employeestatus" value="regular">Regular</label>
									</div>
								</div>

								<div class="form-group col-md-12" id="regulardatediv">
									<label class="col-md-4"> Regularization Date </label>
									<div class="col-md-8">
										<input type="date" name="regularizationdate"
											class="form-control" value="2016-05-09">
									</div>
								</div>

								<div class="form-group col-md-12">
									<label class="col-md-4"> Department </label>
									<div class="col-md-8">
										<select class="form-control" name="department">
											<option>Department 1</option>
											<option>Department 2</option>
											<option>Department 3</option>
											<option>Department 4</option>
										</select>
									</div>
								</div>

								<div class="form-group col-md-12">
									<label class="col-md-4"> Employee Position </label>
									<div class="col-md-8">
										<input type="text" name="employeeposition"
											class="form-control" value="Senior Enterprise Engineer">
									</div>
								</div>

								<div class="form-group col-md-12">
									<label class="col-md-4"> Roles </label>
									<div class="col-md-8">
										<div class="checkbox">
											<label><input type="checkbox" id="adminrole"
												name="roles" value="admin"> Admin </label>
										</div>
										<div class="checkbox" name="roles">
											<label><input type="checkbox" id="supervisorrole"
												name="roles" value="supervisor"> Supervisor </label>
										</div>
										<div class="checkbox" name="roles">
											<label><input type="checkbox" id="hrrole"
												name="roles" value="hr"> HR </label>
										</div>
									</div>
								</div>
								<input type="submit" class="btn btn-primary" value="Submit">
							</form>
						</div>
					</div>
				</div>	
		</div>
	</div>
</div>
</t:wrapper>