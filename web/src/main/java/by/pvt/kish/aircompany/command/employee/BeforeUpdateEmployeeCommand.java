package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.enums.Position;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class BeforeUpdateEmployeeCommand extends EmployeeCommand {

    static Logger logger = Logger.getLogger(BeforeUpdateEmployeeCommand.class.getName());

    private final String EID = "eid";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter(EID);
            if (id == null) {
                logger.error(Message.ERROR_ID_MISSING);
                return Page.ERROR;
            }
            Employee employee = employeeService.getById(Integer.parseInt(id));
            request.setAttribute(Attribute.EMPLOYEE_ATTRIBUTE, employee);
            List<Position> positions = Arrays.asList(Position.values());
            request.setAttribute(Attribute.POSITIONS_ATTRIBUTE, positions);
            return Page.EDIT_EMPLOYEE;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Page.ERROR;
        }
    }
}
