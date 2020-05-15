package dao;

import models.Tenants;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oTenantsDaoTest {

    private static Connection conn;
    private static Sql2oTenantsDao tenantsDao;
    private static Sql2oApartmentDao apartmentDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/tenants_manager_test";
        Sql2o sql2o = new Sql2o(connectionString, "alphania", "2020");
        System.out.println("Connection Initialized");
        tenantsDao=new Sql2oTenantsDao(sql2o);
        apartmentDao=new Sql2oApartmentDao(sql2o);
        conn=sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clear Database");
        tenantsDao.clearAll();
        apartmentDao.clearAll();
    }

    @AfterClass
    public static void shutDown()throws Exception{

        conn.close();
        System.out.println("Connection closed");
    }
    @Test
    public void save_assignsIdToObject() throws Exception{
        Tenants tenants = setupNewTenant();
        tenantsDao.saveTenant(tenants ) ;
        Tenants savedTenant= tenantsDao .getAll().get(0);
        assertEquals(tenants .getId(), savedTenant.getId());
    }
    @Test
    public void getAll_returnsAllInstancesOfTenants_true() throws Exception{
        Tenants tenants = setupNewTenant();
        tenantsDao.saveTenant(tenants) ;
        assertEquals(1, tenantsDao.getAll().size());
    }
    @Test
    public void find_returnsAllTenantsCorrectly()  throws Exception {
        Tenants tenants = setupNewTenant();
        tenantsDao.saveTenant(tenants) ;
        Tenants  foundTenant = tenantsDao.findById(tenants .getId());
        assertEquals(tenants , foundTenant );
    }
    @Test
    public void deleteByIdDeletesCorrectTenant() throws Exception {
        Tenants tenants = setupNewTenant();
        tenantsDao.saveTenant(tenants) ;
        tenantsDao.deleteById(tenants.getId());
        assertEquals(0, tenantsDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Tenants firstTenants = setupNewTenant();
        tenantsDao.saveTenant(firstTenants ) ;
        Tenants secondTenants = setupNewTenant();
        tenantsDao.saveTenant(secondTenants) ;
        int daoSize = tenantsDao.getAll().size();
        tenantsDao.clearAll();
        assertTrue(daoSize > 0 && daoSize > tenantsDao.getAll().size());
    }
    @Test
    public void update()throws Exception {
        Tenants tenants = setupNewTenant();
        tenantsDao.saveTenant(tenants ) ;
        tenantsDao.update(tenants.getId(),"linus","0754321",1,5,1);
        Tenants  getTenant=tenantsDao.findById(tenants .getId());
        assertEquals(getTenant .getId(),tenants .getId());
        assertNotEquals(getTenant .getName(),tenants .getName());
        assertNotEquals(getTenant .getPhone() ,tenants .getPhone() );
        assertNotEquals(getTenant .getRoomNumber() ,tenants .getRoomNumber());
        assertNotEquals(getTenant .getFloor() ,tenants .getFloor());
        assertEquals(getTenant .getApartmentId() ,tenants .getApartmentId() );
    }

    //helper methods
    public Tenants setupNewTenant(){
        return new Tenants ("Rose","0712345",10,3,1) ;
    }

}
