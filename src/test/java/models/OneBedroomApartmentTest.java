package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OneBedroomApartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewOneBedroomApartmentsObjectGetsCorrectlyCreated_true() throws Exception {
        OneBedroomApartment oneBedroomApartment  = setupNewOneBedroomApartment();
        assertEquals(true, oneBedroomApartment   instanceof OneBedroomApartment   );
    }
    @Test
    public void OneBedroomApartments_InstantiatesWithName_true() throws Exception {
        OneBedroomApartment oneBedroomApartment  = setupNewOneBedroomApartment();
        assertEquals("Rose", oneBedroomApartment.getName());
    }
    @Test
    public void OneBedroomApartments_InstantiatesWithLocation_true() throws Exception {
        OneBedroomApartment oneBedroomApartment  = setupNewOneBedroomApartment();
        assertEquals("Gong", oneBedroomApartment.getLocation());
    }

    @Test
    public void OneBedroomApartments_InstantiatesWithNumberOfRooms_true() throws Exception {
        OneBedroomApartment oneBedroomApartment  = setupNewOneBedroomApartment();
        assertEquals(2, oneBedroomApartment.getNumberOfRooms());
    }
    @Test
    public void OneBedroomApartments_InstantiatesWithNumberOfFloors_true() throws Exception {
        OneBedroomApartment oneBedroomApartment  = setupNewOneBedroomApartment();
        assertEquals(8, oneBedroomApartment.getNumberOfFloors());
    }
    @Test
    public void OneBedroomApartments_SetsNumberOfRoomsCorrectly_true() throws Exception {
        OneBedroomApartment oneBedroomApartment  = setupNewOneBedroomApartment();
        oneBedroomApartment .setName("Mary") ;
        assertNotEquals("Rose", oneBedroomApartment.getName());
    }
    @Test
    public void OneBedroomApartments_SetsLocationCorrectly_true() throws Exception {
        OneBedroomApartment oneBedroomApartment  = setupNewOneBedroomApartment();
        oneBedroomApartment .setLocation("Kisumu") ;
        assertNotEquals("Gong", oneBedroomApartment.getLocation());
    }
    //helper methods
    public OneBedroomApartment  setupNewOneBedroomApartment(){
        return new OneBedroomApartment ("Rose","Gong",2,8) ;
    }
}