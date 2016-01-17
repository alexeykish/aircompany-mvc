package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;

import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public interface IUserDAO {

    boolean checkLogin(String login) throws SQLException;
    User getUser(String login, String password) throws SQLException;
    boolean checkStatus(int id) throws SQLException;
    void setStatus(int uid, UserStatus status) throws SQLException;
}
