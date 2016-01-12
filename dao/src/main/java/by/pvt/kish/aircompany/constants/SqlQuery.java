package by.pvt.kish.aircompany.constants;

/**
 * Содержит SQL запросы, использующиеся в DAO классах
 *
 * @author  Kish Alexey
 */
public class SqlQuery {
    public static final String ADD_FLIGHT = "INSERT INTO  flights (`date`,`from`,`to`, `pid`) VALUES (?,?,?,?)";
    public static final String GET_ALL_FLIGHTS = "SELECT * FROM flights";
    public static final String DELETE_FLIGHT = "DELETE FROM flights WHERE fid = ?";
    public static final String GET_FLIGHT_BY_ID = "SELECT * FROM flights WHERE fid = ?";
    public static final String UPDATE_FLIGHT = "UPDATE flights SET `date` = ?, `from` = ?, `to` = ?, `pid` = ? WHERE fid = ?";
    public static final String UPDATE_TEAM_TO_FLIGHT = "UPDATE flights SET `tid` = ? WHERE fid = ?";

    public static final String ADD_EMPLOYEE = "INSERT INTO employees (`first_name`,`last_name`,`position`) VALUES (?,?,?)";
    public static final String GET_ALL_EMPLOYEES = "SELECT * FROM employees";
    public static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE eid = ?";
    public static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE eid = ?";
    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET `first_name` = ?, `last_name` = ?, `position` = ? WHERE eid = ?";

    public static final String ADD_TEAM = "INSERT INTO  teams (`first_pilot_id`,`second_pilot_id`,`navigator_id`,`radiooperator_id`,`stewardess1_id`,`stewardess2_id`,`stewardess3_id`) VALUES (?,?,?,?,?,?,?)";
    public static final String GET_ALL_TEAMS = "SELECT * FROM teams";
    public static final String DELETE_TEAM = "DELETE FROM teams WHERE tid = ?";
    public static final String GET_TEAM_BY_ID = "SELECT * FROM teams WHERE tid = ?";
    public static final String UPDATE_TEAM = "UPDATE teams SET `first_pilot_id` = ?, `second_pilot_id` = ?, `navigator_id` = ?, `radiooperator_id` = ?, `stewardess1_id` = ?, `stewardess2_id` = ?, `stewardess3_id` = ? WHERE tid = ?";

    public static final String ADD_USER = "INSERT INTO  users (`first_name`,`last_name`,`login`,`password`,`email`,`user_type`) VALUES (?,?,?,?,?,?)";
    public static final String CHECK_LOGIN = "SELECT uid FROM users WHERE login = ?";
    public static final String GET_USER = "SELECT * FROM users WHERE login = ? AND password = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM users";

    public static final String ADD_PLANE = "INSERT INTO  planes (`model`,`capacity`,`range`) VALUES (?,?,?)";
    public static final String GET_ALL_PLANES = "SELECT * FROM  planes";
    public static final String DELETE_PLANE = "DELETE FROM planes WHERE pid = ?";
    public static final String GET_PLANE_BY_ID = "SELECT * FROM  planes WHERE pid = ?";
    public static final String UPDATE_PLANE = "UPDATE planes SET `model` = ?, `capacity` = ?, `range` = ? WHERE pid = ?";

    public static final String ADD_AIRPORT = "INSERT INTO  airports (`city`) VALUES (?)";
    public static final String GET_ALL_AIRPORTS = "SELECT * FROM  airports";
    public static final String DELETE_AIRPORT = "DELETE FROM airports WHERE aid = ?";
    public static final String GET_AIRPORT_BY_ID = "SELECT * FROM  airports WHERE aid = ?";
    public static final String UPDATE_AIRPORT = "UPDATE airports SET `city` = ? WHERE aid = ?";
}