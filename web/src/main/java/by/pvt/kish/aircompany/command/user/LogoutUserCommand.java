package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class LogoutUserCommand implements ActionCommand {

	static Logger logger = Logger.getLogger(LogoutUserCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession currentSession = request.getSession();
		if (currentSession != null) {
			try {
				User user = (User)currentSession.getAttribute(Attribute.USER_ATTRIBUTE);
				UserService.getInstance().setStatus(user.getUid(), UserStatus.OFFLINE);
				currentSession.invalidate();
				logger.info(Message.USER_LOGOUT);
			} catch (SQLException e) {
				request.setAttribute(Attribute.LOGIN_MESSAGE_ATTRIBUTE, Message.ERROR_REG_LOGOUT);
				logger.error(Message.ERROR_REG_LOGIN);
				return Page.MAIN;
			}
		}
		return Page.INDEX;
	}
}
