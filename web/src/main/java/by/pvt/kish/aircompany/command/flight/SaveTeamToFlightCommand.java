/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.FlightDAO;
import by.pvt.kish.aircompany.services.TeamService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class SaveTeamToFlightCommand extends FlightCommand {

	static Logger logger = Logger.getLogger(SaveTeamToFlightCommand.class.getName());
	private final String FID = "fid";
	private final String NUM = "num";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter(FID);
			int num = Integer.parseInt(request.getParameter(NUM));
			if (id == null || num == 0) {
				logger.error(Message.ERROR_ID_MISSING);
				return Page.ERROR;
			}
			TeamService teamService = new TeamService();
			List<Integer> team = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				team.add(Integer.parseInt(request.getParameter(String.valueOf(i))));
			}
			teamService.delete(Integer.parseInt(id));
			flightService.updateFlightByTeam(Integer.parseInt(id), team);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_TEAM_CHANGE);
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.MAIN;
	}

}
