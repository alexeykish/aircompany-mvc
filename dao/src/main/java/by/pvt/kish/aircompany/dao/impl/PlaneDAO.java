package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.pool.ConnectionUtils.closeResultSet;

/**
 * This class represents a concrete implementation of the IDAO interface for plane model.
 * @author  Kish Alexey
 */
public class PlaneDAO extends BaseDAO<Plane> {

    private static final String SQL_ADD_PLANE = "INSERT INTO  planes (`model`,`capacity`,`range`, `num_pilots`, `num_nav`, `num_radio`, `num_stew`) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_GET_ALL_PLANES = "SELECT * FROM  planes";
    private static final String SQL_DELETE_PLANE = "DELETE FROM planes WHERE pid = ?";
    private static final String SQL_GET_PLANE_BY_ID = "SELECT * FROM  planes WHERE pid = ?";
    private static final String SQL_UPDATE_PLANE = "UPDATE planes SET `model` = ?, `capacity` = ?, `range` = ?, `num_pilots` = ?, `num_nav` = ?, `num_radio` = ?, `num_stew` = ? WHERE pid = ?";

    private static final String ADD_PLANE_FAIL = "Creating plane failed";
    private static final String GET_ALL_PLANES_FAIL = "Get all planes failed";
    private static final String DELETE_PLANE_FAIL = "Deleting plane failed";
    private static final String UPDATE_PLANE_FAIL = "Updating plane failed";
    private static final String GET_PLANE_BY_ID_FAIL = "Getting plane by ID failed";

    private static PlaneDAO instance;

    private PlaneDAO() {
        super();
    }

    /**
     * Returns an synchronized instance of a PlaneDAO, if the instance does not exist yet - create a new
     * @return - a instance of a PlaneDAO
     */
    public synchronized static PlaneDAO getInstance() {
        if (instance == null) {
            instance = new PlaneDAO();
        }
        return instance;
    }

    /**
     * Create the given plane in the DB
     * @param plane - plane to be created
     * @return - The ID of the plane, generated by DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public int add(Plane plane) throws DaoException {
        int generatedId = 0;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_PLANE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = setStatementParametrs(preparedStatement, plane);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(ADD_PLANE_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return generatedId;
    }

    /**
     * Update the given plane in the DB
     * @param plane - plane to be updated
     * @throws DaoException If something fails at DB level
     */
    @Override
    public void update(Plane plane) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PLANE);
            preparedStatement = setStatementParametrs(preparedStatement, plane);
            preparedStatement.setInt(8, plane.getPid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(UPDATE_PLANE_FAIL, e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    /**
     * Returns a list of all planes from the DB
     * @return - a list of all planes from the DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public List<Plane> getAll() throws DaoException {
        ResultSet resultSet = null;
        List<Plane> planes = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_PLANES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Plane plane = new Plane();
                plane = setPlaneParametrs(resultSet, plane);
                planes.add(plane);
            }
        } catch (SQLException e) {
            throw new DaoException(GET_ALL_PLANES_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return planes;
    }

    /**
     * Returns the plane from the DB matching the given ID
     * @param id - The ID of the plane to be returned
     * @return - the plane from the DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public Plane getById(int id) throws DaoException {
        ResultSet resultSet = null;
        Plane plane = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_PLANE_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                plane = new Plane();
                plane = setPlaneParametrs(resultSet, plane);
            }
        } catch (SQLException e) {
            throw new DaoException(GET_PLANE_BY_ID_FAIL, e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return plane;
    }

    /**
     * Delete the given plane from the DB
     * @param id - The ID of the plane to be deleted from the DB
     * @throws DaoException If something fails at DB level
     */
    @Override
    public void delete(int id) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_PLANE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(DELETE_PLANE_FAIL, e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    private PreparedStatement setStatementParametrs (PreparedStatement preparedStatement, Plane plane) throws SQLException{
        preparedStatement.setString(1, plane.getModel());
        preparedStatement.setInt(2, plane.getCapacity());
        preparedStatement.setInt(3, plane.getRange());
        preparedStatement.setInt(4, plane.getTeam().get(Position.PILOT));
        preparedStatement.setInt(5, plane.getTeam().get(Position.NAVIGATOR));
        preparedStatement.setInt(6, plane.getTeam().get(Position.RADIOOPERATOR));
        preparedStatement.setInt(7, plane.getTeam().get(Position.STEWARDESS));
        return preparedStatement;
    }

    private Plane setPlaneParametrs(ResultSet resultSet, Plane plane) throws SQLException {
        plane.setPid(resultSet.getInt(Column.PLANES_PID));
        plane.setModel(resultSet.getString(Column.PLANES_MODEL));
        plane.setCapacity(resultSet.getInt(Column.PLANES_CAPACITY));
        plane.setRange(resultSet.getInt(Column.PLANES_RANGE));
        Map<Position,Integer> team = new TreeMap<>();
        team.put(Position.PILOT, resultSet.getInt(Column.PLANES_PILOTS));
        team.put(Position.NAVIGATOR, resultSet.getInt(Column.PLANES_NAVIGATORS));
        team.put(Position.RADIOOPERATOR, resultSet.getInt(Column.PLANES_RADIOOPERATORS));
        team.put(Position.STEWARDESS, resultSet.getInt(Column.PLANES_STEWARDESSES));
        plane.setTeam(team);
        return plane;
    }
}