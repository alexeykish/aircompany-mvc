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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public abstract class BaseDAO<T> implements DAO<T>{

	static Logger logger = Logger.getLogger(BaseDAO.class.getName());

//	protected static ConnectionPool poolInstance;

	protected BaseDAO() {
//		try {
//			poolInstance = ConnectionPool.getInstance();
//        } catch (IOException e) {
//			logger.error("IOException at DAO");
//		} catch (SQLException e) {
//			logger.error("SQLException at DAO");
//		} catch (PropertyVetoException e) {
//			logger.error("PropertyVetoException at DAO");
//		}
	}


}
