<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="Leave Credits Display Tag" pageEncoding="UTF-8"%>
<style>
.leavetype {
	background: #e1e1e1;
	width: 17%;
}

.leavecredits {
	background: #f2f2f2;
}

.sides{
	border-left: 1px solid #878787;
	border-right: 1px solid #878787;
}

.left{
	border-left: 1px solid #878787;
}
</style>

<table class="table panel-footer">
	<tr>
		<th class="leavetype">Vacation Leave</th>
		<td class="leavecredits sides">15.0</td>
		<th class="leavetype">Sick Leave</th>
		<td class="leavecredits sides">15.0</td>
		<th class="leavetype">Emergency Leave</th>
		<td class="leavecredits sides">3.0</td>
		<th class="leavetype">Offset</th>
		<td class="leavecredits left">0.0</td>
	</tr>
</table>
