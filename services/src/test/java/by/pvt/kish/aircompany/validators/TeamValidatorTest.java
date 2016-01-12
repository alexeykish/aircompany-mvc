package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.FlightTeam;
import by.pvt.kish.aircompany.enums.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Kish Alexey
 */
public class TeamValidatorTest {
    FlightTeam correctFlightTeam = null;
    FlightTeam emptyTeam1 = null;
    FlightTeam emptyTeam2 = null;
    FlightTeam emptyTeam3 = null;
    FlightTeam emptyTeam4 = null;
    FlightTeam emptyTeam5 = null;
    FlightTeam emptyTeam6 = null;
    FlightTeam emptyTeam7 = null;

    FlightTeam uncorrectTeam1 = null;
    FlightTeam uncorrectTeam2 = null;
    FlightTeam uncorrectTeam3 = null;
    FlightTeam uncorrectTeam4 = null;
    FlightTeam uncorrectTeam5 = null;
    FlightTeam uncorrectTeam6 = null;
    FlightTeam uncorrectTeam7 = null;

    FlightTeam duplicateTeam = null;

    @Before
    public void setUp() throws Exception {
        Employee test = new Employee();
        Employee testPilot = new Employee(0, null, null, Position.PILOT);
        Employee testPilot2 = new Employee(1, null, null, Position.PILOT);
        Employee testNavigator = new Employee(2, null, null, Position.NAVIGATOR);
        Employee testRadiooperator = new Employee(3, null, null, Position.RADIOOPERATOR);
        Employee testStewardess = new Employee(4, null, null, Position.STEWARDESS);
        Employee testStewardess1 = new Employee(5, null, null, Position.STEWARDESS);
        Employee testStewardess2 = new Employee(6, null, null, Position.STEWARDESS);

        correctFlightTeam = new FlightTeam(0 ,null, testPilot,testPilot2,testNavigator,testRadiooperator,testStewardess,testStewardess1,testStewardess2);
        emptyTeam1 = new FlightTeam(0 ,null, null,test,test,test,test,test,test);
        emptyTeam2 = new FlightTeam(0 ,null, test,null,test,test,test,test,test);
        emptyTeam3 = new FlightTeam(0 ,null, test,test,null,test,test,test,test);
        emptyTeam4 = new FlightTeam(0 ,null, test,test,test,null,test,test,test);
        emptyTeam5 = new FlightTeam(0 ,null, test,test,test,test,null,test,test);
        emptyTeam6 = new FlightTeam(0 ,null, test,test,test,test,test,null,test);
        emptyTeam7 = new FlightTeam(0 ,null, test,test,test,test,test,test,null);

        uncorrectTeam1 = new FlightTeam(0, null, test, testPilot2, testNavigator, testRadiooperator, testStewardess, testStewardess1, testStewardess2);
        uncorrectTeam2 = new FlightTeam(0, null, testPilot, test, testNavigator, testRadiooperator, testStewardess, testStewardess1, testStewardess2);
        uncorrectTeam3 = new FlightTeam(0, null, testPilot, testPilot2, test, testRadiooperator, testStewardess, testStewardess1, testStewardess2);
        uncorrectTeam4 = new FlightTeam(0, null, testPilot, testPilot2, testNavigator, test, testStewardess, testStewardess1, testStewardess2);
        uncorrectTeam5 = new FlightTeam(0, null, testPilot, testPilot2, testNavigator, testRadiooperator, test, testStewardess1, testStewardess2);
        uncorrectTeam6 = new FlightTeam(0, null, testPilot, testPilot2, testNavigator, testRadiooperator, testStewardess, test, testStewardess2);
        uncorrectTeam7 = new FlightTeam(0, null, testPilot, testPilot2, testNavigator, testRadiooperator, testStewardess, testStewardess1, test);

        duplicateTeam = new FlightTeam(0, null, testPilot,testPilot, testPilot, testPilot, testPilot, testPilot, testPilot);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValidate() throws Exception {
        assertEquals(TeamValidator.validate(emptyTeam1), Message.ERROR_EMPTY);
        assertEquals(TeamValidator.validate(emptyTeam2), Message.ERROR_EMPTY);
        assertEquals(TeamValidator.validate(emptyTeam3), Message.ERROR_EMPTY);
        assertEquals(TeamValidator.validate(emptyTeam4), Message.ERROR_EMPTY);
        assertEquals(TeamValidator.validate(emptyTeam5), Message.ERROR_EMPTY);
        assertEquals(TeamValidator.validate(emptyTeam6), Message.ERROR_EMPTY);
        assertEquals(TeamValidator.validate(emptyTeam7), Message.ERROR_EMPTY);

        assertEquals(TeamValidator.validate(uncorrectTeam1),Message.ERROR_TEAM_POSITIONS_VALID);
        assertEquals(TeamValidator.validate(uncorrectTeam2),Message.ERROR_TEAM_POSITIONS_VALID);
        assertEquals(TeamValidator.validate(uncorrectTeam3),Message.ERROR_TEAM_POSITIONS_VALID);
        assertEquals(TeamValidator.validate(uncorrectTeam4),Message.ERROR_TEAM_POSITIONS_VALID);
        assertEquals(TeamValidator.validate(uncorrectTeam5),Message.ERROR_TEAM_POSITIONS_VALID);
        assertEquals(TeamValidator.validate(uncorrectTeam6),Message.ERROR_TEAM_POSITIONS_VALID);
        assertEquals(TeamValidator.validate(uncorrectTeam7),Message.ERROR_TEAM_POSITIONS_VALID);

        assertEquals(TeamValidator.validate(duplicateTeam),Message.ERROR_TEAM_VALID);


        assertNull(TeamValidator.validate(correctFlightTeam));
    }
}