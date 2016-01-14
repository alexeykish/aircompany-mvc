package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.FlightDAO;
import by.pvt.kish.aircompany.entity.Flight;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class FlightService implements EntityService<Flight> {
    @Override
    public int add(Flight flight) throws SQLException {
        return FlightDAO.getInstance().add(flight);
    }

    @Override
    public void update(Flight flight) throws SQLException {
        FlightDAO.getInstance().update(flight);
    }

    @Override
    public List<Flight> getAll() throws SQLException {
        return FlightDAO.getInstance().getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        FlightDAO.getInstance().delete(id);
    }

    @Override
    public Flight getById(int id) throws SQLException {
        return FlightDAO.getInstance().getById(id);
    }

    public void updateFlightByTeam(int fid, List<Integer> tid) throws SQLException {
        FlightDAO.getInstance().updateFlightByTeam(fid, tid);
    }
}
