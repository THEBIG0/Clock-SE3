package Clock;

import Views.*;
import java.time.LocalTime;
import javax.swing.Timer;
import java.awt.event.*;


/**
 * @author Owen
 */
public class TestClock extends javax.swing.JFrame { 
    
    public int weekDay;
    Timer timer;
    public int clockSpeed;
    LocalTime currentTime;
    public String meridian = "AM";
    int hour, minutes, seconds;
    
    PageView currentView;
    SetTimeView setTimeView;
    ClockView clockView;
    SettingsView settingsView;
    
    public enum colorScheme{
        HACKER,
        SLATE,
        WHITEBLACK,
        BLACKWHITE,
    }
    public colorScheme colors;
    
    /**
     * Creates new form TestClock
     */
    public TestClock() {
        initComponents();
        initTime();
        setTimeView = new SetTimeView(this);
        clockView = new ClockView(this);
        settingsView = new SettingsView(this);
        
        toClock();
    } 
    
    /**
    * All code to be run on timer interval.
    */
    private void runEverySecond(){
        incrementTime();
        if (currentView == clockView) currentView.show();
        timer.setDelay(1000/clockSpeed); 
    }
    
    /**
     * Display, and pass control to Clock page. 
     */
    public void toClock() {
        currentView = clockView;
        currentView.show();
    }
    /**
     * Display, and pass control to SetTime page. 
     */
    public void toSetTime(){
        currentView = setTimeView;
        currentView.show();
    }
    /**
     * Display, and pass control to Settings page. 
     */
    public void toSettings(){
        currentView = settingsView;
        currentView.show();
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
                
                if(hour > 12) {
                    hour = 1;
                }
                
                if(hour == 12) {
                    if(meridian.compareTo("AM")==0) {
                        meridian = "PM";
                    } else {
                        meridian = "AM";
                        weekDay = (weekDay + 1) % 7;
                    }
                }
            }
        }
    }
    
     /**
     * Adjust the color scheme for all the digits.
     * @param color the color scheme to set all digits to
     */
    public void setColorScheme(colorScheme color){
        for(Digit d: getDigits()) {
            d.setColor(color);
        }
    }
    
    /**
     * @return Digit[] array of all digits, in order from left to right.
     */
    public Digit[] getDigits() {
        Digit[] darr = {
            digit0,
            digit1,
            digit2,
            digit3,
            digit4,
        };
        return darr;
    }
    
    /**
     * Show specified digits (and char) on each digit, in order from left to right.
     * @param d0 first digit to display
     * @param d1 second digit to display
     * @param sep separator character
     * @param d2 third digit to display
     * @param d3 fourth digit to display
     */
    public void showDigits(int d0, int d1, char sep, int d2, int d3) {
        getDigits()[0].setDigit(d0);
        getDigits()[1].setDigit(d1);
        getDigits()[2].setChar(sep);
        getDigits()[3].setDigit(d2);
        getDigits()[4].setDigit(d3);
    }
    
    /**
     * @return current stored value of hours
     */
    public int getHours(){
        return hour;
    }
    /**
     * Sets the stored hours to int parameter.
     * @param hour the value to set
     */
    public void setHours(int hour){
        this.hour = hour;
    }
    /**
     * @return current stored value of minutes
     */
    public int getMinutes(){
        return minutes;
    }
    /**
     * Sets the stored minutes to int parameter.
     * @param minutes the value to set
     */
    public void setMinutes(int minutes){
        this.minutes = minutes;
    }
    /**
     * @return current stored value of seconds
     */
    public int getSeconds(){
        return seconds;
    }
    /**
     * Sets the stored seconds to int parameter.
     * @param seconds the value to set
     */
    public void setSeconds(int seconds){
        this.seconds = seconds;
    }
   
    
    /**
    * Initialise all time variables and start timer.
    */
    private void initTime() {
        clockSpeed = 1;
        weekDay = 0;
        currentTime = LocalTime.now();
        
        hour = (currentTime.getHour()%12 == 0) ? 12 : currentTime.getHour() % 12;
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
        digit2 = new Clock.Digit();
        digit3 = new Clock.Digit();
        digit4 = new Clock.Digit();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        digitCell.setBackground(new java.awt.Color(0, 0, 0));
        digitCell.setMaximumSize(new java.awt.Dimension(770, 32767));
        digitCell.setPreferredSize(new java.awt.Dimension(770, 308));
        digitCell.setSize(new java.awt.Dimension(770, 308));
        digitCell.setLayout(new java.awt.GridLayout(1, 0));

        digit0.addTouchListener(new bgi.TouchListener() {
            public void touchReleased(bgi.TouchEvent evt) {
                digit0TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
            public void touchInitiated(bgi.TouchEvent evt) {
                digit0TouchInitiated(evt);
            }
        });
        digitCell.add(digit0);

        digit1.addTouchListener(new bgi.TouchListener() {
            public void touchReleased(bgi.TouchEvent evt) {
                digit1TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
            public void touchInitiated(bgi.TouchEvent evt) {
                digit1TouchInitiated(evt);
            }
        });
        digitCell.add(digit1);

        digit2.addTouchListener(new bgi.TouchListener() {
            public void touchReleased(bgi.TouchEvent evt) {
                digit2TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
            public void touchInitiated(bgi.TouchEvent evt) {
                digit2TouchInitiated(evt);
            }
        });
        digitCell.add(digit2);

        digit3.addTouchListener(new bgi.TouchListener() {
            public void touchReleased(bgi.TouchEvent evt) {
                digit3TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
            public void touchInitiated(bgi.TouchEvent evt) {
                digit3TouchInitiated(evt);
            }
        });
        digitCell.add(digit3);

        digit4.addTouchListener(new bgi.TouchListener() {
            public void touchReleased(bgi.TouchEvent evt) {
                digit4TouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
            public void touchInitiated(bgi.TouchEvent evt) {
                digit4TouchInitiated(evt);
            }
        });
        digitCell.add(digit4);

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

    // <editor-fold defaultstate="collapsed" desc="Event handlers">
    private void digit0TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit0TouchInitiated
//        currentPage.touched(evt); 
    }//GEN-LAST:event_digit0TouchInitiated
    private void digit0TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit0TouchReleased
        currentView.touched(evt); 
    }//GEN-LAST:event_digit0TouchReleased
    private void digit1TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit1TouchInitiated
//        currentPage.touched(evt); 
    }//GEN-LAST:event_digit1TouchInitiated
    private void digit1TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit1TouchReleased
        currentView.touched(evt); 
    }//GEN-LAST:event_digit1TouchReleased
    private void digit2TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit2TouchInitiated
//        currentPage.touched(evt); 
    }//GEN-LAST:event_digit2TouchInitiated
    private void digit2TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit2TouchReleased
        currentView.touched(evt); 
    }//GEN-LAST:event_digit2TouchReleased
    private void digit3TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit3TouchInitiated
//        currentPage.touched(evt); 
    }//GEN-LAST:event_digit3TouchInitiated
    private void digit3TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit3TouchReleased
        currentView.touched(evt); 
    }//GEN-LAST:event_digit3TouchReleased
    private void digit4TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit4TouchInitiated
//        currentPage.touched(evt); 
    }//GEN-LAST:event_digit4TouchInitiated
    private void digit4TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit4TouchReleased
        currentView.touched(evt); 
    }//GEN-LAST:event_digit4TouchReleased
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
    private Clock.Digit digit0;
    private Clock.Digit digit1;
    private Clock.Digit digit2;
    private Clock.Digit digit3;
    private Clock.Digit digit4;
    private javax.swing.JPanel digitCell;
    // End of variables declaration//GEN-END:variables

}
