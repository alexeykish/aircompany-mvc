/**
 * 
 */
package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.EmployeeDAO;
import by.pvt.kish.aircompany.dao.FlightTeamDAO;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.validators.TeamValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class UpdateTeamCommand extends FlightTeamCommand {

	static Logger logger = Logger.getLogger(UpdateTeamCommand.class.getName());

	private final String TID = "tid";
	private final String FIRSTPILOT_ID = "first_pilot";
	private final String SECONDPILOT_ID = "second_pilot";
	private final String NAVIGATOR_ID = "navigator";
	private final String RADIOOPERATOR_ID = "radiooperator";
	private final String STEWARDESS1_ID = "stewardess1";
	private final String STEWARDESS2_ID = "stewardess2";
	private final String STEWARDESS3_ID = "stewardess3";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			FlightTeam flightTeam = new FlightTeam();

			int tid = Integer.parseInt(request.getParameter(TID).trim());
			int first_pilot_id = Integer.parseInt(request.getParameter(FIRSTPILOT_ID).trim());
			int second_pilot_id = Integer.parseInt(request.getParameter(SECONDPILOT_ID).trim());
			int navigator_id = Integer.parseInt(request.getParameter(NAVIGATOR_ID).trim());
			int radiooperator_id = Integer.parseInt(request.getParameter(RADIOOPERATOR_ID).trim());
			int stewardess1_id = Integer.parseInt(request.getParameter(STEWARDESS1_ID).trim());
			int stewardess2_id = Integer.parseInt(request.getParameter(STEWARDESS2_ID).trim());
			int stewardess3_id = Integer.parseInt(request.getParameter(STEWARDESS3_ID).trim());
			
			EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
			flightTeam.setTid(tid);
			flightTeam.setFirstPilot(employeeDAO.getById(first_pilot_id));
			flightTeam.setSecondPilot(employeeDAO.getById(second_pilot_id));
			flightTeam.setNavigator(employeeDAO.getById(navigator_id));
			flightTeam.setRadiooperator(employeeDAO.getById(radiooperator_id));
			flightTeam.setStewardess1(employeeDAO.getById(stewardess1_id));
			flightTeam.setStewardess2(employeeDAO.getById(stewardess2_id));
			flightTeam.setStewardess3(employeeDAO.getById(stewardess3_id));
			
			String validateResult = TeamValidator.validate(flightTeam);
			if (validateResult!=null) {
				return validateResult;
			}
			teamService.update(flightTeam);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_UPDATE_TEAM);
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.MAIN;
	}
}
