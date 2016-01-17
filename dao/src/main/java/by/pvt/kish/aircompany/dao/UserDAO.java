/**
 *
 */
package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.utils.Coder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.pool.ConnectionUtils.closeResultSet;

/**
 * @author Kish Alexey
 */
public class UserDAO extends BaseDAO<User> implements IUserDAO{

    private static final String ADD_USER = "INSERT INTO  users (`first_name`,`last_name`,`login`,`password`,`email`,`user_type`) VALUES (?,?,?,?,?,?)";
    private static final String CHECK_LOGIN = "SELECT uid FROM users WHERE login = ?";
    private static final String GET_USER = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE uid = ?";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE uid = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_STATUS = "SELECT status FROM users WHERE uid = ?";
    private static final String UPDATE_USER_STATUS = "UPDATE users SET `status` = ? WHERE uid = ?";

    private static UserDAO instance;

    private UserDAO() {
        super();
    }

    public synchronized static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public int add(User user) throws SQLException {
        int generatedId = 0;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, Coder.getHashCode(user.getPassword()));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getUserType().toString());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return generatedId;
    }

    @Override
    public boolean checkLogin(String login) throws SQLException {
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(CHECK_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }

        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return true;
    }

    @Override
    public User getUser(String login, String password) throws SQLException {
        User user = null;
        String pass = Coder.getHashCode(password);
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user = setUserParametrs(resultSet, user);
            }
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user = setUserParametrs(resultSet, user);
                users.add(user);
            }
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return users;
    }

    @Override
    public User getById(int id) throws SQLException {
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user = setUserParametrs(resultSet, user);
            }
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public boolean checkStatus(int id) throws SQLException {
        boolean status = false;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(GET_USER_STATUS);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString(Column.USERS_USERSTATUS).equals(UserStatus.OFFLINE.toString())) {
                    return true;
                }
            }
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return status;
    }

    @Override
    public void setStatus(int id, UserStatus status) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER_STATUS);
            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    private User setUserParametrs(ResultSet resultSet, User user) throws SQLException {
        user.setUid(resultSet.getInt(Column.USERS_UID));
        user.setFirstName(resultSet.getString(Column.USERS_FIRSTNAME));
        user.setLastName(resultSet.getString(Column.USERS_LASTNAME));
        user.setLogin(resultSet.getString(Column.USERS_LOGIN));
        user.setEmail(resultSet.getString(Column.USERS_EMAIL));
        user.setUserType(UserType.valueOf(resultSet.getString(Column.USERS_USERTYPE)));
        user.setStatus(UserStatus.valueOf(resultSet.getString(Column.USERS_USERSTATUS)));
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
