<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div style="margin:5px;">
    <h2>Plane report: #${requestScope.plane.pid}</h2>
    <table class="input-label">
        <tr>
            <td>Model:</td>
            <td>${requestScope.plane.model}</td>
        </tr>
        <tr>
            <td>Passenger capacity:</td>
            <td>${requestScope.plane.capacity}</td>
        </tr>
        <tr>
            <td>Flight range:</td>
            <td>${requestScope.plane.range}</td>
        </tr>
        <tr>
            <td>Flight team:</td>
        </tr>
        <tr>
            <td>Number of pilots:</td>
            <td>${requestScope.team['PILOT']}</td>
        </tr>
        <tr>
            <td>Number of navigators:</td>
            <td>${requestScope.team['NAVIGATOR']}</td>
        </tr>
        <tr>
            <td>Number of radiooperators:</td>
            <td>${requestScope.team['RADIOOPERATOR']}</td>
        </tr>
        <tr>
            <td>Number of stewardess:</td>
            <td>${requestScope.team['STEWARDESS']}</td>
        </tr>
    </table>
    <div>
        <form action="controller" method="post">
            <h4>Plane status is: ${requestScope.plane.status}</h4>
            <select class="inputForm" name="status" title="Status">
                <c:forEach items="${requestScope.statuses}" var="status">
                    <option value="${status}">${status}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="command" value="set_plane_status_command"/>
            <input type="hidden" name="pid" value="${requestScope.plane.pid}"/>
            <input class="button" type="submit" name="submit" value="set status"/>
        </form>
    </div>
    <div>
        <h4>It is possible to change or delete plane if there are no flight associated with this plane</h4>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="delete_plane_command"/>
            <input type="hidden" name="pid" value="${requestScope.plane.pid}"/>
            <c:choose>
                <c:when test="${requestScope.permissionChangeDeleteStatus =='false'}">
                    <input class="button" type="submit" name="submit" value="delete"/>
                </c:when>
                <c:otherwise>
                    <input class="button" type="submit" name="submit" value="delete" disabled/>
                </c:otherwise>
            </c:choose>
        </form>
        <form action="updateplane" method="post">
            <input type="hidden" name="pid" value="${requestScope.plane.pid}"/>
            <c:choose>
                <c:when test="${requestScope.permissionChangeDeleteStatus =='false'}">
                    <input class="button" type="submit" name="submit" value="edit"/>
                </c:when>
                <c:otherwise>
                    <input class="button" type="submit" name="submit" value="edit" disabled/>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
</div>
