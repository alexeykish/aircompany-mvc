package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.AirportDAO;
import by.pvt.kish.aircompany.dao.PlaneDAO;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.entity.Plane;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class BeforeAddFlightCommand implements by.pvt.kish.aircompany.command.ActionCommand {

    static Logger logger = Logger.getLogger(BeforeAddFlightCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Airport> airports = AirportDAO.getInstance().getAll();// TODO Null checking
            List<Plane> planes = PlaneDAO.getInstance().getAll();// TODO Null checking
            request.setAttribute(Attribute.AIRPORTS_ATTRIBUTE, airports);
            request.setAttribute(Attribute.PLANES_ATTRIBUTE, planes);
            return Page.ADD_FLIGHT;
        } catch (SQLException e) {
            logger.error(Message.ERROR_SQL_DAO);
            return Page.ERROR;
        }
    }
}
