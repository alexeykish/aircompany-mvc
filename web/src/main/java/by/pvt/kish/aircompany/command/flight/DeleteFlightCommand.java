/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.FlightDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class DeleteFlightCommand extends FlightCommand {

	static Logger logger = Logger.getLogger(DeleteFlightCommand.class.getName());

	private final String FID = "fid";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter(FID);
			if (id == null) {
				logger.error(Message.ERROR_ID_MISSING);
				return Page.ERROR;
			}
			flightService.delete(Integer.parseInt(id));

			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_DELETE_FLIGHT);
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.MAIN;
	}
}
