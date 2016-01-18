package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class PreUpdateEmployeeFilter implements Filter {

    static Logger logger = Logger.getLogger(PreUpdateEmployeeFilter.class.getName());
    private final String EID = "eid";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String id = request.getParameter(EID);
            if (id == null) {
                logger.error(Message.ERROR_ID_MISSING);
                RequestDispatcher dispatcher = request.getRequestDispatcher(Page.ERROR);
                dispatcher.forward(request, response);
            }
            Employee employee = EmployeeService.getInstance().getById(Integer.parseInt(id));
            request.setAttribute(Attribute.EMPLOYEE_ATTRIBUTE, employee);
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
