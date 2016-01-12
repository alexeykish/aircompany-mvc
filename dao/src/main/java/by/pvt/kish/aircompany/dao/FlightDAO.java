package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.constants.SqlQuery;
import by.pvt.kish.aircompany.entity.Flight;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class FlightDAO extends BaseDAO<Flight> {

	static Logger logger = Logger.getLogger(FlightDAO.class.getName());

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
	public int add(Flight flight) throws SQLException {
		int generatedId = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.ADD_FLIGHT, Statement.RETURN_GENERATED_KEYS);
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
			closeItems(resultSet, preparedStatement, connection);
		}
		return generatedId;
	}

	@Override
	public List<Flight> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Flight> flights = new ArrayList<>();
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_ALL_FLIGHTS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Flight flight = new Flight();
				flight = setFlightParametrs(resultSet, flight);
				flights.add(flight);
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return flights;
	}

	@Override
	public void delete(int fid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.DELETE_FLIGHT); 
			preparedStatement.setInt(1, fid);
			preparedStatement.executeUpdate();
		} finally {
			closeItems(preparedStatement, connection);
		}
	}

	@Override
	public void update(Flight flight) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_FLIGHT); 
			preparedStatement.setDate(1, flight.getDate());
			preparedStatement.setInt(2, flight.getFrom().getAid());
			preparedStatement.setInt(3, flight.getTo().getAid());
			preparedStatement.setInt(4, flight.getPlane().getPid());
			preparedStatement.setInt(5, flight.getFid());
			preparedStatement.executeUpdate();
		} finally {
			closeItems(preparedStatement, connection);
		}
	}

	@Override
	public Flight getById(int fid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Flight flight = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_FLIGHT_BY_ID); 
			preparedStatement.setInt(1, fid);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				flight = new Flight();
				flight = setFlightParametrs(resultSet, flight);
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return flight;
	}

	public void updateFlightByTeam(String fid, String tid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_TEAM_TO_FLIGHT); 
			preparedStatement.setInt(1, Integer.parseInt(tid));
			preparedStatement.setInt(2, Integer.parseInt(fid));
			preparedStatement.executeUpdate();
		} finally {
			closeItems(preparedStatement, connection);
		}
	}
	
	private Flight setFlightParametrs(ResultSet resultSet, Flight flight) throws SQLException {
		flight.setFid(resultSet.getInt(Column.FLIGHTS_FID));
		flight.setDate(resultSet.getDate(Column.FLIGHTS_DATE));
		flight.setFrom(AirportDAO.getInstance().getById(resultSet.getInt(Column.FLIGHTS_FROM)));
		flight.setTo(AirportDAO.getInstance().getById(resultSet.getInt(Column.FLIGHTS_TO)));
		flight.setTid(resultSet.getInt(Column.FLIGHTS_TID));
		flight.setPlane(PlaneDAO.getInstance().getById(resultSet.getInt(Column.FLIGHTS_PID)));
		return flight;
	}
}
