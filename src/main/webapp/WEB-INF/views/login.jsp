<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
	
<link rel="stylesheet" href='<c:url value="/assets/css/bootstrap.min.css" />'>
<link rel="stylesheet" href='<c:url value="/assets/css/main.css" />'>
<link rel="stylesheet" href='<c:url value="/assets/css/font-awesome.min.css" />'>	
	
<title> Leave Management System </title>
</head>
<body id="index">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12" id=banner>
				<div class="col-md-8 col-md-offset-2">
					<h2> LEAVE SUBMISSION AND AUTHORIZATION SYSTEM </h2>
					<img src='<c:url value="/assets/images/oandblogo.png" />' class="img img-responsive" alt="Orange & Bronze" title="Orange & Bronze"/>
				</div>
				<div class="col-md-3 col-md-offset-7">
					<a href="account_info" class="white"> 
						<span> Sign in with: </span> 
						<img src='<c:url value="/assets/images/google.png" />' width = "50px" class="img" alt="Google" title="Google"/>	
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>