package by.pvt.kish.aircompany.command;

import by.pvt.kish.aircompany.command.flight.*;
import by.pvt.kish.aircompany.command.employee.*;
import by.pvt.kish.aircompany.command.team.*;
import by.pvt.kish.aircompany.command.user.*;

/**
 * @author Kish Alexey
 */
public enum CommandEnum {
    BEFORE_ADD_FLIGHT_COMMAND 			{   {this.command = new BeforeAddFlightCommand();    	}	},
    ADD_FLIGHT_COMMAND 					{   {this.command = new AddFlightCommand();		    	}	},
    GET_ALL_FLIGHTS_COMMAND 		 	{	{this.command = new GetAllFlightsCommand();		    }	},
    DELETE_FLIGHT_COMMAND 			 	{	{this.command = new DeleteFlightCommand();	    	}	},
    BEFORE_UPDATE_FLIGHT_COMMAND  	    {	{this.command = new BeforeUpdateFlightCommand();   	}	},
    UPDATE_FLIGHT_COMMAND   		    {	{this.command = new UpdateFlightCommand();	    	}	},

    BEFORE_ADD_EMPLOYEE_COMMAND 	 	{   {this.command = new BeforeAddEmployeeCommand(); 	}	},
    ADD_EMPLOYEE_COMMAND 			 	{   {this.command = new AddEmployeeCommand();		    }	},
    GET_ALL_EMPLOYEES_COMMAND 	    	{	{this.command = new GetAllEmployeesCommand();   	}	},
    DELETE_EMPLOYEE_COMMAND			 	{	{this.command = new DeleteEmployeeCommand();	    }	},
    BEFORE_UPDATE_EMPLOYEE_COMMAND     	{	{this.command = new BeforeUpdateEmployeeCommand();	}	},
    UPDATE_EMPLOYEE_COMMAND            	{	{this.command = new UpdateEmployeeCommand();	    }	},

    REGISTER_USER_COMMAND 			 	{   {this.command = new RegisterUserCommand();	    	}	},
    LOGIN_USER_COMMAND 				 	{   {this.command = new LoginUserCommand();		    	}	},
    LOGOUT_USER_COMMAND					{	{this.command = new LogoutUserCommand();	    	}	},
    GET_ALL_USERS_COMMAND				{	{this.command = new GetAllUsersCommand();	    	}	},

    BEFORE_ADD_TEAM_COMMAND 		 	{   {this.command = new BeforeAddTeamCommand();		   	}	},
    ADD_TEAM_COMMAND 				 	{   {this.command = new AddTeamCommand();		    	}	},
    GET_ALL_TEAMS_COMMAND 			 	{	{this.command = new GetAllTeamsCommand();	    	}	},
    DELETE_TEAM_COMMAND				 	{	{this.command = new DeleteTeamCommand();	    	}	},
    BEFORE_UPDATE_TEAM_COMMAND 	    	{	{this.command = new BeforeUpdateTeamCommand();    	}	},
    UPDATE_TEAM_COMMAND 		    	{	{this.command = new UpdateTeamCommand(); 	    	}	},
    SET_TEAM_COMMAND					{	{this.command = new SetTeamCommand();           	}	},
    SAVE_TEAM_TO_FLIGHT_COMMAND	    	{	{this.command = new SaveTeamToFlightCommand();      }	};
    //TODO user commands (block, delete, update)

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}