/**
 * 
 */
package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;
import by.pvt.kish.aircompany.validators.EmployeeValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class UpdateEmployeeCommand implements ActionCommand {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = UpdateEmployeeCommand.class.getName();
		try {
			Employee employee = RequestHandler.getEmployee(request);
			String validateResult = EmployeeValidator.validate(employee);
			if (validateResult!=null) {
				return ErrorHandler.returnValidateErrorPage(request, Attribute.MESSAGE_ATTRIBUTE, validateResult);
			}
            EmployeeService.getInstance().update(employee);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_UPDATE_EMPLOYEE);
			return Page.MAIN;

		} catch (SQLException e) {
			return ErrorHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
	}
}
