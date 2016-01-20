package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.dao.impl.PlaneDAO;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.PlaneValidator;

import java.util.List;

import static by.pvt.kish.aircompany.utils.ServiceUtils.*;

/**
 * This class represents a concrete implementation of the IService interface for plane model.
 *
 * @author Kish Alexey
 */
public class PlaneService extends BaseService<Plane> {

    private static PlaneService instance;
    private PlaneDAO planeDAO = PlaneDAO.getInstance();
    private PlaneValidator planeValidator = new PlaneValidator();

    /**
     * Returns an synchronized instance of a PlaneService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a PlaneService
     */
    public synchronized static PlaneService getInstance() {
        if (instance == null) {
            instance = new PlaneService();
        }
        return instance;
    }

    @Override
    public int add(Plane plane) throws ServiceException, ServiceValidateException {
        return addEntity(planeDAO, plane, planeValidator);
    }

    @Override
    public void update(Plane plane) throws ServiceException, ServiceValidateException {
        updateEntity(planeDAO, plane, planeValidator);
    }

    @Override
    public List<Plane> getAll() throws ServiceException {
        return getAllEntities(planeDAO);
    }

    @Override
    public void delete(int id) throws ServiceException {
        deleteEntity(planeDAO, id);
    }

    @Override
    public Plane getById(int id) throws ServiceException {
        return getByIdEntity(planeDAO, id);
    }
}
