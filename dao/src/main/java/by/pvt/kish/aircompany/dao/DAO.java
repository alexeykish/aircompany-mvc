package by.pvt.kish.aircompany.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface DAO<T> {

    int add(Connection connection, T t) throws SQLException;

    void update(Connection connection, T t) throws SQLException;

    List<T> getAll(Connection connection) throws SQLException;

    T getById(Connection connection, int id) throws SQLException;

    void delete(Connection connection, int id) throws SQLException;

}
