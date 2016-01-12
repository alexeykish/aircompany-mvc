<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>
	<table class="list">
		<tr>
			<th>ID</th>
			<th>FIRST PILOT</th>
			<th>SECOND PILOT</th>
			<th>NAVIGATOR</th>
			<th>RADIOOPERATOR</th>
			<th>STEWARDESS</th>
			<th>STEWARDESS</th>
			<th>STEWARDESS</th>
		</tr>
		<c:forEach items="${requestScope.teams}" var="team">
			<tr>
				<td>${team.tid}</td>
				<td>
					<div>#${team.firstPilot.eid}</div>
					<div>${team.firstPilot.firstName} ${team.firstPilot.lastName}</div>
					<div>${team.firstPilot.position}</div>
				</td>
				<td>
					<div>#${team.secondPilot.eid}</div>
					<div>${team.secondPilot.firstName} ${team.secondPilot.lastName}</div>
					<div>${team.secondPilot.position}</div>
				</td>
				<td>
					<div>#${team.navigator.eid}</div>
					<div>${team.navigator.firstName} ${team.navigator.lastName}</div>
					<div>${team.navigator.position}</div>
				</td>
				<td>
					<div>#${team.radiooperator.eid}</div>
					<div>${team.radiooperator.firstName} ${team.radiooperator.lastName}</div>
					<div>${team.radiooperator.position}</div>
				</td>
				<td>
					<div>#${team.stewardess1.eid}</div>
					<div>${team.stewardess1.firstName} ${team.stewardess1.lastName}</div>
					<div>${team.stewardess1.position}</div>
				</td>
				<td>
					<div>#${team.stewardess2.eid}</div>
					<div>${team.stewardess2.firstName} ${team.stewardess2.lastName}</div>
					<div>${team.stewardess2.position}</div>
				</td>
				<td>
					<div>#${team.stewardess3.eid}</div>
					<div>${team.stewardess3.firstName} ${team.stewardess3.lastName}</div>
					<div>${team.stewardess3.position}</div>
				</td>
				<td style="width: 70px;">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="delete_team_command" />
						<input type="hidden" name="tid" value="${team.tid}" />
						<input class="table-button" type="submit" name="submit"	value="delete" />
					</form>
				</td>
				<td style="width: 70px;">
					<form action="updateteam" method="post">
						<input type="hidden" name="tid" value="${team.tid}" />
						<input class="table-button" type="submit" name="submit"	value="edit" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
