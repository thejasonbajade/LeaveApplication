<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:wrapper>
<div class="container-fluid" id="content">
	<div class="row">
		<t:menu/>
		<div class="col-md-10" id="mainContent">
			<div class="col-md-12">
				<h1> Account Information </h1>
				<div class="panel panel-default">
					<div class="panel-body">
						<t:account_details user="${user}"/>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<t:admin_tabs/>
				<div class="panel panel-default" id="mainContentBottom">
					<div class="panel-body">
						<t:leave_credits_display/>
					</div>
				</div>
			</div>	
		</div>
	</div>
</div>
</t:wrapper>