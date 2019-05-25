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
public class TriggeredAlarmView extends ClockView {
    
    /**
     * Creates new View
     * @param clock the parent clock model
     */
    public TriggeredAlarmView(TestClock clock) {
        super(clock);
    }
    
    /**
     * Handles touch events directed at this View
     * @param digit the digit that was touched
     * @param region the row of the digit that was touched
     */
    @Override
    public void touched(Digit digit, int region) {
        //Stop alarm if screen is touched.
        //      Hold for snooze can be added after.
        AudioPlayer.player.stop(audioStream);
    }
    
    /**
     * Initialises the View interface
     */
    @Override
    public void show() {
        
        // Clear all text and set alignment
        for(Digit d: clock.getDigits()) {
            d.clearText();
            d.setTextAlignment(0);
            d.setChar(' ');
        }
        clock.getDigits()[0].setChar('a');
        clock.getDigits()[1].setChar('l');
        clock.getDigits()[2].setChar('a');
        clock.getDigits()[3].setChar('r');
        clock.getDigits()[4].setChar('m');
        //clock.setClockSpeed(0);
        //plays Sound
        playSound();
        //stops Sound after period of time
        stopSound();
        // TODO: Show day of week during alarm
        
    }

    /**
     * Makes minimal neccessary changes, does not redisplay entire View.
     * Generally set to run every second.
     */
    @Override()
    public void update() {
       // TODO: toggle between showing the time and ALARM on display
       //       - could use the fact that update() is called once per second
       //       - maybe add a function for playing sound
       //playSound();
       //       - stop after certain number of seconds
       //stopSound();
    }
    
    /**
     * Stops sound from being played after a period of time.
     * 
     */
    public void stopSound()
    {
        Thread soundLoadingThread = new Thread(new Runnable() {
    public void run() {
        
            try {
                //5000 milliseconds = 5 seconds
                Thread.sleep(5000);
                //stop sound
                AudioPlayer.player.stop(audioStream);
            } catch (InterruptedException e) {
                //should never get to this line
                System.out.println("failed to stop sound");
            }
      
    }
});
soundLoadingThread.start();
       
    }
    
    /**
     * Plays sound file from Views package
     */
    
    private void playSound() 
{
  try
  {
    
    // the sound file must be in the same directory as this class file.
    inputStream = getClass().getResourceAsStream("Loud-Alarm-Clock.wav");
    audioStream = new AudioStream(inputStream);
    //play sound
    AudioPlayer.player.start(audioStream);
    
  }
  catch (Exception e)
  {
      if(inputStream == null)
      {
          System.out.println("getResource has failed to get audio file");
      }
    
  }
}
    AudioStream audioStream;
    InputStream inputStream;
    
}

