package by.pvt.kish.aircompany.command.plane;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllPlanesCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = GetAllPlanesCommand.class.getName();
        try {
            List<Plane> planes = PlaneService.getInstance().getAll();
            request.setAttribute(Attribute.PLANES_ATTRIBUTE, planes);
        } catch (SQLException e) {
            return ErrorHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
        }
        return Page.PLANES;
    }
}
