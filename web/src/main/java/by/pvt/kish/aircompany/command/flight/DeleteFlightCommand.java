/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class DeleteFlightCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = DeleteFlightCommand.class.getSimpleName();
		try {
			int id = RequestHandler.getId(request, "fid");
			if (id < 0) {
				return ErrorHandler.returnErrorPage(Message.ERROR_ID_MISSING, className);
			}
			FlightService.getInstance().delete(id);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_DELETE_FLIGHT);
		} catch (SQLException e) {
			return ErrorHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
		return Page.MAIN;
	}
}
