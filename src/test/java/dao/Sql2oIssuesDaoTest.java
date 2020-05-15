package dao;

import models.BedsitterApartment;
import models.Issues;
import models.Tenants;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;


public class Sql2oIssuesDaoTest {
    private static Connection conn;
    private static Sql2oIssuesDao issuesDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/tenants_manager_test";
        Sql2o sql2o = new Sql2o(connectionString, "alphania", "2020");
        System.out.println("Connection Initialized");
        issuesDao = new Sql2oIssuesDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clear Database");
        issuesDao.clearAll();

    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("Connection closed");
    }

    @Test
    public void save_assignsIdToObject() throws Exception {
        Issues issues = setupNewIssues();
        issuesDao.saveIssue(issues);
        Issues savedIssues = issuesDao.getAll().get(0);
        assertEquals(issues.getId(), savedIssues.getId());
    }

    @Test
    public void Find_returnsAllTenantsIssuesCorrectly() throws Exception {
        Issues issues = setupNewIssues();
        issuesDao.saveIssue(issues);
        Issues foundIssues = issuesDao.findById(issues.getId());
        assertEquals(issues, foundIssues);
    }

    @Test
    public void getAll_returnsAllInstancesOfIssues_true() throws Exception {
        Issues issues = setupNewIssues();
        issuesDao.saveIssue(issues);
        assertEquals(1, issuesDao.getAll().size());
    }

    @Test
    public void noIssuesReturnsEmptyList() throws Exception {
        assertEquals(0, issuesDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectIssues() throws Exception {
        Issues issues = setupNewIssues();
        issuesDao.saveIssue(issues);
        issuesDao.deleteById(issues.getId());
        assertEquals(0, issuesDao.getAll().size());

    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Issues issues = setupNewIssues();
        issuesDao.saveIssue(issues);
        Issues otherIssues = setupNewIssues();
        issuesDao.saveIssue(otherIssues);
        int daoSize = issuesDao.getAll().size();
        issuesDao.clearAll();
        assertTrue(daoSize > 0 && daoSize > issuesDao.getAll().size());
    }

    @Test
    public void update() throws Exception {
        Issues testIssues = new Issues("Large", "Big", 1, 1);
        issuesDao.saveIssue(testIssues);
        issuesDao.update(testIssues.getId(), "Cool", "Small", 2, 2);
        Issues getIssues = issuesDao.findById(testIssues.getId());
        assertEquals(getIssues.getId(), testIssues.getId());
        assertNotEquals(getIssues.getType(), testIssues.getType());
        assertNotEquals(getIssues.getContent(), testIssues.getApartmentId());
        assertNotEquals(getIssues.getApartmentId(), testIssues.getRoomId());
        assertNotEquals(getIssues.getRoomId(), testIssues.getRoomId());
    }

    public Issues setupNewIssues() {
        return new Issues("Large", "Big", 1, 1);
    }
}