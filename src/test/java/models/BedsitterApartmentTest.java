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
    @Test
    public void BedsitterApartment_InstantiatesWithNumberOfFloors_true() throws Exception {
        BedsitterApartment bedsitterApartment = setupNewBedroomApartment() ;
        assertEquals(7, bedsitterApartment .getNumberOfFloors());
    }
    @Test
    public void BedSitterApartment_SetsNameCorrectly_true() throws Exception {
        BedsitterApartment bedsitterApartment  = setupNewBedroomApartment();
        bedsitterApartment.setName("Linus") ;
        assertNotEquals("Mogusu", bedsitterApartment.getName());
    }
    @Test
    public void BedSitterApartment_SetsLocationCorrectly_true() throws Exception {
        BedsitterApartment bedsitterApartment  = setupNewBedroomApartment();
        bedsitterApartment.setLocation("Nairobi") ;
        assertNotEquals("Kibera", bedsitterApartment.getLocation() );
    }
    @Test
    public void BedSitterApartment_SetsNumberOfRoomsCorrectly_true() throws Exception {
        BedsitterApartment bedsitterApartment  = setupNewBedroomApartment();
        bedsitterApartment.setNumberOfRooms(6) ;
        assertNotEquals(4, bedsitterApartment.getNumberOfRooms());
    }
    @Test
    public void BedSitterApartment_SetsNumberOfFloorsCorrectly_true() throws Exception {
        BedsitterApartment bedsitterApartment  = setupNewBedroomApartment();
        bedsitterApartment.setNumberOfFloors(8) ;
        assertNotEquals(7, bedsitterApartment.getNumberOfFloors());
    }
    //helper methods
    public BedsitterApartment  setupNewBedroomApartment(){
        return new BedsitterApartment ("Mogusu","Kibera",4,7) ;
    }
}