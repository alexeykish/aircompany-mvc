/**
 *
 */
package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import by.pvt.kish.aircompany.services.impl.TeamService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;
import by.pvt.kish.aircompany.utils.TeamCreator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class SetTeamCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = SetTeamCommand.class.getSimpleName();
        try {
            int id = RequestHandler.getId(request, "fid");
            if (id < 0) {
                return ErrorHandler.returnErrorPage(Message.ERROR_ID_MISSING, className);
            }
            Flight flight = FlightService.getInstance().getById(id);
            List<Employee> team = TeamService.getInstance().getById(id);
            List<Employee> employees = EmployeeService.getInstance().getAll();
            List<String> positions = TeamCreator.getPlanePositions(PlaneService.getInstance().getById(flight.getPlane().getPid()));
            request.setAttribute(Attribute.FLIGHT_ATTRIBUTE, flight);
            request.setAttribute(Attribute.EMPLOYEES_ATTRIBUTE, employees);
            request.setAttribute(Attribute.POSITIONS_ATTRIBUTE, positions);
            if (team.size() != 0) {
                request.setAttribute(Attribute.TEAM_ATTRIBUTE, team);
                return Page.CHANGE_TEAM;
            }
        } catch (SQLException e) {
            return ErrorHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
        }
        return Page.SET_TEAM;
    }
}
