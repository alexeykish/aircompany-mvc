package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.UserDAO;
import by.pvt.kish.aircompany.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class LoginUserCommand implements ActionCommand {

	static Logger logger = Logger.getLogger(LoginUserCommand.class.getName());

	private final String UID = "uid";
	private final String LOGIN = "login";
	private final String PASSWORD = "password";
	private final String ADMINISTRATOR = "ADMINISTRATOR";
	private final String DISPATCHER = "DISPATCHER";
	private final int SESSION_AGE = 60 * 60; //one hour

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String login = request.getParameter(LOGIN);
			String password = request.getParameter(PASSWORD);
			if ((login == null) || (password == null)) {
				logger.error(Message.ERROR_REG_EMPTY);
				return Page.INDEX;
			}

			User user = UserDAO.getInstance().getUser(login, password);
			if (user == null) {
				request.setAttribute(Attribute.LOGIN_MESSAGE_ATTRIBUTE, Message.ERROR_REG_LOGIN);
				logger.error(Message.ERROR_REG_LOGIN);
				return Page.INDEX;
			}
			switch (user.getUserType()) {
				case ADMINISTRATOR:
					session.setAttribute(Attribute.USERTYPE_ATTRIBUTE, 2); // TODO access level
					break;
				case DISPATCHER:
					session.setAttribute(Attribute.USERTYPE_ATTRIBUTE, 1); // TODO access level
					break;
				default:
					session.setAttribute(Attribute.USERTYPE_ATTRIBUTE, 0); // TODO access level
					break;
			}
			session.setAttribute(Attribute.USER_ATTRIBUTE, user);
			Cookie c = new Cookie(UID, String.valueOf(user.getUid()));
			c.setMaxAge(SESSION_AGE);
			response.addCookie(c);
			return Page.MAIN;
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.INDEX;
		}
	}
}
