/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustT
 */
public class TestClockIT {
  
  public TestClockIT() {
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
   * Test of toClock method, of class TestClock.
   */
  @Test
  public void testToClock() {
    System.out.println("toClock");
    TestClock instance = new TestClock();
    instance.toClock();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toSetTime method, of class TestClock.
   */
  @Test
  public void testToSetTime() {
    System.out.println("toSetTime");
    TestClock instance = new TestClock();
    instance.toSetTime();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toSettings method, of class TestClock.
   */
  @Test
  public void testToSettings() {
    System.out.println("toSettings");
    TestClock instance = new TestClock();
    instance.toSettings();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of incrementTime method, of class TestClock.
   */
  @Test
  public void testIncrementTime() {
    System.out.println("incrementTime");
    TestClock instance = new TestClock();
    instance.incrementTime();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setColorScheme method, of class TestClock.
   */
  @Test
  public void testSetColorScheme() {
    System.out.println("setColorScheme");
    TestClock.colorScheme color = null;
    TestClock instance = new TestClock();
    instance.setColorScheme(color);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDigits method, of class TestClock.
   */
  @Test
  public void testGetDigits() {
    System.out.println("getDigits");
    TestClock instance = new TestClock();
    Digit[] expResult = null;
    Digit[] result = instance.getDigits();
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of showDigits method, of class TestClock.
   */
  @Test
  public void testShowDigits() {
    System.out.println("showDigits");
    int d0 = 0;
    int d1 = 0;
    char sep = ' ';
    int d2 = 0;
    int d3 = 0;
    TestClock instance = new TestClock();
    instance.showDigits(d0, d1, sep, d2, d3);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getHours method, of class TestClock.
   */
  @Test
  public void testGetHours() {
    System.out.println("getHours");
    TestClock instance = new TestClock();
    int expResult = 0;
    int result = instance.getHours();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setHours method, of class TestClock.
   */
  @Test
  public void testSetHours() {
    System.out.println("setHours");
    int hour = 0;
    TestClock instance = new TestClock();
    instance.setHours(hour);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMinutes method, of class TestClock.
   */
  @Test
  public void testGetMinutes() {
    System.out.println("getMinutes");
    TestClock instance = new TestClock();
    int expResult = 0;
    int result = instance.getMinutes();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setMinutes method, of class TestClock.
   */
  @Test
  public void testSetMinutes() {
    System.out.println("setMinutes");
    int minutes = 0;
    TestClock instance = new TestClock();
    instance.setMinutes(minutes);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSeconds method, of class TestClock.
   */
  @Test
  public void testGetSeconds() {
    System.out.println("getSeconds");
    TestClock instance = new TestClock();
    int expResult = 0;
    int result = instance.getSeconds();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSeconds method, of class TestClock.
   */
  @Test
  public void testSetSeconds() {
    System.out.println("setSeconds");
    int seconds = 0;
    TestClock instance = new TestClock();
    instance.setSeconds(seconds);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of main method, of class TestClock.
   */
  @Test
  public void testMain() {
    System.out.println("main");
    String[] args = null;
    TestClock.main(args);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
