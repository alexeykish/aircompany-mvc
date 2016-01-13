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
 * Проверяет обеъект Flight перед добавлением или изменением его в БД
 *
 * @author Kish Alexey, 2015
 *
 */
public class FlightValidator {
	
	static Logger logger = Logger.getLogger(FlightValidator.class.getName());

    /**
     * Проверяет на корректность:
     * <li>наличие пустых полей</li>
     * <li>корректность указания мест вылета и прилета</li>
     * @param flight - проверяемый объект Flight
     * @return - null, если все проверки пройдены корректно; страницу ошибки, если данные некорректны
     */
    public static String validate(Flight flight) {
		if (checkEmpty(flight)) {
			return Message.ERROR_EMPTY;
		}
		if (checkEntry(flight)) {
			return Message.ERROR_FLIGHT_VALID;
		}
		return null;
	}

    /**
     * Метод проверяет полноту заполнения всех позиций, пустые позиции не допускаются
     * @param flight - проверяемый объект Flight
     * @return - false, если все проверки пройдены корректно; true - если данные некорректны
     */
    private static boolean checkEmpty(Flight flight) {
		if (flight == null) {
            return true;
        }
		if (flight.getDate() == null) {
			return true;
		}
		if (flight.getFrom() == null) {
			return true;
		}
		if (flight.getTo() == null) {
			return true;
		}
		if (flight.getPlane() == null) {
            return true;
        }
        return false;
	}

    /**
     * Метод проверяет место вылета и место прилета, они не должны совпадать
     * @param flight - проверяемый объект Flight
     * @return - false, если все проверки пройдены корректно; true - если данные некорректны
     */
    private static boolean checkEntry(Flight flight) {
		return flight.getFrom().equals(flight.getTo());
	}
}
