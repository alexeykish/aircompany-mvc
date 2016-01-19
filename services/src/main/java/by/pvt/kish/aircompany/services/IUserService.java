package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceLoginException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;

/**
 * @author Kish Alexey
 */
public interface IUserService {
    int add(User user) throws ServiceException, ServiceLoginException, ServiceValidateException;
    boolean checkLogin(String login) throws ServiceException;
    User getUser(String login, String password) throws ServiceException, ServiceLoginException;
    boolean checkStatus (int id) throws ServiceException;
    void setStatus(int uid, UserStatus status) throws ServiceException, ServiceValidateException;
}
