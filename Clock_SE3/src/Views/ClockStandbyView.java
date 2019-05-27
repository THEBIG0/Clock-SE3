package Views;

import Clock.Digit;
import Clock.TestClock;
import bgi.*;

/**
 * @author Sam
 */
public class ClockStandbyView extends ClockView {
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public ClockStandbyView(TestClock clock) {
        super(clock);
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(int digit, int region) {
        clock.toClockMenu();
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
        
        // Update the time and day
        update();
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        
        // show time on digit display
        showTime(clock.getTime());
        
        // show weekday on correct digit
        clock.getDigit(0).setText(10, DAYS[clock.getWeekDay()]);
        
        char sep = clock.getTime().getSecond()%2==0 ? ' ' : ':';
        
        showSeparator(sep);
    }
    
}

