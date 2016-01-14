package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.TeamDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class TeamService {
    //    @Override
    public int add(FlightTeam flightTeam) throws SQLException {
        return TeamDAO.getInstance().add(flightTeam);
    }
//
//    @Override
    public void update(FlightTeam flightTeam) throws SQLException {
        TeamDAO.getInstance().update(flightTeam);
    }
//
//    @Override
    public List<FlightTeam> getAll() throws SQLException {
        return TeamDAO.getInstance().getAll();
    }

    //
//    @Override
    public void delete(int id) throws SQLException {
        TeamDAO.getInstance().delete(id);
    }
//
//    @Override
    public List<Employee> getById(int id) throws SQLException {
        return TeamDAO.getInstance().getById(id);
    }
}
