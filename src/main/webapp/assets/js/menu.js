$(document).ready(function() {
	var path = location.pathname.split("/")[2];
	$("#verticalNav > .list-group > a").each(function() {
		var href = $(this).attr('href');
		if (path.substring(0, href.length) === href) {
			$(this).closest('a').addClass('active');
		}
	});
	
	if (path == "add_employee" || path == "view_all_employees"){
		$('#employeesMenu').addClass('active');
	}
});