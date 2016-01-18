package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.util.List;

/**
 * @author Kish Alexey
 */
public interface ITeamDAO {
    void add(int fid, List<Integer> team) throws DaoException;
    List<FlightTeam> getAll() throws DaoException;
    void delete(int id) throws DaoException;
    List<Employee> getById(int id) throws DaoException;
}
