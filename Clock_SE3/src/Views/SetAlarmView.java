package Views;

import Clock.*;
import java.time.LocalTime;

/**
 * @author Sam
 */
public class SetAlarmView extends SetTimeView {
    
    Alarm alarm;
    boolean[] days = new boolean[7];
    LocalTime alarmTime;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public SetAlarmView(TestClock clock, Alarm a) {
        super(clock);
        alarmTime = alarm.time;
        this.alarm = a;
    }

    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(Digit digit, int region) {
        //TODO: copy touch stuff over from setTime, should be similar
    }
    
    /**
     * Initialises the View interface
     */
    
    @Override
    public void show() {
        // display same beginning interface as SetTimeView
        super.show();
        
        //TODO: adjust interface
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        // show time on digit display
        showTime(alarmTime);
        showSeparator(' ');
        
        //TODO: show weekday selector on multiple days, as each alarm can run
        //      on multiple days
    }
    
}

