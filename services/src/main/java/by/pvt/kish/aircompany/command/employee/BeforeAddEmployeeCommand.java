package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.enums.Position;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class BeforeAddEmployeeCommand implements by.pvt.kish.aircompany.command.ActionCommand {

    static Logger logger = Logger.getLogger(BeforeAddEmployeeCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Position> positions = Arrays.asList(Position.values());
        request.setAttribute(Attribute.POSITIONS_ATTRIBUTE, positions);
        return Page.ADD_EMPLOYEE;
    }
}
