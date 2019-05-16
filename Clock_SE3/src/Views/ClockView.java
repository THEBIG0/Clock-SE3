package Views;

import Clock.Digit;
import Clock.TestClock;
import bgi.*;

/**
 * @author Sam
 */
public class ClockView extends PageView {
    TestClock clock;
    Digit touched;
    double heldTime = -1;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public ClockView(TestClock clock) {
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
        if(region == -1) {
            heldTime = 0;
            return;
        }
        
    // Check Digit 0
        if(touched == clock.getDigits()[0]) {
            switch(region) {
                case 0:
                    clock.toSettings();
                    return;
            }
        }
    // Check Digit 2 (Separator)
        else if(heldTime > 2 && touched == clock.getDigits()[2]) {
            clock.toSetTime();
            return;
        }
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

        // Show buttons
        clock.getDigits()[0].setText(0, "Settings" );

        // Update the time and day
        update();
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        
        // increment heldtime if started
        if(heldTime > -1) heldTime++;
        
        // if held for longer than 2 seconds, emulate touch 
        if(heldTime > 2) {
            touched(new TouchEvent(0, 0, touched));
            heldTime = -1;
            return;
        }
        
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

