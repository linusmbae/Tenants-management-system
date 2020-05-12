package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BedsitterApartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewBedSitterObjectGetsCorrectlyCreated_true() throws Exception {
        BedsitterApartment bedsitterApartment  = setupNewBedroomApartment() ;
        assertEquals(true, bedsitterApartment   instanceof BedsitterApartment    );
    }
    @Test
    public void BedsitterApartment_InstantiatesWithName_true() throws Exception {
        BedsitterApartment bedsitterApartment = setupNewBedroomApartment() ;
        assertEquals("Mogusu", bedsitterApartment .getName());
    }
    @Test
    public void BedsitterApartment_InstantiatesWithLocation_true() throws Exception {
        BedsitterApartment bedsitterApartment = setupNewBedroomApartment() ;
        assertEquals("Kibera", bedsitterApartment .getLocation());
    }
    @Test
    public void BedsitterApartment_InstantiatesWithNumberOfRooms_true() throws Exception {
        BedsitterApartment bedsitterApartment = setupNewBedroomApartment() ;
        assertEquals(4, bedsitterApartment .getNumberOfRooms());
    }
    //helper methods
    public BedsitterApartment  setupNewBedroomApartment(){
        return new BedsitterApartment ("Mogusu","Kibera",4,7) ;
    }
}