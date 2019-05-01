package Views;

import Clock.*;

/**
 * @author Sam
 */

public abstract class PageView {
    
    /**
     * Handles touch events directed at this View
     * @param touch the touch event to parse.
     */
    public abstract void touched(bgi.TouchEvent touch);
    // Check Template below when extending

    /**
     * Initialises the View interface
     */
    public abstract void show();
    
    /**
     * Makes minimal necesary changes, does not redisplay entire View
     */
    public abstract void update();

}

    // Template for touch handlers. If using View changing methods such as
    // clock.toClockView(), then add a return; statement in place of break;

    /*
    Digit touched = (Digit) touch.getSource();
    int region = touch.getTouched();
    
    // Check Digit 0
        if(touched == clock.getDigits()[0]) {
            switch(region) {
                case 0: break;
                //... (all necessary cases)
                case 10: break;
            }
    // Check Digit 1
    } else if(touched == clock.getDigits()[1]) {
            switch(region) {
                case 0: break;
                //... (all necessary cases)
                case 10: break;
            }
        }
    // Check Digit 2 (Separator)
    } else if(touched == clock.getDigits()[2]) {
            switch(region) {
                case 0: break;
                //... (all necessary cases)
                case 10: break;
            }
        }
    // Check Digit 3
    } else if(touched == clock.getDigits()[3]) {
            switch(region) {
                case 0: break;
                //... (all necessary cases)
                case 10: break;
            }
        }
    // Check Digit 4
    } else if(touched == clock.getDigits()[4]) {
            switch(region) {
                case 0: break;
                //... (all necessary cases)
                case 10: break;
            }
        }
    }
    update();
    
    */