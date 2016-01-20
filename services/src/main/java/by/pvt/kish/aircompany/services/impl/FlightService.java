package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.dao.impl.FlightDAO;
import by.pvt.kish.aircompany.entity.Flight;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.FlightValidator;

import java.util.List;

import static by.pvt.kish.aircompany.utils.ServiceUtils.*;

/**
 * This class represents a concrete implementation of the IService interface for flight model.
 *
 * @author Kish Alexey
 */
public class FlightService extends BaseService<Flight> {

    private static FlightService instance;
    private FlightDAO flightDAO = FlightDAO.getInstance();
    private FlightValidator flightValidator = new FlightValidator();

    /**
     * Returns an synchronized instance of a FlightService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a FlightService
     */
    public synchronized static FlightService getInstance() {
        if (instance == null) {
            instance = new FlightService();
        }
        return instance;
    }

    @Override
    public int add(Flight flight) throws ServiceException, ServiceValidateException {
        return addEntity(flightDAO, flight, flightValidator);
    }

    @Override
    public void update(Flight flight) throws ServiceException, ServiceValidateException {
        updateEntity(flightDAO, flight, flightValidator);
    }

    @Override
    public List<Flight> getAll() throws ServiceException {
        return getAllEntities(flightDAO);
    }

    @Override
    public void delete(int id) throws ServiceException {
        deleteEntity(flightDAO, id);
    }

    @Override
    public Flight getById(int id) throws ServiceException {
        return getByIdEntity(flightDAO, id);
    }
}
