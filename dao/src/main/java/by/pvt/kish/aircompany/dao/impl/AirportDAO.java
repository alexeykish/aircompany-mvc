package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.pool.ConnectionUtils.closeResultSet;

/**
 *
 * @author  Kish Alexey
 */
public class AirportDAO extends BaseDAO<Airport> {

    private static final String ADD_AIRPORT = "INSERT INTO  airports (`city`) VALUES (?)";
    private static final String GET_ALL_AIRPORTS = "SELECT * FROM  airports";
    private static final String DELETE_AIRPORT = "DELETE FROM airports WHERE aid = ?";
    private static final String GET_AIRPORT_BY_ID = "SELECT * FROM  airports WHERE aid = ?";
    private static final String UPDATE_AIRPORT = "UPDATE airports SET `city` = ? WHERE aid = ?";

    private static AirportDAO instance;

    private AirportDAO() {
        super();
    }

    public synchronized static AirportDAO getInstance() {
        if (instance == null) {
            instance = new AirportDAO();
        }
        return instance;
    }

    @Override
    public int add(Airport airport) throws DaoException {
        ResultSet resultSet = null;
        int generatedId = 0;
        try {
            preparedStatement = connection.prepareStatement(ADD_AIRPORT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, airport.getCity());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Creating airport failed");
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return generatedId;
    }

    @Override
    public void update(Airport airport) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_AIRPORT);
            preparedStatement.setString(1, airport.getCity());
            preparedStatement.setInt(2, airport.getAid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Updating airport failed", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Airport> getAll() throws DaoException {
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(GET_ALL_AIRPORTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Airport airport = new Airport();
                airport = setAirportParametrs(resultSet, airport);
                airports.add(airport);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed get all airports", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return airports;
    }

    @Override
    public Airport getById(int id) throws DaoException {
        ResultSet resultSet = null;
        Airport airport = null;
        try {
            preparedStatement = connection.prepareStatement(GET_AIRPORT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                airport = new Airport();
                airport = setAirportParametrs(resultSet, airport);
            }
        } catch (SQLException e) {
            throw new DaoException("Getting airport by ID failed", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return airport;
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(DELETE_AIRPORT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Deleting airport failed", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    private Airport setAirportParametrs(ResultSet resultSet, Airport airport) throws SQLException {
        airport.setAid(resultSet.getInt(Column.AIRPORT_AID));
        airport.setCity(resultSet.getString(Column.AIRPORT_CITY));
        return airport;
    }
}
