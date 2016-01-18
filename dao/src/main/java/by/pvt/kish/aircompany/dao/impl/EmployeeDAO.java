/**
 * 
 */
package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.pvt.kish.aircompany.pool.ConnectionUtils.closePreparedStatement;
import static by.pvt.kish.aircompany.pool.ConnectionUtils.closeResultSet;

/**
 * @author  Kish Alexey
 */
public class EmployeeDAO extends BaseDAO<Employee> {

	private static final String SQL_ADD_EMPLOYEE = "INSERT INTO employees (`first_name`,`last_name`,`position`) VALUES (?,?,?)";
	private static final String SQL_GET_ALL_EMPLOYEES = "SELECT * FROM employees";
	private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employees WHERE eid = ?";
	private static final String SQL_GET_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE eid = ?";
	private static final String SQL_UPDATE_EMPLOYEE = "UPDATE employees SET `first_name` = ?, `last_name` = ?, `position` = ? WHERE eid = ?";

	private static final String ADD_EMPLOYEE_FAIL = "Creating employee failed";
	private static final String GET_ALL_EMPLOYEES_FAIL = "Get all employees failed";
	private static final String DELETE_EMPLOYEE_FAIL = "Deleting employee failed";
	private static final String UPDATE_EMPLOYEE_FAIL = "Updating employee failed";
	private static final String GET_EMPLOYEE_BY_ID_FAIL = "Getting employee by ID failed";
	private static EmployeeDAO instance;

	private EmployeeDAO() {
		super();
	}

	public synchronized static EmployeeDAO getInstance() {
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }

	@Override
	public int add(Employee employee) throws DaoException {
		int generatedId = 0;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SQL_ADD_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getPosition().name());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DaoException(ADD_EMPLOYEE_FAIL, e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return  generatedId;
	}

	@Override
	public List<Employee> getAll() throws DaoException {
		ResultSet resultSet = null;
		List<Employee> employees = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(SQL_GET_ALL_EMPLOYEES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee = setEmployeeParametrs(resultSet, employee);
				employees.add(employee);
			}
		} catch (SQLException e) {
			throw new DaoException(GET_ALL_EMPLOYEES_FAIL, e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return employees;
	}

	@Override
	public void delete(int eid) throws DaoException {
		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE_EMPLOYEE);
			preparedStatement.setInt(1, eid);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(DELETE_EMPLOYEE_FAIL, e);
		} finally {
			closePreparedStatement(preparedStatement);
		}

	}

	@Override
	public void update(Employee employee) throws DaoException {
		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE);
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getPosition().name());
			preparedStatement.setInt(4, employee.getEid());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(UPDATE_EMPLOYEE_FAIL, e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Employee getById(int eid) throws DaoException {
		ResultSet resultSet = null;
		Employee employee = null;
		try {
			preparedStatement = connection.prepareStatement(SQL_GET_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, eid);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee();
				employee = setEmployeeParametrs(resultSet, employee);
			}
		} catch (SQLException e) {
			throw new DaoException(GET_EMPLOYEE_BY_ID_FAIL, e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
		return employee;
	}

	private Employee setEmployeeParametrs(ResultSet resultSet, Employee employee) throws SQLException {
		employee.setEid(resultSet.getInt(Column.EMPLOYEES_EID));
		employee.setLastName(resultSet.getString(Column.EMPLOYEES_LASTNAME));
		employee.setFirstName(resultSet.getString(Column.EMPLOYEES_FIRSTNAME));
		employee.setPosition(Position.valueOf(resultSet.getString(Column.EMPLOYEES_POSITION)));
		return employee;
	}
}
