package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.dao.impl.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.validators.EmployeeValidator;

import java.util.List;

import static by.pvt.kish.aircompany.utils.ServiceUtils.*;

/**
 * This class represents a concrete implementation of the IService interface for employee model.
 *
 * @author Kish Alexey
 */
public class EmployeeService extends BaseService<Employee> {

    private static EmployeeService instance;
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    private EmployeeValidator employeeValidator = new EmployeeValidator();

    /**
     * Returns an synchronized instance of a EmployeeService, if the instance does not exist yet - create a new
     *
     * @return - a instance of a EmployeeService
     */
    public synchronized static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    @Override
    public int add(Employee employee) throws ServiceException, ServiceValidateException {
        return addEntity(employeeDAO, employee, employeeValidator);
    }

    @Override
    public void update(Employee employee) throws ServiceException, ServiceValidateException {
        updateEntity(employeeDAO, employee, employeeValidator);
    }

    @Override
    public List<Employee> getAll() throws ServiceException {
        return getAllEntities(employeeDAO);
    }

    @Override
    public void delete(int id) throws ServiceException {
        deleteEntity(employeeDAO, id);
    }

    @Override
    public Employee getById(int id) throws ServiceException {
        return getByIdEntity(employeeDAO, id);
    }
}
