package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public interface ITeamService {
    void add(int fid, List<Integer> tid) throws SQLException;
    List<FlightTeam> getAll() throws SQLException;
    void delete(int id) throws SQLException;
    List<Employee> getById(int id) throws SQLException;
}
