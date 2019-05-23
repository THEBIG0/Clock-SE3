package Views;

import Clock.*;
import bgi.*;

/**
 * @author Sam
 */
public class ClockMenuView extends ClockStandbyView {

    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public ClockMenuView(TestClock clock) {
        super(clock);
        this.clock = clock;
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(Digit digit, int region) {
        
    // Check Digit 0
        if(digit == clock.getDigits()[0]) {
            switch(region) {
                case 0:
                    clock.toSettings();
                    return;
            }
        }
    // Check Digit 2
        else if(digit == clock.getDigits()[2] && region == 0) {
            clock.toSetTime();
            return;
        }
    // Check Digit 4
        else if(digit == clock.getDigits()[4] && region == 0) {
            clock.toHelp();
            return;
        }

        clock.toClockStandby();
    }
    
    /**
     * Initialises the View interface
     */
    @Override
    public void show() {
        // show parent interface
        super.show();

        // Show buttons
        clock.getDigits()[0].setText(0, "Settings" );
        clock.getDigits()[2].setText(0, "Set Time" );

        // Show help button
        clock.getDigits()[4].setText(0, "?");
        
        // Update the time and day using parent update method
        update();
    }
}

