package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.pool.ConnectionUtils.closeResultSet;

/**
 * @author Kish Alexey
 */
public class FlightDAO extends BaseDAO<Flight> {

    private static final String SQL_ADD_FLIGHT = "INSERT INTO  flights (`date`,`from`,`to`, `pid`) VALUES (?,?,?,?)";
    private static final String SQL_GET_ALL_FLIGHTS = "SELECT * FROM flights";
    private static final String SQL_DELETE_FLIGHT = "DELETE FROM flights WHERE fid = ?";
    private static final String SQL_GET_FLIGHT_BY_ID = "SELECT * FROM flights WHERE fid = ?";
    private static final String SQL_UPDATE_FLIGHT = "UPDATE flights SET `date` = ?, `from` = ?, `to` = ?, `pid` = ? WHERE fid = ?";

    private static final String ADD_FLIGHT_FAIL = "Creating flight failed";
    private static final String GET_ALL_FLIGHTS_FAIL = "Get all flights failed";
    private static final String DELETE_FLIGHT_FAIL = "Deleting flight failed";
    private static final String UPDATE_FLIGHT_FAIL = "Updating flight failed";
    private static final String GET_FLIGHT_BY_ID_FAIL = "Getting flight by ID failed";

    private static FlightDAO instance;

    private FlightDAO() {
        super();
    }

    public synchronized static FlightDAO getInstance() {
        if (instance == null) {
            instance = new FlightDAO();
        }
        return instance;
    }

    @Override
    public int add(Flight flight) throws DaoException {
        int generatedId = 0;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_FLIGHT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, flight.getDate());
            preparedStatement.setInt(2, flight.getFrom().getAid());
            preparedStatement.setInt(3, flight.getTo().getAid());
            preparedStatement.setInt(4, flight.getPlane().getPid());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(ADD_FLIGHT_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return generatedId;
    }

    @Override
    public List<Flight> getAll() throws DaoException {
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_FLIGHTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight();
                flight = setFlightParametrs(resultSet, flight);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException(GET_ALL_FLIGHTS_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return flights;
    }

    @Override
    public void delete(int fid) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_FLIGHT);
            preparedStatement.setInt(1, fid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(DELETE_FLIGHT_FAIL, e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Flight flight) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_FLIGHT);
            preparedStatement.setDate(1, flight.getDate());
            preparedStatement.setInt(2, flight.getFrom().getAid());
            preparedStatement.setInt(3, flight.getTo().getAid());
            preparedStatement.setInt(4, flight.getPlane().getPid());
            preparedStatement.setInt(5, flight.getFid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(UPDATE_FLIGHT_FAIL, e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public Flight getById(int fid) throws DaoException {
        ResultSet resultSet = null;
        Flight flight = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_FLIGHT_BY_ID);
            preparedStatement.setInt(1, fid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flight = new Flight();
                flight = setFlightParametrs(resultSet, flight);
            }
        } catch (SQLException e) {
            throw new DaoException(GET_FLIGHT_BY_ID_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return flight;
    }

    private Flight setFlightParametrs(ResultSet resultSet, Flight flight) throws SQLException, DaoException {
        int fid = resultSet.getInt(Column.FLIGHTS_FID);
        flight.setFid(fid);
        flight.setDate(resultSet.getDate(Column.FLIGHTS_DATE));
        flight.setFrom(AirportDAO.getInstance().getById(resultSet.getInt(Column.FLIGHTS_FROM)));
        flight.setTo(AirportDAO.getInstance().getById(resultSet.getInt(Column.FLIGHTS_TO)));
        flight.setPlane(PlaneDAO.getInstance().getById(resultSet.getInt(Column.FLIGHTS_PID)));
        flight.setTeam(TeamDAO.getInstance().getById(fid));
        return flight;
    }
}
