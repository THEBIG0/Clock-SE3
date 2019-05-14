package Views;

import Clock.Digit;
import Clock.TestClock;

/**
 * @author Sam
 */
public class HelpView extends PageView {
    TestClock clock;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public HelpView(TestClock clock) {
        this.clock = clock;
    }
    
    /**
     * Handles touch events directed at this View
     * @param touch the touch event to parse.
     */
    @Override
    public void touched(bgi.TouchEvent touch) {
        clock.toClock();
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
        }
        clock.getDigits()[2].setChar(' ');
        
        clock.getDigits()[2].setColorSpecific(0, 8);
        
//        clock.getDigits()[2].set
        
        // Show buttons
        clock.getDigits()[0].setText(0, "Open Settings" );
        //clock.getDigits()[2].setText(4, "Hold to set time");

        // Update the time and day
        update();
    }

    
    /**
     * Makes minimal necesary changes, does not redisplay entire View
     */
    @Override()
    public void update() {
        
    }
    
    final String[] days = {
    "Sunday",
    "Monday",    "Tuesday", 
    "Wednesday", "Thursday", 
    "Friday",    "Saturday",
    };
}

