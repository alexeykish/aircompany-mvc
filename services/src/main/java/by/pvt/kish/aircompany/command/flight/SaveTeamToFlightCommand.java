/**
 * 
 */
package by.pvt.kish.aircompany.command.flight;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.dao.FlightDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class SaveTeamToFlightCommand implements ActionCommand {

	static Logger logger = Logger.getLogger(SaveTeamToFlightCommand.class.getName());
	private final String FID = "fid";
	private final String TID = "tid";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter(FID);
			String tid = request.getParameter(TID);
			if (id == null || tid == null) {
				logger.error(Message.ERROR_ID_MISSING);
				return Page.ERROR;
			}
			FlightDAO.getInstance().updateFlightByTeam(id, tid);
			request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_TEAM_CHANGE);
		} catch (SQLException e) {
			logger.error(Message.ERROR_SQL_DAO);
			return Page.ERROR;
		}
		return Page.MAIN;
	}

}
