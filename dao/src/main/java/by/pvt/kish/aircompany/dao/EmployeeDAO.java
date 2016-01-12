/**
 * 
 */
package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.constants.Column;
import by.pvt.kish.aircompany.constants.SqlQuery;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.enums.Position;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  Kish Alexey
 */
public class EmployeeDAO extends BaseDAO<Employee> {
	static Logger logger = Logger.getLogger(EmployeeDAO.class.getName());
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
	public int add(Employee employee) throws SQLException {
		int generatedId = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.ADD_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getPosition().name());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = resultSet.getInt(1);
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return  generatedId;
	}

	@Override
	public List<Employee> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = new ArrayList<>();
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_ALL_EMPLOYEES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee = setEmployeeParametrs(resultSet, employee);
				employees.add(employee);
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
		}
		return employees;
	}

	@Override
	public void delete(int eid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.DELETE_EMPLOYEE);
			preparedStatement.setInt(1, eid);
			preparedStatement.executeUpdate();
		} finally {
			closeItems(preparedStatement, connection);
		}

	}

	@Override
	public void update(Employee employee) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_EMPLOYEE);
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getPosition().name());
			preparedStatement.setInt(4, employee.getEid());
			preparedStatement.executeUpdate();
		} finally {
			closeItems(preparedStatement, connection);
		}
	}

	@Override
	public Employee getById(int eid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;
		try {
			connection = poolInstance.getConnection();
			preparedStatement = connection.prepareStatement(SqlQuery.GET_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, eid);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee();
				employee = setEmployeeParametrs(resultSet, employee);
			}
		} finally {
			closeItems(resultSet, preparedStatement, connection);
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
