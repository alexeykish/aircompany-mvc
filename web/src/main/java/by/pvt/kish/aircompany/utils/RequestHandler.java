package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.services.impl.AirportService;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class RequestHandler {

    static Logger logger = Logger.getLogger(RequestHandler.class.getName());

    public static Flight getFlight(HttpServletRequest request) throws SQLException {
        Flight flight = new Flight();
        String date = request.getParameter("date");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String pid = request.getParameter("pid");
        if (!checkNull(date) || !(checkNull(from)) || !(checkNull(to)) || !(checkNull(pid))) {
            return null;
        }
        flight.setDate(Date.valueOf(date.trim()));
        flight.setTo(AirportService.getInstance().getById(Integer.parseInt(to.trim())));
        flight.setFrom(AirportService.getInstance().getById(Integer.parseInt(from.trim())));
        flight.setPlane(PlaneService.getInstance().getById(Integer.parseInt(pid.trim())));

        int id = RequestHandler.getId(request, "fid");
        if (id > 0) {
            flight.setFid(id);
        }
        return flight;
    }

    private static boolean checkNull(String string) {
        return string != null && !string.trim().equals("");
    }

    public static int getId(HttpServletRequest request, String requestId) {
        String id = request.getParameter(requestId);
        if (id == null) {
            return -1;
        }
        return Integer.parseInt(id);
    }

    public static String returnValidateErrorPage(HttpServletRequest request, String validateResult, String className) {
        request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, validateResult);
        logger.error(className + ": " + validateResult);
        return Page.ERROR;
    }

    public static String returnErrorPage(String error, String className) {
        logger.error(className + ": " + error);
        return Page.ERROR;
    }

    public static Employee getEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String position = request.getParameter("position");
        if (!checkNull(firstName) || !(checkNull(lastName)) || !(checkNull(position))) {
            return null;
        }
        employee.setFirstName(firstName.trim());
        employee.setLastName(lastName.trim());
        employee.setPosition(Position.valueOf(position.trim()));
        int id = RequestHandler.getId(request, "eid");
        if (id > 0) {
            employee.setEid(id);
        }
        return employee;
    }
}
