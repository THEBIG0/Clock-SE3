package Views;

import Clock.Digit;
import Clock.TestClock;
import bgi.*;

/**
 * @author Sam
 */
public class ClockStandbyView extends PageView {
    TestClock clock;
    Digit touched;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public ClockStandbyView(TestClock clock) {
        this.clock = clock;
    }
    
    /**
     * Handles touch events directed at this View
     * @param touch the touch event to parse.
     */
    @Override
    public void touched(TouchEvent touch) {
        touched = (Digit) touch.getSource();
        int region = touch.getTouched();
        if(!(region == -1)) {
            clock.toClock();
            return;
        }

//        clock.toClock();
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
        
        // Update the time and day
        update();
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        
        
        // get stored time values
        int hour = clock.getHours();
        int minute = clock.getMinutes();
        if (hour > 12) hour -= 12;
        
        // get digits from time
        int d0 = hour >= 10 ? hour/10: 0;
        int d1 = hour % 10;
        int d2 = minute >= 10 ? minute/10 : 0;
        int d3 = minute % 10;
        char sep = (clock.getSeconds()%2==0) ? ':' : ' ';

        // show time on digit display
        clock.showDigits(d0, d1, sep, d2, d3);
        
        // show weekday and meridian on correct digit
        clock.getDigits()[0].setText(10, days[clock.weekDay]);
        clock.getDigits()[4].setText(10, clock.meridian);      
    }
    
    
    final String[] days = {
    "Sunday",
    "Monday",    "Tuesday", 
    "Wednesday", "Thursday", 
    "Friday",    "Saturday",
    };
}

