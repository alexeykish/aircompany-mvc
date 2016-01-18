/**
 * 
 */
package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.dao.ITeamDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.pool.ConnectionUtils.closeResultSet;

/**
 * @author  Kish Alexey
 */
public class TeamDAO extends BaseDAO implements ITeamDAO {

	public static final String ADD_TEAM = "INSERT INTO  teams (`t_eid`,`t_fid`) VALUES (?,?)";
	public static final String GET_ALL_TEAMS = "SELECT * FROM teams";
	public static final String DELETE_TEAM = "DELETE FROM teams WHERE t_fid = ?";
	public static final String GET_TEAM_BY_ID = "SELECT * FROM teams WHERE t_fid = ?";

	static Logger logger = Logger.getLogger(TeamDAO.class.getName());

	private static TeamDAO instance;

	private TeamDAO() {
		super();
	}

	public synchronized static TeamDAO getInstance() {
		if (instance == null) {
			instance = new TeamDAO();
		}
		return instance;
	}

	@Override
	public void add(int fid, List<Integer> team) throws DaoException {
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(ADD_TEAM);
			for (Integer i : team) {
				preparedStatement.setInt(1, i);
				preparedStatement.setInt(2, fid);
				preparedStatement.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				logger.debug("Commit failed");
				connection.rollback();
				throw new DaoException("Creating team failed (commit failed)");
			}catch (SQLException e2) {
				logger.debug("Rollback failed");
				throw new DaoException("Creating team failed (rollback failed)");
			}
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public int add(Object o) throws DaoException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Object o) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<FlightTeam> getAll() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(int id) throws SQLException {
		try {
			preparedStatement = connection.prepareStatement(DELETE_TEAM);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}
	@Override
	public List<Employee> getById(int id) throws SQLException {
		ResultSet resultSet = null;
		List<Employee> team = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(GET_TEAM_BY_ID); //TODO refactor
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				team.add(EmployeeDAO.getInstance().getById(resultSet.getInt(Column.TEAMS_EID)));
			}
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return team;
	}
}
