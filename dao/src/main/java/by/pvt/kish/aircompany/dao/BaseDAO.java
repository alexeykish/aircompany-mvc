/**
 * 
 */
package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public abstract class BaseDAO<T> implements IDAO<T> {

	static Logger logger = Logger.getLogger(BaseDAO.class.getName());

	protected static Connection connection;
	protected PreparedStatement preparedStatement;

	protected BaseDAO() {
		try {
			connection = ConnectionPool.getInstance().getConnection();
        } catch (IOException e) {
			logger.error("IOException at IDAO");
		} catch (SQLException e) {
			logger.error("SQLException at IDAO");
		} catch (PropertyVetoException e) {
			logger.error("PropertyVetoException at IDAO");
		}
	}


}
