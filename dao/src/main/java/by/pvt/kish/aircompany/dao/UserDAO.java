/**
 * 
 */
package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.constants.SqlQuery;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.utils.Coder;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  Kish Alexey
 */
public class UserDAO extends BaseDAO<User> {

	static Logger logger = Logger.getLogger(UserDAO.class.getName());

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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.ADD_USER, Statement.RETURN_GENERATED_KEYS);
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
			closeItems(resultSet, preparedStatement, connection);
		}
		return generatedId;
	}

	public boolean checkLogin(String login) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.CHECK_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return false;
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return true;
	}

	public User getUser(String login, String password) throws SQLException {
		User user = null;
		String pass = Coder.getHashCode(password);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		logger.debug("get user");
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_USER); 
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pass);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user = setUserParametrs(resultSet, user);
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return user;
	}

	@Override
	public List<User> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = new ArrayList<>();
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_ALL_USERS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user = setUserParametrs(resultSet, user);
				users.add(user);
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return users;
	}
	
	private User setUserParametrs(ResultSet resultSet, User user) throws SQLException {
		user.setUid(resultSet.getInt(Column.USERS_UID));
		user.setFirstName(resultSet.getString(Column.USERS_FIRSTNAME));
		user.setLastName(resultSet.getString(Column.USERS_LASTNAME));
		user.setLogin(resultSet.getString(Column.USERS_LOGIN));
		user.setEmail(resultSet.getString(Column.USERS_EMAIL));
		user.setUserType(UserType.valueOf(resultSet.getString(Column.USERS_USERTYPE)));
		return user;
	}


	@Override
	public void update(User user) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public User getById(int id) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(int id) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
