package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.services.AirportService;
import by.pvt.kish.aircompany.services.PlaneService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class PreAddEmployeeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        List<Position> positions = Arrays.asList(Position.values());
        request.setAttribute(Attribute.POSITIONS_ATTRIBUTE, positions);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
