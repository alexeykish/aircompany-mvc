package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.PlaneDAO;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.PlaneValidator;

import java.util.List;

/**
 * @author Kish Alexey
 */
public class PlaneService extends BaseService<Plane> {

    private static PlaneService instance;
    private PlaneDAO planeDAO = PlaneDAO.getInstance();

    public synchronized static PlaneService getInstance() {
        if (instance == null) {
            instance = new PlaneService();
        }
        return instance;
    }

    @Override
    public int add(Plane plane) throws ServiceException, ServiceValidateException {
        try {
            String validateResult = PlaneValidator.validate(plane);
            if (validateResult!=null) {
                throw new ServiceValidateException(validateResult);
            }
            return planeDAO.add(plane);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Plane plane) throws ServiceException, ServiceValidateException {
        try {
            String validateResult = PlaneValidator.validate(plane);
            if (validateResult != null) {
                throw new ServiceValidateException(validateResult);
            }
            planeDAO.update(plane);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Plane> getAll() throws ServiceException {
        try {
            return planeDAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            planeDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Plane getById(int id) throws ServiceException {
        try {
            return planeDAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
