/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;
import by.pvt.kish.aircompany.services.impl.FlightService;
import by.pvt.kish.aircompany.validators.FlightValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class AddFlightCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = AddFlightCommand.class.getSimpleName();
		try {
			Flight flight = RequestHandler.getFlight(request);
			String validateResult = FlightValidator.validate(flight);
			if (validateResult != null) {
				return ErrorHandler.returnValidateErrorPage(request, validateResult, className);
			}
			FlightService.getInstance().add(flight);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_ADD_FLIGHT);
		} catch (IllegalArgumentException e) {
			return ErrorHandler.returnErrorPage(Message.ERROR_IAE, className);
		} catch (SQLException e1) {
			return ErrorHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
		return Page.MAIN;
	}
}
