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
        this.alarmTime = a.time;
        this.alarm = a;
        this.alarmNum = alarmNum;
        this.days = a.days.clone(); // Otherwise it passes the object and ya get duplicate errors
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
                
                // If "Exit" is pressed
                case 0:
                    clock.toAlarmList();
                    return;
                    
                // If "Save" is pressed
                case 10:
                    alarm.updateAlarm(days, alarmTime);
                    // Pass control to alarm list
                    clock.toAlarmList();
                    return;
            }          
        }
    // Check Digit 1
        else if(digit == clock.getDigits()[1]) {
            switch(region) {
                // If up is pressed
                case 1:
                    alarmTime = alarmTime.plusHours(1);
                    break;
               // If down is pressed
                case 9:
                    alarmTime = alarmTime.minusHours(1);
                    break;
            }
        }
    // Check Digit 2 (Separator)
        else if(digit == clock.getDigits()[2]) {
            switch(region) {
                // If weekday is pressed (catches regions 2-8)
                case 2: // Su
                case 3: // M
                case 4: // T
                case 5: // W
                case 6: // Th
                case 7: // F
                case 8: // S
                    // Set weekDay based on region
                    toggleSelection(region-2);
                    break;
                case 10:
                    alarm.toggleActive();
                }
            }
    // Check Digit 3
        else if(digit == clock.getDigits()[3]) {
            switch(region) {

                // If up is pressed.
                case 1:
                    alarmTime = alarmTime.plusMinutes(10);
                    break;

                // If down is pressed.
                case 9:
                    alarmTime = alarmTime.minusMinutes(10);
                    break;
            }
        }
    // Check Digit 4
        else if(digit == clock.getDigits()[4]) {
            switch(region) {
            
                // If up is pressed.
                case 1:
                    alarmTime = alarmTime.plusMinutes(1);
                    break;
                    
                // If down is pressed.
                case 9:
                    alarmTime = alarmTime.minusMinutes(1);
                    break;
                    
                // If AM/PM is pressed
                case 10:
                    //toggle meridian
                    if(alarmTime.getHour() < 12) alarmTime = alarmTime.plusHours(12);
                    else alarmTime = alarmTime.minusHours(12);
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
        
         for(int i = 0; i < DAYS.length; i++) {
            clock.getDigits()[2].setText(i+2, DAYS[i]);
        }
        String str;
        for(int i = 0; i < DAYS.length; i++){
            str = DAYS[i];
            if(days[i]){
                str = ">"+DAYS[i]+"<";
            }
            clock.getDigits()[2].setText(i+2,str); 
        }

    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        // show time on digit display
        //showTime(alarmTime);
        showSeparator(' ');
        
        // Update time display
        showTime(alarmTime);
        
        // Clear weekday Selector
        for(int i = 0; i < DAYS.length; i++) {
            clock.getDigits()[2].setText(i+2, DAYS[i]);
        }
        // Update weekday selector
        String str;
        for(int i = 0; i < DAYS.length; i++){
            str = DAYS[i];
            if(days[i]){
                str = ">"+DAYS[i]+"<";
            }
            clock.getDigits()[2].setText(i+2,str); 
        }
        String activeText = alarm.active ? "Disable" : "Enable";
        clock.getDigits()[2].setText(10, activeText);
    }
    
}

