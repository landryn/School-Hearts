/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author orbit
 */
public class UserAuthenticatorTest {

    public UserAuthenticatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class UserAuthenticator.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String username = "user";
        String password = "pass";
        UserAuthenticator instance = new UserAuthenticator();
        instance.addUser(username, password);
        assertTrue(instance.checkUser(username, password));
        instance.clear();
    }

    /**
     * Test of checkUser method, of class UserAuthenticator.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        String username = "dupa";
        String password = "jasio";
        UserAuthenticator instance = new UserAuthenticator();
        boolean expResult = false;
        boolean result = instance.checkUser(username, password);
        assertEquals(expResult, result);
        instance.clear();
    }

    /**
     * Test of delUser method, of class UserAuthenticator.
     */
    @Test
    public void testDelUser() {
        System.out.println("delUser");
        String username = "dupa";
        UserAuthenticator instance = new UserAuthenticator();
        instance.addUser("dupa", "jasio");
        assertTrue(instance.checkUser("dupa", "jasio"));
        assertFalse(instance.checkUser("dupa", "jasio1"));
        assertFalse(instance.checkUser("dupa1", "jasio"));
        instance.delUser(username);
        assertFalse(instance.checkUser("dupa", "jasio"));
        instance.clear();
    }

}