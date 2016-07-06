<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:wrapper>
<div class="container-fluid" id="content">
	<div class="row">
		<t:menu/>
		<div class="col-md-10" id="mainContent">
			<div class="col-md-12">
			<h1>Leave Application Form</h1>
				<div class="panel panel-default">
					<div class="panel-body">
						<t:apply_leave_credits/>
						<t:apply_form/>
					</div>
				</div>		
			</div>	
		</div>
	</div>
</div>
</t:wrapper>