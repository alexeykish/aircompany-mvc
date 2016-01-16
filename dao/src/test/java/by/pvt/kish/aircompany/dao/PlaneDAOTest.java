package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.entity.Plane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class PlaneDAOTest {
    private PlaneDAO planeDAO = PlaneDAO.getInstance();
    private int id;
    private Plane testPlane;

    @Before
    public void setUp() throws Exception {
        testPlane = new Plane();
        testPlane.setModel("testModel");
        testPlane.setCapacity(100);
        testPlane.setRange(200);
        //id = planeDAO.add(testPlane);
    }

    @Test
    public void testAdd() throws Exception {
        //Plane addedPlane = planeDAO.getById(id);
//        assertEquals("Add method failed: wrong model", addedPlane.getModel(), testPlane.getModel());
//        assertEquals("Add method failed: wrong capacity", addedPlane.getCapacity(), testPlane.getCapacity());
//        assertEquals("Add method failed: wrong range", addedPlane.getRange(), testPlane.getRange());
    }

    @Test
    public void testUpdate() throws Exception {
        Plane prepareToUpdatePlane = new Plane();
        prepareToUpdatePlane.setPid(id);
        prepareToUpdatePlane.setModel("updatedModel");
        prepareToUpdatePlane.setCapacity(300);
        prepareToUpdatePlane.setRange(400);
        //planeDAO.update(prepareToUpdatePlane);
       // Plane updatedPlane = planeDAO.getById(id);
//        assertEquals("Update method failed: wrong pid", prepareToUpdatePlane.getPid(), updatedPlane.getPid());
//        assertEquals("Update method failed: wrong model", prepareToUpdatePlane.getModel(), updatedPlane.getModel());
//        assertEquals("Update method failed: wrong capacity", prepareToUpdatePlane.getCapacity(), updatedPlane.getCapacity());
//        assertEquals("Update method failed: wrong range", prepareToUpdatePlane.getRange(), updatedPlane.getRange());
    }

    @Test
    public void testGetAll() throws Exception {
//        int beforeAddNumber = planeDAO.getAll().size();
//        int getAllId = planeDAO.add(testPlane);
//        int afterAddNumber = planeDAO.getAll().size();
//        assertEquals("Get all method failed", beforeAddNumber, afterAddNumber-1);
//        planeDAO.delete(getAllId);
    }

    @Test
    public void testDelete() throws Exception {
//        planeDAO.delete(id);
//        assertNull("Delete method: failed",planeDAO.getById(id));
    }

    @After
    public void tearDown() throws Exception {
//        planeDAO.delete(id);

    }
}