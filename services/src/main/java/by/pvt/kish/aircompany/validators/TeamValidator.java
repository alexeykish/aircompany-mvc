/**
 *
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.services.impl.FlightService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Проверяет обеъект FlightTeam перед добавлением или изменением его в БД
 *
 * @author Kish Alexey, 2015
 */
public class TeamValidator {

    static Logger logger = Logger.getLogger(TeamValidator.class.getName());

    /**
     * Проверяет на корректность:
     * <li>наличие пустых полей,</li>
     * <li>один и тот же сотрудник не должен быть в команде на нескольких позициях</li>
     * <li>каждый сострудник должен находится на соответсвующей его должности позиции</li>
     *
     * @param id - id рейса
     * @param team - проверяемый объект FlightTeam
     * @return - null, если все проверки пройдены корректно; если данные некорректны - соответствующую строку с указанием ошибки
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
     * Метод проверяет соответсвие позиций сотрудников их позициям в команде
     *
     * @param team - проверяемый объект FlightTeam
     * @return false если соответсвие позиций корректно, true если было найдено хотя бы одно несоответсвие
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
        if (num_pilots != plane.getTeam().get(Position.PILOT) ||
                num_navigators != plane.getTeam().get(Position.NAVIGATOR) ||
                num_radiooperators != plane.getTeam().get(Position.RADIOOPERATOR) ||
                num_stewardesses != plane.getTeam().get(Position.STEWARDESS)) {
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет полноту заполнения всех позиций, пустые позиции не допускаются
     *
     * @param team - проверяемый объект
     * @return - false если все позиции заполнены, true если одна из позиций равна null
     */
    private static boolean checkEmpty(List<Integer> team) { //TODO проверка наличия сотрудника в базе
        if (team == null) {
            return true;
        }
        for (Integer i : team){
            if (i == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод проверяет уникальность каждого сотрудника в команде, сотрудник не должен занимать сразу несколько позиций
     *
     * @param team - проверяемый список комманды
     * @return - false все позиции уникальны, true если найдены совпадения
     */
    private static boolean checkEntry(List<Integer> team) {
        int i = 0;
        Set<Integer> set = new HashSet<>(team);
        return set.size() != team.size();
    }
}
