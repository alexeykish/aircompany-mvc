<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div>
    <table class="list">
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>From</th>
            <th>To</th>
            <th>Flight_team</th>
            <th>Plane</th>
        </tr>
        <c:forEach items="${requestScope.flights}" var="flight">
            <tr>
                <td>${flight.fid}</td>
                <td>${flight.date}</td>
                <td>${flight.from.city}</td>
                <td>${flight.to.city}</td>
                <td>${flight.tid}</td>
                <td>${flight.plane.model}</td>
                <c:if test="${(sessionScope.userType == '2')}">
                    <td style="width: 70px;">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="delete_flight_command"/>
                            <input type="hidden" name="fid" value="${flight.fid}"/>
                            <input class="table-button" type="submit"
                                   name="submit"
                                   value="delete"/>
                        </form>
                    </td>
                    <td style="width: 70px;">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="before_update_flight_command"/>
                            <input type="hidden" name="fid" value="${flight.fid}"/>
                            <input class="table-button" type="submit" name="submit" value="edit"/>
                        </form>
                    </td>
                </c:if>
                <c:if test="${(sessionScope.userType == '1')}">
                    <td style="width: 100px;">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="set_team_command"/>
                            <input type="hidden" name="fid" value="${flight.fid}"/>
                            <c:if test="${(flight.tid == '0')}">
                                <input class="table-button" style="width: 90px;  color:red;" type="submit" name="submit"
                                       value="set flight team"/>
                            </c:if>
                            <c:if test="${(flight.tid > '0')}">
                                <input class="table-button" style="width: 90px;" type="submit" name="submit"
                                       value="change flight team"/>
                            </c:if>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
