<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div style="margin: 5px;">
	<h2>Set flight team to flight</h2>
	<form name="setteam" action="controller" method="post">
		<input type="hidden" name="command" value="save_team_to_flight_command" />
		<table>
			<tr>
				<td class="input-label">Flight id:</td>
				<td><input class="inputForm" type="text" name="fid"	value="${requestScope.flight.fid}" readonly  title="Flight id"/></td>
			</tr>
			<tr>
				<td class="input-label">Flight team:</td>
				<td><select class="inputForm" name="tid" title="Flight team">
						<option value="${requestScope.flight.tid}">${requestScope.flight.tid}</option>
						<c:forEach items="${requestScope.teams}" var="team">
							<option value="${team.tid}">${team.tid}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input class="button" type="submit" name="submit" value="save flight team to flight" />
	</form>
</div>
