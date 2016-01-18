package by.pvt.kish.aircompany.command.plane;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;
import by.pvt.kish.aircompany.validators.PlaneValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Kish Alexey
 */
public class AddPlaneCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = AddPlaneCommand.class.getName();
        try {
            Plane plane = RequestHandler.getPlane(request);
            String validateResult = PlaneValidator.validate(plane);
            if (validateResult!=null) {
                return ErrorHandler.returnValidateErrorPage(request, Attribute.MESSAGE_ATTRIBUTE, validateResult);
            }
            PlaneService.getInstance().add(plane);
            request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_ADD_PLANE);
            return Page.MAIN;
        } catch (IllegalArgumentException e) {
            return ErrorHandler.returnErrorPage(Message.ERROR_IAE, className);
        } catch (SQLException e) {
            return ErrorHandler.returnErrorPage(Message.ERROR_SQL_DAO, className);
        }
    }
}
