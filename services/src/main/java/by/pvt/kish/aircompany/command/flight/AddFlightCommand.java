/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.AirportDAO;
import by.pvt.kish.aircompany.dao.FlightDAO;
import by.pvt.kish.aircompany.dao.PlaneDAO;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.validators.FlightValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class AddFlightCommand implements ActionCommand {

	static Logger logger = Logger.getLogger(AddFlightCommand.class.getName());

	private final String DATE = "date";
	private final String FROM = "from";
	private final String TO = "to";
	private final String FLIGHT_TEAM = "tid";
	private final String PID = "pid";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			Flight flight = new Flight();

			flight.setDate(Date.valueOf(request.getParameter(DATE).trim()));
			flight.setFrom(AirportDAO.getInstance().getById(Integer.parseInt(request.getParameter(FROM).trim())));
			flight.setTo(AirportDAO.getInstance().getById(Integer.parseInt(request.getParameter(TO).trim())));
			flight.setTid(Integer.parseInt(request.getParameter(FLIGHT_TEAM).trim()));
			flight.setPlane(PlaneDAO.getInstance().getById(Integer.parseInt(request.getParameter(PID).trim())));

			String validateResult = FlightValidator.validate(flight, request);
			if (validateResult != null) {
				return validateResult;
			}

			int id = FlightDAO.getInstance().add(flight);

			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_ADD_FLIGHT + "; flight id : " + id);
		} catch (IllegalArgumentException e) {
			logger.error(Message.ERROR_IAE);
			return Page.ERROR;
		} catch (SQLException e1) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.MAIN;
	}
}