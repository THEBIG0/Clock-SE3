package Views;

import Clock.Digit;
import Clock.TestClock;
import java.io.*;
import sun.audio.*;
/**
 * @author Sam, Owen
 * 
 * The actual alarm shown when an alarm is going off.
 */
public class TriggeredAlarmView extends ClockStandbyView{
    
    boolean flash = true;
    
    TestClock.colorScheme oldColorScheme = null;
    AudioStream audioStream;
    Thread soundLoadingThread;
    
    int savedClockSpeed;
    
    int repeatSound = 0;
    
    int heldDigit;
    int heldTime = -1;
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public TriggeredAlarmView(TestClock clock) {
        super(clock);
        initSound();
        playSound();
        oldColorScheme = clock.getColorScheme();
        savedClockSpeed = clock.getClockSpeed();
        clock.setClockSpeed(1);
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(int digit, int region) {
        // Snooze alarm if screen is touched.
        // Only completes if alarm can be snoozed
        if(clock.snooze()) {
            stopSound();
            clock.setColorScheme(oldColorScheme);
            clock.setClockSpeed(savedClockSpeed);
            clock.toClockStandby();
        } else {
            clock.getDigit(2).setText(0, "Snooze");
            clock.getDigit(2).setText(1, "Disabled");
            heldTime = -1;
        }
    }
    
    /**
     * start long press
     */
    @Override
    public void longPress() {
        heldTime = 0;
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
        
        //update using clockstandbyview
        super.update();

        // alarm sound needs to be manually repeated
        if(repeatSound > 14) {
            repeatSound = 0;
            stopSound();
            initSound();
            playSound();
        } else repeatSound++;
        
        // toggle time and ALARM, also flash colours
        if(flash) {
            showSeparator(':');
            showTime(clock.getTime());
            clock.setColorScheme(TestClock.colorScheme.WHITEBLACK);
        }
        else {
            showAlarm();
            clock.setColorScheme(TestClock.colorScheme.BLACKWHITE);
        }   
        flash = !flash;
        
        
        // hold for alarm cancel
        if (heldTime != -1) {
            heldTime++;
            // Cancel if held for 3 seconds
            if(heldTime > 2){
                stopSound();
                clock.setClockSpeed(savedClockSpeed);
                clock.setColorScheme(oldColorScheme); //resets color after alarm is cancelled
                clock.toClockStandby();
                clock.setColorScheme(TestClock.colorScheme.HACKER); //resets color after alarm is cancelled
            }
        }


    }
    
    /**
     * Display ALARM on digits
     */
    private void showAlarm() {
        clock.getDigits()[0].setChar('A');
        clock.getDigits()[1].setChar('L');
        clock.getDigits()[2].setChar('A');
        clock.getDigits()[3].setChar('R');
        clock.getDigits()[4].setChar('M');
    }
    
    
    /**
     * Initialise audio stream
     */
    public void initSound() {
        InputStream inputStream = null;
        try {
            // the sound file must be in the same directory as this class file.
            inputStream = clock.getClass().getResourceAsStream("Loud-Alarm-Clock.wav");
            audioStream = new AudioStream(inputStream);
        }
        catch (Exception e) {
            if(inputStream == null) {
                System.out.println("getResource failed to get audio file");
            }
        }
    }
    
    /**
     * Play audio stream
     */
    public void playSound() {
        try {
            AudioPlayer.player.start(audioStream);
        }
        catch (Exception e) {
            System.out.println("Start audio failed");
        }   
    }

    /**
     * Stop audio stream
     */
    public void stopSound() {
        try {
            AudioPlayer.player.stop(audioStream);
        }
        catch (Exception e) {
            System.out.println("Stop audio failed");
        }    
    }

}
    
