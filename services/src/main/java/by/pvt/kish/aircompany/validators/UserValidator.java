/**
 * 
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.pvt.kish.aircompany.validators.FormDataValidator.*;

public class UserValidator {
	static Logger logger = Logger.getLogger(UserValidator.class.getName());
	
	public static String validate(User user, HttpServletRequest request) {
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
