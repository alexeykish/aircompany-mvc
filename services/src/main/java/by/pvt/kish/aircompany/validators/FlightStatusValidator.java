package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.FlightService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kish Alexey
 */
public class FlightStatusValidator {

    public static void updateFlightsStatus() throws ServiceException {
        List<Flight> flights = FlightService.getInstance().getAll();
        for (Flight flight : flights) {
            System.out.println("#" + flight.getFid() + ", Flight date is: " + flight.getDate() + ": " + checkDate(flight) + ", Team: " + checkTeam(flight));
        }
    }

    private static String checkDate(Flight flight) {
        java.util.Date yesterdayDate = new java.util.Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
        java.util.Date todayDate = new java.util.Date(System.currentTimeMillis());
        java.util.Date flightDate = flight.getDate();
        if (yesterdayDate.after(flightDate)) {
            return "Yesterday";
        } else if (todayDate.before(flightDate)) {
            return "Tomorrow";
        } else {
            return "Today";
        }
    }

    private static String checkTeam(Flight flight) {
        if (flight.getTeam().size() > 0) {
            return "Matched";
        } else {
            return "Empty";
        }
    }
}
