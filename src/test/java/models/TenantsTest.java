package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TenantsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewTenantsObjectGetsCorrectlyCreated_true() throws Exception {
        Tenants tenants  = setupNewTenant();
        assertEquals(true, tenants  instanceof Tenants  );
    }
    @Test
    public void User_InstantiatesWithName_true() throws Exception {
        Tenants tenants  = setupNewTenant();
        assertEquals("Rose", tenants .getName());
    }
    //helper methods
    public Tenants setupNewTenant(){
        return new Tenants ("Rose","0712345",10,3,1) ;
    }
}