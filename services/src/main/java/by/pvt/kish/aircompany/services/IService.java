package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.exceptions.ServiceLoginException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.exceptions.ServiceException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface IService<T> {
    int add(T t) throws ServiceException, ServiceValidateException, ServiceLoginException;
    void update(T t) throws SQLException, ServiceException, ServiceValidateException;
    List<T> getAll() throws SQLException, ServiceException;
    void delete(int id) throws SQLException, ServiceException;
    T getById(int id) throws SQLException, ServiceException;
}
