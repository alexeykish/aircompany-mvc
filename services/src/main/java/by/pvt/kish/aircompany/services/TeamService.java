package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.dao.TeamDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class TeamService {

    static Logger logger = Logger.getLogger(TeamService.class.getName());

    private static TeamService instance;
    private TeamDAO teamDAO = TeamDAO.getInstance();
    ConnectionPool poolInstance;
    Connection connection;

    private TeamService() {
        super();
        try {
            poolInstance = ConnectionPool.getInstance();
        } catch (IOException | SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public synchronized static TeamService getInstance() {
        if (instance == null) {
            instance = new TeamService();
        }
        return instance;
    }

    public void add(int fid, List<Integer> tid) throws SQLException {
        try {
            connection.setAutoCommit(false);
            TeamDAO.getInstance().delete(fid);
            TeamDAO.getInstance().add(fid, tid);
            connection.commit();
        } catch (SQLException e){
            try {
                logger.debug("Commit failed");
                connection.rollback();
            }catch (SQLException e2) {
                logger.debug("Rollback failed");
            }
        }
    }

    public void update(FlightTeam flightTeam) throws SQLException {
        //TeamDAO.getInstance().update(flightTeam);
    }

    public List<FlightTeam> getAll() throws SQLException {
        connection = poolInstance.getConnection();
        return TeamDAO.getInstance().getAll();
    }

    public void delete(int id) throws SQLException {
        connection = poolInstance.getConnection();
        TeamDAO.getInstance().delete(id);
    }

    public List<Employee> getById(int id) throws SQLException {
        connection = poolInstance.getConnection();
        return TeamDAO.getInstance().getById(id);
    }
}
