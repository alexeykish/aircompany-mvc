/**
 * 
 */
package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.validators.EmployeeValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kish Alexey
 */
public class UpdateEmployeeCommand implements ActionCommand {
	
	static Logger logger = Logger.getLogger(UpdateEmployeeCommand.class.getName());
	private final String EID = "eid";
	private final String FIRSTNAME = "firstName";
	private final String LASTNAME = "lastName";
	private final String POSITION = "position";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee employee = new Employee();

			employee.setEid(Integer.parseInt(request.getParameter(EID).trim()));
			employee.setFirstName(request.getParameter(FIRSTNAME).trim());
			employee.setLastName(request.getParameter(LASTNAME).trim());
			employee.setPosition(Position.valueOf(request.getParameter(POSITION).trim()));

			String validateResult = EmployeeValidator.validate(employee,  request);
			if (validateResult!=null) {
				return validateResult;
			}

			EmployeeDAO.getInstance().update(employee);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_UPDATE_EMPLOYEE);
			return Page.MAIN;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return Page.ERROR;
		}
	}
}
