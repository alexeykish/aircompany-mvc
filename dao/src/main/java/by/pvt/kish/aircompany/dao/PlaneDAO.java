package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.constants.SqlQuery;
import by.pvt.kish.aircompany.entity.Plane;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public int add(Plane plane) throws SQLException {
        int generatedId = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.ADD_PLANE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, plane.getModel());
            preparedStatement.setInt(2, plane.getCapacity());
            preparedStatement.setInt(3, plane.getRange());
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
    public void update(Plane plane) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_PLANE);
            preparedStatement.setString(1, plane.getModel());
            preparedStatement.setInt(2, plane.getCapacity());
            preparedStatement.setInt(3, plane.getRange());
            preparedStatement.setInt(4, plane.getPid());
            preparedStatement.executeUpdate();
        } finally {
            closeItems(preparedStatement, connection);
        }
    }

    @Override
    public List<Plane> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Plane> planes = new ArrayList<>();
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.GET_ALL_PLANES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Plane plane = new Plane();
                plane = setPlaneParametrs(resultSet, plane);
                planes.add(plane);
            }
        } finally {
            closeItems(resultSet, preparedStatement, connection);
        }
        return planes;
    }

    @Override
    public Plane getById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Plane plane = null;
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.GET_PLANE_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                plane = new Plane();
                plane = setPlaneParametrs(resultSet, plane);
            }
        } finally {
            closeItems(resultSet, preparedStatement, connection);
        }
        return plane;
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = poolInstance.getConnection();
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_PLANE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            closeItems(preparedStatement, connection);
        }
    }

    private Plane setPlaneParametrs(ResultSet resultSet, Plane plane) throws SQLException {
        plane.setPid(resultSet.getInt(Column.PLANES_PID));
        plane.setModel(resultSet.getString(Column.PLANES_MODEL));
        plane.setCapacity(resultSet.getInt(Column.PLANES_CAPACITY));
        plane.setRange(resultSet.getInt(Column.PLANES_RANGE));
        return plane;
    }
}