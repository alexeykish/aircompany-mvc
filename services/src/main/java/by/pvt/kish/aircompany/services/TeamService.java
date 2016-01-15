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

    private static TeamService instance;

    public synchronized static TeamService getInstance() {
        if (instance == null) {
            instance = new TeamService();
        }
        return instance;
    }

    public int add(FlightTeam flightTeam) throws SQLException {
        return TeamDAO.getInstance().add(flightTeam);
    }

    public void update(FlightTeam flightTeam) throws SQLException {
        TeamDAO.getInstance().update(flightTeam);
    }

    public List<FlightTeam> getAll() throws SQLException {
        return TeamDAO.getInstance().getAll();
    }

    public void delete(int id) throws SQLException {
        TeamDAO.getInstance().delete(id);
    }

    public List<Employee> getById(int id) throws SQLException {
        return TeamDAO.getInstance().getById(id);
    }
}
