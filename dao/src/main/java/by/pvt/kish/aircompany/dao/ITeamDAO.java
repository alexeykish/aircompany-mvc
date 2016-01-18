package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface ITeamDAO {
    void add(int fid, List<Integer> team) throws DaoException;
    List<FlightTeam> getAll() throws SQLException;
    void delete(int id) throws SQLException;
    List<Employee> getById(int id) throws SQLException;
}
