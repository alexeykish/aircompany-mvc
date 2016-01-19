package by.pvt.kish.aircompany.services.impl;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.dao.impl.TeamDAO;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;
import by.pvt.kish.aircompany.services.BaseService;
import by.pvt.kish.aircompany.services.ITeamService;
import by.pvt.kish.aircompany.validators.TeamValidator;
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
    public void add(int fid, List<Integer> team) throws ServiceException, ServiceValidateException {
        try {
            if (fid < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            String validateResult = TeamValidator.validate(fid, team);
            if (validateResult!=null) {
                throw new ServiceValidateException(validateResult);
            }
            connection.setAutoCommit(false);
            teamDAO.delete(fid);
            teamDAO.add(fid, team);
            connection.commit();
        } catch (SQLException e){
            try {
                logger.debug("Add team: commit failed");
                connection.rollback();
            }catch (SQLException e2) {
                logger.debug("Add team: rollback failed");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int add(Object o) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Object o) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List getAll() throws SQLException, ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            teamDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Employee> getById(int id) throws ServiceException {
        try {
            if (id < 0) {
                throw new ServiceException(Message.ERROR_ID_MISSING);
            }
            return teamDAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
