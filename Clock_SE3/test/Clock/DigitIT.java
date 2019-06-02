/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clock;

import bgi.TouchListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martinimonis
 */
public class DigitIT {
  
  public DigitIT() {
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
   * Test of setDigit method, of class Digit.
   */
  @Test
  public void testSetDigit() {
    System.out.println("setDigit");
   // int i = 0;
    Digit instance = new Digit();
    //instance.setDigit(i);
    for (int i=0; i<10; i++)
    {
      instance.setDigit(i);
    if (instance.getDigit()==i)
    {
     System.out.println("Digit "+i +" works.");
    }
    else 
    {fail("Digit "+i+ " failed");}
    }
    }
  

  /**
   * Test of getDigit method, of class Digit.
   */
  @Test 
  public void testGetDigit() {
    System.out.println("getDigit");
    Digit instance = new Digit();
    int expResult = 0;
    int result = instance.getDigit();
    //assertEquals(expResult, result);
    if (expResult == result)
    {
      System.out.println("getDigit() passed.");
    }
    else {fail("getDigit() failed. Expected ="+expResult+" Obtained ="+result );}
  }

  
 
  
}
