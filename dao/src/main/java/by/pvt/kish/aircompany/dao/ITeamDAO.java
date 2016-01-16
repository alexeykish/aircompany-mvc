package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface ITeamDAO {
    void add(Connection connection, int fid, List<Integer> team) throws SQLException;
    List<FlightTeam> getAll(Connection connection) throws SQLException;
    void delete(Connection connection, int id) throws SQLException;
    List<Employee> getById(Connection connection, int id) throws SQLException;
}
