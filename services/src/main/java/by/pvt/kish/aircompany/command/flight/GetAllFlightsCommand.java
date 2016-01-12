/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.FlightDAO;
import by.pvt.kish.aircompany.entity.Flight;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllFlightsCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(GetAllFlightsCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Flight> flights = FlightDAO.getInstance().getAll(); // TODO Null checking
			request.setAttribute(Attribute.FLIGHTS_ATTRIBUTE, flights);
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.FLIGHTS;
	}

}
