/**
 *
 */
package by.pvt.kish.aircompany.pool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

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

    static Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private static ConnectionPool instance;
    private static BasicDataSource dataSource = new BasicDataSource();
    public static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

    private ConnectionPool() throws IOException, SQLException, PropertyVetoException {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        //dataSource = new BasicDataSource();
        dataSource.setDriverClassName(bundle.getString("SQL_DB_DRIVER_CLASS"));
        dataSource.setUsername(bundle.getString("SQL_DB_USERNAME"));
        dataSource.setPassword(bundle.getString("SQL_DB_PASSWORD"));
        dataSource.setUrl(bundle.getString("SQL_DB_URL"));

    }

    /**
     * Возвращает экземпляр пула соединений, если экземпляра еще не существует - создается новый
     *
     * @return - пул содинений
     * @throws IOException
     * @throws SQLException
     * @throws PropertyVetoException
     */
    public synchronized static ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
        //connectionHolder.set(dataSource.getConnection());
        if (instance == null) {
            instance = new ConnectionPool();
            //connectionHolder.set(dataSource.getConnection());
            return instance;
        } else {
            //connectionHolder.set(dataSource.getConnection());
            return instance;
        }
    }

    /**
     * Возвращает экземпляр соединения
     *
     * @return - соединение с БД
     * @throws SQLException
     */
//    public Connection getConnection() throws SQLException {
//        logger.info(Thread.currentThread()+": get a connection.");
//        return connectionHolder.get();
//    }
    public Connection getConnection() throws SQLException {

        if(threadConnection.get() == null) {
            Connection connection = this.dataSource.getConnection();
            threadConnection.set(connection);
            logger.info(Thread.currentThread()+": set a connection: " + connection.getMetaData().getURL());
            return threadConnection.get();
        } else
            logger.info(Thread.currentThread()+": get a connection.");
            return threadConnection.get();
    }



}


