/**
 * 
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Employee;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kish Alexey
 */
public class EmployeeValidator {
	
	static Logger logger = Logger.getLogger(EmployeeValidator.class.getName());
	
	public static String validate(Employee employee, HttpServletRequest request) {
		if (checkEmpty(employee)) {
			logger.error(Message.ERROR_EMPTY);
			return Page.ERROR;
		}
		return null;
	}
	
	private static boolean checkEmpty(Employee employee) {
		if ((employee.getFirstName() == null) || (employee.getFirstName().equals(""))) {
			return true;
		}
		if (employee.getLastName() == null || (employee.getLastName().equals(""))) {
			return true;
		}
		return (employee.getPosition() == null) || (employee.getPosition().equals(""));
	}
}
