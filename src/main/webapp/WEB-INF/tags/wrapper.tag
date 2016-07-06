<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Wrapper Tag" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
	
<link rel="stylesheet" href='<c:url value="/assets/css/bootstrap.min.css" />'>
<link rel="stylesheet" href='<c:url value="/assets/css/main.css" />'>
<link rel="stylesheet" href='<c:url value="/assets/css/font-awesome.min.css" />'>	
<link rel="stylesheet" href='<c:url value="/assets/css/validation-styles.css" />'>	
<link rel="stylesheet" href='<c:url value="/assets/css/bootstrap-toggle.min.css" />'>	
	
<title> Leave Management System </title>
</head>
	
<body>
<nav>
	<div  class="navbar navbar-fixed-top">
		<div class="navbar-header">
		<a class="navbar-brand" href="#"> 
			<img src='<c:url value="/assets/images/oandblogo.png" />' alt="Orange & Bronze Software Labs, Inc." title="Orange & Bronze Software Labs, Inc." id="navLogo">
		</a>
		</div>
		<div id="loggedAccount">
			You are logged in as 
			<p> rochelle.sisa@orangeandbronze.com </p>
		</div>
		<a href="login" role="button" title="Logout" id="navLogout">
		<i class="fa fa-power-off fa-2x" aria-hidden="true"></i>
		</a>
		
	</div> 
</nav>

	<jsp:doBody/>
<!--<div class="alert alert-danger fade in col-md-6" style="position: absolute; right: 0px">
	This is another sample alert with close that fades! 
	<button type="button" class="close" data-dismiss="alert" aria-label="close">&times;</button>
</div>-->
</body>
<script type="text/javascript" charset="utf8" src='<c:url value="assets/js/jquery.min.js"/>'></script>
<script type="text/javascript" charset="utf8" src='<c:url value="assets/js/bootstrap.min.js"/>'></script>
<script type="text/javascript" charset="utf8" src='<c:url value="assets/js/jquery.validate.js"/>'></script>
<script type="text/javascript" charset="utf8" src='<c:url value="assets/js/bootstrap-toggle.min.js"/>'></script>
<script type="text/javascript" charset="utf8" src='<c:url value="assets/js/menu.js"/>'></script>
<script type="text/javascript" charset="utf8" src='<c:url value="assets/js/validate.add.js"/>'></script>
<script type="text/javascript" charset="utf8" src='<c:url value="assets/js/validate.editprofile.js"/>'></script>
<script type="text/javascript" charset="utf8" src='<c:url value="assets/js/validate.leaveapplication.js"/>'></script>
</html>