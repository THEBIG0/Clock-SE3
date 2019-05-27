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
import java.time.LocalTime;

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
   * Test of toClockStandby method, of class TestClock.
   */
  @Test
  public void testToClockStandBy() {
    System.out.println("toClockStandby");
    TestClock instance = new TestClock();
    instance.toClockStandby();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
  /**
   * Test of toClockMenu method, of class TestClock.
   */
  @Test
  public void testToClockMenu() {
    System.out.println("toClockMenu");
    TestClock instance = new TestClock();
    instance.toClockMenu();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
  /**
   * Test of toHelp method, of class TestClock.
   */
  @Test
  public void testToHelp() {
    System.out.println("toHelp");
    TestClock instance = new TestClock();
    instance.toHelp();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
    /**
   * Test of toClockStandby method, of class TestClock.
   */
  @Test
  public void testToClockStandby() {
    System.out.println("toClockStandby");
    TestClock instance = new TestClock();
    instance.toClockStandby();
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
    TestClock instance = new TestClock();
    instance.setColorScheme(instance.getColorScheme().BLACKWHITE);
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
   * Test of getTime method, of class TestClock.
   */
  @Test
  public void testGetTime() {
    System.out.println("getTime");
    TestClock instance = new TestClock();
    LocalTime expResult = null;
    LocalTime result = instance.getTime();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setTime method, of class TestClock.
   */
  @Test
  public void testSetTime() {
    System.out.println("setTime");
    LocalTime time = null;
    TestClock instance = new TestClock();
    instance.setTime(time);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSetWeekDay method, of class TestClock.
   */
  @Test
  public void testSetWeekDay() {
    System.out.println("setWeekDay");
    int day = 0;
    TestClock instance = new TestClock();
    instance.setWeekDay(day);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setTime method, of class TestClock.
   */
  @Test
  public void testGetWeekDay() {
    System.out.println("getWeekDay");
    TestClock instance = new TestClock();
    instance.getWeekDay();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setTime method, of class TestClock.
   */
  @Test
  public void testSetClockSpeed() {
    System.out.println("setClockSpeed");
    TestClock instance = new TestClock();
    int speed = 0;
    instance.setClockSpeed(speed);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setTime method, of class TestClock.
   */
  @Test  
  public void testGetClockSpeed() {
    System.out.println("getClockSpeed");
    TestClock instance = new TestClock();
    instance.getClockSpeed();
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
