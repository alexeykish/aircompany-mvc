package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.AirportDAO;
import by.pvt.kish.aircompany.entity.Airport;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class AirportService extends BaseService<Airport> {

    private static AirportService instance;
    private AirportDAO airportDAO = AirportDAO.getInstance();
//    private Connection connection;

    public synchronized static AirportService getInstance() {
        if (instance == null) {
            instance = new AirportService();
        }
        return instance;
    }

    @Override
    public int add(Airport airport) throws SQLException {
//        connection = poolInstance.getConnection();
        return airportDAO.add(airport);
    }

    @Override
    public void update(Airport airport) throws SQLException {
//        connection = poolInstance.getConnection();
        AirportDAO.getInstance().update(airport);
    }

    @Override
    public List<Airport> getAll() throws SQLException {
//        connection = poolInstance.getConnection();
        return AirportDAO.getInstance().getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
//        connection = poolInstance.getConnection();
        AirportDAO.getInstance().delete(id);
    }

    @Override
    public Airport getById(int id) throws SQLException {
//        connection = poolInstance.getConnection();
        return AirportDAO.getInstance().getById(id);
    }
}
