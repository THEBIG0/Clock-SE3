package Views;

import Clock.Digit;
import Clock.TestClock;

/**
 * @author Sam
 * 
 * The actual alarm shown when an alarm is going off.
 */
public class TriggeredAlarmView extends ClockView {
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public TriggeredAlarmView(TestClock clock) {
        super(clock);
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(Digit digit, int region) {
        //TODO: Stop alarm if screen is touched, for now.
        //      Hold for snooze can be added after.
        
    }
    
    /**
     * Initialises the View interface
     */
    @Override
    public void show() {
        
        // Clear all text and set alignment
        for(Digit d: clock.getDigits()) {
            d.clearText();
            d.setTextAlignment(0);
            d.setChar(' ');
        }
        clock.getDigits()[0].setChar('a');
        clock.getDigits()[1].setChar('l');
        clock.getDigits()[2].setChar('a');
        clock.getDigits()[3].setChar('r');
        clock.getDigits()[4].setChar('m');
        //clock.setClockSpeed(0);
        
        // TODO: Show day of week during alarm
        
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
       // TODO: toggle between showing the time and ALARM on display
       //       - could use the fact that update() is called once per second
       //       - maybe add a function for playing sound
       //       - stop after certain number of seconds
    }
    
}

