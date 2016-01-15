<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<div>
    <table class="list">
        <tr>
            <th>ID</th>
            <th>LASTNAME</th>
            <th>FIRSTNAME</th>
            <th>POSITION</th>
        </tr>
        <c:forEach items="${requestScope.employees}" var="employee">
            <tr>
                <td>${employee.eid}</td>
                <td>${employee.lastName}</td>
                <td>${employee.firstName}</td>
                <td>${employee.position}</td>
                <td style="width: 70px;">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="delete_employee_command"/>
                        <input type="hidden" name="eid" value="${employee.eid}"/>
                        <input class="table-button" type="submit" name="submit" value="delete"/>
                    </form>
                </td>
                <td style="width: 70px;">
                    <form action="updateemployee" method="post">
                        <input type="hidden" name="eid" value="${employee.eid}"/>
                        <input class="table-button" type="submit" name="submit" value="edit"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="addemployee" method="post">
        <input class="button" type="submit" name="submit" value="add employee"/>
    </form>
</div>
