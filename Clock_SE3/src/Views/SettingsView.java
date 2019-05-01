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

public class SettingsView extends PageView {
    
    private TestClock clock;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public SettingsView(TestClock clock) {
        this.clock = clock;
    }
    
    /**
     * Handles touch events directed at this View
     * @param touch the touch event to parse.
     */
    @Override
    public void touched(bgi.TouchEvent touch){
        int region = touch.getTouched();
        Digit touched = (Digit) touch.getSource();
        
        /**
         * TODO: Add exit button, Add settings toggle thing
         */
    // Check Digit 0
        if(touched == clock.getDigits()[0]){
            
            switch(region) {
                //Exit button pressed
                case 0:
                    clock.toClock();
                    return;
            }
        }
    // Check Digit 1
        else if(touched == clock.getDigits()[1]){
            switch(region){
                // Hacker pressed
                case 3:
                    clock.setColorScheme(clock.colors.BLACKGREEN);
                    break;
                // Slate pressed
                case 4: 
                    clock.setColorScheme(clock.colors.WHITEBLACK);
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
