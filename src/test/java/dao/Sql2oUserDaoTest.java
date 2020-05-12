package dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private static Connection conn;
    private static Sql2oUserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/tenants_manager_test";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
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
}