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
    public void User_InstantiatesWithName_true() throws Exception {
        Issues issues = setupNewIssues() ;
        assertEquals("Large", issues .getType() );
    }
    //helper methods
    public Issues  setupNewIssues(){
        return new Issues ("Large","Big",1,1) ;
    }
}