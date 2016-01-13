package by.pvt.kish.aircompany.services;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface EntityService<T> {
    int add(T t) throws SQLException;
    void update(T t) throws SQLException;
    List<T> getAll() throws SQLException;
    void delete(int id) throws SQLException;
    T getById(int id) throws SQLException;
}
