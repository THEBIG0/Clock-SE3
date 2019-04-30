package Clock;

/*
 * Testing environment for checking simple functions of digit class.
 */
import bgi.TouchEvent;
import java.time.LocalTime;
import javax.swing.Timer;
import java.awt.event.*;


/**
 * @author Owen, Sam
 */
public class TestClock extends javax.swing.JFrame {
    
    
    enum clockDisplay {
        SHOW_TIME,
        SET_TIME,
        SET_ALARM,
        SHOW_HELP,  
        SHOW_SETTINGS,
    }
    clockDisplay page;
    
    /*
     * I hate colour without a u but Java uses the inferior 
     * American spelling so probably should stick to that
     */
    enum colorScheme{
        BLACKGREEN,
        WHITEBLACK,
    }
    colorScheme colors;
    
    final String[] days = {
        "Monday",    "Tuesday", 
        "Wednesday", "Thursday", 
        "Friday",    "Saturday", 
        "Sunday"
    };
    int weekDay;
    
    Timer timer;
    int clockSpeed = 1;
    LocalTime currentTime;
    boolean twelveHour = true;
    String meridian = "AM";
    int hour, minutes, seconds;
    int d0,d1,d2,d3;
    Digit[] digits = new Digit[4];
    
    /**
     * Creates new form TestClock
     */
    public TestClock() {
        initComponents();
        initTime();
        digits[0] = digit0;
        digits[1] = digit1;
        digits[2] = digit2;
        digits[3] = digit3;
        to_SHOW_TIME();
    } 
    
    /**
    * All code to be run on timer interval.
    */
    private void runEverySecond(){
        incrementTime();
        timer.setDelay(1000/clockSpeed);
        
        switch(page) {
            case SHOW_TIME: displayTime(); break;
            case SET_TIME: break;
            case SET_ALARM: break;
            case SHOW_HELP: break;
            case SHOW_SETTINGS: break;
        }  
    }
    
    /*
     * Methods below handle the transitions between pages
     * 
     * to_SHOW_TIME: Clears display and shows the current time
     * to_SET_TIME: Shows time at call, also displays time change gui
     * to_SET_ALARM: Not implemented
     * to_SHOW_HELP: Not implemented
     */
    
    private void to_SHOW_TIME() {
        // Set page
        page = clockDisplay.SHOW_TIME;
        // Configure text alignment
        digit0.setTextAlignment(1);
        digit3.setTextAlignment(1);
        // Clear all text
        for(Digit d: digits) d.clearText();
        digSeparator.clearText();
        // Set text with correct values
        digit0.setText(10, days[weekDay]);
        digit3.setText(10, meridian);
        // Add Settings button
        digit0.setText(0, "Settings" );
        // Show the time
        this.displayTime();
    }
    
    private void to_SHOW_SETTINGS(){
        page = clockDisplay.SHOW_SETTINGS;
        // Clear all digits

        for(Digit d: digits) {
            d.clearText();
            d.setChar(' ');
        }
        digSeparator.clearText();
        digSeparator.setChar(' ');
        
        // Add title text
        digSeparator.setText(0, "Settings");
        digit1.setText(2, "Colours:"); // Auto wraps to new line
        
        // Exit button
        digit0.setText(0,"Exit");
        
        // Color Scheme 1 Button
        digit1.setText(3, "  Hacker");
        // Color Scheme 2 Button
        digit1.setText(4, "  Slate");
        
    }
    
    private void to_SET_TIME() {
        page = clockDisplay.SET_TIME;        
        
        // Configure all digits with centre aligned arrows.
        for(Digit d: digits) {
            d.setTextAlignment(1);
            d.clearText();
            d.setText(1, "+");
            d.setText(9, "-");
        }
            
        // Set display for digSeparator
        digSeparator.setChar(' ');
        digSeparator.setTextAlignment(1);
        digSeparator.clearText();
        // Show weekdays
        for(int i = 0; i < days.length; i++) {
            digSeparator.setText(i+2, days[i]);
        }
        // Show clockspeed
        digSeparator.setText(0, clockSpeed + "X Speed");
        // Show weekDay cursor
        digSeparator.setText(weekDay+2, ">"+days[weekDay]+"<");
        // Show Save button and AM/PM button
        digit0.setText(10, "Save");
        digit3.setText(10, meridian);
      
    }
    
    private void to_SET_ALARM() { 
        page = clockDisplay.SET_ALARM;
    }
    
    private void to_SHOW_HELP() {
        page = clockDisplay.SHOW_HELP;
    }
    
     /*
     * Page event handlers that manage events when the clock is showing different pages
     * 
     * SHOW_TIME: Handles touch events when the standard clock is shown
     * SET_TIME: Handles touch events when the time is being set
     * SET_ALARM: Not implemented
     * SHOW_HELP: Not implemented
     * SHOW_SETTINGS: Handles touch inputs when settings page is open
     */
    
    private void SHOW_TIME_touched(bgi.TouchEvent touch) {
        if(touch.getSource() == digSeparator) to_SET_TIME(); 
        
        if((touch.getSource() == digit0) && touch.getTouched() == 0) to_SHOW_SETTINGS();
    }

    
    private void SET_TIME_touched(bgi.TouchEvent touch){
        int region = touch.getTouched();
        Digit touched = (Digit)touch.getSource();
        int current;
        
        if(touched == digSeparator) {
            // If Speed is pressed
            if (region == 0) {
                // Toggle 1X/10X Speed
                if(touched.getText()[0].compareTo("1X Speed") == 0){
                    clockSpeed = 10;
                } else {
                    clockSpeed = 1;
                }
                // Set clock speed.
                touched.setText(0, clockSpeed + "X Speed");
            }
            // If weekday is pressed
            else if (region >= 2 && region < 9) {
                // clear selector
                for(int i = 0; i < days.length; i++) {
                    touched.setText(i+2, days[i]);
                }
                // set selector to touched weekDay
                weekDay = region - 2;
                touched.setText(region, ">"+days[weekDay]+"<");
            }
            return;
        }
        
        if(touched == digit0) {
            // Get value of current digit
            current = touched.getDigit();
            // If up or down is pressed (in this case they are equal)
            if(region == 1 || region == 9) {
                // Avoid showing value above 12.
                if(digit1.getDigit() <= 2) {
                    touched.setDigit((current + 1) % 2);
                }
            }
            // If "Save" is pressed
            else if(region == 10) {
                // Send current digits to time.
                setTime();
                // Pass control to SHOW_TIME.
                to_SHOW_TIME();
            }
            return;
        }
        
        if(touched == digit1) {
            // Get value of current digit
            current = touched.getDigit();
            // If up is pressed.
            if(region == 1) {
                if(digit0.getDigit() == 0) {
                    touched.setDigit((current + 1) % 10);
                } else {
                    //avoid showing value above 12
                    touched.setDigit((current + 1) % 3);
                }
            // If down is pressed.
            } else if (region == 9) {
                if(digit0.getDigit() == 0) {
                    // Decrements avoiding negative, looping back to 9
                    touched.setDigit((current + 9) % 10);
                } else {
                    // Decrements avoiding negative, looping back to 2
                    touched.setDigit((current + 2) % 3);
                }
            }
            return;
        }
        
        if(touched == digit2) {
            // Get value of current digit
            current = touched.getDigit();
            // If up is pressed.
            if(region == 1) {
                touched.setDigit((current + 1) % 6);
            // If down is pressed.
            } else if (region == 9) {
                touched.setDigit((current + 5) % 6);
            }
            return;
        }
        
        if(touched == digit3) {
            // Get value of current digit
            current = touched.getDigit();
            // If up is pressed.
            if(region == 1) {
                // Increment if less than 6
                if(digit2.getDigit() < 6) {
                    touched.setDigit((current + 1) % 10);
                }
            // If down is pressed.
            } else if (region == 9) {
                // Decrement if less than 6, avoiding negatives
                if(digit2.getDigit() < 6) {
                    touched.setDigit((current + 9) % 10);
                }
            // If AM/PM is pressed
            } else if (region == 10) {
                // Toggle AM/PM
                if(touched.getText()[10].compareTo("AM") == 0){
                    touched.setText(10, "PM");
                } else {
                    touched.setText(10, "AM");
                }  
            }
            return;
        }
    }

    private void SET_ALARM_touched(bgi.TouchEvent touch){
    }
    
    private void SHOW_HELP_touched(bgi.TouchEvent touch){  
    }
    
    private void SETTINGS_touched(bgi.TouchEvent touch){
        int region = touch.getTouched();
        Digit touched = (Digit)touch.getSource();
        
        /**
         * TODO: Add exit button, Add settings toggle thing
         */
        
        if(touched == digit0){
            //Exit button
            if(region == 0){
                to_SHOW_TIME();
            }
        }
        
        if(touched == digit1){
            switch(region){
                case 3: setColorScheme(colors.BLACKGREEN); break;
                case 4: setColorScheme(colors.WHITEBLACK); break;
            }   
        }
    }

    /**
     * Adjust the color scheme for all the digits
     */
    private void setColorScheme(colorScheme color){
        for(Digit d: digits) {
            d.setColor(color.ordinal());
        }
        digSeparator.setColor(color.ordinal());
        
        
    }
    
    
    /**
    * Increment seconds and adjust minute/hour/day for overflow
    */
    public void incrementTime() {
        
        seconds++;
        
        if(seconds == 60) {
            minutes++;
            seconds = 0;
        
            if(minutes == 60) {
                hour++;
                minutes = 0;
                meridian = (hour > 11) ? "PM": "AM";
                
                if(hour == 24) {
                    weekDay = (weekDay > 6) ? 0 : weekDay++;
                    hour = 0;
                }
            }
        }
    }
    
   /**
    * Update display with digits from time values
    */
    public void displayTime() {
        
        int hourTemp = (twelveHour) ? hour %= 12 : hour;
        
        // get digits from time
        d0 = hourTemp > 10 ? hourTemp/10: 0;
        d1 = hourTemp % 10;
        d2 = minutes > 10 ? minutes/10: 0;
        d3 = minutes % 10;  
        
        // flash separator
        char sep = (seconds%2 ==0) ? ':' : ' ';
        
        // shows time on digit display
        digit0.setDigit(d0);
            digit0.setText(10, days[weekDay]);
        digit1.setDigit(d1);
        digSeparator.setChar(sep);
        digit2.setDigit(d2);
        digit3.setDigit(d3);
        if(twelveHour) digit3.setText(10, meridian);
    }
    
    /**
     * Updates Time based on digits currently displayed
     */
    
    public void setTime() {
        int setd0 = digit0.getDigit();
        int setd1 = digit1.getDigit();
        int setd2 = digit2.getDigit();
        int setd3 = digit3.getDigit();
        
        meridian = digit3.getText()[10];
        
        String concat = Integer.toString(setd0) + Integer.toString(setd1);
        hour = Integer.parseInt(concat);
        
        String concat2 = Integer.toString(setd2) + Integer.toString(setd3);
        minutes = Integer.parseInt(concat2);
    }
    
    /**
    * Initialise all time variables and start timer.
    */
    private void initTime() {
        currentTime = LocalTime.now();
        weekDay = 0;
        hour = currentTime.getHour();
        minutes = currentTime.getMinute();
        seconds = currentTime.getSecond(); 
        
        timer = new Timer(1000/clockSpeed, new ActionListener(){
            //this code will run every second, sped up by a factor of clockSpeed
            @Override
            public void actionPerformed(ActionEvent evt) {runEverySecond();}
        });
        timer.start();
    }

    
    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        digitCell = new javax.swing.JPanel();
        digit0 = new Clock.Digit();
        digit1 = new Clock.Digit();
        digSeparator = new Clock.Digit();
        digit2 = new Clock.Digit();
        digit3 = new Clock.Digit();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        digitCell.setBackground(new java.awt.Color(0, 0, 0));
        digitCell.setMaximumSize(new java.awt.Dimension(770, 32767));
        digitCell.setPreferredSize(new java.awt.Dimension(770, 308));
        digitCell.setSize(new java.awt.Dimension(770, 308));
        digitCell.setLayout(new java.awt.GridLayout(1, 0));

        digit0.addTouchListener(new bgi.TouchListener() {
            public void touchInitiated(bgi.TouchEvent evt) {
            }
            public void touchReleased(bgi.TouchEvent evt) {
                digit0TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
        });
        digitCell.add(digit0);

        digit1.addTouchListener(new bgi.TouchListener() {
            public void touchInitiated(bgi.TouchEvent evt) {
            }
            public void touchReleased(bgi.TouchEvent evt) {
                digit1TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
        });
        digitCell.add(digit1);

        digSeparator.addTouchListener(new bgi.TouchListener() {
            public void touchInitiated(bgi.TouchEvent evt) {
            }
            public void touchReleased(bgi.TouchEvent evt) {
                digSeparatorTouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
        });
        digitCell.add(digSeparator);

        digit2.addTouchListener(new bgi.TouchListener() {
            public void touchInitiated(bgi.TouchEvent evt) {
            }
            public void touchReleased(bgi.TouchEvent evt) {
                digit2TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
        });
        digitCell.add(digit2);

        digit3.addTouchListener(new bgi.TouchListener() {
            public void touchInitiated(bgi.TouchEvent evt) {
            }
            public void touchReleased(bgi.TouchEvent evt) {
                digit3TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
        });
        digitCell.add(digit3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(digitCell, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(digitCell, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

// <editor-fold defaultstate="collapsed" desc="Event handlers for each digit. Passes straight to Page event handlers, so folded for readability."> 
    private void digit1TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit1TouchReleased
        switch(page) {
            case SHOW_TIME: SHOW_TIME_touched(evt); break;
            case SET_TIME: SET_TIME_touched(evt); break;
            case SET_ALARM: SET_ALARM_touched(evt); break;
            case SHOW_HELP: SHOW_HELP_touched(evt); break;
            case SHOW_SETTINGS: SETTINGS_touched(evt); break;
        } 
    }//GEN-LAST:event_digit1TouchReleased

    private void digSeparatorTouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digSeparatorTouchReleased
        switch(page) {
            case SHOW_TIME: SHOW_TIME_touched(evt); break;
            case SET_TIME: SET_TIME_touched(evt); break;
            case SET_ALARM: SET_ALARM_touched(evt); break;
            case SHOW_HELP: SHOW_HELP_touched(evt); break;
            case SHOW_SETTINGS: SETTINGS_touched(evt); break;
        }
    }//GEN-LAST:event_digSeparatorTouchReleased

    private void digit2TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit2TouchReleased
        switch(page) {
            case SHOW_TIME: SHOW_TIME_touched(evt); break;
            case SET_TIME: SET_TIME_touched(evt); break;
            case SET_ALARM: SET_ALARM_touched(evt); break;
            case SHOW_HELP: SHOW_HELP_touched(evt); break;
            case SHOW_SETTINGS: SETTINGS_touched(evt); break;
        }
    }//GEN-LAST:event_digit2TouchReleased

    private void digit3TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit3TouchReleased
        switch(page) {
            case SHOW_TIME: SHOW_TIME_touched(evt); break;
            case SET_TIME: SET_TIME_touched(evt); break;
            case SET_ALARM: SET_ALARM_touched(evt); break;
            case SHOW_HELP: SHOW_HELP_touched(evt); break;
            case SHOW_SETTINGS: SETTINGS_touched(evt); break;
        }
    }//GEN-LAST:event_digit3TouchReleased

    private void digit0TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit0TouchReleased
         switch(page) {
            case SHOW_TIME: SHOW_TIME_touched(evt); break;
            case SET_TIME: SET_TIME_touched(evt); break;
            case SET_ALARM: SET_ALARM_touched(evt); break;
            case SHOW_HELP: SHOW_HELP_touched(evt); break;
            case SHOW_SETTINGS: SETTINGS_touched(evt); break;
         }
    }//GEN-LAST:event_digit0TouchReleased
    // </editor-fold> 
       
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestClock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestClock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestClock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestClock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestClock().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Clock.Digit digSeparator;
    private Clock.Digit digit0;
    private Clock.Digit digit1;
    private Clock.Digit digit2;
    private Clock.Digit digit3;
    private javax.swing.JPanel digitCell;
    // End of variables declaration//GEN-END:variables

}
