package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.FlightValidator;

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
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Flight flight) throws ServiceException, ServiceValidateException {
        try {
            String validateResult = FlightValidator.validate(flight);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            flightDAO.update(flight);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Flight> getAll() throws ServiceException {
        try {
            return flightDAO.getAll();
        }catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            flightDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Flight getById(int id) throws ServiceException {
        try {
            return flightDAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
