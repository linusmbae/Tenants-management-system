package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IssuesTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewIssuesObjectGetsCorrectlyCreated_true() throws Exception {
        Issues issues  = setupNewIssues();
        assertEquals(true, issues  instanceof Issues  );
    }
    @Test
    public void Issues_InstantiatesWithType_true() throws Exception {
        Issues issues = setupNewIssues() ;
        assertEquals("Large", issues .getType() );
    }
    @Test
    public void Issues_InstantiatesWithContent_true() throws Exception {
        Issues issues = setupNewIssues() ;
        assertEquals("Big", issues .getContent());
    }
    @Test
    public void Issues_InstantiatesWithApartmentId_true() throws Exception {
        Issues issues = setupNewIssues() ;
        assertEquals(1, issues .getApartmentId());
    }
    @Test
    public void Issues_InstantiatesWithRoomId_true() throws Exception {
        Issues issues = setupNewIssues() ;
        assertEquals(1, issues .getRoomId());
    }
    @Test
    public void Issues_SetsTypesCorrectly_true() throws Exception {
        Issues issues  = setupNewIssues();
        issues.setType("Big") ;
        assertNotEquals("large", issues .getType());
    }
    @Test
    public void Issues_SetsContentCorrectly_true() throws Exception {
        Issues issues  = setupNewIssues();
        issues.setContent("Small") ;
        assertNotEquals("Big", issues .getContent());
    }

    @Test
    public void Issues_SetsApartmentIdCorrectly_true() throws Exception {
        Issues issues = setupNewIssues();
        issues.setApartmentId(2) ;
        assertNotEquals(1, issues.getApartmentId());
    }

    @Test
    public void Issues_SetsRoomIdCorrectly_true() throws Exception {
        Issues issues = setupNewIssues();
        issues.setRoomId(2) ;
        assertNotEquals(1, issues.getRoomId());
    }
        //helper methods
    public Issues  setupNewIssues(){
        return new Issues ("Large","Big",1,1) ;
    }
}