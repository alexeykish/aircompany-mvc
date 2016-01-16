package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.constants.SqlQuery;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.pool.ConnectionUtils.closeResultSet;

/**
 * @author  Kish Alexey
 */
public class PlaneDAO extends BaseDAO<Plane> {

    static Logger logger = Logger.getLogger(PlaneDAO.class.getName());

    private static PlaneDAO instance;

    private PlaneDAO() {
        super();
    }

    public synchronized static PlaneDAO getInstance() {
        if (instance == null) {
            instance = new PlaneDAO();
        }
        return instance;
    }

    @Override
    public int add(Connection connection, Plane plane) throws SQLException {
        int generatedId = 0;
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.ADD_PLANE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = setStatementParametrs(preparedStatement, plane);
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
    public void update(Connection connection, Plane plane) throws SQLException {
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
//            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_PLANE);
            preparedStatement = setStatementParametrs(preparedStatement, plane);
            preparedStatement.setInt(8, plane.getPid());
            preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Plane> getAll(Connection connection) throws SQLException {
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Plane> planes = new ArrayList<>();
        try {
//            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.GET_ALL_PLANES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Plane plane = new Plane();
                plane = setPlaneParametrs(resultSet, plane);
                planes.add(plane);
            }
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return planes;
    }

    @Override
    public Plane getById(Connection connection, int id) throws SQLException {
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Plane plane = null;
        try {
//            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.GET_PLANE_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                plane = new Plane();
                plane = setPlaneParametrs(resultSet, plane);
            }
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return plane;
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
//            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_PLANE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
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
        Map<Position,Integer> team = new HashMap<>();
        team.put(Position.PILOT, resultSet.getInt(Column.PLANES_PILOTS));
        team.put(Position.NAVIGATOR, resultSet.getInt(Column.PLANES_NAVIGATORS));
        team.put(Position.RADIOOPERATOR, resultSet.getInt(Column.PLANES_RADIOOPERATORS));
        team.put(Position.STEWARDESS, resultSet.getInt(Column.PLANES_STEWARDESSES));
        plane.setTeam(team);
        return plane;
    }
}
