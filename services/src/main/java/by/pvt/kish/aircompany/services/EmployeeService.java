package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.EmployeeDAO;
import by.pvt.kish.aircompany.entity.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class EmployeeService extends BaseService<Employee> {

    private static EmployeeService instance;
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    //Connection connection;

    public synchronized static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    @Override
    public int add(Employee employee) throws SQLException {
        //connection = poolInstance.getConnection();
        return employeeDAO.add(employee);
    }

    @Override
    public void update(Employee employee) throws SQLException {
        //connection = poolInstance.getConnection();
        employeeDAO.update(employee);
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        //connection = poolInstance.getConnection();
        return employeeDAO.getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        //connection = poolInstance.getConnection();
        employeeDAO.delete(id);
    }

    @Override
    public Employee getById(int id) throws SQLException {
        //connection = poolInstance.getConnection();
        return employeeDAO.getById(id);
    }
}
