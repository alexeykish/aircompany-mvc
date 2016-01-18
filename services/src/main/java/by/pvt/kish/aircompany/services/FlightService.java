package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.entity.Flight;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class FlightService extends BaseService<Flight> {

    private static FlightService instance;
    private FlightDAO flightDAO = FlightDAO.getInstance();

    public synchronized static FlightService getInstance() {
        if (instance == null) {
            instance = new FlightService();
        }
        return instance;
    }

    @Override
    public int add(Flight flight) throws SQLException {
        if (flight != null) {
            return flightDAO.add(flight);
        }
        return 0;
    }

    @Override
    public void update(Flight flight) throws SQLException {
        flightDAO.update(flight);
    }

    @Override
    public List<Flight> getAll() throws SQLException {
        List<Flight> list = flightDAO.getAll();
        return list;
    }

    @Override
    public void delete(int id) throws SQLException {
        flightDAO.delete(id);
    }

    @Override
    public Flight getById(int id) throws SQLException {
        Flight flight=  flightDAO.getById(id);
        return flight;
    }
}
