/**
 * 
 */
package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.constants.SqlQuery;
import by.pvt.kish.aircompany.entity.FlightTeam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  Kish Alexey
 */
public class FlightTeamDAO extends BaseDAO<FlightTeam> {

	//static Logger logger = Logger.getLogger(FlightTeamDAO.class.getName());
	private static FlightTeamDAO instance;

	private FlightTeamDAO() {
		super();
	}

	public synchronized static FlightTeamDAO getInstance() {
		if (instance == null) {
			instance = new FlightTeamDAO();
		}
		return instance;
	}

	@Override
	public int add(FlightTeam flightTeam) throws SQLException {
		int generatedId = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.ADD_TEAM, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, flightTeam.getFirstPilot().getEid());
			preparedStatement.setInt(2, flightTeam.getSecondPilot().getEid());
			preparedStatement.setInt(3, flightTeam.getNavigator().getEid());
			preparedStatement.setInt(4, flightTeam.getRadiooperator().getEid());
			preparedStatement.setInt(5, flightTeam.getStewardess1().getEid());
			preparedStatement.setInt(6, flightTeam.getStewardess2().getEid());
			preparedStatement.setInt(7, flightTeam.getStewardess3().getEid());
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

	@Override
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

	@Override
	public FlightTeam getById(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		FlightTeam team = new FlightTeam();
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_TEAM_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				team = setTeamParametrs(resultSet, team);
				return team;
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return team;
	}

	@Override
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

}
