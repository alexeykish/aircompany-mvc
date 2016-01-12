<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div style="margin: 5px;">
	<h2>Update Team</h2>
	<form name="updateflights" action="controller" method="post">
		<input type="hidden" name="command" value="update_team_command" />
		<input type="hidden" name="tid" value="${requestScope.team.tid}" />
		<table>
			<tr>
				<td class="input-label">ID:</td>
				<td><input class="inputForm" type="text" name="tid"
					value="${team.tid}" readonly /></td>
			</tr>
			<tr>
				<td class="input-label">First pilot:</td>
				<td><select class="inputForm" name="first_pilot">
						<option selected value="${team.firstPilot.eid}">${team.firstPilot.firstName} ${team.firstPilot.lastName}/${team.firstPilot.position}</option>
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${(employee.position == 'PILOT')&&(employee.eid != team.firstPilot.eid)}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Second pilot:</td>
				<td><select class="inputForm" name="second_pilot">
						<option selected value="${team.secondPilot.eid}">${team.secondPilot.firstName} ${team.secondPilot.lastName}/${team.secondPilot.position}</option>
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${(employee.position == 'PILOT')&&(employee.eid != team.secondPilot.eid)}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Navigator:</td>
				<td><select class="inputForm" name="navigator">
						<option selected value="${team.navigator.eid}">${team.navigator.firstName} ${team.navigator.lastName}/${team.navigator.position}</option>
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${(employee.position == 'NAVIGATOR')&&(employee.eid != team.navigator.eid)}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Radiooperator:</td>
				<td><select class="inputForm" name="radiooperator">
						<option selected value="${team.radiooperator.eid}">${team.radiooperator.firstName} ${team.radiooperator.lastName}/${team.radiooperator.position}</option>
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${(employee.position == 'RADIOOPERATOR')&&(employee.eid != team.radiooperator.eid)}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Stewardess1:</td>
				<td><select class="inputForm" name="stewardess1">
						<option selected value="${team.stewardess1.eid}">${team.stewardess1.firstName} ${team.stewardess1.lastName}/${team.stewardess1.position}</option>
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${(employee.position == 'STEWARDESS')&&(employee.eid != team.stewardess1.eid)}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Stewardess2:</td>
				<td><select class="inputForm" name="stewardess2">
						<option selected value="${team.stewardess2.eid}">${team.stewardess2.firstName} ${team.stewardess2.lastName}/${team.stewardess2.position}</option>
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${(employee.position == 'STEWARDESS')&&(employee.eid != team.stewardess2.eid)}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Stewardess3:</td>
				<td><select class="inputForm" name="stewardess3">
						<option selected value="${team.stewardess3.eid}">${team.stewardess3.firstName} ${team.stewardess3.lastName}/${team.stewardess3.position}</option>
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${(employee.position == 'STEWARDESS')&&(employee.eid != team.stewardess3.eid)}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input class="button" type="submit" name="submit"
			value="update flight" />
	</form>
</div>
