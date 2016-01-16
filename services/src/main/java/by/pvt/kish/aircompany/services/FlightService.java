package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.FlightDAO;
import by.pvt.kish.aircompany.dao.TeamDAO;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.pool.ConnectionPool;
import by.pvt.kish.aircompany.pool.ConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.*;

/**
 * @author Kish Alexey
 */
public class FlightService extends BaseService<Flight> {

    private static FlightService instance;
    private FlightDAO flightDAO = FlightDAO.getInstance();
    Connection connection;

    public synchronized static FlightService getInstance() {
        if (instance == null) {
            instance = new FlightService();
        }
        return instance;
    }

    @Override
    public int add(Flight flight) throws SQLException {
        connection = poolInstance.getConnection();
        if (flight != null) {
            return flightDAO.add(connection, flight);
        }
        closeConnection(connection);
        return 0;
    }

    @Override
    public void update(Flight flight) throws SQLException {
        connection = poolInstance.getConnection();
        flightDAO.update(connection, flight);
        closeConnection(connection);
    }

    @Override
    public List<Flight> getAll() throws SQLException {
        connection = poolInstance.getConnection();
        List<Flight> list = flightDAO.getAll(connection);
        closeConnection(connection);
        return list;
    }

    @Override
    public void delete(int id) throws SQLException {
        connection = poolInstance.getConnection();
        flightDAO.delete(connection, id);
        closeConnection(connection);
    }

    @Override
    public Flight getById(int id) throws SQLException {
        connection = poolInstance.getConnection();
        Flight flight=  flightDAO.getById(connection, id);
        closeConnection(connection);
        return flight;
    }
}
