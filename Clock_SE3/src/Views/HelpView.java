package Views;

import Clock.Digit;
import Clock.TestClock;

/**
 * @author Sam
 */
public class HelpView extends ClockMenuView {
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public HelpView(TestClock clock) {
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
        clock.toClockMenu();
    }
    
    /**
     * Initialises the View interface
     */
    
    @Override
    public void show() {
        
        // show parent interface
        super.show();
        
        clock.getDigits()[2].setChar(' ');
        
        clock.getDigits()[2].setColorSpecific(0, 8);
        
        // Show buttons
        clock.getDigits()[0].setText(0, "Open Settings" );
        //clock.getDigits()[2].setText(4, "Hold to set time");

        // Update the time and day using parent update method
        update();
    }
}

