/**
 * 
 */
package by.pvt.kish.aircompany.command.user;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.UserDAO;
import by.pvt.kish.aircompany.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllUsersCommand implements ActionCommand {
	
	static Logger logger = Logger.getLogger(GetAllUsersCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User> users = UserDAO.getInstance().getAll();//TODO Null checking
			request.setAttribute(Attribute.USERS_ATTRIBUTE, users);
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.USERS;
	}
}
