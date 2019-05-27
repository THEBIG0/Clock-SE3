package Views;

import Clock.TestClock;
import Clock.Digit;
import java.time.LocalTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
public class SetTimeView extends ClockView {

    int weekDay;
    int hour, minutes;
    LocalTime newTime;
    int clockSpeed;
    String meridian;
    
    
    public SetTimeView(TestClock clock) {
        super(clock);
        newTime = clock.getTime();
        weekDay = clock.getWeekDay();
        clockSpeed = clock.getClockSpeed();
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
                        clock.toClockStandby();
                        return;
                    case 10:    // If "Save" is pressed
                        // Send current digits to time.
                        clock.setTime(newTime);
                        clock.setWeekDay(weekDay);
                        clock.setClockSpeed(clockSpeed);

                        // Pass control to clock view
                        clock.toClockStandby();
                        return;
                }          
            }
        // Check Digit 1
            case 1: {
                switch(region) {    
                    case 1:     // If minus is pressed
                        newTime = newTime.plusHours(1);
                        break;
                    case 9:     // If minus is pressed
                        newTime = newTime.minusHours(1);
                        break;
                }
                break;
            }
        // Check Digit 2 (Separator)
            case 2: {
                switch(region) { 
                    case 2: // If weekday is pressed (catches regions 2-8)
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        // Set weekDay based on region
                        weekDay = region - 2;
                        break;
                }
                break;
            }
        // Check Digit 3
            case 3: {
                switch(region) {
                    case 1:     // If minus is pressed
                        newTime = newTime.plusMinutes(10);
                        break;
                    case 9:     // If minus is pressed
                        newTime = newTime.minusMinutes(10);
                        break;
                }
                break;
            }
        // Check Digit 4
            case 4: {
                switch(region) {
                    case 1:     // If minus is pressed
                        newTime = newTime.plusMinutes(1);
                        break;
                    case 9:     // If minus is pressed
                        newTime = newTime.minusMinutes(1);
                        break;
                    case 10:    // If AM/PM is pressed
                        //toggle meridian
                        if(newTime.getHour() < 12) newTime = newTime.plusHours(12);
                        else newTime = newTime.minusHours(12);
                        break;
                }
                break;
            }
        }
        update();
    }
    
    
    /**
     * Initialises the SetTime page interface
     */
    @Override
    public void show() {
       
        
        // Configure all digits with centre aligned arrows.
        for(Digit d: clock.getDigits()) {
            d.setTextAlignment(1);
            d.clearText();
            d.setText(1, "+");
            d.setText(9, "-");
        }
        
        // Clear arrows on separator
        clock.getDigits()[2].clearText();
        clock.getDigits()[2].setChar(' ');
        
        // Show title
        clock.getDigit(2).setText(0, "Set Time");
        

        // Show weekdays
        for(int i = 0; i < DAYS.length; i++) {
            clock.getDigit(2).setText(i+2, DAYS[i]);
        }

        // Show Save and Back button
        clock.getDigit(0).clearText();
        clock.getDigit(0).setText(0, "Exit");
        clock.getDigit(0).setText(10, "Save");
       
        // Update display with stored variables
        update();
    }
    
    /**
     * Only updates minimal changes, does not redisplay entire page
     */
    @Override
    public void update() {
        
        // Update time display
        showTime(newTime);
        
        // Clear weekday Selector
        for(int i = 0; i < DAYS.length; i++) {
            clock.getDigit(2).setText(i+2, DAYS[i]);
        }
        // Update weekday selector
        clock.getDigit(2).setText(weekDay+2, ">"+DAYS[weekDay]+"<");

        
    }
}
