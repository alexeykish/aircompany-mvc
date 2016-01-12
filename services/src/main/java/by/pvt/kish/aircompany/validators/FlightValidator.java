/**
 * 
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Flight;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kish Alexey, 2015
 *
 */
public class FlightValidator {
	
	static Logger logger = Logger.getLogger(FlightValidator.class.getName());
	
	public static String validate(Flight flight, HttpServletRequest request) {
		if (checkEmpty(flight)) {
			logger.error(Message.ERROR_EMPTY);
			return Page.ERROR;
		}
		if (checkEntry(flight)) {
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.ERROR_FLIGHT_VALID);
			logger.error(Message.ERROR_FLIGHT_VALID);
			return Page.ERROR;
		}
		return null;
	}

	private static boolean checkEmpty(Flight flight) {
		if (flight.getDate() == null) {
			return true;
		}
		if (flight.getFrom() == null) {
			return true;
		}
		if (flight.getTo() == null) {
			return true;
		}
		return flight.getPlane() == null;
	}

	private static boolean checkEntry(Flight flight) {
		return flight.getFrom().equals(flight.getTo());
	}
}
