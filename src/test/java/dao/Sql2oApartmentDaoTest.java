package dao;

import models.BedsitterApartment;
import models.OneBedroomApartment;
import models.Tenants;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oApartmentDaoTest {
    private static Connection conn;
    private static Sql2oTenantsDao tenantsDao;
    private static Sql2oApartmentDao apartmentDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/tenants_manager_test";
        Sql2o sql2o = new Sql2o(connectionString, "linus", "mariano@9496");
        System.out.println("Connection Initialized");
        apartmentDao=new Sql2oApartmentDao(sql2o);
        tenantsDao=new Sql2oTenantsDao(sql2o);
        conn=sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clear Database");
        apartmentDao.clearAll();
        tenantsDao.clearAll();
    }

    @AfterClass
    public static void shutDown()throws Exception
    {
        conn.close();
        System.out.println("Connection closed");
    }
    @Test
    public void save_assignsIdToBedsitter() throws Exception{
        BedsitterApartment  bedsitterApartment = setupNewBedSitterApartment();
        apartmentDao.saveBedSitterApartment(bedsitterApartment) ;
        BedsitterApartment  savedBedsitterApartment= apartmentDao.getAllWithTypeBedsitter(bedsitterApartment.getType()).get(0);
        assertEquals(bedsitterApartment.getId(), savedBedsitterApartment.getId());
    }
    @Test
    public void save_assignsToOneBedroom() throws Exception{
        OneBedroomApartment   oneBedroomApartment = setupNewOneBedroomApartment();
        apartmentDao.saveOneBedroomApartment(oneBedroomApartment );
        OneBedroomApartment   savedOneBedroomApartment= apartmentDao.getAllWithTypeOneBedroom(oneBedroomApartment.getType()).get(0);
        assertEquals(oneBedroomApartment.getId(), savedOneBedroomApartment .getId());
    }
    @Test
    public void update_OneBedroom()throws Exception {
        OneBedroomApartment   oneBedroomApartment = setupNewOneBedroomApartment();
        apartmentDao.saveOneBedroomApartment(oneBedroomApartment ) ;
        apartmentDao.updateOneBedroom("Linus","Nairobi","One Bedroom",4,4,oneBedroomApartment.getId());
        OneBedroomApartment   getOneBedroom=apartmentDao.findOneBedroomById(oneBedroomApartment.getId()) ;
        assertEquals(getOneBedroom .getId(),oneBedroomApartment .getId());
        assertEquals(getOneBedroom .getType() ,oneBedroomApartment .getType());
        assertNotEquals(getOneBedroom .getName(),oneBedroomApartment .getName());
        assertNotEquals(getOneBedroom .getLocation() ,oneBedroomApartment .getLocation() );
        assertNotEquals(getOneBedroom .getNumberOfFloors() ,oneBedroomApartment .getNumberOfFloors() );
        assertNotEquals(getOneBedroom .getNumberOfRooms() ,oneBedroomApartment .getNumberOfRooms() );

    }
    @Test
    public void update_Bedsitter()throws Exception {
        BedsitterApartment  bedsitterApartment = setupNewBedSitterApartment();
        apartmentDao.saveBedSitterApartment( bedsitterApartment ) ;
        apartmentDao.updateBedsitter("Mary","Kitui","Bedsitter",2,4,bedsitterApartment.getId());
        BedsitterApartment getBedsitter=apartmentDao.findBedsitterById(bedsitterApartment.getId());
        assertEquals(getBedsitter.getId(),bedsitterApartment.getId());
        assertEquals(getBedsitter .getType() ,bedsitterApartment .getType());
        assertNotEquals(getBedsitter .getName(),bedsitterApartment .getName());
        assertNotEquals(getBedsitter .getLocation() ,bedsitterApartment .getLocation() );
        assertNotEquals(getBedsitter .getNumberOfFloors() ,bedsitterApartment .getNumberOfFloors() );
        assertNotEquals(getBedsitter .getNumberOfRooms() ,bedsitterApartment .getNumberOfRooms());

    }
    @Test
    public void clearAllClearsAll_oneBedroom() throws Exception {
        OneBedroomApartment   oneBedroomApartment = setupNewOneBedroomApartment();
        apartmentDao.saveOneBedroomApartment(oneBedroomApartment ) ;
        OneBedroomApartment   secondOneBedroomApartment = setupNewOneBedroomApartment();
        apartmentDao.saveOneBedroomApartment(secondOneBedroomApartment ) ;
        int daoSize = apartmentDao.getAllWithTypeOneBedroom(oneBedroomApartment.getType()).size();
        apartmentDao.clearAll();
        assertTrue(daoSize > 0 && daoSize > apartmentDao.getAllWithTypeOneBedroom(oneBedroomApartment.getType()).size());
    }
    @Test
    public void clearAllClearsAll_bedsitter() throws Exception {
        BedsitterApartment  bedsitterApartment = setupNewBedSitterApartment() ;
        apartmentDao.saveBedSitterApartment( bedsitterApartment ) ;
        BedsitterApartment  secondBedsitterApartment = setupNewBedSitterApartment();
        apartmentDao.saveBedSitterApartment( secondBedsitterApartment ) ;
        int daoSize = apartmentDao.getAllWithTypeBedsitter(bedsitterApartment.getType()).size();
        apartmentDao.clearAll();
        assertTrue(daoSize > 0 && daoSize > apartmentDao.getAllWithTypeBedsitter(bedsitterApartment.getType()).size());
    }
    @Test
    public void deleteByIdDeletesCorrect_Bedsitter() throws Exception {
        BedsitterApartment  bedsitterApartment = setupNewBedSitterApartment() ;
        apartmentDao.saveBedSitterApartment(bedsitterApartment) ;
        apartmentDao.deleteByBedsitterId(bedsitterApartment.getId());
        assertEquals(0, apartmentDao.getAllWithTypeBedsitter(bedsitterApartment.getType()).size());
    }
    @Test
    public void deleteByIdDeletesCorrect_oneBedroom() throws Exception {
        OneBedroomApartment   oneBedroomApartment = setupNewOneBedroomApartment();
        apartmentDao.saveOneBedroomApartment(oneBedroomApartment ) ;
        apartmentDao.deleteByOneBedroomId(oneBedroomApartment.getId());
        assertEquals(0, apartmentDao.getAllWithTypeOneBedroom(oneBedroomApartment.getType()).size());
    }
    @Test
    public void Delete_OneBedroom() throws Exception {
        OneBedroomApartment   oneBedroomApartment = setupNewOneBedroomApartment();
        apartmentDao.saveOneBedroomApartment(oneBedroomApartment ) ;
        OneBedroomApartment   secondOneBedroomApartment = setupNewOneBedroomApartment();
        apartmentDao.saveOneBedroomApartment(secondOneBedroomApartment ) ;
        int daoSize = apartmentDao.getAllWithTypeOneBedroom(oneBedroomApartment.getType()).size();
        apartmentDao.deleteOneBedroom(oneBedroomApartment.getType());
        assertTrue(daoSize > 0 && daoSize > apartmentDao.getAllWithTypeOneBedroom(oneBedroomApartment.getType()).size());
    }
    @Test
    public void delete_bedsitter() throws Exception {
        BedsitterApartment  bedsitterApartment = setupNewBedSitterApartment() ;
        apartmentDao.saveBedSitterApartment( bedsitterApartment ) ;
        BedsitterApartment  secondBedsitterApartment = setupNewBedSitterApartment();
        apartmentDao.saveBedSitterApartment( secondBedsitterApartment ) ;
        int daoSize = apartmentDao.getAllWithTypeBedsitter(bedsitterApartment.getType()).size();
        apartmentDao.deleteBedsitter(bedsitterApartment.getType());
        assertTrue(daoSize > 0 && daoSize > apartmentDao.getAllWithTypeBedsitter(bedsitterApartment.getType()).size());
    }


    public BedsitterApartment setupNewBedSitterApartment(){
        return new BedsitterApartment ("Mogusu","Kibera",4,7) ;
    }
    public OneBedroomApartment setupNewOneBedroomApartment(){
        return new OneBedroomApartment ("Rose","Gong",2,8) ;
    }
}