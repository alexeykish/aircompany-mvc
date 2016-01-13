package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.services.FlightService;

/**
 * @author Kish Alexey
 */
public abstract class FlightCommand  implements ActionCommand{
    FlightService flightService = new FlightService();
}
