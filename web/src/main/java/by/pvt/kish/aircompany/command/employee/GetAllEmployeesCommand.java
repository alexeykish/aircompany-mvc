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
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllEmployeesCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String className = GetAllEmployeesCommand.class.getName();
		try {
			List<Employee> employees = EmployeeService.getInstance().getAll();
			request.setAttribute(Attribute.EMPLOYEES_ATTRIBUTE, employees);
		} catch (SQLException e) {
			return RequestHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
		}
		return Page.EMPLOYEES;
	}
}
