package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.AirportDAO;
import by.pvt.kish.aircompany.dao.FlightDAO;
import by.pvt.kish.aircompany.dao.PlaneDAO;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.services.FlightService;
import by.pvt.kish.aircompany.utils.RequestHandler;
import by.pvt.kish.aircompany.validators.FlightValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class UpdateFlightCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = UpdateFlightCommand.class.getSimpleName();
		try {
			Flight flight = RequestHandler.getFlight(request);
			String validateResult = FlightValidator.validate(flight);
			if (validateResult != null) {
				return RequestHandler.returnValidateErrorPage(request, validateResult, AddFlightCommand.class.getName());
			}
			FlightService.getInstance().update(flight);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_UPDATE_FLIGHT);
		} catch (IllegalArgumentException e) {
			return RequestHandler.returnErrorPage(Message.ERROR_IAE, className);
		} catch (SQLException e) {
			return RequestHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
		return Page.MAIN;
	}
}
