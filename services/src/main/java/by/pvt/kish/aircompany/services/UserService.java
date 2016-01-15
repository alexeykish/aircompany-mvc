package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.UserDAO;
import by.pvt.kish.aircompany.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class UserService implements IService<User> {

    private static UserService instance;

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
        return UserDAO.getInstance().getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getById(int id) throws SQLException {
        return null;
    }
    @Override
    public int add(User user) throws SQLException{
        return UserDAO.getInstance().add(user);
    }

    public boolean checkLogin(String login) throws SQLException{
        return UserDAO.getInstance().checkLogin(login);
    }

    public User getUser(String login, String password) throws SQLException{
        return UserDAO.getInstance().getUser(login, password);
    }
}
