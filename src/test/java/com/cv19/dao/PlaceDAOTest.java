/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao;

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
public class PlaceDAOTest {
    
    public PlaceDAOTest() {
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
     * Test of getPlaceByID method, of class PlaceDAO.
     */
    @Test
    public void testGetPlaceByID_3args() {
        System.out.println("getPlaceByID");
        String id = "";
        DatabaseCallback callback = null;
        int callbackCode = 0;
        PlaceDAO instance = new PlaceDAOImpl();
        instance.getPlaceByID(id, callback, callbackCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlaceByID method, of class PlaceDAO.
     */
    @Test
    public void testGetPlaceByID_4args() {
        System.out.println("getPlaceByID");
        String id = "";
        int pos = 0;
        DatabaseCallback callback = null;
        int callbackCode = 0;
        PlaceDAO instance = new PlaceDAOImpl();
        instance.getPlaceByID(id, pos, callback, callbackCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PlaceDAOImpl implements PlaceDAO {

        public void getPlaceByID(String id, DatabaseCallback callback, int callbackCode) {
        }

        public void getPlaceByID(String id, int pos, DatabaseCallback callback, int callbackCode) {
        }
    }
    
}
