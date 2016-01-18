package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.FlightValidator;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class FlightService extends BaseService<Flight> {

    private static FlightService instance;
    private FlightDAO flightDAO = FlightDAO.getInstance();

    public synchronized static FlightService getInstance() {
        if (instance == null) {
            instance = new FlightService();
        }
        return instance;
    }

    @Override
    public int add(Flight flight) throws ServiceException, ServiceValidateException {
        try {
            String validateResult = FlightValidator.validate(flight);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            return flightDAO.add(flight);
        } catch (DaoException e) {
            throw new ServiceException("Creation flight failed", e);
        }
    }

    @Override
    public void update(Flight flight) throws SQLException {
        flightDAO.update(flight);
    }

    @Override
    public List<Flight> getAll() throws SQLException {
        List<Flight> list = flightDAO.getAll();
        return list;
    }

    @Override
    public void delete(int id) throws SQLException {
        flightDAO.delete(id);
    }

    @Override
    public Flight getById(int id) throws SQLException {
        Flight flight = flightDAO.getById(id);
        return flight;
    }
}
