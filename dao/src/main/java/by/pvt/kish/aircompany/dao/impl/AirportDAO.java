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

import static by.pvt.kish.aircompany.utils.DaoUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.utils.DaoUtils.closeResultSet;
import static by.pvt.kish.aircompany.utils.DaoUtils.deleteEntity;

/**
 * This class represents a concrete implementation of the IDAO interface for airport model.
 *
 * @author Kish Alexey
 */
public class AirportDAO extends BaseDAO<Airport> {

    private static final String SQL_ADD_AIRPORT = "INSERT INTO  airports (`city`) VALUES (?)";
    private static final String SQL_GET_ALL_AIRPORTS = "SELECT * FROM  airports";
    private static final String SQL_DELETE_AIRPORT = "DELETE FROM airports WHERE aid = ?";
    private static final String SQL_GET_AIRPORT_BY_ID = "SELECT * FROM  airports WHERE aid = ?";
    private static final String SQL_UPDATE_AIRPORT = "UPDATE airports SET `city` = ? WHERE aid = ?";

    private static final String ADD_AIRPORT_FAIL = "Creating airport failed";
    private static final String GET_ALL_AIRPORTS_FAIL = "Get all airports failed";
    private static final String DELETE_AIRPORT_FAIL = "Deleting airport failed";
    private static final String UPDATE_AIRPORT_FAIL = "Updating airport failed";
    private static final String GET_AIRPORT_BY_ID_FAIL = "Getting airport by ID failed";

    private static AirportDAO instance;

    private AirportDAO() {
        super();
    }

    /**
     * Returns an synchronized instance of a AirportDAO, if the instance does not exist yet - create a new
     *
     * @return - a instance of a AirportDAO
     */
    public synchronized static AirportDAO getInstance() {
        if (instance == null) {
            instance = new AirportDAO();
        }
        return instance;
    }

    /**
     * Create the given airport in the DB
     *
     * @param airport - airport to be created
     * @return - The ID of the airport, generated by DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public Long add(Airport airport) throws DaoException {
        ResultSet resultSet = null;
        Long generatedId = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_AIRPORT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, airport.getCity());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException(ADD_AIRPORT_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return generatedId;
    }

    /**
     * Update the given airport in the DB
     *
     * @param airport - airport to be updated
     * @throws DaoException If something fails at DB level
     */
    @Override
    public void update(Airport airport) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_AIRPORT);
            preparedStatement.setString(1, airport.getCity());
            preparedStatement.setLong(2, airport.getAid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(UPDATE_AIRPORT_FAIL, e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    /**
     * Returns a list of all airports from the DB
     *
     * @return - a list of all airports from the DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public List<Airport> getAll() throws DaoException {
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_AIRPORTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Airport airport = new Airport();
                airport = setAirportParametrs(resultSet, airport);
                airports.add(airport);
            }
        } catch (SQLException e) {
            throw new DaoException(GET_ALL_AIRPORTS_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return airports;
    }

    /**
     * Returns the airport from the DB matching the given ID
     *
     * @param id - The ID of the airport to be returned
     * @return - the airport from the DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public Airport getById(Long id) throws DaoException {
        ResultSet resultSet = null;
        Airport airport = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_AIRPORT_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                airport = new Airport();
                airport = setAirportParametrs(resultSet, airport);
            }
        } catch (SQLException e) {
            throw new DaoException(GET_AIRPORT_BY_ID_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return airport;
    }

    /**
     * Delete the given airport from the DB
     *
     * @param id - The ID of the airport to be deleted from the DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public void delete(Long id) throws DaoException {
        deleteEntity(connection, preparedStatement, id, SQL_DELETE_AIRPORT, DELETE_AIRPORT_FAIL);
    }

    private Airport setAirportParametrs(ResultSet resultSet, Airport airport) throws SQLException {
        airport.setAid(resultSet.getLong(Column.AIRPORT_AID));
        airport.setCity(resultSet.getString(Column.AIRPORT_CITY));
        return airport;
    }
}
