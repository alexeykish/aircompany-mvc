/**
 * 
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.pvt.kish.aircompany.validators.FormDataValidator.*;

/**
 * Проверяет объект User перед добавлением или изменением его в БД
 *
 * @author Kish Alexey
 */
public class UserValidator {

    /**
     * Проверяет соттветсвие введенных данных пользователя шаблону
     * @param user - проверяемый объект User
     * @return - null, если все проверки пройдены корректно; если данные некорректны - соответствующую строку с указанием ошибки
     */
    public static String validate(User user) {
		if (!namePattern.matcher(user.getFirstName()).matches()) {
			return Message.MALFORMED_FIRSTNAME;
		}
		if (!namePattern.matcher(user.getLastName()).matches()) {
			return Message.MALFORMED_LASTNAME;
		}
		if (!loginPattern.matcher(user.getLogin()).matches()) {
			return Message.MALFORMED_LOGIN;
		}
		if (!passwordPattern.matcher(user.getPassword()).matches()) {
			return Message.MALFORMED_PASSWORD;
		}
		if (!emailPattern.matcher(user.getEmail()).matches()) {
			return Message.MALFORMED_EMAIL;
		}
		return null;
	}
}
