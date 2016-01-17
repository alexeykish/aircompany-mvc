package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.UserDAO;
import by.pvt.kish.aircompany.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class UserService extends BaseService<User> {

    private static UserService instance;
    private UserDAO userDAO = UserDAO.getInstance();
//    Connection connection;

    public synchronized static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void update(User user) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() throws SQLException {
//        connection = poolInstance.getConnection();
        List<User> list = userDAO.getAll();
        return list;
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getById(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int add(User user) throws SQLException{
//        connection = poolInstance.getConnection();
        int id =  userDAO.add(user);
        return id;
    }

    public boolean checkLogin(String login) throws SQLException{
//        connection = poolInstance.getConnection();
        boolean result =  userDAO.checkLogin(login);
        return result;
    }

    public User getUser(String login, String password) throws SQLException{
//        connection = poolInstance.getConnection();
        User user = userDAO.getUser(login, password);
        return user;
    }
}
