package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.entity.Flight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class FlightStatusValidatorTest {


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testUpdateFlightsStatus() throws Exception {
        FlightStatusValidator.updateFlightsStatus();
    }

    @After
    public void tearDown() throws Exception {

    }


}