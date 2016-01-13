/**
 * 
 */
package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class DeleteEmployeeCommand extends EmployeeCommand {

	static Logger logger = Logger.getLogger(DeleteEmployeeCommand.class.getName());
	private final String EID = "eid";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter(EID);
			if (id == null) {
				logger.error(Message.ERROR_ID_MISSING);
				return Page.ERROR;
			}
			employeeService.delete(Integer.parseInt(id));
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_DELETE_EMPLOYEE);
			return Page.MAIN;
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
	}
}
