/**
 *
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Flight;

/**
 * Describes the utility class to test the Flight object before adding or changing it in the DB
 *
 * @author Kish Alexey, 2015
 */
public class FlightValidator implements IValidator<Flight> {

    /**
     * Check the validity of:
     * <li>the presence of empty fields</li>
     * <li>correct indication of places of departure and arrival</li>
     *
     * @param flight - Flight object being checked
     * @return - Null, if everything checks out correctly; error page if the data is incorrect
     */
    public String validate(Flight flight) {
        if (checkEmpty(flight)) {
            return Message.ERROR_EMPTY;
        }
        if (checkEntry(flight)) {
            return Message.ERROR_FLIGHT_VALID;
        }
        return null;
    }

    /**
     * The method checks the object to <code>null</code> completeness of all positions are empty positions are not allowed
     *
     * @param flight - Flight object being checked
     * @return - false, if everything checks out correctly; true - if the data is invalid
     */
    private static boolean checkEmpty(Flight flight) {
        return flight == null ||
                flight.getDate() == null ||
                flight.getFrom() == null ||
                flight.getTo() == null ||
                flight.getPlane() == null;
    }

    /**
     * The method checks the place of departure and place of arrival, they will not be the same
     *
     * @param flight - Flight object being checked
     * @return - false, if everything checks out correctly; true - if the data is invalid
     */
    private static boolean checkEntry(Flight flight) {
        return flight.getFrom().equals(flight.getTo());
    }
}
