package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.dao.impl.TeamDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.services.ITeamService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class TeamService extends BaseService implements ITeamService {

    static Logger logger = Logger.getLogger(TeamService.class.getName());

    private static TeamService instance;
    private TeamDAO teamDAO = TeamDAO.getInstance();

    private TeamService() {
        super();
    }

    public synchronized static TeamService getInstance() {
        if (instance == null) {
            instance = new TeamService();
        }
        return instance;
    }

    @Override
    public void add(int fid, List<Integer> tid) throws SQLException {
        try {
            connection.setAutoCommit(false);
            teamDAO.delete(fid);
            teamDAO.add(fid, tid);
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

    @Override
    public int add(Object o) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Object o) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<FlightTeam> getAll() throws SQLException {
        return teamDAO.getAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        teamDAO.delete(id);
    }

    @Override
    public List<Employee> getById(int id) throws SQLException {
        return teamDAO.getById(id);
    }
}
