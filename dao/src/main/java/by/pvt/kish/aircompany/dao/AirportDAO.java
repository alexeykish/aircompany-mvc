package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.constants.SqlQuery;
import by.pvt.kish.aircompany.entity.Airport;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  Kish Alexey
 */
public class AirportDAO extends BaseDAO<Airport> {

    static Logger logger = Logger.getLogger(AirportDAO.class.getName());

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
    public int add(Airport airport) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int generatedId = 0;
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.ADD_AIRPORT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, airport.getCity());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        } finally {
            closeItems(resultSet, preparedStatement, connection);
        }
        return generatedId;
    }

    @Override
    public void update(Airport airport) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_AIRPORT);
            preparedStatement.setString(1, airport.getCity());
            preparedStatement.setInt(2, airport.getAid());
            preparedStatement.executeUpdate();
        } finally {
            closeItems(preparedStatement, connection);
        }
    }

    @Override
    public List<Airport> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.GET_ALL_AIRPORTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Airport airport = new Airport();
                airport = setAirportParametrs(resultSet, airport);
                airports.add(airport);
            }
        } finally {
            closeItems(resultSet, preparedStatement, connection);
        }
        return airports;
    }

    @Override
    public Airport getById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Airport airport = null;
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.GET_AIRPORT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                airport = new Airport();
                airport = setAirportParametrs(resultSet, airport);
            }
        } finally {
            closeItems(resultSet, preparedStatement, connection);
        }
        return airport;
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_AIRPORT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            closeItems(preparedStatement, connection);
        }
    }

    private Airport setAirportParametrs(ResultSet resultSet, Airport airport) throws SQLException {
        airport.setAid(resultSet.getInt(Column.AIRPORT_AID));
        airport.setCity(resultSet.getString(Column.AIRPORT_CITY));
        return airport;
    }
}
