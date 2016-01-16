package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.pool.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.*;

/**
 * @author Kish Alexey
 */
public class FlightDAO extends BaseDAO<Flight> {

	private static final String ADD_FLIGHT = "INSERT INTO  flights (`date`,`from`,`to`, `pid`) VALUES (?,?,?,?)";
	private static final String GET_ALL_FLIGHTS = "SELECT * FROM flights";
	private static final String DELETE_FLIGHT = "DELETE FROM flights WHERE fid = ?";
	private static final String GET_FLIGHT_BY_ID = "SELECT * FROM flights WHERE fid = ?";
	private static final String UPDATE_FLIGHT = "UPDATE flights SET `date` = ?, `from` = ?, `to` = ?, `pid` = ? WHERE fid = ?";

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
	public int add(Connection connection, Flight flight) throws SQLException {
		int generatedId = 0;
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
//			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(ADD_FLIGHT, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1, flight.getDate());
			preparedStatement.setInt(2, flight.getFrom().getAid());
			preparedStatement.setInt(3, flight.getTo().getAid());
			preparedStatement.setInt(4, flight.getPlane().getPid());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = resultSet.getInt(1);
			}
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return generatedId;
	}

	@Override
	public List<Flight> getAll(Connection connection) throws SQLException {
//		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Flight> flights = new ArrayList<>();
		try {
//			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_FLIGHTS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Flight flight = new Flight();
				flight = setFlightParametrs(connection, resultSet, flight);
				flights.add(flight);
			}
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return flights;
	}

	@Override
	public void delete(Connection connection, int fid) throws SQLException {
//		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
//			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_FLIGHT);
			preparedStatement.setInt(1, fid);
			preparedStatement.executeUpdate();
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(Connection connection, Flight flight) throws SQLException {
//		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
//			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_FLIGHT);
			preparedStatement.setDate(1, flight.getDate());
			preparedStatement.setInt(2, flight.getFrom().getAid());
			preparedStatement.setInt(3, flight.getTo().getAid());
			preparedStatement.setInt(4, flight.getPlane().getPid());
			preparedStatement.setInt(5, flight.getFid());
			preparedStatement.executeUpdate();
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Flight getById(Connection connection, int fid) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Flight flight = null;
		try {
//			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(GET_FLIGHT_BY_ID);
			preparedStatement.setInt(1, fid);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				flight = new Flight();
				flight = setFlightParametrs(connection, resultSet, flight);
			}
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return flight;
	}

	private Flight setFlightParametrs(Connection connection, ResultSet resultSet, Flight flight) throws SQLException {
		int fid = resultSet.getInt(Column.FLIGHTS_FID);
		flight.setFid(fid);
		flight.setDate(resultSet.getDate(Column.FLIGHTS_DATE));
		flight.setFrom(AirportDAO.getInstance().getById(connection, resultSet.getInt(Column.FLIGHTS_FROM)));
		flight.setTo(AirportDAO.getInstance().getById(connection, resultSet.getInt(Column.FLIGHTS_TO)));
		flight.setPlane(PlaneDAO.getInstance().getById(connection, resultSet.getInt(Column.FLIGHTS_PID)));
		flight.setTeam(TeamDAO.getInstance().getById(connection, fid));
		return flight;
	}
}
