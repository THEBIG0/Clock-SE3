/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Clock.*;

/**
 * @author Lachlan
 */

public class SettingsView extends ClockView {
    TestClock.colorScheme colors;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public SettingsView(TestClock clock) {
        super(clock);
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(Digit digit, int region){
        
    // Check Digit 0
        if(digit == clock.getDigits()[0]){
            
            switch(region) {
                //Exit button pressed
                case 0:
                    clock.toClockMenu();
                    return;
            }
        }
    // Check Digit 1
        else if(digit == clock.getDigits()[1]){
            switch(region){
                // Hacker pressed
                case 3:
                    clock.setColorScheme(colors.HACKER);
                    break;
                // Slate pressed
                case 4: 
                    clock.setColorScheme(colors.SLATE);
                    break;
            }   
        }
        update();
    }
    
    /**
     * Initialises the View interface
     */
    @Override
    public void show() {
        
        // Clear all digits
        for(Digit d: clock.getDigits()) {
            d.clearText();
            d.setChar(' ');
        }
        
        // Add title text
        clock.getDigits()[2].setText(0, "Settings");
        clock.getDigits()[1].setText(2, "Colours:"); // Auto wraps to new line
        
        // Exit button
        clock.getDigits()[0].setText(0,"Exit");
        
        // Color Scheme 1 Button
        clock.getDigits()[1].setTextAlignment(0);
        clock.getDigits()[1].setText(3, "  Hacker");
        // Color Scheme 2 Button
        clock.getDigits()[1].setText(4, "  Slate");
        update();
    }
    
    /**
     * Makes minimal necesary changes, does not redisplay entire View
     */
    @Override
    public void update() {
        
    }
}
