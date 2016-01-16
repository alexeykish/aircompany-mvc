/**
 * 
 */
package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.DAO;
import by.pvt.kish.aircompany.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public abstract class BaseService<T> implements IService<T>{

	static Logger logger = Logger.getLogger(BaseService.class.getName());

	protected static ConnectionPool poolInstance;

	protected BaseService() {
		try {
			poolInstance = ConnectionPool.getInstance();
        } catch (IOException e) {
			logger.error("IOException at Service");
		} catch (SQLException e) {
			logger.error("SQLException at Service");
		} catch (PropertyVetoException e) {
			logger.error("PropertyVetoException at Service");
		}
	}
}
