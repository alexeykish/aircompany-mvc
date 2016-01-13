package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.FlightTeamDAO;
import by.pvt.kish.aircompany.entity.FlightTeam;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class TeamService implements EntityService<FlightTeam> {
    @Override
    public int add(FlightTeam flightTeam) throws SQLException {
        return FlightTeamDAO.getInstance().add(flightTeam);
    }

    @Override
    public void update(FlightTeam flightTeam) throws SQLException {
        FlightTeamDAO.getInstance().update(flightTeam);
    }

    @Override
    public List<FlightTeam> getAll() throws SQLException {
        return FlightTeamDAO.getInstance().getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        FlightTeamDAO.getInstance().delete(id);
    }

    @Override
    public FlightTeam getById(int id) throws SQLException {
        return FlightTeamDAO.getInstance().getById(id);
    }
}
