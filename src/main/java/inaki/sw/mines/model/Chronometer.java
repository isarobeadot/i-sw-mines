package inaki.sw.mines.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author inaki
 */
public class Chronometer implements Runnable {

    private static final Logger LOGGER = getLogger(Chronometer.class.getName());
    public static final String C_UPDATE_CHRONO = "UPDATE_CHRONO";
    private boolean instanceRunning;
    private final int step = 100;
    private Integer minutes, seconds, miliseconds;
    private ActionListener actionlistener;
    private int eventNo = 0;

    public Integer getMinutes() {
        return minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    @Override
    public void run() {
        if (!instanceRunning) {
            instanceRunning = true;
            minutes = 0;
            seconds = 0;
            miliseconds = 0;
            LOGGER.info("Chronometer started");
            actionlistener.actionPerformed(new ActionEvent(this, eventNo, C_UPDATE_CHRONO));
            eventNo++;
            while (instanceRunning) {
                try {
                    // Wait for an step
                    Thread.sleep(step);
                    miliseconds += step;

                    // One more second
                    if (miliseconds == 1000) {
                        miliseconds = 0;
                        seconds += 1;
                        // One more minute
                        if (seconds == 60) {
                            seconds = 0;
                            minutes++;
                        }
                        actionlistener.actionPerformed(new ActionEvent(this, eventNo, C_UPDATE_CHRONO));
                        eventNo++;
                    }
                }
                catch (InterruptedException ex) {
                    LOGGER.log(Level.SEVERE, "Chronometer interrupted: {0}", ex);
                }
            }
            LOGGER.info("Chronometer stopped");
        } else {
            LOGGER.info("There is an instance running");
        }
    }

    public void setActionlistener(ActionListener actionlistener) {
        this.actionlistener = actionlistener;
    }

    public void stopChronometer() {
        instanceRunning = false;
    }
}
