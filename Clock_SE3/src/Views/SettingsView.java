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
    int clockSpeed;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public SettingsView(TestClock clock) {
        super(clock);
        clockSpeed = clock.getClockSpeed();
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(int digit, int region){
        
        switch(digit) {
    // Check Digit 0
            case 0: {
                switch(region) {
                    case 0:     //Exit button pressed
                        clock.setClockSpeed(clockSpeed);
                        clock.toClockMenu();
                        return;
                }
            break;
        }
    // Check Digit 1
            case 1: {
                switch(region){
                    case 3:     // Hacker pressed
                        clock.setColorScheme(colors.HACKER);
                        break;
                    case 4:     // Slate pressed
                        clock.setColorScheme(colors.SLATE);
                        break;
                }  
            break;
            }
    // Check Digit 2
            case 3: {
                switch(region) {
                    case 3:     //12-hour pressed
                        clock.twelveHour = !clock.twelveHour;
                        break;
                    case 6:     // If Speed is pressed
                        if(clockSpeed >= 1000) clockSpeed = 1;
                        else clockSpeed *= 10;
                        break;
                }
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
        clock.getDigit(2).setText(0, "Settings");
        clock.getDigit(1).setText(2, "Colours:"); // Auto wraps to new line
        clock.getDigit(3).setText(2, "Clock:");
        clock.getDigit(3).setText(5, "Speed:");
        
        
        
        // Exit button
        clock.getDigit(0).setText(0,"Exit");
        
        // Color Scheme 1 Button
        clock.getDigit(1).setTextAlignment(0);
        clock.getDigit(3).setTextAlignment(0);
        clock.getDigit(1).setText(3, "  Hacker");
        // Color Scheme 2 Button
        clock.getDigit(1).setText(4, "  Slate");
        
        update();
    }
    
    /**
     * Makes minimal necesary changes, does not redisplay entire View
     */
    @Override
    public void update() {
        if(clock.twelveHour) {
            clock.getDigit(3).setText(3, "  12-Hour");
        } else {
            clock.getDigit(3).setText(3, "  24-Hour");
        }
        clock.getDigit(3).setText(6, "  " + clockSpeed + "X");
         
    }
}
