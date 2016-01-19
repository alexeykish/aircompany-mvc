package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class PlaneValidatorTest {

    Plane validePlane;
    int numberOfPositions;
    Map<Position, Integer> testTeam;

    @Before
    public void setUp() throws Exception {
        validePlane = new Plane();
        validePlane.setModel("testModel");
        validePlane.setCapacity(99999999);
        validePlane.setRange(99999999);
        testTeam = new TreeMap<>();
        numberOfPositions = 100;
        testTeam.put(Position.PILOT, numberOfPositions);
        testTeam.put(Position.NAVIGATOR, numberOfPositions);
        testTeam.put(Position.RADIOOPERATOR, numberOfPositions);
        testTeam.put(Position.STEWARDESS, numberOfPositions);
        validePlane.setTeam(testTeam);

    }

    @Test
    public void testValidate() throws Exception {
        assertNull("Validate method failed", PlaneValidator.validate(validePlane));
        validePlane.setModel(null);
        assertEquals("Validate method failed: model is null", PlaneValidator.validate(validePlane), Message.ERROR_EMPTY);
        validePlane.setModel("testModel");
        validePlane.setCapacity(0);
        assertEquals("Validate method failed: capacity is null", PlaneValidator.validate(validePlane), Message.ERROR_EMPTY);
        validePlane.setCapacity(99999999);
        validePlane.setRange(0);
        assertEquals("Validate method failed: range is null", PlaneValidator.validate(validePlane), Message.ERROR_EMPTY);
        validePlane.setRange(99999999);
        testTeam.put(Position.PILOT, -1);
        validePlane.setTeam(testTeam);
        assertEquals("Validate method failed: number of pilots is null", PlaneValidator.validate(validePlane), Message.ERROR_EMPTY);
        testTeam.put(Position.PILOT, numberOfPositions);
        testTeam.put(Position.NAVIGATOR, -1);
        validePlane.setTeam(testTeam);
        assertEquals("Validate method failed: number of navigators is null", PlaneValidator.validate(validePlane), Message.ERROR_EMPTY);
        testTeam.put(Position.NAVIGATOR, numberOfPositions);
        testTeam.put(Position.RADIOOPERATOR, -1);
        validePlane.setTeam(testTeam);
        assertEquals("Validate method failed: number of radiooperators is null", PlaneValidator.validate(validePlane), Message.ERROR_EMPTY);
        testTeam.put(Position.RADIOOPERATOR, numberOfPositions);
        testTeam.put(Position.STEWARDESS, -1);
        validePlane.setTeam(testTeam);
        assertEquals("Validate method failed: number of stewardess is null", PlaneValidator.validate(validePlane), Message.ERROR_EMPTY);
        assertEquals("Validate method failed: plane is null", PlaneValidator.validate(null), Message.ERROR_EMPTY);
    }
}