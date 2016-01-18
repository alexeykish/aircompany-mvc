package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.EmployeeValidator;

import java.util.List;

/**
 * @author Kish Alexey
 */
public class EmployeeService extends BaseService<Employee> {

    private static EmployeeService instance;
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

    public synchronized static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    @Override
    public int add(Employee employee) throws ServiceException, ServiceValidateException {
        try {
            String validateResult = EmployeeValidator.validate(employee);
            if (validateResult!=null) {
                throw new ServiceValidateException(validateResult);
            }
            return employeeDAO.add(employee);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Employee employee) throws ServiceException, ServiceValidateException {
        try {
            String validateResult = EmployeeValidator.validate(employee);
            if (validateResult!=null) {
                throw new ServiceValidateException(validateResult);

            }
            employeeDAO.update(employee);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAll() throws ServiceException {
        try {
            return employeeDAO.getAll();
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
            employeeDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public Employee getById(int id) throws ServiceException {
        try {
            return employeeDAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
