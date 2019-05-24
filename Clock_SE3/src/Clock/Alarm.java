
package Clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Sam
 */
public class Alarm {
    static final String[] DAYS = {
        "Su",
        "M",    "T", 
        "W",    "Th", 
        "F",    "S",
    };
    
    public LocalTime time;
    public boolean[] days = new boolean[7];
    public boolean active = false;
    
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
        this.time = time;
    }
    
    /**
     * Checks if this alarm should be triggered at the supplied time, and on
     * the supplied day
     * @param now the time to check
     * @param day the day to check
     * @return true if the alarm should be triggered
     */
    Boolean check(LocalTime now, int day) {
        
        //System.out.println("\n\nNow: " + now.truncatedTo(ChronoUnit.MINUTES));
        //System.out.println("Alarm" + time.truncatedTo(ChronoUnit.MINUTES));
        
        return (
            now.truncatedTo(ChronoUnit.MINUTES).equals(time.truncatedTo(ChronoUnit.MINUTES))
            && days[day] && active
        );
    }
    
    @Override
    public String toString(){ 
        // Format in 12 hour
        String timeStr = time.format(DateTimeFormatter.ofPattern("hh:mma"));
        String str = "" + timeStr + "\n";
        
        if(active){
            str = ">" + timeStr + "<\n";
        }
        
        for(int i = 0; i < 7; i++){
            if(days[i]){
                str +=  DAYS[i];
            }
        }
        return str;
    }
    
    public void toggleActive(){
        active = !active;
    }
    
    public void updateAlarm(boolean days[], LocalTime alarmTime){
        this.days = days;
        this.time = alarmTime;

    }
}