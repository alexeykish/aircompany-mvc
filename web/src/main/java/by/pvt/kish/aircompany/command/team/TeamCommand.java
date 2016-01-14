package by.pvt.kish.aircompany.command.team;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.services.TeamService;

/**
 * @author Kish Alexey
 */
public abstract class TeamCommand implements ActionCommand{
    TeamService teamService = new TeamService();
}
