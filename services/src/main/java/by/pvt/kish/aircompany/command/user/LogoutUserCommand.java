package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kish Alexey
 */
public class LogoutUserCommand implements ActionCommand {

	static Logger logger = Logger.getLogger(LogoutUserCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession currentSession = request.getSession();
		if (currentSession != null) {

			logger.info(Message.USER_LOGOUT);
			currentSession.invalidate();

		}
		return Page.INDEX;
	}

}
