<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>

<div style="margin: 5px;">
	<h2>Update Flight</h2>
	<form name="updateflights" action="controller" method="post">
		<input type="hidden" name="command" value="update_flight_command" />
		<input type="hidden" name="fid" value="${flight.fid}" />
		<table>
			<tr>
				<td class="input-label">ID:</td>
				<td><input class="inputForm" type="text" name="fid"
					value="${flight.fid}" readonly /></td>
			</tr>
			<tr>
				<td class="input-label">date:</td>
				<td><input class="inputForm" type="text" name="date"
					value="${flight.date}" /></td>
			</tr>
			<tr>
				<td class="input-label">from:</td>
					<td><select class="inputForm" name="from">
						<option value="${flight.from.aid}">${flight.from.city}</option>
						<c:forEach items="${requestScope.airports}" var="airport">
							<c:if test="${flight.to.aid != airport.aid}">
								<option value="${airport.aid}">${airport.city}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">to:</td>
				<td><select class="inputForm" name="to">
						<option value="${flight.to.aid}">${flight.to.city}</option>
						<c:forEach items="${requestScope.airports}" var="airport">
							<c:if test="${flight.to.aid != airport.aid}">
								<option value="${airport.aid}">${airport.city}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">Plane:</td>
				<td><select class="inputForm" name="pid">
					<option value="${flight.plane.pid}">${flight.plane.model}</option>
					<c:forEach items="${requestScope.planes}" var="plane">
						<c:if test="${flight.plane.pid != plane.pid}">
							<option value="${plane.pid}">${plane.model}</option>
						</c:if>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="input-label">flight team:</td>
				<td><input class="inputForm" type="text" name="tid"
					value="${flight.tid}" readonly /></td>
			</tr>
		</table>
		<input class="button" type="submit" name="submit"
			value="update flight" />
	</form>
</div>
