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
        this.clock = clock;
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
                    clock.toClockStandby();
                    return;
                    
                // If "Save" is pressed
                case 10:
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
        else if(digit == clock.getDigits()[1]) {
            switch(region) {    
            
                // If up is pressed
                case 1:
                    newTime = newTime.plusHours(1);
                    break;
                
               // If down is pressed
                case 9:
                    newTime = newTime.minusHours(1);
                    break;
            }
        }
    // Check Digit 2 (Separator)
        else if(digit == clock.getDigits()[2]) {
            switch(region) { 
                // If Speed is pressed
                case 0:
                    // Toggle Speed
                    if(clockSpeed >= 1000) clockSpeed = 1;
                    else clockSpeed *= 10;
                    break;

                // If weekday is pressed (catches regions 2-8)
                case 2:
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
            }
    // Check Digit 3
        else if(digit == clock.getDigits()[3]) {
            switch(region) {

                // If up is pressed.
                case 1:
                    newTime = newTime.plusMinutes(10);
                    break;

                // If down is pressed.
                case 9:
                    newTime = newTime.minusMinutes(10);
                    break;
            }
        }
    // Check Digit 4
        else if(digit == clock.getDigits()[4]) {
            switch(region) {
            
                // If up is pressed.
                case 1:
                    newTime = newTime.plusMinutes(1);
                    break;
                    
                // If down is pressed.
                case 9:
                    newTime = newTime.minusMinutes(1);
                    break;
                    
                // If AM/PM is pressed
                case 10:
                    //toggle meridian
                    if(newTime.getHour() < 12) newTime = newTime.plusHours(12);
                    else newTime = newTime.minusHours(12);
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
        
        newTime = clock.getTime();
        weekDay = clock.getWeekDay();
        clockSpeed = clock.getClockSpeed();
        
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

        // Show weekdays
        for(int i = 0; i < DAYS.length; i++) {
            clock.getDigits()[2].setText(i+2, DAYS[i]);
        }

        // Show Save and Back button
        clock.getDigits()[0].clearText();
        clock.getDigits()[0].setText(0, "Exit");
        clock.getDigits()[0].setText(10, "Save");
        
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
            clock.getDigits()[2].setText(i+2, DAYS[i]);
        }
        // Update weekday selector
        clock.getDigits()[2].setText(weekDay+2, ">"+DAYS[weekDay]+"<");
        
        // Update speed display
        clock.getDigits()[2].setText(0, clockSpeed + "X Speed");
        
    }
}