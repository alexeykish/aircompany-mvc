/**
 * 
 */
package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.services.EmployeeService;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class DeleteEmployeeCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = DeleteEmployeeCommand.class.getName();
		try {
			int id = RequestHandler.getId(request, "eid");
			if (id < 0) {
				return RequestHandler.returnErrorPage(Message.ERROR_ID_MISSING, className);
			}
			EmployeeService.getInstance().delete(id);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_DELETE_EMPLOYEE);
			return Page.MAIN;
		} catch (SQLException e) {
			return RequestHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
	}
}
