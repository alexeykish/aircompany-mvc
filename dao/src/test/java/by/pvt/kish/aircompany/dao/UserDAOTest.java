package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.utils.Coder;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Kish Alexey
 */
public class UserDAOTest {
    private UserDAO userDAO = UserDAO.getInstance();
    private  int id;
    private User testUser;

    @Before
    public void setUp() throws Exception {
        testUser = new User();
        testUser.setFirstName("testFirstName");
        testUser.setLastName("testLastName");
        testUser.setLogin("testLogin");
        testUser.setPassword(Coder.getHashCode("testPassword"));
        testUser.setEmail("test@test.com");
        testUser.setUserType(UserType.ADMINISTRATOR);
    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testCheckLogin() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }
}