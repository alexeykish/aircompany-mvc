/**
 * 
 */
package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.command.flight.UpdateFlightCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.services.impl.TeamService;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class SaveTeamToFlightCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) { //TODO validate
		String className = UpdateFlightCommand.class.getSimpleName();
		try {
			int id = RequestHandler.getId(request, "fid");
			if (id < 0) {
				return RequestHandler.returnErrorPage(Message.ERROR_ID_MISSING, className);
			}
			int num = Integer.parseInt(request.getParameter("num"));
			List<Integer> team = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				team.add(Integer.parseInt(request.getParameter(String.valueOf(i))));
			}
			TeamService.getInstance().add(id, team);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_TEAM_CHANGE);
		} catch (SQLException e) {
			return RequestHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
		return Page.MAIN;
	}

}
