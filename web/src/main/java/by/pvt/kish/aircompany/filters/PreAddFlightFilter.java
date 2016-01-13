package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.services.AirportService;
import by.pvt.kish.aircompany.services.PlaneService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class PreAddFlightFilter implements Filter {

    static Logger logger = Logger.getLogger(PreAddFlightFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            PlaneService planeService = new PlaneService();
            AirportService airportService = new AirportService();
            List<Plane> planes = planeService.getAll();
            List<Airport> airports = airportService.getAll();
            request.setAttribute(Attribute.AIRPORTS_ATTRIBUTE, airports);
            request.setAttribute(Attribute.PLANES_ATTRIBUTE, planes);
            chain.doFilter(request,response);
        } catch (SQLException e) {
            logger.error(Message.ERROR_SQL_DAO);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Page.ERROR);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
