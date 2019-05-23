
package Clock;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Sam
 */
public class Alarm {
    
    public LocalTime time;
    public boolean[] days = new boolean[7];
    
    private Alarm(){}
    
    //constructor for alarm with one day
    public Alarm(int day, LocalTime time) {
        this(new boolean[7], time);
        days[day] = true;
        this.time = time;
    }
    
    // constructor with alarm on multiple days
    public Alarm(boolean[] days, LocalTime time) {
        this();
        this.days = days;
    }
    
    /**
     * Checks if this alarm should be triggered at the supplied time, and on
     * the supplied day
     * @param now the time to check
     * @param day the day to check
     * @return true if the alarm should be triggered
     */
    Boolean check(LocalTime now, int day) {
        return (
            now.truncatedTo(ChronoUnit.MINUTES) == time.truncatedTo(ChronoUnit.MINUTES)
            && days[day]
        );
    }
    
}
