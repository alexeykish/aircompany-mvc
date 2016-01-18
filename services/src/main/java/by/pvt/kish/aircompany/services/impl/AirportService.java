package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.dao.impl.AirportDAO;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.services.BaseService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class AirportService extends BaseService<Airport> {

    private static AirportService instance;
    private AirportDAO airportDAO = AirportDAO.getInstance();

    public synchronized static AirportService getInstance() {
        if (instance == null) {
            instance = new AirportService();
        }
        return instance;
    }

    @Override
    public int add(Airport airport) throws SQLException {
        try {
            return airportDAO.add(airport);
        } catch (by.pvt.kish.aircompany.exceptions.DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Airport airport) throws SQLException {
        AirportDAO.getInstance().update(airport);
    }

    @Override
    public List<Airport> getAll() throws SQLException {
        return AirportDAO.getInstance().getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        AirportDAO.getInstance().delete(id);
    }

    @Override
    public Airport getById(int id) throws SQLException {
        return AirportDAO.getInstance().getById(id);
    }
}
