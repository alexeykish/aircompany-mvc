package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.enums.UserType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class UserValidatorTest {

    User valideUser;

    @Before
    public void setUp() throws Exception {
        valideUser = new User();
        valideUser.setFirstName("Firstname");
        valideUser.setLastName("Lastname");
        valideUser.setLogin("login");
        valideUser.setPassword("1111111");
        valideUser.setEmail("email@email.com");
        valideUser.setUserType(UserType.DISPATCHER);
        valideUser.setStatus(UserStatus.OFFLINE);
    }

    @Test
    public void testValidateData() throws Exception {
        assertNull("User validate failed", UserValidator.validate(valideUser));
        valideUser.setFirstName("FIRSTNAME");
        assertEquals("Validate method failed: firstName is wrong", UserValidator.validate(valideUser), Message.MALFORMED_FIRSTNAME);
        valideUser.setFirstName("firstTname");
        assertEquals("Validate method failed: firstName is wrong", UserValidator.validate(valideUser), Message.MALFORMED_FIRSTNAME);
        valideUser.setFirstName("Firstname");
        valideUser.setLastName("LASTNAME");
        assertEquals("Validate method failed: lastName is wrong", UserValidator.validate(valideUser), Message.MALFORMED_LASTNAME);
        valideUser.setLastName("lastName");
        assertEquals("Validate method failed: lastName is wrong", UserValidator.validate(valideUser), Message.MALFORMED_LASTNAME);
        valideUser.setLastName("Lastname");
        valideUser.setLogin("testLoginlong");
        assertEquals("Validate method failed: login is to long", UserValidator.validate(valideUser), Message.MALFORMED_LOGIN);
        valideUser.setLogin("te");
        assertEquals("Validate method failed: login is to short", UserValidator.validate(valideUser), Message.MALFORMED_LOGIN);
        valideUser.setLogin("test!login");
        assertEquals("Validate method failed: login is wrong", UserValidator.validate(valideUser), Message.MALFORMED_LOGIN);
        valideUser.setLogin("login");
        valideUser.setPassword("passw");
        assertEquals("Validate method failed: password is to short", UserValidator.validate(valideUser), Message.MALFORMED_PASSWORD);
        valideUser.setPassword("passwordpassword");
        assertEquals("Validate method failed: password is to long", UserValidator.validate(valideUser), Message.MALFORMED_PASSWORD);
        valideUser.setPassword("pass.word");
        assertEquals("Validate method failed: password is wrong", UserValidator.validate(valideUser), Message.MALFORMED_PASSWORD);
        valideUser.setPassword("1111111");
        valideUser.setEmail("emailemail.com");
        assertEquals("Validate method failed: email is wrong", UserValidator.validate(valideUser), Message.MALFORMED_EMAIL);
        valideUser.setEmail("emai@lemailcom");
        assertEquals("Validate method failed: email is wrong", UserValidator.validate(valideUser), Message.MALFORMED_EMAIL);
    }

    @Test
    public void testValidateNull() throws Exception {
        valideUser.setEmail("email@email.com");
        valideUser.setFirstName(null);
        assertEquals("Validate method failed: firstName is null", UserValidator.validate(valideUser), Message.ERROR_EMPTY);
        valideUser.setFirstName("Firstname");
        valideUser.setLastName(null);
        assertEquals("Validate method failed: lastName is null", UserValidator.validate(valideUser), Message.ERROR_EMPTY);
        valideUser.setLastName("Lastname");
        valideUser.setLogin(null);
        assertEquals("Validate method failed: login is null", UserValidator.validate(valideUser), Message.ERROR_EMPTY);
        valideUser.setLogin("login");
        valideUser.setPassword(null);
        assertEquals("Validate method failed: password is null", UserValidator.validate(valideUser), Message.ERROR_EMPTY);
        valideUser.setPassword("1111111");
        valideUser.setEmail(null);
        assertEquals("Validate method failed: email is null", UserValidator.validate(valideUser), Message.ERROR_EMPTY);
        valideUser.setEmail("email@email.com");
        valideUser.setUserType(null);
        assertEquals("Validate method failed: type is null", UserValidator.validate(valideUser), Message.ERROR_EMPTY);
        valideUser.setUserType(UserType.DISPATCHER);
        valideUser.setStatus(null);
        assertEquals("Validate method failed: status is null", UserValidator.validate(valideUser), Message.ERROR_EMPTY);
        valideUser.setStatus(UserStatus.OFFLINE);
    }


}