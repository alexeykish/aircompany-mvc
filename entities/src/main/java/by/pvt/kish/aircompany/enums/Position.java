package by.pvt.kish.aircompany.enums;

/**
 * Описывает возможные должности в составе полетной бригады
 *
 * @author Kish Alexey
 */
public enum Position {
    PILOT(1), NAVIGATOR(2), RADIOOPERATOR(3), STEWARDESS(4);

    private int order;
    Position(int i) {
        order = i;
    }
}
