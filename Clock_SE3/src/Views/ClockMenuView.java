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
    public void touched(int digit, int region) {
        
    switch(digit) {
    // Check digit 0    
        case 0:
            switch(region) {
                case 0:
                    clock.toSettings();
                    return;
            }
            break;
    // Check digit 2
        case 2:
            switch(region) {
                case 0:
                    clock.toSetTime();
                    return;                    
                case 10:
                    clock.toAlarmList();
                    return;
            }
            break;
    // Check digit 4
        case 4:
            switch(region) {
                case 0:
                    clock.toHelp();
                    return; 
            }
        default:
            clock.toClockStandby();
    }
}
    
    /**
     * Initialises the View interface
     */
    @Override
    public void show() {
        // show parent interface
        super.show();

        // Show buttons
        clock.getDigit(0).setText(0, "Settings" );
        clock.getDigit(2).setText(0, "Set Time" );

        // Show help button
        clock.getDigit(4).setText(0, "?");
        
        clock.getDigit(2).setText(10, "Set Alarm");
        
        
        // Update the time and day using parent update method
        update();
    }
}

