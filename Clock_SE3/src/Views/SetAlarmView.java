package Views;

import Clock.*;
import java.time.LocalTime;

/**
 * @author Sam
 */
public class SetAlarmView extends SetTimeView {
    
    Alarm alarm;
    private boolean[] days = new boolean[7];
    LocalTime alarmTime;
    private final int alarmNum;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     * @param a
     * @param alarmNum
     */
    public SetAlarmView(TestClock clock, Alarm a, int alarmNum) {
        super(clock);
        this.alarmTime = a.getTime();
        this.alarm = a;
        this.alarmNum = alarmNum;
        this.days = a.getDays().clone();
        this.alarm.setActive(true);
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(int digit, int region) {   
        switch(digit) {
        // Check Digit 0
            case 0: {
                switch(region) {    
                    case 0:     // If "Exit" is pressed
                        clock.toAlarmList();
                        return;
                    case 10:    // If "Save" is pressed
                        alarm.updateAlarm(days, alarmTime);
                        // Pass control to alarm list
                        clock.toAlarmList();
                        return;
                }          
                break;
            }
        // Check Digit 1
            case 1: {
                switch(region) {
                    case 1:     // If plus is pressed
                        alarmTime = alarmTime.plusHours(1);
                        break;
                    case 9:     // If minus is pressed
                        alarmTime = alarmTime.minusHours(1);
                        break;
                }
                break;
            }
        // Check Digit 2 (Separator)
            case 2: {
                switch(region) {
                    // If weekday is pressed (catches regions 2-8)
                    case 2: // Su
                    case 3: // M
                    case 4: // T
                    case 5: // W
                    case 6: // Th
                    case 7: // F
                    case 8: // S
                        // Set weekDays based on region
                        toggleSelection(region-2);
                        break;
                    case 10:
                        alarm.toggleActive();
                }
                break;
            }
        // Check Digit 3
            case 3: {
                switch(region) {
                    case 1:     // If plus is pressed
                        alarmTime = alarmTime.plusMinutes(10);
                        break;
                    case 9:     // If minus is pressed
                        alarmTime = alarmTime.minusMinutes(10);
                        break;
                }
                break;
            }
        // Check Digit 4
            case 4: {
                switch(region) {
                    case 1:     // If plus is pressed
                        alarmTime = alarmTime.plusMinutes(1);
                        break;
                    case 9:     // If minus is pressed
                        alarmTime = alarmTime.minusMinutes(1);
                        break;
                    case 10:    // If AM/PM is pressed
                        if(alarmTime.getHour() < 12) alarmTime = alarmTime.plusHours(12);
                        else alarmTime = alarmTime.minusHours(12);
                        break;
                }
                break;
            }
        }
        update();
    }
    
    public void toggleSelection(int i){
        days[i] = !days[i];
    }
    
    /**
     * Initialises the View interface
     */
    
    @Override
    public void show() {
        // display same beginning interface as SetTimeView
        super.show();
        
        // show title
        clock.getDigit(2).setText(0, "Set Alarm");
        
         for(int i = 0; i < DAYS.length; i++) {
            clock.getDigit(2).setText(i+2, DAYS[i]);
        }
        String str;
        for(int i = 0; i < DAYS.length; i++){
            str = DAYS[i];
            if(days[i]){
                str = ">"+DAYS[i]+"<";
            }
            clock.getDigit(2).setText(i+2,str); 
        }

    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        showSeparator(' ');
        
        // Update time display
        showTime(alarmTime);
        
        // Clear weekday Selector
        for(int i = 0; i < DAYS.length; i++) {
            clock.getDigit(2).setText(i+2, DAYS[i]);
        }
        // Update weekday selector
        String str;
        for(int i = 0; i < DAYS.length; i++){
            str = DAYS[i];
            if(days[i]){
                str = ">"+DAYS[i]+"<";
            }
            clock.getDigit(2).setText(i+2,str); 
        }
        String activeText = alarm.active ? "Disable" : "Enable";
        clock.getDigit(2).setText(10, activeText);
    }
    
}

