package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.impl.UserDAO;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class UserService extends BaseService<User> implements IUserService{

    private static UserService instance;
    private UserDAO userDAO = UserDAO.getInstance();

    public synchronized static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public int add(User user) throws SQLException {
        if (!userDAO.checkLogin(user.getLogin())) {
            return -1;
        } else {
            return userDAO.add(user);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() throws SQLException {
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
    public boolean checkLogin(String login) throws SQLException {
        boolean result = userDAO.checkLogin(login);
        return result;
    }

    @Override
    public User getUser(String login, String password) throws SQLException {
        User user = userDAO.getUser(login, password);
        return user;
    }

    @Override
    public boolean checkStatus (int id) throws SQLException {
        return userDAO.checkStatus(id);
    }

    @Override
    public void setStatus(int uid, UserStatus status) throws SQLException {
        userDAO.setStatus(uid, status);
    }
}
