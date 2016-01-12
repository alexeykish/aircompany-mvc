/**
 * 
 */
package by.pvt.kish.aircompany.pool;

import org.apache.commons.dbcp2.BasicDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Реализует пул соединений
 * Данные для соединения с БД берутся из файла db.properties
 * Количество соединений по умолчанию равно 8
 *
 * @author Kish Alexey
 */
public class ConnectionPool {
	private static ConnectionPool pool;
	private BasicDataSource dataSource;

	private ConnectionPool() throws IOException, SQLException, PropertyVetoException {
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(bundle.getString("SQL_DB_DRIVER_CLASS"));
		dataSource.setUsername(bundle.getString("SQL_DB_USERNAME"));
		dataSource.setPassword(bundle.getString("SQL_DB_PASSWORD"));
		dataSource.setUrl(bundle.getString("SQL_DB_URL"));
	}

    /**
     * Возвращает экземпляр пула соединений, если экземпляра еще не существует - создается новый
     * @return - пул содинений
     * @throws IOException
     * @throws SQLException
     * @throws PropertyVetoException
     */
    public synchronized static ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
		if (pool == null) {
			pool = new ConnectionPool();
			return pool;
		} else {
			return pool;
		}
	}

    /**
     * Возвращает экземпляр соединения
     * @return - соединение с БД
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
}


