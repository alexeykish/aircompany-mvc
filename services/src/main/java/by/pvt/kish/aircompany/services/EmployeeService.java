package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.validators.EmployeeValidator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class EmployeeService implements EntityService<Employee> {

    @Override
    public int add(Employee employee) throws SQLException {
        return EmployeeDAO.getInstance().add(employee);
    }

    @Override
    public void update(Employee employee) throws SQLException {
        EmployeeDAO.getInstance().update(employee);
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return EmployeeDAO.getInstance().getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        EmployeeDAO.getInstance().delete(id);
    }

    @Override
    public Employee getById(int id) throws SQLException {
        return EmployeeDAO.getInstance().getById(id);
    }
}
