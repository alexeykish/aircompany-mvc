package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.exceptions.DaoException;

import java.util.List;

/**
 * @author Kish Alexey
 */
public interface DAO<T> {

    int add(T t) throws DaoException;

    void update(T t) throws DaoException;

    List<T> getAll() throws DaoException;

    T getById(int id) throws DaoException;

    void delete(int id) throws DaoException;

}
