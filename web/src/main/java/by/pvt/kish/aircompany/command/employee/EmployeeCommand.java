package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.services.EmployeeService;

/**
 * @author Kish Alexey
 */
public abstract class EmployeeCommand implements ActionCommand{

    EmployeeService employeeService = new EmployeeService();

}
