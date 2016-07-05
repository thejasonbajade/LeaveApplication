<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:wrapper>
<div class="container-fluid" id="content">
	<div class="row">
		<t:menu/>
		<div class="col-md-10" id="mainContent">
			<div class="col-md-12">
				<h1>Notify AWOL</h1>
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
						<form class="col-md-8 col-md-offset-2">
							<div class="form-group col-md-12">
								<label class="col-md-4"> Subject </label>
								<div class="col-md-8">
									<input type="text" class="form-control" value="Notify AWOL">
								</div>
							</div>
							
							<div class="form-group col-md-12">
								<label class="col-md-4"> Message: </label>
								<div class="col-md-8">
									<textarea class="form-control" rows="4">Insert text template for AWOL notification here. . .</textarea>
								</div>
							</div>
							
							<input type="button" class="btn btn-primary" value="Send">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</t:wrapper>