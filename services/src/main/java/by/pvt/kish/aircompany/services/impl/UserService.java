package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.UserDAO;
import by.pvt.kish.aircompany.entity.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceLoginException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.services.IUserService;
import by.pvt.kish.aircompany.validators.UserValidator;

import java.util.List;

/**
 * @author Kish Alexey
 */
public class UserService extends BaseService<User> implements IUserService {

    private static UserService instance;
    private UserDAO userDAO = UserDAO.getInstance();

    public synchronized static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public int add(User user) throws ServiceException, ServiceLoginException, ServiceValidateException {
        try {
            String validateResult = UserValidator.validate(user);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            if (!userDAO.checkLogin(user.getLogin())) {
                throw new ServiceLoginException(Message.ERROR_REG_USER_EXISTS);
            } else {
                return userDAO.add(user);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public void update(User user) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getById(int id) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean checkLogin(String login) throws ServiceException {
        try {
            return userDAO.checkLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUser(String login, String password) throws ServiceException, ServiceLoginException {
        User user = null;
        try {
            user = userDAO.getUser(login, password);
            if (user == null) {
                throw new ServiceLoginException(Message.ERROR_REG_LOGIN);
            } else if (user.getStatus().equals(UserStatus.ONLINE)) {
                throw new ServiceLoginException(Message.ERROR_REG_USER_EXISTS);
            } else {
                userDAO.setStatus(user.getUid(), UserStatus.ONLINE);
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean checkStatus(int id) throws ServiceException {
        try {
            return userDAO.checkStatus(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setStatus(int uid, UserStatus status) throws ServiceException {
        try {
            userDAO.setStatus(uid, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
