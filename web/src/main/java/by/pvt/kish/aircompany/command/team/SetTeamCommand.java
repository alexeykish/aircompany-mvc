/**
 * 
 */
package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.FlightDAO;
import by.pvt.kish.aircompany.dao.FlightTeamDAO;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.services.FlightService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class SetTeamCommand extends FlightTeamCommand {

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
			Flight flight = flightService.getById(Integer.parseInt(id));
			List<FlightTeam> teams = teamService.getAll();
			request.setAttribute(Attribute.FLIGHT_ATTRIBUTE, flight); 
			request.setAttribute(Attribute.TEAMS_ATTRIBUTE, teams); 
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.SET_TEAM;
	}
}
