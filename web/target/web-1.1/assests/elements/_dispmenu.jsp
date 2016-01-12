<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div>
    <table>
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="get_all_flights_command"/>
                    <input class="button"
                           type="submit"
                           name="submit"
                           value="show flights"/>
                </form>
            </td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="before_add_employee_command"/>
                    <input class="button" type="submit" name="submit" value="add employee"/>
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="get_all_employees_command"/>
                    <input class="button"
                           type="submit"
                           name="submit"
                           value="show employees"/>
                </form>
            </td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="before_add_team_command"/>
                    <input class="button" type="submit" name="submit" value="add flight team"/>
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="get_all_teams_command"/>
                    <input class="button"
                           type="submit"
                           name="submit"
                           value="show flight teams"/>
                </form>
            </td>
        </tr>
    </table>
</div>
