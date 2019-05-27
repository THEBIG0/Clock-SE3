package Views;

import Clock.Digit;
import Clock.TestClock;
import Clock.Alarm;
import bgi.*;

/**
 * @author Sam
 * 
 * Shows a list of each alarm that is set: shows time and days for each alarm
 * 
 */
public class AlarmListView extends ClockView {
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public AlarmListView(TestClock clock) {
        super(clock);
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(int digit, int region) {
        //TODO: Add hold functionality to edit
        
        if(digit == 0 && region == 0){
            clock.toClockMenu();
            return;
        }
        
        switch(region){
            case 3:
            case 4:
//                System.out.println("Click " + digNum + " | " + region);
                clock.toSetAlarm(clock.getAlarms().get(digit), digit);
                break;
            case 6:
            case 7:
//                System.out.println("Click " + digNum + " | " + region);
                clock.toSetAlarm(clock.getAlarms().get(digit+5), digit +5);
                break;
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
            d.setChar(' ');
        }
        
        // Exit button
        clock.getDigit(0).setText(0,"Exit");
        // Heading        
        clock.getDigit(2).setText(0, "Alarms");

        
        int digitNum = 0;
        int offset = 3;
        for(Alarm a : clock.getAlarms()){
            
//            System.out.println("\n a " + a.toString());
            clock.getDigit(digitNum).setText(offset, a.toString()); 
            digitNum++;
            
            if(digitNum >= 5){
                offset = 6;
                digitNum = 0;
            }
            
        }        
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        
    }

}

