package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.AirportDAO;
import by.pvt.kish.aircompany.entity.Airport;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class AirportService implements EntityService<Airport> {
    @Override
    public int add(Airport airport) throws SQLException {
        return AirportDAO.getInstance().add(airport);
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
