package Views;

import Clock.TestClock;
import Clock.Digit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
public class SetTimeView extends PageView {
    
    TestClock clock;
    
    int weekDay;
    String meridian;
    int hour, minutes;
    int clockSpeed;
    
    final String[] days = {
        "Monday",    "Tuesday", 
        "Wednesday", "Thursday", 
        "Friday",    "Saturday", 
        "Sunday"
    };
    
    
    public SetTimeView(TestClock clock) {
        this.clock = clock;
    }
    
    /**
     * Handles the touch event that occurs when this page is in control.
     * Increments local time variables based on which digit is the source, 
     * and in which region the touch occured.
     * @param touch the touch event recieved
     */
    
    @Override
    public void touched(bgi.TouchEvent touch) { 
        int region = touch.getTouched();
        Digit touched = (Digit)touch.getSource();
        
    // Check Digit 0
        if(touched == clock.getDigits()[0]) {
            switch(region) {
                
                // If up is pressed.
                case 1: 
                    if(hour <= 2) {
                        hour += 10;
                    }
                    break;
                    
                // If down is pressed.
                case 9:
                    if(hour >= 10){
                        hour -= 10;
                    }
                    break;
                    
                // If "Save" is pressed
                case 10:
                    // Send current digits to time.
                    clock.setHours(hour);
                    clock.setMinutes(minutes);
                    clock.meridian = meridian;
                    clock.weekDay = weekDay;
                    clock.clockSpeed = this.clockSpeed;

                    // Pass control to SHOW_TIME.
                    clock.toClock();
                    return;
            }          
        }
    // Check Digit 1
        else if(touched == clock.getDigits()[1]) {
            switch(region) {    
            
                // If up is pressed
                case 1:
                    // Avoid showing value above 12.
                    if(hour == 12) {
                        hour = 1;
                    } else hour++;
                    break;
                
               // If down is pressed
                case 9:
                    if(hour == 1){
                        hour = 12;
                    } else hour--;
                    break;
            }
        }
    // Check Digit 2 (Separator)
        else if(touched == clock.getDigits()[2]) {
            switch(region) { 
                // If Speed is pressed
                case 0:
                    // Toggle Speed
                    if(clockSpeed == 100){
                        clockSpeed = 1;
                    } else {
                        clockSpeed *= 10;
                    }
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
        else if(touched == clock.getDigits()[3]) {
            switch(region) {

                // If up is pressed.
                case 1:
                    minutes = (minutes + 10) % 60 ;
                    break;

                // If down is pressed.
                case 9:
                    minutes = (minutes + 50) % 60 ;
                    break;
            }
        }
    // Check Digit 4
        else if(touched == clock.getDigits()[4]) {
            switch(region) {
            
                // If up is pressed.
                case 1:
                    if(minutes >= 59) {
                        minutes = 0;
                    } else {
                        minutes++;
                    }
                    break;
                    
                // If down is pressed.
                case 9:
                    if(minutes <= 0) {
                        minutes = 59;
                    } else {
                        minutes--;
                    }
                    break;
                    
                // If AM/PM is pressed
                case 10:
                    //toggle meridian
                    meridian = (meridian.compareTo("AM")==0) ? "PM" : "AM";
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
        
        this.clock = clock;
        this.hour = (clock.getHours() == 0) ? 12 : clock.getHours();
        this.minutes = clock.getMinutes();
        this.meridian = clock.meridian;
        this.weekDay = clock.weekDay;
        this.clockSpeed = clock.clockSpeed;
        
        // Configure all digits with centre aligned arrows.
        for(Digit d: clock.getDigits()) {
            d.setTextAlignment(1);
            d.clearText();
            d.setText(1, "+");
            d.setText(9, "-");
        }
        
        // Clear arrows on separator
        clock.getDigits()[2].clearText();

        // Show weekdays
        for(int i = 0; i < days.length; i++) {
            clock.getDigits()[2].setText(i+2, days[i]);
        }

        // Show Save button
        clock.getDigits()[0].setText(10, "Save");
        
        // Update display with stored variables
        update();
    }
    
    /**
     * Only updates minimal changes, does not redisplay entire page
     */
    @Override
    public void update() {
        
        // Get digits from time
        int d0 = hour >= 10 ? hour/10: 0;
        int d1 = hour % 10;
        int d2 = minutes > 10 ? minutes/10: 0;
        int d3 = minutes % 10;
        
        // Update digits
        clock.showDigits(d0, d1, ' ', d2, d3);
        
        // Clear weekday Selector
        for(int i = 0; i < days.length; i++) {
            clock.getDigits()[2].setText(i+2, days[i]);
        }
        // Update weekday selector
        clock.getDigits()[2].setText(weekDay+2, ">"+days[weekDay]+"<");
        
        // Update speed and meridian
        clock.getDigits()[2].setText(0, clockSpeed + "X Speed");
        clock.getDigits()[4].setText(10, meridian);
        
    }
}