package by.pvt.kish.aircompany.command.airport;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.entity.Airport;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.services.impl.AirportService;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class DeleteAirportCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
