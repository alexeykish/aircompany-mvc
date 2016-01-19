/**
 *
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Employee;
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
     *
     * @param user - проверяемый объект User
     * @return - null, если все проверки пройдены корректно; если данные некорректны - соответствующую строку с указанием ошибки
     */
    public static String validate(User user) {

        if (checkEmpty(user)) {
            return Message.ERROR_EMPTY;
        }
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

    /**
     * Метод проверяет объект на <code>null</code> полноту заполнения всех позиций, пустые позиции не допускаются
     *
     * @param user - проверяемый объект User
     * @return - false, если все проверки пройдены корректно; true - если данные некорректны
     */
    private static boolean checkEmpty(User user) {
        if ((user == null) ||
                (user.getFirstName() == null) || (user.getFirstName().equals("")) ||
                (user.getLastName() == null) || (user.getLastName().equals("")) ||
                (user.getLogin() == null) || (user.getLogin().equals("")) ||
                (user.getPassword() == null) || (user.getPassword().equals("")) ||
                (user.getEmail() == null) || (user.getEmail().equals("")) ||
                (user.getUserType() == null) ||
                (user.getStatus() == null)) {
            return true;
        }
        return false;
    }
}
