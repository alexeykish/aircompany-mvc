package by.pvt.kish.aircompany.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface DAO<T> {

    int add(T t) throws SQLException;

    void update(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    void delete(int id) throws SQLException;

}
