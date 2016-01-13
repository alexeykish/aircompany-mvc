package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.employee.EmployeeCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.services.EmployeeService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class BeforeAddTeamCommand extends FlightTeamCommand {

    static Logger logger = Logger.getLogger(BeforeAddTeamCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employees = employeeService.getAll();
            request.setAttribute(Attribute.EMPLOYEES_ATTRIBUTE, employees);
            return Page.ADD_TEAM;
        } catch (SQLException e) {
            logger.error(Message.ERROR_SQL_DAO);
            return Page.ERROR;
        }
    }
}
