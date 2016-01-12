/**
 * 
 */
package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.enums.Position;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kish Alexey, 2015
 *
 */
public class TeamValidator {
	
	static Logger logger = Logger.getLogger(TeamValidator.class.getName());

    /**
     * Проверяет полетную команду на корректность:
     * <li>есть ли незаполненные позиции,</li>
     * <li>один и тот же сотрудник не должен быть в команде на нескольких позициях</li>
     * <li>каждый сострудник должен находится на соответсвующей его должности позиции</li>
     * @param flightTeam - проверяемая команда
     * @return - null, если все проверки пройдены корректно; страницу ошибки, если данные некорректны
     */
    public static String validate(FlightTeam flightTeam) {
		if (checkEmpty(flightTeam)) {
			return Message.ERROR_EMPTY;
		}
		if (checkEntry(flightTeam)) {
			return Message.ERROR_TEAM_VALID;
		}
		if (checkPositions(flightTeam)) {
			return Message.ERROR_TEAM_POSITIONS_VALID;
		}
		return null;
	}

    /**
     * Метод проверяет соответсвие позиции сотрудника его позиции в команде
     * @param team - проверяемая команда
     * @return false если соответсвие позиций корректно, true если было найдено хотя бы одно несоответсвие
     */
    private static boolean checkPositions(FlightTeam team) {
        if (team.getFirstPilot().getPosition() != Position.PILOT) {
            return true;
        }
        if (team.getSecondPilot().getPosition() != Position.PILOT) {
            return true;
        }
        if (team.getNavigator().getPosition() != Position.NAVIGATOR) {
            return true;
        }
        if (team.getRadiooperator().getPosition() != Position.RADIOOPERATOR) {
            return true;
        }
        if (team.getStewardess1().getPosition() != Position.STEWARDESS) {
            return true;
        }
        if (team.getStewardess2().getPosition() != Position.STEWARDESS) {
            return true;
        }
        if (team.getStewardess3().getPosition() != Position.STEWARDESS) {
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет полноту заполнения всех позиций, пустые позиции не допускаются
     * @param team - проверяемая команда
     * @return - false если все позиции заполнены, true если одна из позиций равна null
     */
    private static boolean checkEmpty(FlightTeam team) { //TODO проверка наличия сотрудника в базе
            if (team.getFirstPilot() == null) {
                return true;
            }
            if (team.getSecondPilot() == null) {
                return true;
            }
            if (team.getNavigator() == null) {
                return true;
            }
            if (team.getRadiooperator() == null) {
                return true;
            }
            if (team.getStewardess1() == null) {
                return true;
            }
            if (team.getStewardess2() == null) {
                return true;
            }
            return team.getStewardess3() == null;
	}

    /**
     * Метод проверяет уникальность каждого сотрудника в команде, сотрудник не должен занимать соазу несколько позиций
     * @param team - проверяемая команда
     * @return - false все позиции уникальны, true если найдены совпадения
     */
    private static boolean checkEntry(FlightTeam team) {
		int i = 0;
		Set<Employee> set = new HashSet<>();
		set.add(team.getFirstPilot()); i++;
		set.add(team.getSecondPilot()); i++;
		set.add(team.getNavigator()); i++;
		set.add(team.getRadiooperator()); i++;
		set.add(team.getStewardess1()); i++;
		set.add(team.getStewardess2()); i++;
		set.add(team.getStewardess3()); i++;
		return i != set.size();
	}
}
