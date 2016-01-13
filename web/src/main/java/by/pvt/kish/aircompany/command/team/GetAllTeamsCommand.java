/**
 * 
 */
package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.FlightTeamDAO;
import by.pvt.kish.aircompany.entity.FlightTeam;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllTeamsCommand extends FlightTeamCommand {

	static Logger logger = Logger.getLogger(GetAllTeamsCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<FlightTeam> teams = teamService.getAll();
			request.setAttribute(Attribute.TEAMS_ATTRIBUTE, teams);
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.TEAMS;
	}

}
