package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kish Alexey
 */
public abstract class UserCommand implements ActionCommand {
    UserService userService = new UserService();
}
