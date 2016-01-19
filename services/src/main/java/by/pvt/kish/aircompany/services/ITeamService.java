package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.exceptions.ServiceValidateException;

import java.util.List;

/**
 * @author Kish Alexey
 */
public interface ITeamService {
    void add(int fid, List<Integer> tid) throws ServiceException, ServiceValidateException;
    void delete(int id) throws ServiceException;
    List<Employee> getById(int id) throws ServiceException;
}
