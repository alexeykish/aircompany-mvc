package by.pvt.kish.aircompany.command;

import by.pvt.kish.aircompany.constants.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author  Kish Alexey
 *
 * В случае ошибки или прямого обращения к контроллеру переадресация на страницу ввода логина
 */
public class EmptyCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return Page.INDEX;
    }
}
