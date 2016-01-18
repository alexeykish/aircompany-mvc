/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllFlightsCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = GetAllFlightsCommand.class.getSimpleName();
		try {
			List<Flight> flights = FlightService.getInstance().getAll();
			request.setAttribute(Attribute.FLIGHTS_ATTRIBUTE, flights);
		} catch (SQLException e) {
			return RequestHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
		return Page.FLIGHTS;
	}

}
