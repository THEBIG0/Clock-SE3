
import Clock.TestClockDigit;
import java.util.Timer;

/**
 *
 * @author
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Clock.TestClockDigit test = new Clock.TestClockDigit();
        test.setVisible(true);
        Clock.TestClockDigit.T timer = (new Clock.TestClockDigit()).new T();
        Timer t = new Timer();
        t.schedule(timer, 0,1000);
  
    }
    
}
