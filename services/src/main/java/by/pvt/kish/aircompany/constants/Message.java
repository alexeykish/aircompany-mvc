package by.pvt.kish.aircompany.constants;

/**
 * @author Kish Alexey
 */
public class Message {
    public static final String SUCCESS_ADD_FLIGHT = "The flight was successfully added";
    public static final String SUCCESS_DELETE_FLIGHT = "The flight was successfully deleted";
    public static final String SUCCESS_UPDATE_FLIGHT = "The flight was successfully updated";

    public static final String SUCCESS_TEAM_CHANGE = "The flight team was successfully changed";
    public static final String SUCCESS_ADD_TEAM = "The flight team was successfully added";
    public static final String SUCCESS_DELETE_TEAM = "The flight team was successfully deleted";
    public static final String SUCCESS_UPDATE_TEAM = "The flight team was successfully updated";

    public static final String SUCCESS_ADD_EMPLOYEE = "The employee was successfully added";
    public static final String SUCCESS_DELETE_EMPLOYEE = "The employee was successfully deleted";
    public static final String SUCCESS_UPDATE_EMPLOYEE = "The employee was successfully updated";

    public static final String SUCCESS_REG = "You have successfully registered";

    public static final String MALFORMED_FIRSTNAME = "Incorrectly filled 'Firstname' field";
    public static final String MALFORMED_LASTNAME = "Incorrectly filled 'Lastname' field";
    public static final String MALFORMED_LOGIN = "Incorrectly filled 'Login' field";
    public static final String MALFORMED_PASSWORD = "Password should contain six or more symbols";
    public static final String MALFORMED_EMAIL = "Incorrectly filled 'e-mail' field";

    public static final String ERROR_REG_LOGIN = "Wrong login name or password";
    public static final String ERROR_REG_EMPTY= "Empty data at login or password";
    public static final String ERROR_SQL = "Something wrong with the database, please check connection";
    public static final String ERROR_EMPTY = "Empty data at request";
    public static final String ERROR_ID_MISSING = "Missing id parameter";
    public static final String ERROR_REG_DATA = "Incorrect data at user registration";
    public static final String ERROR_FLIGHT_VALID = "Action canceled! Place of departure and place of arrival should be different";
    public static final String ERROR_TEAM_VALID = "Action canceled! Duplication in the composition of the team members";
    public static final String ERROR_MESSAGE = "Opps! Something gone wrong... Check log file.";
    public static final String ERROR_SQL_DAO = "SQL exception at DAO";
    public static final String ERROR_IAE = "IllegalArgument Exception: DATE field";
    public static final String USER_LOGOUT = "(Session invalidation) Logout of user";
    public static final String ERROR_TEAM_POSITIONS_VALID = "Action canceled! Members of the team must be at correct positions";
}
