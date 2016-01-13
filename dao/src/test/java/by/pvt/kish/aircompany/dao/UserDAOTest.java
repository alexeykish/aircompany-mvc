package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.utils.Coder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class UserDAOTest {
    private UserDAO userDao = UserDAO.getInstance();
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
        id = userDao.add(testUser);
    }

    @Test
    public void testAdd() throws Exception {
        User addedUser = userDao.getById(id);
        assertEquals("Add method failed: wrong firstname", testUser.getFirstName(), addedUser.getFirstName());
        assertEquals("Add method failed: wrong lastname", testUser.getLastName(), addedUser.getLastName());
        assertEquals("Add method failed: wrong login", testUser.getLogin(), addedUser.getLogin());
        assertEquals("Add method failed: wrong email", testUser.getEmail(), addedUser.getEmail());
        assertEquals("Add method failed: wrong usertype", testUser.getUserType(), addedUser.getUserType());
    }

    @Test
    public void testCheckLogin() throws Exception {
        assertFalse("CheckLogin method positive test failed", userDao.checkLogin(testUser.getLogin()));
        assertTrue("CheckLogin method negative test failed", userDao.checkLogin("wrongLogin"));
    }

    @Test
    public void testGetUser() throws Exception {
        User gettedUser = userDao.getUser(testUser.getLogin(),testUser.getPassword());
        assertEquals("Get method failed: wrong firstname", testUser.getFirstName(), gettedUser.getFirstName());
        assertEquals("Get method failed: wrong lastname", testUser.getLastName(), gettedUser.getLastName());
        assertEquals("Get method failed: wrong login", testUser.getLogin(), gettedUser.getLogin());
        assertEquals("Get method failed: wrong email", testUser.getEmail(), gettedUser.getEmail());
        assertEquals("Get method failed: wrong usertype", testUser.getUserType(), gettedUser.getUserType());
    }

    @Test
    public void testGetAll() throws Exception {
        User testUser2 = testUser;
        testUser2.setLogin("testLogin2");
        int beforeAddNumber = userDao.getAll().size();
        int getAllId = userDao.add(testUser2);
        int afterAddNumber = userDao.getAll().size();
        assertEquals("Get all users method failed", beforeAddNumber, afterAddNumber-1);
        userDao.delete(getAllId);
    }

    @Test
    public void testDelete() throws Exception {
        userDao.delete(id);
        assertNull("Delete user: failed",userDao.getById(id));
    }

    @After
    public void tearDown() throws Exception{
        userDao.delete(id);
    }


}