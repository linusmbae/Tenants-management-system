package dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.sql2o.Connection;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oApartmentDaoTest {


    private static Connection conn;
    private static Sql2oApartmentDao apartmentDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/user_test";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        System.out.println("Connection Initialized");
        apartmentDao=new Sql2oApartmentDao(sql2o);
        conn=sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clear Database");
        apartmentDao.clearAll();
    }

    @AfterClass
    public static void shutDown()throws Exception
    {
        conn.close();
        System.out.println("Connection closed");
    }
}