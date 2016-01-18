/**
 * 
 */
package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.services.impl.TeamService;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class DeleteTeamCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = DeleteTeamCommand.class.getSimpleName();
		try {
			int id = RequestHandler.getId(request, "tid");
			if (id < 0) {
				return RequestHandler.returnErrorPage(Message.ERROR_ID_MISSING, className);
			}
			TeamService.getInstance().delete(id);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_DELETE_TEAM);
		} catch (SQLException e) {
			return RequestHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
		return Page.MAIN;
	}
}
