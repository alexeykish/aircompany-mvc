package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;

/**
 * Проверяет обеъект Plane перед добавлением или изменением его в БД
 *
 * @author Kish Alexey
 */
public class PlaneValidator {

    /**
     * Проверяет на корректность:
     * <li>наличие пустых полей</li>
     * @param plane - проверяемый объект Plane
     * @return - null, если все проверки пройдены корректно; страницу ошибки, если данные некорректны
     */
    public static String validate(Plane plane) {
        if (checkEmpty(plane)) {
            return Message.ERROR_EMPTY;
        }
        return null;
    }

    /**
     * Метод проверяет полноту заполнения всех позиций, пустые позиции не допускаются
     * @param plane - проверяемый объект Plane
     * @return - false, если все проверки пройдены корректно; true - если данные некорректны
     */
    private static boolean checkEmpty(Plane plane) {
        if ((plane == null) ||
                (plane.getModel() == null) ||
                (plane.getModel().equals("")) ||
                (plane.getCapacity() < 1) ||
                (plane.getRange() < 1) ||
                (plane.getTeam() == null) ||
                (plane.getTeam().get(Position.PILOT) <= 0) ||
                (plane.getTeam().get(Position.NAVIGATOR) <= 0) ||
                (plane.getTeam().get(Position.RADIOOPERATOR) <= 0) ||
                (plane.getTeam().get(Position.STEWARDESS) <= 0)) {
            return true;
        }
        return false;
    }
}
