package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class BeforeAddTeamCommand implements by.pvt.kish.aircompany.command.ActionCommand {

    static Logger logger = Logger.getLogger(BeforeAddTeamCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Employee> employees = EmployeeDAO.getInstance().getAll();// TODO Null checking
            request.setAttribute(Attribute.EMPLOYEES_ATTRIBUTE, employees);
            return Page.ADD_TEAM;
        } catch (SQLException e) {
            logger.error(Message.ERROR_SQL_DAO);
            return Page.ERROR;
        }
    }
}
