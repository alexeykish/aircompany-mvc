package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.dao.impl.AirportDAO;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.BaseService;

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
    public int add(Airport airport) throws ServiceException {
        try {
            return airportDAO.add(airport);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Airport airport) throws ServiceException {
        try {
            AirportDAO.getInstance().update(airport);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Airport> getAll() throws ServiceException {
        try {
            return AirportDAO.getInstance().getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            AirportDAO.getInstance().delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Airport getById(int id) throws ServiceException {
        try {
            return AirportDAO.getInstance().getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
