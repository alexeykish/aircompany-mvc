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
public class UpdateFlightCommand extends FlightCommand {

	static Logger logger = Logger.getLogger(UpdateFlightCommand.class.getName());

	private final String FID = "fid";
	private final String DATE = "date";
	private final String FROM = "from";
	private final String TO = "to";
	private final String FLIGHT_TEAM = "tid";
	private final String PID = "pid";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			Flight flight = new Flight();

			flight.setFid(Integer.parseInt(request.getParameter(FID).trim()));
			flight.setDate(Date.valueOf(request.getParameter(DATE).trim()));
			flight.setFrom(AirportDAO.getInstance().getById(Integer.parseInt(request.getParameter(FROM).trim())));
			flight.setTo(AirportDAO.getInstance().getById(Integer.parseInt(request.getParameter(TO).trim())));
			//flight.setTid(Integer.parseInt(request.getParameter(FLIGHT_TEAM).trim()));
			flight.setPlane(PlaneDAO.getInstance().getById(Integer.parseInt(request.getParameter(PID).trim())));

			String validateResult = FlightValidator.validate(flight);
			if (validateResult!=null) {
				request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, validateResult);
				return Page.ERROR;
			}
			flightService.update(flight);

			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_UPDATE_FLIGHT);
		} catch (IllegalArgumentException e) {
			logger.error(Message.ERROR_IAE);
			return Page.ERROR;
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.MAIN;
	}
}
