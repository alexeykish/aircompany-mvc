/**
 *
 */
package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.services.EmployeeService;
import by.pvt.kish.aircompany.services.FlightService;
import by.pvt.kish.aircompany.services.PlaneService;
import by.pvt.kish.aircompany.services.TeamService;
import by.pvt.kish.aircompany.utils.TeamCreator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class SetTeamCommand extends TeamCommand {

    static Logger logger = Logger.getLogger(SetTeamCommand.class.getName());

    private final String FID = "fid";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter(FID);
            if (id == null) {
                logger.error(Message.ERROR_ID_MISSING);
                return Page.ERROR;
            }

            FlightService flightService = new FlightService();
            PlaneService planeService = new PlaneService();
            EmployeeService employeeService = new EmployeeService();
            TeamService teamService = new TeamService();

            Flight flight = flightService.getById(Integer.parseInt(id));
            List<Employee> team = teamService.getById(Integer.parseInt(id));
            List<String> positions = TeamCreator.getPlanePositions(planeService.getById(flight.getPlane().getPid()));
            List<Employee> employees = employeeService.getAll();

            request.setAttribute(Attribute.FLIGHT_ATTRIBUTE, flight);
            request.setAttribute(Attribute.EMPLOYEES_ATTRIBUTE, employees);
            request.setAttribute(Attribute.POSITIONS_ATTRIBUTE, positions);
            if (team.size() == 0) {
                request.setAttribute(Attribute.TEAM_ATTRIBUTE, team);
                return Page.CHANGE_TEAM;
            } else {
                return Page.SET_TEAM;
            }
        } catch (SQLException e) {
            logger.error(Message.ERROR_SQL_DAO);
            return Page.ERROR;
        }
    }
}
