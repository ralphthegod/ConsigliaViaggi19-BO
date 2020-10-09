/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao.firebase;

import com.cv19.dao.DatabaseCallback;
import com.cv19.dao.models.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafdi
 */
public class UserFirebaseDAOTest {
    
    public UserFirebaseDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserByEmail method, of class UserFirebaseDAO.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "";
        DatabaseCallback callback = null;
        int callbackCode = 0;
        UserFirebaseDAO instance = new UserFirebaseDAO();
        instance.getUserByEmail(email, callback, callbackCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByUsername method, of class UserFirebaseDAO.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("getUserByUsername");
        String username = "";
        DatabaseCallback callback = null;
        int callbackCode = 0;
        UserFirebaseDAO instance = new UserFirebaseDAO();
        instance.getUserByUsername(username, callback, callbackCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByID method, of class UserFirebaseDAO.
     */
    @Test
    public void testGetUserByID() {
        System.out.println("getUserByID");
        String ID = "";
        DatabaseCallback callback = null;
        int callbackCode = 0;
        UserFirebaseDAO instance = new UserFirebaseDAO();
        instance.getUserByID(ID, callback, callbackCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserMetadataByUser method, of class UserFirebaseDAO.
     */
    @Test
    public void testGetUserMetadataByUser() {
        System.out.println("getUserMetadataByUser");
        User user = null;
        DatabaseCallback callback = null;
        int callbackCode = 0;
        UserFirebaseDAO instance = new UserFirebaseDAO();
        instance.getUserMetadataByUser(user, callback, callbackCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUserByUser method, of class UserFirebaseDAO.
     */
    @Test
    public void testDeleteUserByUser() {
        System.out.println("deleteUserByUser");
        User user = null;
        DatabaseCallback callback = null;
        int callbackCode = 0;
        UserFirebaseDAO instance = new UserFirebaseDAO();
        instance.deleteUserByUser(user, callback, callbackCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of blacklistUserByUser method, of class UserFirebaseDAO.
     */
    @Test
    public void testBlacklistUserByUser() {
        System.out.println("blacklistUserByUser");
        User user = null;
        DatabaseCallback callback = null;
        int callbackCode = 0;
        UserFirebaseDAO instance = new UserFirebaseDAO();
        instance.blacklistUserByUser(user, callback, callbackCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
