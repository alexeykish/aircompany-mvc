package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.EmployeeDAO;
import by.pvt.kish.aircompany.dao.FlightTeamDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class BeforeUpdateTeamCommand implements by.pvt.kish.aircompany.command.ActionCommand {

    static Logger logger = Logger.getLogger(BeforeUpdateTeamCommand.class.getName());

    private final String TID = "tid";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter(TID);
            if (id == null) {
                logger.error(Message.ERROR_ID_MISSING);
                return Page.ERROR;
            }
            List<Employee> employees = EmployeeDAO.getInstance().getAll();//TODO Null checking
            FlightTeam team = FlightTeamDAO.getInstance().getById(Integer.parseInt(id));//TODO Null checking
            request.setAttribute(Attribute.TEAM_ATTRIBUTE, team);
            request.setAttribute(Attribute.EMPLOYEES_ATTRIBUTE, employees);
            return Page.EDIT_TEAM;
        } catch (SQLException e) {
            logger.error(Message.ERROR_SQL_DAO);
            return Page.ERROR;
        }
    }
}
