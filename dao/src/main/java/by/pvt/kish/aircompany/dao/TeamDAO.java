/**
 * 
 */
package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.constants.SqlQuery;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  Kish Alexey
 */
public class TeamDAO {

	static Logger logger = Logger.getLogger(TeamDAO.class.getName());
	private static TeamDAO instance;
	ConnectionPool poolInstance;;

	private TeamDAO() {
		super();
		try {
			poolInstance = ConnectionPool.getInstance();
		} catch (IOException | SQLException | PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public synchronized static TeamDAO getInstance() {
		if (instance == null) {
			instance = new TeamDAO();
		}
		return instance;
	}

	public void add(int fid, List<Integer> team) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = poolInstance.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SqlQuery.ADD_TEAM);
			for (Integer i : team) {
				preparedStatement.setInt(1, i);
				preparedStatement.setInt(2, fid);
				preparedStatement.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				logger.debug("Commit failed");
				if (connection != null) {
					connection.rollback();
				}
			}catch (SQLException e2) {
				logger.debug("Rollback failed");
			}
		} finally {
			closeItems(preparedStatement, connection);
		}
	}

	//@Override
	public List<FlightTeam> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<FlightTeam> teams = new ArrayList<>();
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_ALL_TEAMS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FlightTeam team = new FlightTeam();
				team = setTeamParametrs(resultSet, team);
				teams.add(team);
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return teams;
	}

	//@Override
	public void delete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.DELETE_TEAM);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} finally {
			closeItems(preparedStatement, connection);
		}

	}

	//@Override
	public List<Employee> getById(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> team = new ArrayList<>();
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_TEAM_BY_ID); //TODO refactor
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				team.add(EmployeeDAO.getInstance().getById(resultSet.getInt(Column.TEAMS_EID)));
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return team;
	}

	//@Override
	public void update(FlightTeam team) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_TEAM);
			preparedStatement.setInt(1, team.getFirstPilot().getEid());
			preparedStatement.setInt(2, team.getSecondPilot().getEid());
			preparedStatement.setInt(3, team.getNavigator().getEid());
			preparedStatement.setInt(4, team.getRadiooperator().getEid());
			preparedStatement.setInt(5, team.getStewardess1().getEid());
			preparedStatement.setInt(6, team.getStewardess2().getEid());
			preparedStatement.setInt(7, team.getStewardess3().getEid());
			preparedStatement.setInt(8, team.getTid());
			preparedStatement.executeUpdate();
		} finally {
			closeItems(preparedStatement, connection);
		}
		
	}
	private FlightTeam setTeamParametrs(ResultSet resultSet, FlightTeam team) throws SQLException {
		team.setTid(resultSet.getInt(Column.TEAMS_TID));
		EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
		team.setFirstPilot(employeeDAO.getById(resultSet.getInt(Column.TEAMS_FIRSTPILOT_ID)));
		team.setSecondPilot(employeeDAO.getById(resultSet.getInt(Column.TEAMS_SECONDPILOT_ID)));
		team.setNavigator(employeeDAO.getById(resultSet.getInt(Column.TEAMS_NAVIGATOR_ID)));
		team.setRadiooperator(employeeDAO.getById(resultSet.getInt(Column.TEAMS_RADIOPERATOR_ID)));
		team.setStewardess1(employeeDAO.getById(resultSet.getInt(Column.TEAMS_STEWARDESS1_ID)));
		team.setStewardess2(employeeDAO.getById(resultSet.getInt(Column.TEAMS_STEWARDESS2_ID)));
		team.setStewardess3(employeeDAO.getById(resultSet.getInt(Column.TEAMS_STEWARDESS3_ID)));
		return team;
	}
	private void closeItems(PreparedStatement preparedStatement, Connection connection){
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("SQL exception occurred during closing connection");
		}
	}
	private void closeItems(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("SQL exception occurred during closing connection");
		}
	}

}