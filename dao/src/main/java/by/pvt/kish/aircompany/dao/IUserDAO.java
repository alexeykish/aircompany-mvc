package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;

/**
 * @author Kish Alexey
 */
public interface IUserDAO {

    boolean checkLogin(String login) throws DaoException;
    User getUser(String login, String password) throws DaoException;
    boolean checkStatus(int id) throws DaoException;
    void setStatus(int uid, UserStatus status) throws DaoException;
}
