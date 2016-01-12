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
 * Проверяет обеъект Employee перед добавлением или изменением его в БД
 *
 * @author Kish Alexey
 */
public class EmployeeValidator {
	
	static Logger logger = Logger.getLogger(EmployeeValidator.class.getName());

    /**
     * Проверяет на корректность:
     * <li>наличие пустых полей</li>
     * @param employee - проверяемый объект Employee
     * @return - null, если все проверки пройдены корректно; страницу ошибки, если данные некорректны
     */
    public static String validate(Employee employee) {
		if (checkEmpty(employee)) {
			return Message.ERROR_EMPTY;
		}
		return null;
	}

    /**
     * Метод проверяет полноту заполнения всех позиций, пустые позиции не допускаются
     * @param employee - проверяемый объект Employee
     * @return - false, если все проверки пройдены корректно; true - если данные некорректны
     */
    private static boolean checkEmpty(Employee employee) {
		if ((employee.getFirstName() == null) || (employee.getFirstName().equals(""))) {
			return true;
		}
		if ((employee.getLastName() == null) || (employee.getLastName().equals(""))) {
			return true;
		}
		if ((employee.getPosition() == null) || (employee.getPosition().equals(""))) {
            return true;
        }
        return false;
	}
}
