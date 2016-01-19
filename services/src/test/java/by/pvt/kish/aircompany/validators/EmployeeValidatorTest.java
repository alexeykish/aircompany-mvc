package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.enums.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class EmployeeValidatorTest {

    Employee validEmployee;
    Employee invalidEmployee;

    @Before
    public void setUp() throws Exception {
        validEmployee = new Employee();
        validEmployee.setFirstName("testfirstname");
        validEmployee.setLastName("testlastname");
        validEmployee.setPosition(Position.PILOT);

    }

    @Test
    public void testValidate() throws Exception {
        assertNull("Validate method failed", EmployeeValidator.validate(validEmployee));
        assertEquals("Validate method failed: employee is null", EmployeeValidator.validate(invalidEmployee), Message.ERROR_EMPTY);
        invalidEmployee = new Employee();
        invalidEmployee.setLastName("testlastname");
        invalidEmployee.setPosition(Position.PILOT);
        assertEquals("Validate method failed: firstName is null", EmployeeValidator.validate(invalidEmployee), Message.ERROR_EMPTY);
        invalidEmployee.setFirstName("testfirstname");
        invalidEmployee.setLastName(null);
        assertEquals("Validate method failed", EmployeeValidator.validate(invalidEmployee), Message.ERROR_EMPTY);
        invalidEmployee.setLastName("testlastname: lastName is null");
        invalidEmployee.setPosition(null);
        assertEquals("Validate method failed: position is null", EmployeeValidator.validate(invalidEmployee), Message.ERROR_EMPTY);

    }

    @After
    public void tearDown() throws Exception {

    }
}