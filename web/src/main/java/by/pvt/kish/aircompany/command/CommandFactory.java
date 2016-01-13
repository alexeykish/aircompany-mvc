package by.pvt.kish.aircompany.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kish Alexey
 */
public class CommandFactory {

    static Logger logger = Logger.getLogger(CommandFactory.class.getName());

    public ActionCommand defineCommand(String action) {
        ActionCommand currentCommand = new EmptyCommand();
        if (action == null || action.isEmpty()) {
            return currentCommand;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            currentCommand = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }
        return currentCommand;
    }
}