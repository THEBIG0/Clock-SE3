
package Views;
import Clock.*;
import java.time.LocalTime;

/**
 * @author Sam
 * 
 * This abstract class handles the display of each view. It contains display
 * methods that can be used by each subclass, but the touched(), show(), and
 * update() methods must be overridden when extended.
 * 
 * Current subclass structure:
 * ClockView
 * |   SettingsView
 * |   AlarmListView
 * |   ClockStandbyView
 * |   |   ClockMenuView
 * |   |   TriggeredAlarmView
 * |   SetTimeView
 * |   |   SetAlarmsView
 * 
 */

public abstract class ClockView {
    
    TestClock clock;
    
    public ClockView(TestClock clock) {
        this.clock = clock;
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the integer index of the digit that was touched
     * @param region the row of the digit that was touched
     */
    public abstract void touched(int digit, int region);
    
    
    /**
     * Handles long press events directed at this View. Currently does not
     * distinguish between digits, is only for use with TriggeredAlarmView
     * but can be expanded if necessary.
     */
    public void longPress() {}
    
    /**
     * Initialises the View interface
     */
    public abstract void show();
    
    /**
     * Makes minimal necesary changes, does not redisplay entire View
     */
    public abstract void update();

    /**
     * Show specified digits (and char) on each digit, in order from left to right.
     * @param d0 first digit to display
     * @param d1 second digit to display
     * @param d2 third digit to display
     * @param d3 fourth digit to display
     */
    public void showDigits(int d0, int d1, int d2, int d3) {
        clock.getDigits()[0].setDigit(d0);
        clock.getDigits()[1].setDigit(d1);
        clock.getDigits()[3].setDigit(d2);
        clock.getDigits()[4].setDigit(d3);
    }
    
    public void showSeparator(char sep) {
        clock.getDigits()[2].setChar(sep);
    }
    
    /**
     * Update display with time. Adjusts for AM/PM, 24-12 hour
     * @param time, the time to show
     * @param twelveHour
     */
    public void showTime(LocalTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();
        String meridian = "";
        
        if(clock.twelveHour) {
            meridian = hour < 12 ? "AM" : "PM";
            if(hour == 0) hour = 12;
            hour = hour > 12 ? hour-12: hour;
        }
        
        int d0 = hour >= 10 ? hour/10: 0;
        int d1 = hour % 10;
        int d2 = minute >= 10 ? minute/10 : 0;
        int d3 = minute % 10;

        clock.getDigits()[4].setText(10, meridian);
        showDigits(d0, d1, d2, d3);
    }
    
    
    static final String[] DAYS = {
    "Sunday",
    "Monday",    "Tuesday", 
    "Wednesday", "Thursday", 
    "Friday",    "Saturday",
    };

}