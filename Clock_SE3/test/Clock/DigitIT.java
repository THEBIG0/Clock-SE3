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

  
  ////Commented out for now. Need to check class methods and establish expected input/outputs. 
//  /**
//   * Test of setText method, of class Digit.
//   */
//  @Test
//  public void testSetText_StringArr() {
//    System.out.println("setText");
//    String[] text = null;
//    
//    Digit instance = new Digit();
//    //instance.setText(text);
//    if (1==1)
//    {
//      System.out.println("Need to add proper test for this. why is it a string[]");
//    }
//    {fail("setTest failed.");}
//  }
//
//  /**
//   * Test of getText method, of class Digit.
//   */
//  @Test
//  public void testGetText() {
//    System.out.println("getText");
//    Digit instance = new Digit();
//    String[] expResult = null;
//    String[] result = instance.getText();
//    assertArrayEquals(expResult, result);
//    if (expResult == result)
//    {
//      System.out.println("getText() passed");
//    }
//    // TODO review the generated test code and remove the default call to fail.
//    else{ fail("getText() failed");}
//  }
//
//  /**
//   * Test of setTextAlignment method, of class Digit.
//   */
//  @Test
//  public void testSetTextAlignment() {
//    System.out.println("setTextAlignment");
//    int i = 0;
//    Digit instance = new Digit();
//    instance.setTextAlignment(i);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getTextAlignment method, of class Digit.
//   */
//  @Test
//  public void testGetTextAlignment() {
//    System.out.println("getTextAlignment");
//    Digit instance = new Digit();
//    int expResult = 0;
//    int result = instance.getTextAlignment();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of addTouchListener method, of class Digit.
//   */
//  @Test
//  public void testAddTouchListener() {
//    System.out.println("addTouchListener");
//    TouchListener touchListener = null;
//    Digit instance = new Digit();
//    instance.addTouchListener(touchListener);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of removeTouchListener method, of class Digit.
//   */
//  @Test
//  public void testRemoveTouchListener() {
//    System.out.println("removeTouchListener");
//    TouchListener touchListener = null;
//    Digit instance = new Digit();
//    instance.removeTouchListener(touchListener);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of setText method, of class Digit.
//   */
//  @Test
//  public void testSetText_int_String() {
//    System.out.println("setText");
//    int index = 0;
//    String text = "";
//    Digit instance = new Digit();
//    instance.setText(index, text);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of clearText method, of class Digit.
//   */
//  @Test
//  public void testClearText() {
//    System.out.println("clearText");
//    Digit instance = new Digit();
//    instance.clearText();
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of setChar method, of class Digit.
//   */
//  @Test
//  public void testSetChar() {
//    System.out.println("setChar");
//    char in = ' ';
//    Digit instance = new Digit();
//    instance.setChar(in);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getRows method, of class Digit.
//   */
//  @Test
//  public void testGetRows() {
//    System.out.println("getRows");
//    Digit instance = new Digit();
//    int expResult = 0;
//    int result = instance.getRows();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of setColor method, of class Digit.
//   */
//  @Test
//  public void testSetColor() {
//    System.out.println("setColor");
//    TestClock.colorScheme color = null;
//    Digit instance = new Digit();
//    instance.setColor(color);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
  
}
