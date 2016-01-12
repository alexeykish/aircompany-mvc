<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div style="margin: 5px;">
	<h2>Add Team</h2>
	<form name="addteam" action="controller" method="post">
		<input type="hidden" name="command" value="add_team_command" />
		<table>
			<tr>
				<td class="input-label">First pilot:</td>
				<td><select class="inputForm" name="first_pilot" title="First pilot">
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${employee.position == 'PILOT'}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Second pilot:</td>
				<td><select class="inputForm" name="second_pilot" title="Second pilot">
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${employee.position.equals('PILOT')}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Navigator:</td>
				<td><select class="inputForm" name="navigator" title="Navigator">
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${employee.position.equals('NAVIGATOR')}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Radiooperator:</td>
				<td><select class="inputForm" name="radiooperator" title="Radiooperator">
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${employee.position.equals('RADIOOPERATOR')}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Stewardess 1:</td>
				<td><select class="inputForm" name="stewardess1" title="Stewardess1">
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${employee.position.equals('STEWARDESS')}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Stewardess 2:</td>
				<td><select class="inputForm" name="stewardess2" title="Stewardess2">
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${employee.position.equals('STEWARDESS')}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Stewardess 3:</td>
				<td><select class="inputForm" name="stewardess3" title="Stewardess3">
						<c:forEach items="${requestScope.employees}" var="employee">
							<c:if test="${employee.position.equals('STEWARDESS')}">
								<option value="${employee.eid}">${employee.firstName} ${employee.lastName}/${employee.position}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input class="button" type="submit" name="submit" value="add team" />
	</form>
</div>
