package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.services.AirportService;
import by.pvt.kish.aircompany.services.EmployeeService;
import by.pvt.kish.aircompany.services.PlaneService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class PreAddTeamFilter implements Filter {

    static Logger logger = Logger.getLogger(PreAddTeamFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employees = employeeService.getAll();
            request.setAttribute(Attribute.EMPLOYEES_ATTRIBUTE, employees);
            chain.doFilter(request,response);
        } catch (SQLException e) {
            logger.error(Message.ERROR_SQL_DAO);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Page.ERROR);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
