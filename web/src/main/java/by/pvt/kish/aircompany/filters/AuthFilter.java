package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Kish Alexey
 */
public class AuthFilter implements Filter {

    static Logger logger = Logger.getLogger(AuthFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(Attribute.USER_ATTRIBUTE);
        //int userType = (int) session.getAttribute(Attribute.USERTYPE_ATTRIBUTE);
        if (user == null) {
        //if (userType < 1){
            logger.info("AuthFilter in action: user type = 0");
            RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher(Page.INDEX);
            requestDispatcher.forward(servletRequest, servletResponse);
        } else {
            logger.info("AuthFilter in action: user type > 0 ");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Authentication filter is initialized");
    }

    @Override
    public void destroy() {
    }

}
