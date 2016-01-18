package by.pvt.kish.aircompany.command.airport;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.services.impl.AirportService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllAirportsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = GetAllAirportsCommand.class.getName();
        try {
            List<Airport> airports = AirportService.getInstance().getAll();
            request.setAttribute(Attribute.AIRPORTS_ATTRIBUTE, airports);
        } catch (SQLException e) {
            return ErrorHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
        }
        return Page.AIRPORTS;
    }
}
