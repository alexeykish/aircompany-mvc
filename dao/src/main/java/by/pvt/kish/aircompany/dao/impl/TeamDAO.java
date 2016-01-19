/**
 * 
 */
package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.dao.ITeamDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.pool.ConnectionUtils.closeResultSet;

/**
 * This class represents a concrete implementation of the {@link ITeamDAO} interface.
 *
 * @author  Kish Alexey
 */
public class TeamDAO extends BaseDAO implements ITeamDAO {

	public static final String SQL_ADD_TEAM = "INSERT INTO  teams (`t_eid`,`t_fid`) VALUES (?,?)";
	public static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE t_fid = ?";
	public static final String SQL_GET_TEAM_BY_ID = "SELECT * FROM teams WHERE t_fid = ?";

	public static final String ADD_TEAM_COMMIT_FAIL = "Creating team failed (commit failed)";
	public static final String ADD_TEAM_ROLLBACK_FAIL = "Creating team failed (rollback failed)";
	public static final String DELETE_TEAM_FAIL = "Deleting team failed";
	public static final String GET_TEAM_BY_ID_FAIL = "Getting team by ID failed";

	private static TeamDAO instance;

	private TeamDAO() {
		super();
	}

	/**
	 * Returns an synchronized instance of a TeamDAO, if the instance does not exist yet - create a new
	 * @return - a instance of a TeamDAO
	 */
	public synchronized static TeamDAO getInstance() {
		if (instance == null) {
			instance = new TeamDAO();
		}
		return instance;
	}

	/**
	 * Create a given flight team in the database for a particular flight from the DB matching the given ID
	 * @param id - The ID of the flight
	 * @param team - The flight team to be created
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public void add(int id, List<Integer> team) throws DaoException {
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_ADD_TEAM);
			for (Integer i : team) {
				preparedStatement.setInt(1, i);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
				throw new DaoException(ADD_TEAM_COMMIT_FAIL, e);
			}catch (SQLException e2) {
				throw new DaoException(ADD_TEAM_ROLLBACK_FAIL, e2);
			}
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	/**
	 * Delete the given team from the database
	 * @param id - ID of the team to be deleted from the DB
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public void delete(int id) throws DaoException {
		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE_TEAM);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(DELETE_TEAM_FAIL, e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	/**
	 * Returns a list of employees of the flight team for particular flight from the DB matching the given ID
	 * @param id - The ID of the flight
	 * @return a list of the employees, that is a flight team
	 * @throws DaoException If something fails at DB level
	 */
	@Override
	public List<Employee> getById(int id) throws DaoException {
		ResultSet resultSet = null;
		List<Employee> team = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(SQL_GET_TEAM_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				team.add(EmployeeDAO.getInstance().getById(resultSet.getInt(Column.TEAMS_EID)));
			}
		} catch (SQLException e) {
			throw new DaoException(GET_TEAM_BY_ID_FAIL, e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return team;
	}

	@Override
	public int add(Object o) throws DaoException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Object o) throws DaoException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List getAll() throws DaoException {
		throw new UnsupportedOperationException();
	}
}
