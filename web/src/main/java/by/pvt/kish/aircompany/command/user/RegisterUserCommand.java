package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.services.impl.UserService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;
import by.pvt.kish.aircompany.validators.PlaneValidator;
import by.pvt.kish.aircompany.validators.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class RegisterUserCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = RegisterUserCommand.class.getName();
		try {
			User user = RequestHandler.getUser(request);
			String validateResult = UserValidator.validate(user);
			if (validateResult!=null) {
				return ErrorHandler.returnValidateErrorPage(request, validateResult, className);
			}
			if (UserService.getInstance().add(user) < 0) {
				return ErrorHandler.returnLoginErrorPage(request, Message.ERROR_REG_USER_EXISTS, className);
			} else {
				request.setAttribute(Attribute.LOGIN_MESSAGE_ATTRIBUTE, Message.SUCCESS_REG);
				return Page.INDEX;
			}
		} catch (SQLException e) {
			return ErrorHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
	}
}
