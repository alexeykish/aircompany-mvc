/**
 *
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.services.impl.FlightService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Describes the utility class to test the flight team before adding or changing it in the DB
 *
 * @author Kish Alexey, 2015
 */
public class TeamValidator {

    /**
     * Check the validity of:
     * <li>the presence of empty fields</li>
     * <li>one and the same employee should not be in command at several positions</li>
     * <li>each employee must be located at a position corresponding to its position</li>
     *
     * @param id   - The flight id
     * @param team - The flight team being checked
     * @return - Null, if everything checks out correctly; error page if the data is incorrect
     */
    public static String validate(int id, List<Integer> team) throws ServiceException {
        if (checkEmpty(team)) {
            return Message.ERROR_EMPTY;
        }
        if (checkEntry(team)) {
            return Message.ERROR_TEAM_VALID;
        }
        if (checkPositions(id, team)) {
            return Message.ERROR_TEAM_POSITIONS_VALID;
        }
        return null;
    }

    /**
     * The method checks the position of employees to their positions on the team
     *
     * @param team - The flight team being checked
     * @return false if it corresponds to the position correctly, true if found at least one to inadequate
     */
    private static boolean checkPositions(int fid, List<Integer> team) throws ServiceException {
        List<Employee> list = new ArrayList<>();
        for (Integer i : team) {
            list.add(EmployeeService.getInstance().getById(i));
        }
        Plane plane = FlightService.getInstance().getById(fid).getPlane();
        int num_pilots = 0;
        int num_navigators = 0;
        int num_radiooperators = 0;
        int num_stewardesses = 0;
        for (Employee e : list) {
            if (e.getPosition().equals(Position.PILOT)) {
                num_pilots++;
            }
            if (e.getPosition().equals(Position.NAVIGATOR)) {
                num_navigators++;
            }
            if (e.getPosition().equals(Position.RADIOOPERATOR)) {
                num_radiooperators++;
            }
            if (e.getPosition().equals(Position.STEWARDESS)) {
                num_stewardesses++;
            }
        }
        return num_pilots != plane.getTeam().get(Position.PILOT) ||
                num_navigators != plane.getTeam().get(Position.NAVIGATOR) ||
                num_radiooperators != plane.getTeam().get(Position.RADIOOPERATOR) ||
                num_stewardesses != plane.getTeam().get(Position.STEWARDESS);
    }

    /**
     * The method checks the object to <code>null</code> completeness of all positions are empty positions are not allowed
     *
     * @param team - The flight team being checked
     * @return - false if all positions are filled, true if one of the items is null
     */
    private static boolean checkEmpty(List<Integer> team) {
        if (team == null) {
            return true;
        }
        for (Integer i : team) {
            if (i == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method checks the uniqueness of each employee in the team, the employee should take no more than one position
     *
     * @param team - The flight team being checked
     * @return - false all items are unique, true if a match is found
     */
    private static boolean checkEntry(List<Integer> team) {
        Set<Integer> set = new HashSet<>(team);
        return set.size() != team.size();
    }
}
