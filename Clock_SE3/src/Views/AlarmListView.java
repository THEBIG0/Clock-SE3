package Views;

import Clock.Digit;
import Clock.TestClock;
import bgi.*;

/**
 * @author Sam
 * 
 * Shows a list of each alarm that is set: shows time and days for each alarm
 * TODO: implement display, implement touch
 * 
 */
public class AlarmListView extends ClockView {
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public AlarmListView(TestClock clock) {
        super(clock);
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(Digit digit, int region) {
        //TODO: Add exit button
        //TODO: go to Set alarm when alarm is touched
    }
    
    /**
     * Initialises the View interface
     */
    
    @Override
    public void show() {
        
        // Clear all text and set alignment
        for(Digit d: clock.getDigits()) {
            d.clearText();
            d.setTextAlignment(1);
            d.setChar(' ');
        }
        
        //TODO: display list interface
        
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        
    }
    
}

