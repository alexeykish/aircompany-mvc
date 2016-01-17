package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.PlaneDAO;
import by.pvt.kish.aircompany.entity.Plane;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class PlaneService extends BaseService<Plane> {

    private static PlaneService instance;
    private PlaneDAO planeDAO = PlaneDAO.getInstance();
//    Connection connection;

    public synchronized static PlaneService getInstance() {
        if (instance == null) {
            instance = new PlaneService();
        }
        return instance;
    }

    @Override
    public int add(Plane plane) throws SQLException {
//        connection = poolInstance.getConnection();
        return PlaneDAO.getInstance().add(plane);
    }

    @Override
    public void update(Plane plane) throws SQLException {
//        connection = poolInstance.getConnection();
        PlaneDAO.getInstance().update(plane);
    }

    @Override
    public List<Plane> getAll() throws SQLException {
//        connection = poolInstance.getConnection();
        return PlaneDAO.getInstance().getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
//        connection = poolInstance.getConnection();
        PlaneDAO.getInstance().delete(id);
    }

    @Override
    public Plane getById(int id) throws SQLException {
//        connection = poolInstance.getConnection();
        return PlaneDAO.getInstance().getById(id);
    }
}
