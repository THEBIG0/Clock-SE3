package Clock;

import Views.*;
import java.time.LocalTime;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.Calendar;
import java.util.ArrayList;


/**
 * @author Owen, Sam, Lachie
 * 
 * This class contains global storage and data manipulation methods for the
 * clock. There are no display methods, they are all contained in ClockView or
 * a subclass of ClockView. The color scheme is here as an exception, as it is
 * changed globally.
 * 
 */
public class TestClock extends javax.swing.JFrame { 
    
    //Basic clock variables
    int weekDay;
    LocalTime time;
    Timer timer;    
    public boolean twelveHour = true;
    // boolean can be safely public
    
    //Alarm and snooze variables
    int timesSnoozed;
    int snoozeTimer;
    int maxSnoozes = 3;
    int snoozeMinutes = 7;
    ArrayList<Alarm> alarms = new ArrayList<>();
    
    // Abstract class ClockView is placeholder variable
    ClockView currentView;
    
    /**
     * Enumeration of the available color schemes.
     */
    public enum colorScheme {
        HACKER,
        SLATE,
        WHITEBLACK,
        BLACKWHITE,
    }
    colorScheme colors = colorScheme.HACKER;
    
    /**
     * Creates new form TestClock
     */
    public TestClock() {
        initComponents();
        initTime();        
        initAlarms();
        toClockStandby();
    } 
    
    /**
    * All code to be run each second
    */
    private void runEverySecond(){
        incrementTime();
        currentView.update();
        
        if(time.getSecond() == 0) pollAlarms();
        
        if(snoozeTimer > 0) snoozeTimer++;
        if(snoozeTimer > snoozeMinutes * 60) {
            snoozeTimer = 0;
            toTriggeredAlarm();
        }
    }
    
    /**
     * Display, and pass control to each respective view 
     */
    public void toClockStandby() {
        currentView = new ClockStandbyView(this);
        currentView.show();
    }
    public void toClockMenu() {
        currentView = new ClockMenuView(this);
        currentView.show();
    }
    public void toHelp() {
        currentView = new HelpView(this);
        currentView.show();
    }
    public void toTriggeredAlarm() {
        currentView = new TriggeredAlarmView(this);
        currentView.show();
    }
    public void toAlarmList() {
        currentView = new AlarmListView(this);
        currentView.show();
    }
    public void toSetAlarm(Alarm a, int alarmNum) {
        currentView = new SetAlarmView(this, a, alarmNum);
        currentView.show();
    }
    public void toSetTime() {
        currentView = new SetTimeView(this);
        currentView.show();
    }
    public void toSettings() {
        currentView = new SettingsView(this);
        currentView.show();
    }
    

   /**
    * Increment seconds and adjust weekDay
    */
    public void incrementTime() {
        LocalTime previous = time;
        time = time.plusSeconds(1);
        if(time.isBefore(previous)) {
            weekDay = ++weekDay % 7;
        } 
    }
    
    /**
    * Check if any of the stored alarms are set to trigger at the current time
    * If so, show the Alarm view
    */
    public void pollAlarms() {
        for(Alarm a: alarms) {
            if(a.check(time, weekDay)) {
                toTriggeredAlarm();
                timesSnoozed = 0;
                return;
            }
        }
    }
    
    public boolean snooze() {
        if(timesSnoozed <= maxSnoozes) {
            snoozeTimer = 1;
            timesSnoozed++;
            return true;
        }
        return false;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters.">    
    
    
     /**
     * Adjust the color scheme for all the digits globally.
     * @param color the color scheme to set all digits to
     */
    public void setColorScheme(colorScheme color){
        colors = color;
        for(Digit d: getDigits()) {
            d.setColor(color);
        }
    }
            
    /**
     * Get the enum corresponding to the current color scheme
     * @return 
     */
    public colorScheme getColorScheme(){
        return colors;
    }
    
    /**
     * @return Digit[] array of all digits (including separator), in order from
     * left to right.
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
     * @return Digit shown in clock, indexed in order from left to right.
     * @param index the digit to get.
     */
    public Digit getDigit(int index) {
        return getDigits()[index];
    }
    
    /**
     * Set the time of the clock
     * @param newTime the LocalTime variable to set the time to.
     */
    public void setTime(LocalTime newTime) {
        time = newTime;
    }
    
    /**
     * @return the LocalTime variable stored as the current time.
     */
    public LocalTime getTime() {
        return time;
    }
    
    /**
     * Set the current weekday, indexed from 0-6 with Sunday = 0
     * @param day the index of the day to be set
     */
    public void setWeekDay(int day) {
        this.weekDay = day;
    }
    
    /**
     * @return the index of the current stored weekday.
     */
    public int getWeekDay() {
        return weekDay;
    }
    
    /**
     * Sets the clock speed
     * @param factor the speed scale factor to be applied
     */
    public void setClockSpeed(int factor) {
        timer.setDelay(1000/factor); 
    }
    
    /**
     * @return the speed at which the clock is currently running. 1 = real time.
     */
    public int getClockSpeed() {
        return 1000/timer.getDelay(); 
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Alarm> getAlarms(){
        return alarms;
    }
    
    /**
     *
     * @param alarmNum
     * @param newAlarm
     */
    public void setAlarm(int alarmNum, Alarm newAlarm){
        alarms.set(alarmNum, newAlarm);
    }
    
    // </editor-fold>
   
    /**
    * initialize all time variables and start timer.
    */
    private void initTime() {
        time = LocalTime.now();
        
        // Not the pretiest thing but it'll do
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        weekDay = day;
        weekDay--; // Day is 1 indexed, we use 0 indexed so decrement one.
        
        timer = new Timer(ONE_SECOND, (ActionEvent evt) -> {
            runEverySecond();
        });
        timer.start();
      
    }

    private void initAlarms(){
        boolean  temp[] = {true, true, true, true, true, true, true};
        for(int i = 0; i < 10; i++){
            //alarms.add(new Alarm(0, LocalTime.parse("06:00")));
            alarms.add(new Alarm(temp, LocalTime.parse("06:00")));
        }
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
        currentView.longPress(); 
    }//GEN-LAST:event_digit0TouchInitiated
    private void digit0TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit0TouchReleased
        currentView.touched(0, evt.getTouched()); 
    }//GEN-LAST:event_digit0TouchReleased
    private void digit1TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit1TouchInitiated
        currentView.longPress(); 
    }//GEN-LAST:event_digit1TouchInitiated
    private void digit1TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit1TouchReleased
        currentView.touched(1, evt.getTouched()); 
    }//GEN-LAST:event_digit1TouchReleased
    private void digit2TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit2TouchInitiated
        currentView.longPress(); 
    }//GEN-LAST:event_digit2TouchInitiated
    private void digit2TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit2TouchReleased
        currentView.touched(2, evt.getTouched()); 
    }//GEN-LAST:event_digit2TouchReleased
    private void digit3TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit3TouchInitiated
        currentView.longPress(); 
    }//GEN-LAST:event_digit3TouchInitiated
    private void digit3TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit3TouchReleased
        currentView.touched(3, evt.getTouched()); 
    }//GEN-LAST:event_digit3TouchReleased
    private void digit4TouchInitiated(bgi.TouchEvent evt) {//GEN-FIRST:event_digit4TouchInitiated
        currentView.longPress(); 
    }//GEN-LAST:event_digit4TouchInitiated
    private void digit4TouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digit4TouchReleased
        currentView.touched(4, evt.getTouched()); 
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
    final int ONE_SECOND = 1000;
    
}
