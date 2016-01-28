package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;

import java.util.List;

/**
 * This interface represents a contract for a IDAO for the plane model.
 *
 * @author Kish Alexey
 */
public interface IPlaneDAO {

    /**
     * Check if there are any flights associated with plane matching the given ID
     *
     * @param id - The ID of the plane
     * @return - false if there are no flights, true - there are flights associated with this plane
     * @throws DaoException If something fails at DB level
     */
    boolean checkFlights(int id) throws DaoException;

    /**
     * Update particular plane status int the DB matching the given ID
     *
     * @param id - The ID of the flight
     * @throws DaoException If something fails at DB level
     */
    void setStatus(int id, PlaneStatus status) throws DaoException;
}
