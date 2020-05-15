package dao;

import models.User;
import org.junit.*;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private static Connection conn;
    private static Sql2oUserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/tenants_manager_test";
        Sql2o sql2o = new Sql2o(connectionString, "alphania", "2020");
        System.out.println("Connection Initialized");
        userDao=new Sql2oUserDao(sql2o);
        conn=sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clear Database");
        userDao.clearAll();
    }

    @AfterClass
    public static void shutDown()throws Exception
    {
        conn.close();
        System.out.println("Connection closed");
    }
    @Test
    public void save_assignsIdToObject() throws Exception{
        User user = setupNewUser();
        userDao.save(user);
        User savedUser= userDao.getAll().get(0);
        assertEquals(user.getId(),savedUser.getId());
    }
    @Test
    public void Find_returnsAllTenantsCorrectly()  throws Exception {
        User  user = setupNewUser();
        userDao.save(user);
        User  foundUser = userDao.findById(user.getId());
        assertEquals(user, foundUser);
    }


    @Test
    public void getAll_returnsAllInstancesOfUser_true()  throws Exception {
        User  user = setupNewUser();
        userDao.save(user);
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void noUsersReturnsEmptyList() throws Exception {
        assertEquals(0, userDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectUser() throws Exception {
        User  user = setupNewUser();
        userDao.save(user);
        userDao.deleteById(user.getId());
        assertEquals(0, userDao.getAll().size());

    }
    @Test
    public void clearAllClearsAll() throws Exception {
        User  user = setupNewUser();
        User  otherUser  = setupNewUser();
        userDao.save(user);
        userDao.save(otherUser) ;
        int daoSize = userDao.getAll().size();
        userDao.clearAll();
        assertTrue(daoSize > 0 && daoSize > userDao.getAll().size());
    }
    @Test
    public void update()throws Exception {
        User testUser=new User("linus","linus@gmail.com(opens in new tab)","linus","linus1");
        userDao.save(testUser);
        userDao.update(testUser.getId(),"rose","rose@gmail.com(opens in new tab)","rose","rose");
        User getUser=userDao.findById(testUser.getId());
        assertEquals(getUser.getId(),testUser.getId());
        assertNotEquals(getUser.getName(),testUser.getName());
        assertNotEquals(getUser.getEmail(),testUser.getEmail());
        assertNotEquals(getUser.getUserName(),testUser.getUserName());
        assertNotEquals(getUser.getPassword(),testUser.getPassword());
    }
    //helper methods
    public User setupNewUser(){
        return new User ("Rose","rmogusu123@gmail.com","rmogusu","12345") ;
    }
}
