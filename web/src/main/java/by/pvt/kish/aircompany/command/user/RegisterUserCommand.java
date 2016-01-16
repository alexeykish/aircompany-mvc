package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.services.UserService;
import by.pvt.kish.aircompany.validators.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class RegisterUserCommand implements ActionCommand {

	static Logger logger = Logger.getLogger(RegisterUserCommand.class.getName());

	private final String FIRSTNAME = "first_name";
	private final String LASTNAME = "last_name";
	private final String LOGIN = "login";
	private final String PASSWORD = "password";
	private final String EMAIL = "email";
	private final String USERTYPE = "user_type";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = new User();
			user.setFirstName(request.getParameter(FIRSTNAME).trim());
			user.setLastName(request.getParameter(LASTNAME).trim());
			user.setLogin(request.getParameter(LOGIN).trim());
			user.setPassword(request.getParameter(PASSWORD).trim());
			user.setEmail(request.getParameter(EMAIL).trim());
			user.setUserType(UserType.valueOf(request.getParameter(USERTYPE).trim()));

			String validateResult = UserValidator.validate(user);
			if (validateResult != null) {
				request.setAttribute(Attribute.LOGIN_MESSAGE_ATTRIBUTE, validateResult);
				logger.error(Message.ERROR_REG_DATA + " " + validateResult);
				return Page.INDEX;
			}

			if (!UserService.getInstance().checkLogin(user.getLogin())) {
				request.setAttribute(Attribute.LOGIN_MESSAGE_ATTRIBUTE, Message.ERROR_REG_LOGIN);
				logger.error(Message.ERROR_REG_LOGIN);
				return Page.INDEX;
			}
			UserService.getInstance().add(user);
			request.setAttribute(Attribute.LOGIN_MESSAGE_ATTRIBUTE, Message.SUCCESS_REG);
			return Page.INDEX;
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.INDEX;
		}
	}
}
