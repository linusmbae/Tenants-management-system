package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewUserObjectGetsCorrectlyCreated_true() throws Exception {
        User  user  = setupNewUser();
        assertEquals(true, user instanceof User );
    }
    @Test
    public void User_InstantiatesWithName_true() throws Exception {
        User user  = setupNewUser();
        assertEquals("Rose", user.getName());
    }
    @Test
    public void User_InstantiatesWithEmail_true() throws Exception {
        User user  = setupNewUser();
        assertEquals("rmogusu123@gmail.com", user.getEmail());
    }

    @Test
    public void User_InstantiatesWithUserName_true() throws Exception {
        User user  = setupNewUser();
        assertEquals("rmogusu", user.getUserName());
    }
    @Test
    public void User_InstantiatesWithPassword_true() throws Exception {
        User user  = setupNewUser();
        assertEquals("12345", user.getPassword());
    }
    //helper methods
    public User setupNewUser(){
        return new User ("Rose","rmogusu123@gmail.com","rmogusu","12345") ;
    }
}